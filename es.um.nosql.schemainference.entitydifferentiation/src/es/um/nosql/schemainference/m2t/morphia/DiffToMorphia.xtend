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
import es.um.nosql.schemainference.m2t.config.ConfigMorphia

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

  MorphiaIndexValGen indexValGen;

  /**
   * Method used to start the generation process from a diff model file
   */
  def void m2t(File modelFile, File outputFolder, File configFile)
  {
    val loader = new ModelLoader(EntitydifferentiationPackage.eINSTANCE);
    val diff = loader.load(modelFile, EntityDifferentiation);

    m2t(diff, outputFolder, configFile);
  }

  /**
   * Method used to start the generation process from an EntityDifferentiation object.
   */
  def void m2t(EntityDifferentiation diff, File outputFolder, File configFile)
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

    // Process the configuration file
    indexValGen = new MorphiaIndexValGen(Commons.PARSE_CONFIG_FILE(ConfigMorphia, configFile, diff));

    // Calc dependencies between entities
    analyzer = new DependencyAnalyzer();
    analyzer.performAnalysis(diff);
    analyzer.getTopOrderEntities().forEach[e | Commons.WRITE_TO_FILE(outputDir, schemaFileName(e), genSchema(e))]
  }

  def schemaFileName(Entity entity)
  {
    entity.name + ".java"
  }

  /**
   * This method generates the basic structure of the Java class.
   */
  def genSchema(Entity entity)
  '''
    package «importRoute»;

    «genIncludes(entity)»

    «IF entity.entityversions.exists[ev | ev.isRoot]»@Entity(value = "«entity.name.toFirstLower»", noClassnameStored = true)«ELSE»@Embedded«ENDIF»
    «indexValGen.genIndexesForEntity(entity)»
    public class «entity.name»
    {
      «genSpecs(entity, analyzer.getDiffByEntity().get(entity))»
    }
  '''

  /**
   * To generate imports, we just check the conditions in which these imports will be used.
   */
  // TODO:Actually, Commons should not be imported if there is a Union which is reduced on a single element.
  // Doesnt seem easy to bypass these cases at this point, since unions are analyzed later on.
  // Por otro lado los SET pueden devolver this, pero no tengo a mano el nombre del tipo a devolver. Entity_name
  def genIncludes(Entity entity)
  {
    val isEntityRoot = entity.entityversions.exists[ev | ev.isRoot];
    val collListUnionProperties = analyzer.getTypeListByPropertyName.get(entity).values;
    '''
    «IF isEntityRoot»
    import org.mongodb.morphia.annotations.Entity;
    import org.mongodb.morphia.annotations.Id;
    «IF isEntityRoot && entity.entityversions.exists[ev | ev.properties.exists[p | p.name.equals("_id") &&
      p instanceof Attribute && (p as Attribute).type instanceof PrimitiveType && ((p as Attribute).type as PrimitiveType).name.equals("ObjectId")]]»
    import org.bson.types.ObjectId;
    «ENDIF»
    «ENDIF»
    «IF collListUnionProperties.exists[l | !l.isEmpty]»
      «IF collListUnionProperties.exists[l | l.exists[ps | ps.property instanceof Aggregate]]»
      import «importRoute».commons.Commons;
      «ENDIF»
      «IF collListUnionProperties.exists[l | l.exists[ps | ps.property instanceof Reference]]»
      import org.mongodb.morphia.annotations.PrePersist;
      «ENDIF»
      import org.mongodb.morphia.annotations.PreLoad;
      import com.mongodb.DBObject;
      «IF collListUnionProperties.exists[l | l.exists[ps | ps.property instanceof Aggregate
        && (ps.property as Aggregate).lowerBound !== 1 && (ps.property as Aggregate).upperBound !== 1]]»
      import com.mongodb.BasicDBList;
      «ENDIF»
    «ENDIF»
    «IF entity.entityversions.exists[ev | !ev.isRoot || ev.properties.exists[p | p instanceof Aggregate]]»import org.mongodb.morphia.annotations.Embedded;«ENDIF»
    «IF entity.entityversions.exists[ev | ev.properties.exists[p | p instanceof Attribute]]»import org.mongodb.morphia.annotations.Property;«ENDIF»
    «IF entity.entityversions.exists[ev | ev.properties.exists[p | p instanceof Reference]]»import org.mongodb.morphia.annotations.Reference;«ENDIF»
    «IF !analyzer.getDiffByEntity().get(entity).commonProps.isEmpty»import javax.validation.constraints.NotNull;«ENDIF»
    «indexValGen.genIncludesForEntity(entity)»

    «FOR Entity e : analyzer.getEntityDeps().get(entity).sortWith(Comparator.comparing[e | analyzer.getTopOrderEntities().indexOf(e)])»
      import «importRoute».«e.name»;
    «ENDFOR»
    '''
  }

  /**
   * For each property of any version of an entity, generate code.
   * Two methods are called depending on if there is a need for the type check.
   * s.key stores a PropertySpec
   * s.value stores "required" or not
   */
  def genSpecs(Entity entity, EntityDiffSpec spec)
  '''
    «FOR s : (spec.commonProps.map[cp | cp -> true] + spec.specificProps.map[sp | sp -> false])
      .reject[p | p.key.property.name.startsWith("_") && !p.key.property.name.equals("_id")]
      .sortBy[p | p.key.property.name] SEPARATOR '\n'»
      «IF s.key.needsTypeCheck»
        «genCodeForTypeCheckProperty(entity, s.key.property, s.value)»
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
  def genCodeForTypeCheckProperty(Entity entity, Property property, boolean required)
  {
    val typeList = analyzer.getTypeListByPropertyName().get(entity).get(property.name)
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
  «indexValGen.genValidatorsForField(list.head.eContainer.eContainer as Entity, theName)»
  private Object «theName»;
  public Object get«theName.toFirstUpper»() {return this.«theName»;}
  public void set«theName.toFirstUpper»(Object «theName»)
  {
    if («list.map[p | p.name + " instanceof " + genTypeForProperty(p)].join(' || ')»)
      this.«theName» = «theName»;
    else
      throw new ClassCastException("«theName» must be of type «theTypes.join(' or ')»");
  }
  «IF list.exists[p | p instanceof Reference]»

  @PrePersist
  private void prePersistUnion_«theTypes.join('_').replace("[]", "")»()
  {
    «FOR Property p : list.filter[p | p instanceof Reference] SEPARATOR '\nelse '»
      if («p.name» instanceof «genTypeForProperty(p as Reference)»)
        «p.name» = ((«genTypeForProperty(p as Reference)»)«p.name»).get_id();
    «ENDFOR»
  }
  «ENDIF»  

  @PreLoad
  private void preLoadUnion_«theTypes.join('_').replace("[]", "")»(DBObject dbObj)
  {
    «IF !required»
    if (!dbObj.containsField("«theName»"))
      return;
    «ENDIF»

    Object fieldObj = dbObj.get("«theName»");

    «FOR Property prop : list SEPARATOR '\nelse '»
      «genUnionFor(prop)»
    «ENDFOR»
    else
      throw new ClassCastException("«theName» must be of type «theTypes.join(' or ')»");

    dbObj.removeField("«theName»");
  }
  '''
  }

  def dispatch genUnionFor(Aggregate aggr)
  {
    val typeAggr = genTypeForProperty(aggr);
    '''
    «IF typeAggr.toString.endsWith("[]")»
      if (fieldObj instanceof BasicDBList && Commons.IS_CASTABLE_ARRAY(«(aggr.refTo.head.eContainer as Entity).name».class, (BasicDBList)fieldObj))
        this.«aggr.name» = Commons.CAST_ARRAY(«(aggr.refTo.head.eContainer as Entity).name».class, ((BasicDBList)fieldObj).toArray());
    «ELSE»
      if (fieldObj instanceof DBObject && Commons.IS_CASTABLE(«(aggr.refTo.head.eContainer as Entity).name».class, (DBObject)fieldObj))
        this.«aggr.name» = Commons.CAST(«(aggr.refTo.head.eContainer as Entity).name».class, fieldObj);
    «ENDIF»
    '''
  }

  // TODO: Conflictive point.
  // It is impossible to resolve references once an _id field is queried, since we do not have access to the Datastore
  // So we will have to let the user resolve the reference as he wishes. Remember the field is of Object type,
  // so the user can still query the references manually.
  // This also evades the problem of having unions of different references, because a priori we do not know against which
  // collection should we query the _id attribute.
  def dispatch genUnionFor(Reference ref)
  {
    var typeRef = genAttributeType(ref.originalType);
    if (typeRef == "")
      typeRef = "String";

    '''
    «IF Commons.IS_DBREF(ref)»
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
    «indexValGen.genValidatorsForField(aggr.eContainer.eContainer as Entity, aggr.name)»
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
    @Reference«IF !Commons.IS_DBREF(ref)»(idOnly = true)«ENDIF»
    «IF required»@NotNull(message = "«ref.name» can't be null")«ENDIF»
    «indexValGen.genValidatorsForField(ref.eContainer.eContainer as Entity, ref.name)»
    private «genTypeForProperty(ref)» «ref.name»;
    public «genTypeForProperty(ref)» get«ref.name.toFirstUpper»() {return this.«ref.name»;}
    public void set«ref.name.toFirstUpper»(«genTypeForProperty(ref)» «ref.name») {this.«ref.name» = «ref.name»;}
  '''

  /**
   * Shortcut method to generate a Reference type.
   */
  def dispatch genTypeForProperty(Reference ref)
  {
    var returnValue = ref.refTo.name;

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
    «IF a.name.toLowerCase.equals("_id")»@Id«ELSE»@Property«ENDIF»
    «IF required»@NotNull(message = "«a.name» can't be null")«ENDIF»
    «indexValGen.genValidatorsForField(a.eContainer.eContainer as Entity, a.name)»
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
      case Commons.IS_STRING(typeName) : "String"
      case Commons.IS_INT(typeName) : "Integer"
      case Commons.IS_FLOAT(typeName) :  "Float"
      case Commons.IS_BOOLEAN(typeName) : "Boolean"
      case Commons.IS_OBJECTID(typeName) : "ObjectId"
      default: ""
    }
  }
}