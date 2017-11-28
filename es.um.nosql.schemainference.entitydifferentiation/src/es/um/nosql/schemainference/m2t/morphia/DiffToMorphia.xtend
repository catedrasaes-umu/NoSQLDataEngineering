package es.um.nosql.schemainference.m2t.morphia

import java.io.File
import es.um.nosql.schemainference.NoSQLSchema.Entity
import java.util.List
import es.um.nosql.schemainference.entitydifferentiation.EntityDiffSpec
import es.um.nosql.schemainference.entitydifferentiation.PropertySpec
import es.um.nosql.schemainference.util.emf.ModelLoader
import es.um.nosql.schemainference.entitydifferentiation.EntitydifferentiationPackage
import es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation
import es.um.nosql.schemainference.NoSQLSchema.Aggregate
import java.util.Comparator
import es.um.nosql.schemainference.NoSQLSchema.Attribute
import es.um.nosql.schemainference.NoSQLSchema.Reference
import es.um.nosql.schemainference.NoSQLSchema.Tuple
import es.um.nosql.schemainference.NoSQLSchema.PrimitiveType
import es.um.nosql.schemainference.NoSQLSchema.Property
import java.util.ArrayList
import es.um.nosql.schemainference.m2t.commons.Commons
import es.um.nosql.schemainference.m2t.commons.DependencyAnalyzer

/**
 * Class designed to perform the Morphia code generation: Java
 */
class DiffToMorphia
{
  /**
   * The name of the model, directly extracted from the EntityDifferentiation object.
   */
  var modelName = "";

  /**
   * This route stores the import routes between objects.
   */
  var importRoute = "";

  static File outputDir;

  DependencyAnalyzer analyzer;

  /**
   * Method used to start the generation process from a diff model file
   */
  def void m2t(File modelFile, File outputFolder)
  {
    val loader = new ModelLoader(EntitydifferentiationPackage.eINSTANCE);
    val diff = loader.load(modelFile, EntityDifferentiation);

    m2t(diff, outputFolder);
  }

  /**
   * Method used to start the generation process from an EntityDifferentiation object.
   */
  def void m2t(EntityDifferentiation diff, File outputFolder)
  {
    modelName = diff.name;
    outputDir = outputFolder.toPath.resolve(modelName).toFile;
    outputDir.mkdirs;
    if (outputDir.toString.contains("src"))
    {
      importRoute = outputDir.toString.substring(outputDir.toString.lastIndexOf("src") + 4).replace(File.separatorChar, ".");
    }
    else
    {
      importRoute = outputDir.toString;
    }

    // Calc dependencies between entities
    analyzer = new DependencyAnalyzer();
    analyzer.performAnalysis(diff);
    analyzer.getTopOrderEntities().forEach[e | Commons.WRITE_TO_FILE(outputDir, schemaFileName(e), genSchema(e))]
  }

  def schemaFileName(Entity e)
  {
    e.name + ".java"
  }

  /**
   * This method generates the basic structure of the Java class.
   */
  def genSchema(Entity e)
  '''
    package «importRoute»;

    «genIncludes(e)»

    «IF (e.entityversions.exists[ev | ev.isRoot])»@Entity(value = "«e.name.toFirstLower»", noClassnameStored = true)«ELSE»@Embedded«ENDIF»
    public class «e.name»
    {
      «IF (e.entityversions.exists[ev | ev.isRoot])»
      @Id
      private ObjectId _id;
      public ObjectId getObjectId() {return this._id;}
      public void setObjectId(ObjectId _id) {this._id = _id;}

      «ENDIF»
      «genSpecs(e, analyzer.getDiffByEntity().get(e))»
    }
  '''

