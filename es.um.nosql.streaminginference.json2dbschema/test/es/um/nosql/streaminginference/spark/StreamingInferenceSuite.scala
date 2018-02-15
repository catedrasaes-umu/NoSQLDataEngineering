package es.um.nosql.streaminginference.spark


import org.scalatest.{FunSuite, BeforeAndAfterAll}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import es.um.nosql.streaminginference.NoSQLSchema.NoSQLSchemaFactory
import es.um.nosql.streaminginference.NoSQLSchema.NoSQLSchemaPackage
import es.um.nosql.streaminginference.spark.utils.EcoreHelper
import scala.collection.JavaConversions._
import org.hamcrest.core.IsEqual
import es.um.nosql.streaminginference.NoSQLSchema.Type
import es.um.nosql.streaminginference.NoSQLSchema.Tuple
import es.um.nosql.streaminginference.NoSQLSchema.PrimitiveType
import es.um.nosql.streaminginference.NoSQLSchema.Entity
import es.um.nosql.streaminginference.NoSQLSchema.Reference
import es.um.nosql.streaminginference.NoSQLSchema.EntityVersion
import es.um.nosql.streaminginference.NoSQLSchema.Aggregate
import es.um.nosql.streaminginference.NoSQLSchema.Property
import es.um.nosql.streaminginference.NoSQLSchema.Attribute

@RunWith(classOf[JUnitRunner])
abstract class StreamingInferenceSuite extends FunSuite with BeforeAndAfterAll {

  val factory:NoSQLSchemaFactory = NoSQLSchemaPackage.eINSTANCE.getNoSQLSchemaFactory
  
  def primitiveType(name:String): PrimitiveType = { 
    val myString = factory.createPrimitiveType
    myString.setName(name)
    myString
  }

  def tuple(parameters: Type*): Tuple = {
    val myTuple = factory.createTuple
    myTuple.getElements.addAll(seqAsJavaList(parameters))
    myTuple
  }
  
  def entity(name: String, versions: EntityVersion*): Entity = {
    val myEntity = factory.createEntity
    myEntity.setName(name)
    myEntity.getEntityversions.addAll(seqAsJavaList(versions))
    myEntity
  }
  
  def reference(name: String, lowerBound: Int, upperBound: Int, entity: Entity): Reference = {
    val myReference = factory.createReference
    myReference.setName(name)
    myReference.setLowerBound(lowerBound)
    myReference.setUpperBound(upperBound)
    myReference.setRefTo(entity)
    myReference
  }
  
  def aggregate(name: String, lowerBound: Int, upperBound: Int, versions: EntityVersion*): Aggregate = {
    val myAggregate = factory.createAggregate
    myAggregate.setName(name)
    myAggregate.setLowerBound(lowerBound)
    myAggregate.setUpperBound(upperBound)
    myAggregate.getRefTo.addAll(seqAsJavaList(versions))
    myAggregate
  }
  
  def entityVersion(properties: Property*): EntityVersion = {
    val myEntityVersion = factory.createEntityVersion
    myEntityVersion.getProperties.addAll(seqAsJavaList(properties))
    myEntityVersion
  }
  
  def attribute(name: String, typed: Type): Attribute = {
    val myAttribute = factory.createAttribute
    myAttribute.setName(name)
    myAttribute.setType(typed)
    myAttribute
  }
  
}
