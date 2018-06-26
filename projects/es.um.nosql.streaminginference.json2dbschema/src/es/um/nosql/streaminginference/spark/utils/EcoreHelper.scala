package es.um.nosql.streaminginference.spark.utils


import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.JavaConversions.seqAsJavaList
import scala.collection.mutable.HashMap

import org.apache.commons.lang3.tuple.MutablePair
import org.eclipse.emf.common.util.EList

import es.um.nosql.streaminginference.NoSQLSchema.Aggregate
import es.um.nosql.streaminginference.NoSQLSchema.Association
import es.um.nosql.streaminginference.NoSQLSchema.Attribute
import es.um.nosql.streaminginference.NoSQLSchema.Entity
import es.um.nosql.streaminginference.NoSQLSchema.EntityVersion
import es.um.nosql.streaminginference.NoSQLSchema.NoSQLSchema
import es.um.nosql.streaminginference.NoSQLSchema.NoSQLSchemaPackage
import es.um.nosql.streaminginference.NoSQLSchema.PrimitiveType
import es.um.nosql.streaminginference.NoSQLSchema.Property
import es.um.nosql.streaminginference.NoSQLSchema.Reference
import es.um.nosql.streaminginference.NoSQLSchema.Tuple
import es.um.nosql.streaminginference.NoSQLSchema.Type
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.ArraySC
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.BooleanSC
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.NumberSC
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.ObjectSC
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.SchemaComponent
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.StringSC
import es.um.nosql.streaminginference.json2dbschema.process.SchemaInference

object EcoreHelper 
{  
  def isEqual(r1: Reference, r2: Reference): Boolean = 
    r1.getName == r2.getName && 
    r1.getLowerBound == r2.getLowerBound &&
    r1.getUpperBound == r2.getUpperBound &&
    isEqual(r1.getRefTo, r2.getRefTo, checkVersions = false)

  def isEqual(a1: Aggregate, a2: Aggregate): Boolean =
    a1.getName == a2.getName &&
    a1.getLowerBound == a2.getLowerBound &&
    a1.getUpperBound == a2.getUpperBound && 
    {
      val homogeneous1 = a1.getRefTo.forall(isEqual(_, a1.getRefTo.head))
      val homogeneous2 = a2.getRefTo.forall(isEqual(_, a2.getRefTo.head))
      homogeneous1 && 
      homogeneous2 && 
      (a1.getRefTo.isEmpty || 
       a2.getRefTo.isEmpty ||       
       isEqual(a1.getRefTo.head, a2.getRefTo.head)) ||
      a1.getRefTo.size == a2.getRefTo.size &&
      a1.getRefTo.zipWithIndex.forall { case (ev1, index) => isEqual(ev1, a2.getRefTo.get(index)) }
    }
    
  def isEqual(a1: Association, a2: Association): Boolean =
    a1.getClass == a2.getClass &&
    (a1.isInstanceOf[Aggregate] && isEqual(a1.asInstanceOf[Aggregate], a2.asInstanceOf[Aggregate]) ||
     a1.isInstanceOf[Reference] && isEqual(a1.asInstanceOf[Reference], a2.asInstanceOf[Reference]))
  
  def isEqual(p1: PrimitiveType, p2: PrimitiveType): Boolean = p1.getName == p2.getName
  
  def isEqual(t1: Tuple, t2: Tuple): Boolean = 
  { 
    val homogeneous1 = t1.getElements.forall(element => isEqual(element, t1.getElements.head))
    val homogeneous2 = t2.getElements.forall(isEqual(_, t2.getElements.head))
    homogeneous1 && 
    homogeneous2 && 
    (t1.getElements.isEmpty || 
     t2.getElements.isEmpty ||
     isEqual(t1.getElements.head, t2.getElements.head)) ||
    t1.getElements.size == t2.getElements.size && 
    t1.getElements.zipWithIndex.forall { case (elem, index) => isEqual(elem, t2.getElements.get(index)) }   
  }
  
  def isEqual(t1: Type, t2: Type): Boolean = 
    t1.getClass == t2.getClass &&
    (t1.isInstanceOf[PrimitiveType] && isEqual(t1.asInstanceOf[PrimitiveType], t2.asInstanceOf[PrimitiveType]) ||
     t1.isInstanceOf[Tuple] && isEqual(t1.asInstanceOf[Tuple], t2.asInstanceOf[Tuple]))
  