  /**
   * To generate imports, we just check the conditions in which these imports will be used.
   */
  // Actually, Commons should not be imported if there is a Union which is reduced on a single element.
  // Doesnt seem easy to bypass these cases at this point, since unions are analyzed later on.
  def genIncludes(Entity entity)
  '''
    «IF (entity.entityversions.exists[ev | ev.isRoot])»
      import org.mongodb.morphia.annotations.Entity;
      import org.mongodb.morphia.annotations.Id;
      import org.bson.types.ObjectId;
    «ENDIF»
    «IF (analyzer.getTypeListByPropertyName.get(entity).values.exists[l | !l.isEmpty])»
      import «importRoute».commons.Commons;
      import org.mongodb.morphia.annotations.PreLoad;
      import com.mongodb.DBObject;
    «ENDIF»
    «IF (entity.entityversions.exists[ev | !ev.isRoot || ev.properties.exists[p | p instanceof Aggregate]])»import org.mongodb.morphia.annotations.Embedded;«ENDIF»
    «IF (entity.entityversions.exists[ev | !ev.properties.empty])»import org.mongodb.morphia.annotations.Property;«ENDIF»
    «IF (entity.entityversions.exists[ev | ev.properties.exists[p | p instanceof Reference && Commons.EXPAND_REF(p as Reference).length == 2]])»import org.mongodb.morphia.annotations.Reference;«ENDIF»
    import javax.validation.constraints.NotNull;

    «FOR Entity e : analyzer.getEntityDeps().get(entity).sortWith(Comparator.comparing[e | analyzer.getTopOrderEntities().indexOf(e)])»
      import «importRoute».«e.name»;
    «ENDFOR»
  '''

  /**
   * For each property of any version of an entity, generate code.
   * Two methods are called depending on if there is a need for the type check.
   * s.key stores a PropertySpec
   * s.value stores "required" or not
   */
  def genSpecs(Entity e, EntityDiffSpec spec)
  '''
    «FOR s : spec.commonProps.map[cp | cp -> true] + spec.specificProps.map[sp | sp -> false] SEPARATOR '\n'»
      «IF s.key.needsTypeCheck»
        «genCodeForTypeCheckProperty(e, s.key.property, s.value)»
      «ELSE»
        «genCodeForProperty(s.key.property, s.value)»
      «ENDIF»
    «ENDFOR»
  '''

  def specificProps(EntityDiffSpec spec)
  {
    spec.entityVersionProps.map[propertySpecs].fold(<PropertySpec>newHashSet(),
      [result, neew |
        val names = result.map[p | p.property.name].toSet
        result.addAll(neew.filter[p | !names.contains(p.property.name)])
        result
      ])
  }

  /**
   * Method used to try to reduce a Property Union to a single property.
   * This is sometimes possible, for example when a Union is composed of a Reference String[] and a Tuple String[].
   * If the reduction is possible, we generate the property as any other.
   * If not, a Union is generated.
   */
  def genCodeForTypeCheckProperty(Entity e, Property property, boolean required)
  {
    val typeList = analyzer.getTypeListByPropertyName().get(e).get(property.name)
    // On uniqueTypeList we removed duplicated property types, such as a String PrimitiveType and a Reference w originalType String.
    val uniqueTypeList = new ArrayList<Property>();
    // Just a shortcut list so we don't have to access every time to the type field of a property (and all its casts...)
    val typeShortcutList = new ArrayList<String>();

    // We try to reduce Unions. For example, a Union of type Reference.String and a PrimitiveType.String should be reduced to a String field.
    for (PropertySpec ps : typeList)
      reduceUnionProperty(ps.property, uniqueTypeList, typeShortcutList)

    // We reduced the union to a single type!
    if (uniqueTypeList.size == 1)
    {
      genCodeForProperty(uniqueTypeList.head, required);
    }
    else
    {
      genUnion(uniqueTypeList, required);
    }
  }

  def dispatch reduceUnionProperty(Aggregate aggr, List<Property> uniqueTypeList, List<String> typeShortcutList)
  {
    addToReduceLists(aggr, (aggr.refTo.get(0).eContainer as Entity).name, uniqueTypeList, typeShortcutList);
  }

  def dispatch reduceUnionProperty(Reference ref, List<Property> uniqueTypeList, List<String> typeShortcutList)
  {
    addToReduceLists(ref, ref.originalType, uniqueTypeList, typeShortcutList);
  }

