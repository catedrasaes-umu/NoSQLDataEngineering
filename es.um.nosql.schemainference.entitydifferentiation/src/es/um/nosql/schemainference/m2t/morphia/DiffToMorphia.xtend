package es.um.nosql.schemainference.m2t.morphia

import java.io.File
import es.um.nosql.schemainference.NoSQLSchema.Entity
import java.util.List
import java.util.Map
import java.util.Set
import es.um.nosql.schemainference.entitydifferentiation.EntityDiffSpec
import es.um.nosql.schemainference.entitydifferentiation.PropertySpec
import es.um.nosql.schemainference.util.emf.ModelLoader
import es.um.nosql.schemainference.entitydifferentiation.EntitydifferentiationPackage
import es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation
import es.um.nosql.schemainference.NoSQLSchema.Aggregate
import java.io.PrintStream
import java.util.Comparator
import es.um.nosql.schemainference.NoSQLSchema.Attribute
import es.um.nosql.schemainference.NoSQLSchema.Reference
import es.um.nosql.schemainference.NoSQLSchema.PrimitiveType
import es.um.nosql.schemainference.NoSQLSchema.Tuple

class DiffToMorphia
{
  var modelName = "";
  var importRoute = "";
  static File outputDir;

  // List of dependencies
  List<Entity> topOrderEntities
  Map<Entity, Set<Entity>> entityDeps
  Map<Entity, Set<Entity>> inverseEntityDeps
  Map<Entity, EntityDiffSpec> diffByEntity
  Map<Entity, Map<String, List<PropertySpec>>> typeListByPropertyName

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
   * Method used to start the generation process from an EntityDifferentiation object
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

    diffByEntity = newHashMap(diff.entityDiffSpecs.map[ed | ed.entity -> ed])
    val entities = diff.entityDiffSpecs.map[entity]

    // Calc dependencies between entities
    topOrderEntities = calculateDeps(entities)