  def isEqual(at1: Attribute, at2: Attribute): Boolean = 
    at1.getName == at2.getName && isEqual(at1.getType, at2.getType)
  
  def isEqual(p1: Property, p2: Property): Boolean =
    p1.getClass == p2.getClass && 
    (p1.isInstanceOf[Association] && isEqual(p1.asInstanceOf[Association], p2.asInstanceOf[Association]) ||
     p1.isInstanceOf[Attribute] && isEqual(p1.asInstanceOf[Attribute], p2.asInstanceOf[Attribute]))    
  
  def isEqual(ev1: EntityVersion, ev2: EntityVersion): Boolean =
    // FIXME: sometimes eContainer is null and it causes a NullPointerException
    // eCointaner must be always included in an entity so eContainer must always be set
    //ev1.eContainer.asInstanceOf[Entity].getName == ev2.eContainer.asInstanceOf[Entity].getName &&
    ev1.getProperties.size == ev2.getProperties.size && 
    ev1.getProperties.forall(property => ev2.getProperties.exists(other => isEqual(property, other)))

  def isEqual(entity1: Entity, entity2: Entity, checkVersions: Boolean = true): Boolean =
    entity1.getName == entity2.getName &&
    (!checkVersions ||
      entity1.getEntityversions.size == entity2.getEntityversions.size && 
      entity1.getEntityversions.forall(ev1 => entity2.getEntityversions.exists(ev2 => isEqual(ev1, ev2))))
  
  
  def merge(entity1: Entity, entity2: Entity): Entity = 
  {      
    val versions1 = entity1.getEntityversions
    val versions2 = entity2.getEntityversions
    val appendVersions = versions2.filter(version => !versions1.exists(other => isEqual(version, other)))
    val currentVersion = versions1.size + 1
    appendVersions.zipWithIndex.foreach { case (ev, index) => ev.setVersionId(currentVersion + index) }
    versions1.addAll(seqAsJavaList(appendVersions))
    entity1
  }
  
  def merge(entities1: EList[Entity], entities2: EList[Entity]): EList[Entity] = 
  {
    val appendEntities = entities2.filter(first => entities1.forall(second => second.getName != first.getName))
    val sameEntities = entities2.diff(appendEntities)
    val mergedEntities = entities1.map(entity => 
    {
      val coincidence = sameEntities.find(other => entity.getName == other.getName)
      if (coincidence.isEmpty || isEqual(entity, coincidence.get)) entity 
      else merge(entity, coincidence.get)
    })
    val finalEntities = mergedEntities ++ appendEntities
    entities1.clear()
    entities1.addAll(seqAsJavaList(finalEntities))
    entities1
  }

  /**
   * FIXME: Correct references to entity versions when merging NoSQLSchemas
   * Merges two schemas
   */
  def merge(schema1: Option[NoSQLSchema], schema2: Option[NoSQLSchema]): Option[NoSQLSchema] = 
    (schema1, schema2) match 
  {
      case (None, None) => None
      case (None, schema2) => schema2
      case (schema1, None) => schema1
      case (Some(schema1), Some(schema2)) => 
      {
        val packageInstance: NoSQLSchemaPackage = NoSQLSchemaPackage.eINSTANCE
        val newSchema = packageInstance.getNoSQLSchemaFactory.createNoSQLSchema
        val mergedEntities = merge(schema1.getEntities(), schema2.getEntities())
        val name = if (!schema1.getName.isEmpty) schema1.getName else schema2.getName
        newSchema.setName(name)
        newSchema.getEntities.addAll(mergedEntities)
        Option(newSchema)
      }
  }
  