  def dispatch reduceUnionProperty(Attribute attr, List<Property> uniqueTypeList, List<String> typeShortcutList)
  {
    if (attr.type instanceof PrimitiveType)
      addToReduceLists(attr, (attr.type as PrimitiveType).name, uniqueTypeList, typeShortcutList);
    if (attr.type instanceof Tuple)
    {
      val typeTuple = (attr.type as Tuple).elements;
      if (typeTuple.size == 1)
      {
        uniqueTypeList.add(attr);
        typeShortcutList.add((typeTuple.get(0) as PrimitiveType).name);
      }
      else if (typeTuple.size > 1)
        addToReduceLists(attr, "Object[]", uniqueTypeList, typeShortcutList);
    }
  }

  def addToReduceLists(Property p, String name, List<Property> uniqueTypeList, List<String> typeShortcutList)
  {
    if (!typeShortcutList.exists[type | type.equals(name)])
    {
      uniqueTypeList.add(p);
      typeShortcutList.add(name);
    }
  }
  /** End of the Union reduction process */

  /**
   * Method used to generate Union code. In Java this is performed by creating an Object attribute
   * and some restrictions when setting that attribute.
   * It is also neccesary to create a @Preload method in order to identify the Union during the loading process.
   */
  def String genUnion(Iterable<Property> list, boolean required)
  {
    val theTypes = list.map[p | genTypeForProperty(p)];
    val theName = list.head.name;

  '''
  // @Union_«theTypes.join('_')»
  «IF list.exists[p | p instanceof Aggregate]»@Embedded«ELSE»@Property«ENDIF»
  «IF required»@NotNull(message = "«theName» can't be null")«ENDIF»
  private Object «theName»;
  public Object get«theName.toFirstUpper»() {return this.«theName»;}
  public void set«theName.toFirstUpper»(Object «theName»)
  {
    if («list.map[p | p.name + " instanceof " + genTypeForProperty(p)].join(' || ')»)
      this.«theName» = «theName»;
    else
      throw new ClassCastException("«theName» must be of type «theTypes.join(' or ')»");
  }

  @PreLoad
  private void processUnion_«theTypes.join('_').replace("[]", "")»(DBObject dbObj)
  {
    «IF !required»
    if (!dbObj.containsField("«theName»"))
      return;
    «ENDIF»

    Object fieldObj = dbObj.get("«theName»");

    «FOR Property prop : list SEPARATOR '\nelse '»
      «genUnionFor(prop)»
    «ENDFOR»

    dbObj.removeField("«theName»");
  }
  '''
  }

  def dispatch genUnionFor(Aggregate aggr)
  '''
    if (fieldObj instanceof DBObject && ((DBObject)fieldObj).get("className").equals(«(aggr.refTo.head.eContainer as Entity).name».class.getCanonicalName()))
      this.«aggr.name» = Commons.CAST(«(aggr.refTo.head.eContainer as Entity).name».class, fieldObj);
  '''

  def dispatch genUnionFor(Reference ref)
  {
    val typeRef = genTypeForProperty(ref);
    '''
    «IF Commons.EXPAND_REF(ref).length == 2»
      if (fieldObj instanceof DBObject && ((DBObject)fieldObj).get("$ref").equals("«ref.refTo.name»"))
        this.«ref.name» = ((DBObject)fieldObj).get("$id");
    «ELSE»
      if (fieldObj instanceof «typeRef»)
        this.«ref.name» = («typeRef»)fieldObj;
    «ENDIF»
    '''
  }

  def dispatch genUnionFor(Attribute attr)
  '''
    if (fieldObj instanceof «genAttributeType(attr.type)»)
      this.«attr.name» = («genAttributeType(attr.type)»)fieldObj;
  '''