    typeListByPropertyName = calcTypeListMatrix(entities)
    topOrderEntities.forEach[e | writeToFile(schemaFileName(e), generateSchema(e))]
  }

  def schemaFileName(Entity e)
  {
    e.name + ".java"
  }

  def generateSchema(Entity e) '''
    package «importRoute»;

    «generateIncludes(e)»

    «IF (e.entityversions.exists[ev | ev.isRoot])»@Entity(noClassnameStored = true)«ELSE»@Embedded«ENDIF»
    public class «e.name»
    {
      «generateSpecs(e, diffByEntity.get(e))»
    }
  '''

  def generateIncludes(Entity entity) '''
    «IF (entity.entityversions.exists[ev | ev.isRoot])»import org.mongodb.morphia.annotations.Entity;«ENDIF»
    «IF (entity.entityversions.exists[ev | !ev.isRoot || ev.properties.exists[p | p instanceof Aggregate]])»import org.mongodb.morphia.annotations.Embedded;«ENDIF»
    «IF (entity.entityversions.exists[ev | !ev.properties.empty])»import org.mongodb.morphia.annotations.Property;«ENDIF»
    import javax.validation.constraints.NotNull;

    «FOR e : entityDeps.get(entity).sortWith(Comparator.comparing[e | topOrderEntities.indexOf(e)])»
      import «importRoute».«e.name»;
    «ENDFOR»
  '''

  def generateSpecs(Entity e, EntityDiffSpec spec) '''
  «FOR s : spec.commonProps.map[cp | cp -> true] + spec.specificProps.map[sp | sp -> false] SEPARATOR '\n'»
    «generatePropSpec(e, s.key, s.value)»
  «ENDFOR»
  '''

  def generatePropSpec(Entity e, PropertySpec ps, boolean required)
  {
    if (ps.needsTypeCheck)
    {
      // TODO: Welcome to dreamland...genTypeForTypeCheckProperty(e, ps.property, required)
      // What if we try to Object[] everything and let the morphia serializer deal with it....dinamically? Worth a try
      //TODO
// http://lambda-the-ultimate.org/node/2694
// This will be helpful for the union types.
      //Another idea: @PreSave with a custom validator...
    }
    else
    {
      generateTypeForProperty(ps.property, required);
    }
  }

  def dispatch generateTypeForProperty(Aggregate aggr, boolean required)
  {
    var entityName = (aggr.refTo.get(0).eContainer as Entity).name;
    if (aggr.lowerBound != 1 || aggr.upperBound != 1)
      entityName = entityName + "[]";
    '''
    @Embedded
    «IF required»@NotNull(message = "«aggr.name» can't be null")«ENDIF»
    private «entityName» «aggr.name»;
    public «entityName» get«aggr.name.toFirstUpper»() {return this.«aggr.name»;}
    public void set«aggr.name.toFirstUpper»(«entityName» «aggr.name») {this.«aggr.name» = «aggr.name»;}
    '''
  }

  def dispatch generateTypeForProperty(Reference ref, boolean required)
  {
    //TODO: References pending...
  }

  def dispatch generateTypeForProperty(Attribute a, boolean required)
  '''
    @Property("«a.name»")
    «IF required && !a.name.equals("type")»@NotNull(message = "«a.name» can't be null")«ENDIF»
    private «generateAttributeType(a.type)» «a.name»;
    public «generateAttributeType(a.type)» get«a.name.toFirstUpper»() {return this.«a.name»;}
    public void set«a.name.toFirstUpper»(«generateAttributeType(a.type)» «a.name») {this.«a.name» = «a.name»;}
  '''

  def dispatch generateAttributeType(PrimitiveType type)
  {
    switch typeName : type.name.toLowerCase
    {
      case "string" : "String"
      case typeName.isInt : "Integer"
      case typeName.isFloat :  "Float"
      case typeName.isBoolean : "Boolean"
      case typeName.isObjectId : "ObjectId"
      default: ""
    }
  }

  def dispatch generateAttributeType(Tuple tuple)
  {
    if (tuple.elements.size == 1)
      '''«generateAttributeType(tuple.elements.get(0))»[]'''
    else
      //TODO: Heterogeneous arrays. Too complex for now...
      '''Object[]'''
  }

  private def isInt(String type) { #["int", "integer", "number"].contains(type)}
  private def isFloat(String type) { #["float", "double"].contains(type)}
  private def isBoolean(String type) { #["boolean", "bool"].contains(type)}
  private def isObjectId(String type) { #["objectid"].contains(type)}

  def specificProps(EntityDiffSpec spec)
  {
    spec.entityVersionProps.map[propertySpecs].fold(<PropertySpec>newHashSet(),
      [result, neew |
        val names = result.map[p | p.property.name].toSet
        result.addAll(neew.filter[p | !names.contains(p.property.name)])
        result
      ])
  }

  // Fill, for each property of each entity that appear in more than 
  // one entity version *with different type* (those that hold the needsTypeCheck
  // boolean attribute), the list of types, to check possible type folding in
  // a latter pass
  def calcTypeListMatrix(List<Entity> entities)
  {
    entities.toInvertedMap[e |
      diffByEntity.get(e).entityVersionProps
        .map[propertySpecs]
        .flatten
        .filter[needsTypeCheck].groupBy[property.name]
    ]
  }

  /**
   * Method used to write a generated CharSequence to a file
   */
  private def writeToFile(String filename, CharSequence toWrite)
  {
    val outFile = outputDir.toPath().resolve(filename).toFile()
    val outFileWriter = new PrintStream(outFile)
    outFileWriter.print(toWrite)
    outFileWriter.close()
  }

  /**
   * Method used to calculate the dependencies between entities, and reorder them in the correct order
   */
  private def calculateDeps(List<Entity> entities) 
  { 
    entityDeps = newHashMap(entities.map[e | e -> getDepsFor(e)])
    inverseEntityDeps = newHashMap(entities.map[e | 
      e -> entities.filter[e2 | entityDeps.get(e2).contains(e)].toSet
    ])

    // Implement a topological order, Khan's algorithm
    // https://en.wikipedia.org/wiki/Topological_sorting#Kahn.27s_algorithm
    topologicalOrder()
  }

  // Get the first level of dependencies for an Entity
  private def getDepsFor(Entity entity)
  {
    entity.entityversions.map[ev | 
      ev.properties.filter[p | p instanceof Aggregate]
      .map[p | (p as Aggregate).refTo.map[ev2 | ev2.eContainer as Entity]]
      .flatten
    ].flatten.toSet
  }

  private def List<Entity> topologicalOrder()
  {
    depListRec(
      entityDeps.filter[k, v| v.empty].keySet,
      newLinkedList(),
      newHashSet()
    )
  }

  private def List<Entity> depListRec(Set<Entity> to_consider, List<Entity> top_order, Set<Entity> seen)
  {
    // End condition
    if (to_consider.isEmpty)
      top_order
    else
    {
      // Recursive
      val e = to_consider.head
      val to_consider_ = to_consider.tail.toSet

      // Add current node (no dependencies to cover)
      top_order.add(e)
      seen.add(e)

      val dependent = inverseEntityDeps.get(e)
      to_consider_.addAll(
        dependent.filter[ d | seen.containsAll(entityDeps.get(d))
      ])

      depListRec(to_consider_, top_order, seen)
    }
  }
}