package es.um.nosql.streaminginference.spark.implicits

import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.JavaConversions.seqAsJavaList
import scala.collection.mutable.Buffer
import scala.collection.mutable.HashMap

import org.apache.spark.sql.Row
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.ArrayType
import org.apache.spark.sql.types.BooleanType
import org.apache.spark.sql.types.DataType
import org.apache.spark.sql.types.DoubleType
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.StructType

import es.um.nosql.streaminginference.NoSQLSchema.Aggregate
import es.um.nosql.streaminginference.NoSQLSchema.Association
import es.um.nosql.streaminginference.NoSQLSchema.Attribute
import es.um.nosql.streaminginference.NoSQLSchema.EntityVersion
import es.um.nosql.streaminginference.NoSQLSchema.NoSQLSchema
import es.um.nosql.streaminginference.NoSQLSchema.PrimitiveType
import es.um.nosql.streaminginference.NoSQLSchema.Property
import es.um.nosql.streaminginference.NoSQLSchema.Reference
import es.um.nosql.streaminginference.NoSQLSchema.Tuple
import es.um.nosql.streaminginference.NoSQLSchema.Type
import es.um.nosql.streaminginference.spark.utils.EcoreHelper


object InferenceHelpers 
{
  private def getPrimitiveType(name: String): DataType = 
      name match
      {
        // TODO: Unify primitive types
        case "Boolean" => BooleanType
        case "Number" => DoubleType
        case _ => StringType
      }
  
  implicit class PrimitiveExtend(val primitive: PrimitiveType) extends TypeExtend(primitive)
  {
    override def getSQLType: DataType = getPrimitiveType(primitive.getName)
  }
  
  implicit class TupleExtend(val tuple: Tuple) extends TypeExtend(tuple)
  {
    
    private def isHomogeneous: Boolean = 
    {
      val elements = tuple.getElements
      elements.size match 
      {
        case 0 => true
        // Nested tuples won't be homogeneous
        case 1 => !elements.get(0).isInstanceOf[Tuple] 
        case _ => 
          elements.map(elem => elem.getClass).distinct.size == 1 &&
          // Nested tuples won't be homogeneous
          elements.get(0).isInstanceOf[PrimitiveType]
      }
    }
    
    override def getSQLType: DataType = 
    {
      val elements = tuple.getElements.toSeq
      if (elements.size == 0)
        // FIXME: What is the type of an empty Tuple?
        ArrayType(StringType)
      if (isHomogeneous)
      {
        ArrayType(elements.get(0).getSQLType)
      }
      else
      {
        StructType(
            elements
            .zipWithIndex
            .map { case (element, index) => 
              StructField(index.toString, element.getSQLType)})
      }
    }
  }
  
  implicit class TypeExtend(val typ:Type)
  {
    def getSQLType: DataType = 
    {
      if (typ.isInstanceOf[PrimitiveType])
        typ.asInstanceOf[PrimitiveType].getSQLType
      else
        typ.asInstanceOf[Tuple].getSQLType    
    }
  }
  

  implicit class ReferenceExtend(val reference: Reference) extends AssociationExtend(reference)
  {
    override def getSQLType: DataType = 
    {
      
      if (reference.getUpperBound == -1) 
      {
        ArrayType(getPrimitiveType(reference.getOriginalType))
      }
      else 
      {
        getPrimitiveType(reference.getOriginalType)
      }
    }
  }
  
  implicit class AssociationExtend(val association: Association) extends PropertyExtend(association)
  {
    override def getSQLType: DataType = 
      if (association.isInstanceOf[Reference])
        association.asInstanceOf[Reference].getSQLType
      else
        association.asInstanceOf[Aggregate].getSQLType    
  }
  
  implicit class AggregateExtend(val aggregate: Aggregate) extends AssociationExtend(aggregate)
  {
    private def isHomogeneous: Boolean = 
    {
      val references = aggregate.getRefTo.toSeq
      references.size match 
      {
        case 0 => true
        case 1 => true
        case _ => references.forall(EcoreHelper.isEqual(_, references.head))
      }
    }
    
    override def getSQLType: DataType = {

      val references = aggregate.getRefTo.toSeq
      
      if (references.size == 0)
        // TODO: check empty aggregates
        ArrayType(StringType)
      else if (isHomogeneous)
        ArrayType(references.head.getSQLType)        
      else
        StructType(references
                    .zipWithIndex
                    .map { 
                        case (reference, index) => 
                          StructField(index.toString, reference.getSQLType)})

    }  
  }
  
  
  implicit class AttributeExtend(val attribute: Attribute) extends PropertyExtend(attribute) 
  {
    override def getSQLType: DataType = attribute.getType.getSQLType
  }
  
  implicit class PropertyExtend(val property: Property) 
  {
    def getSQLType: DataType = 
      if (property.isInstanceOf[Attribute])
        property.asInstanceOf[Attribute].getSQLType
      else
        property.asInstanceOf[Association].getSQLType
  }
  
  implicit class VersionExtend(val version: EntityVersion) 
  {
    def getSQLType: StructType = 
      StructType(
          version
            .getProperties
            .map(property => StructField(property.getName, property.getSQLType)))

  }
  
  implicit class NoSQLSchemaExtend(val schema: NoSQLSchema) 
  {

    def toSparkSQLVersions() = {
      
      val versions = HashMap[String, Buffer[StructType]]()
      schema.getEntities.foreach(entity => {
        versions += 
          entity.getName -> entity.getEntityversions.map(version => version.getSQLType)
      })
      
      versions
    }
    
    def printSQLSchema(ss: SparkSession): Unit = {
      schema
      .toSparkSQLVersions
      .map { case (entity, versions) => 
        println(entity)
        println("--------------")
        versions.foreach(version => {
          ss.createDataFrame(ss.sparkContext.emptyRDD[Row], version).printSchema
        })
      }     
    }
    
  }

}