  /**
   * Generate code method for Aggregation
   * It generates a private attribute, and Get/Set methods.
   */
  def dispatch genCodeForProperty(Aggregate aggr, boolean required)
  '''
    @Embedded
    «IF required»@NotNull(message = "«aggr.name» can't be null")«ENDIF»
    private «genTypeForProperty(aggr)» «aggr.name»;
    public «genTypeForProperty(aggr)» get«aggr.name.toFirstUpper»() {return this.«aggr.name»;}
    public void set«aggr.name.toFirstUpper»(«genTypeForProperty(aggr)» «aggr.name») {this.«aggr.name» = «aggr.name»;}
  '''

  /**
   * Shortcut method to generate an Aggregate type.
   */
  def dispatch genTypeForProperty(Aggregate aggr)
  {
    var entityName = (aggr.refTo.get(0).eContainer as Entity).name;
    if (aggr.lowerBound !== 1 || aggr.upperBound !== 1)
      entityName = entityName + "[]";

    entityName;
  }

  /**
   * Generate code method for Reference
   * It generates a private attribute, and Get/Set methods.
   */
  def dispatch genCodeForProperty(Reference ref, boolean required)
  '''
    «IF Commons.EXPAND_REF(ref).length == 2»@Reference«ELSE»@Property«ENDIF»
    «IF required»@NotNull(message = "«ref.name» can't be null")«ENDIF»
    private «genTypeForProperty(ref)» «ref.name»;
    public «genTypeForProperty(ref)» get«ref.name.toFirstUpper»() {return this.«ref.name»;}
    public void set«ref.name.toFirstUpper»(«genTypeForProperty(ref)» «ref.name») {this.«ref.name» = «ref.name»;}
  '''

  /**
   * Shortcut method to generate a Reference type.
   */
  def dispatch genTypeForProperty(Reference ref)
  {
    val refComps = Commons.EXPAND_REF(ref);
    var returnValue = "";

    // References as DBRef as stored as @Reference private ObjectReferences([])?
    if (refComps.length == 2)
    {
      returnValue = ref.refTo.name;
    }
    // References as Strings or Integers are just stored as @Property private String|Integer([])?, just as usual attributes
    else
    {
      if (ref.originalType === null || ref.originalType.empty)
      {
        returnValue = "String";
      }
      else
      {
        returnValue = ref.originalType;
      }
      returnValue = genAttributeType(returnValue).toString;
    }

    if (ref.lowerBound !== 1 || ref.upperBound !== 1)
      returnValue = returnValue + "[]";

    returnValue;
  }

  /**
   * Generate code method for Attribute
   * It generates a private attribute, and Get/Set methods.
   */
  def dispatch genCodeForProperty(Attribute a, boolean required)
  '''
    @Property
    «IF required && !a.name.equals("type")»@NotNull(message = "«a.name» can't be null")«ENDIF»
    private «genTypeForProperty(a)» «a.name»;
    public «genTypeForProperty(a)» get«a.name.toFirstUpper»() {return this.«a.name»;}
    public void set«a.name.toFirstUpper»(«genTypeForProperty(a)» «a.name») {this.«a.name» = «a.name»;}
  '''

  /**
   * Shortcut method to generate an Attribute type.
   */
  def dispatch genTypeForProperty(Attribute a)
  {
    genAttributeType(a.type)
  }

  /**
   * Shortcut method to generate a PrimitiveType type.
   */
  def dispatch CharSequence genAttributeType(PrimitiveType type)
  {
    genAttributeType(type.name)
  }

  /**
   * Shortcut method to generate a Tuple type.
   */
  def dispatch CharSequence genAttributeType(Tuple tuple)
  {
    if (tuple.elements.size == 1)
      '''«genAttributeType(tuple.elements.get(0))»[]'''
    else
      // Heterogeneous arrays. Too complex for now...
      '''Object[]'''
  }

  def dispatch CharSequence genAttributeType(String type)
  {
    switch typeName : type.toLowerCase
    {
      case "string" : "String"
      case Commons.IS_INT(typeName) : "Integer"
      case Commons.IS_FLOAT(typeName) :  "Float"
      case Commons.IS_BOOLEAN(typeName) : "Boolean"
      case Commons.IS_OBJECTID(typeName) : "ObjectId"
      default: ""
    }
  }
}