  /**
   * Casts a NoSQLSchema model to an SchemaInference state 
   */
  def toSchemaInferenceState(noSql: NoSQLSchema): HashMap[String, SchemaInference] = 
  {
    /**
     * Casts an Entity Version to the equivalent Schema Component
     * 
     * We need to pass schema as parameter to update its internal state: rawEntities and innerSchemaNames
     */
    def fromEntityVersion(schema: SchemaInference, entity: String, version: EntityVersion): ObjectSC = 
    {
      val objSC = new ObjectSC
      var content:SchemaComponent = null
      objSC.isRoot = version.isRoot
      objSC.entityName = entity
      
      version.getProperties.foreach(property => 
      {
        if (property.isInstanceOf[Attribute])
        {
          val attribute = property.asInstanceOf[Attribute]
          if (attribute.getType.isInstanceOf[PrimitiveType])
           content = fromPrimitiveType(attribute.getType.asInstanceOf[PrimitiveType])
          else if (attribute.getType.isInstanceOf[Tuple])
           content = fromTuple(attribute.getType.asInstanceOf[Tuple])
          else 
            throw new NotImplementedError("Unknown attribute type: " + attribute.getType)
        }
        else if (property.isInstanceOf[Aggregate])
          content = fromAggregate(schema, property.getName, property.asInstanceOf[Aggregate])
        else if (property.isInstanceOf[Reference])
          content = fromReference(property.asInstanceOf[Reference])
        else 
          throw new NotImplementedError("Unknown property: " + property.toString)
        
        objSC.add((new MutablePair[String, SchemaComponent](property.getName, content)))

      })

      val collection = schema.getEntities
      if (!collection.containsKey(entity))
        collection.put(entity, new java.util.ArrayList(10))
      
      // Put entity version in list of versions if it does not exist
      if (!collection.get(entity).exists(version => version.equals(objSC)))
        collection.get(entity).add(objSC)
      
      // Add entity name to list of schema names (needed for merging)
      schema.getInnerSchemaNames.add(entity)
      
      objSC
   }
    
     /**
      * Casts a PrimitiveType string to equivalent SchemaComponent
      */
     def fromPrimitiveTypeString(primitive: String): SchemaComponent = 
        primitive match 
        {
            case "Number" => new NumberSC
            case "String" => new StringSC
            case "Boolean" => new BooleanSC
            case _ => throw new NotImplementedError("Unknown primitive type: " + primitive) 
        }
     
     
     /**
      * Casts a PrimitiveType to equivalent SchemaComponent
      */
     def fromPrimitiveType(primitive: PrimitiveType): SchemaComponent = 
        fromPrimitiveTypeString(primitive.getName)
      
    /**
     * Casts a Tuple to equivalent Schema Component  
     */
    def fromTuple(tuple: Tuple): SchemaComponent = 
    {
      val array = new ArraySC
      tuple.getElements.foreach(element => {
        if (element.isInstanceOf[PrimitiveType])
          array.add(fromPrimitiveType(element.asInstanceOf[PrimitiveType]))
        if (element.isInstanceOf[Tuple])
          array.add(fromTuple(element.asInstanceOf[Tuple]))
        else 
          throw new NotImplementedError("Unknown type: " + element.toString)
      })
      array
    }
    
    /**
     * Casts a Reference to equivalent Schema Component
     */
    def fromReference(reference: Reference): SchemaComponent = 
    {
      if (reference.getUpperBound == 1 && reference.getLowerBound == 1)
        fromPrimitiveTypeString(reference.getOriginalType)
      else
      {
        val array = new ArraySC
        array.add(fromPrimitiveTypeString(reference.getOriginalType))
        array
      }
    }
      
    /**
     * Casts an Aggregate to equivalent Schema Component
     */
    def fromAggregate(schema: SchemaInference, entity: String, aggregate: Aggregate): SchemaComponent = 
    {
      if (aggregate.getUpperBound == 1 && aggregate.getLowerBound == 1)
        fromEntityVersion(schema, entity, aggregate.getRefTo.get(0))
      else 
      {
        val array = new ArraySC
        aggregate.getRefTo.foreach(version => array.add(fromEntityVersion(schema, entity, version)))
        array
      }
    }
  
    val schemas = HashMap[String, SchemaInference]()
    noSql
    .getEntities
    .foreach(entity => {
      val versions = entity.getEntityversions.filter(_.isRoot)
      if (!versions.isEmpty)
      {
        val schema = new SchemaInference
        versions.foreach(fromEntityVersion(schema, entity.getName,_))
        schemas.put(entity.getName, schema)
      }
    })
    
    schemas    
  }
}