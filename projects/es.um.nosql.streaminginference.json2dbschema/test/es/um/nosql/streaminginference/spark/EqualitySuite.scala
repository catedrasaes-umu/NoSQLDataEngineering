package es.um.nosql.streaminginference.spark


import org.scalatest.{FunSuite, BeforeAndAfterAll}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import es.um.nosql.streaminginference.NoSQLSchema.NoSQLSchemaFactory
import es.um.nosql.streaminginference.NoSQLSchema.NoSQLSchemaPackage
import es.um.nosql.streaminginference.spark.utils.EcoreHelper
import scala.collection.JavaConversions._
import org.hamcrest.core.IsEqual
import es.um.nosql.streaminginference.NoSQLSchema.Tuple

@RunWith(classOf[JUnitRunner])
class EqualitySuite extends StreamingInferenceSuite {

  test("Equality between primitive types") {
    assert(!EcoreHelper.isEqual(primitiveType("String"), primitiveType("Number")), "String primitive and Number primitive are not equal")
    assert(EcoreHelper.isEqual(primitiveType("String"), primitiveType("String")), "String primitive and String primitive are equal")
  }
  
  test("Equality between tuples") {
    
    var tuple1:Tuple = tuple(primitiveType("String"))
    var tuple2:Tuple = null
    assert(!EcoreHelper.isEqual(tuple1, primitiveType("String")), "Tuple and PrimitiveType are not equal")
    tuple1 = tuple(primitiveType("String"), primitiveType("Number"))
    tuple2 = tuple(primitiveType("String"), primitiveType("String"))
    assert(!EcoreHelper.isEqual(tuple1, tuple2), "Tuples with distinct types are not equal")
    tuple1 = tuple(primitiveType("String"), primitiveType("String"))
    assert(EcoreHelper.isEqual(tuple1, tuple2), "Tuples with the same types are equal")
    tuple2 = tuple(primitiveType("String"), primitiveType("String"), primitiveType("String"))
    assert(EcoreHelper.isEqual(tuple1, tuple2), "Tuples with the same types but different sizes are equal")
    tuple1 = tuple(tuple(primitiveType("String")), primitiveType("String"))
    tuple2 = tuple(tuple(primitiveType("String")), primitiveType("String"))
    assert(EcoreHelper.isEqual(tuple1, tuple2), "Nested Tuples with the same types and order are equal")
    tuple1 = tuple(tuple(primitiveType("String")), primitiveType("String"))
    tuple2 = tuple(primitiveType("String"), tuple(primitiveType("String")))
    assert(!EcoreHelper.isEqual(tuple1, tuple2), "Nested Tuples with the same types but different order are not equal")
  }
  
  test("Equality between References") {
    
    var reference1 = reference("author", 0, 1, entity("author"))
    var reference2 = reference("author", 0, 1, entity("author"))
    assert(EcoreHelper.isEqual(reference1,reference2), "References with the same name, cardinality and referenced entity are equal")
    reference2 = reference("book", 0, 1, entity("author"))
    assert(!EcoreHelper.isEqual(reference1,reference2), "References with different name are not equal")
    reference2 = reference("author", 1, 1, entity("author"))
    assert(!EcoreHelper.isEqual(reference1,reference2), "References with different cardinality are not equal")
    reference2 = reference("author", 0, 1, entity("publisher"))
    assert(!EcoreHelper.isEqual(reference1,reference2), "References referencing entities with different names are not equal")
  }
  
  test("Equality between Aggregates -- Basic Tests") {
    
    var version1 = entityVersion(attribute("name", primitiveType("String")))
    var version2 = entityVersion(attribute("name", primitiveType("String")))
    var version3 = entityVersion(attribute("name", primitiveType("String")))
    var entity1 = entity("author", version1)
    var entity2 = entity("author", version2, version3)
    var aggregate1 = aggregate("authors", 0, 1, version1)
    var aggregate2 = aggregate("authors", 0, 1, version2)
    assert(EcoreHelper.isEqual(aggregate1, aggregate2), "Aggregates with same name, cardinality and referenced entity types are equal")
    aggregate2 = aggregate("authors", 0, 1, version2, version3)
    assert(EcoreHelper.isEqual(aggregate1, aggregate2), "Aggregates with same name, cardinality and referenced entity types are equal, even if number of references are different")
    aggregate2 = aggregate("publishers", 0, 1, version2)
    assert(!EcoreHelper.isEqual(aggregate1, aggregate2), "Aggregates with different names are not equal")
    aggregate2 = aggregate("authors", 1, 1, version2)
    assert(!EcoreHelper.isEqual(aggregate1, aggregate2), "Aggregates with different cardinalities are not equal")
    // FIXME: Unresolved test case
    entity2 = entity("publisher", version2)
    aggregate2 = aggregate("authors", 0, 1, version2)
    assert(!EcoreHelper.isEqual(aggregate1, aggregate2), "Aggregates referencing versions from different entities are not equal")
    version2 = entityVersion(attribute("name", primitiveType("String")), attribute("lastname", primitiveType("String")))
    entity2 = entity("author", version2)
    assert(!EcoreHelper.isEqual(aggregate1, aggregate2), "Aggregates referencing versions with different properties are not equal")
  }
  
  test("Equality between Aggregates -- Advanced Tests") {
    
    var version1 = entityVersion(attribute("name", primitiveType("String")), attribute("lastname", primitiveType("String")))
    var version2 = entityVersion(attribute("lastname", primitiveType("String")), attribute("name", primitiveType("String")))
    var version3 = entityVersion(attribute("name", primitiveType("String")))
    var version4 = entityVersion(attribute("name", primitiveType("String")))
    var entity1 = entity("author", version1, version2, version3, version4)
    
    var aggregate1 = aggregate("authors", 0, 1, version1)
    var aggregate2 = aggregate("authors", 0, 1, version2)
    assert(EcoreHelper.isEqual(aggregate1, aggregate2), "Aggregates referencing versions with same properties but different order are equal")
    aggregate1 = aggregate("authors", 0, 1, version1, version3)
    aggregate2 = aggregate("authors", 0, 1, version2, version4)
    assert(EcoreHelper.isEqual(aggregate1, aggregate2), "Aggregates referencing different versions with the same order are equal")
    aggregate1 = aggregate("authors", 0, 1, version3, version1)
    aggregate2 = aggregate("authors", 0, 1, version2, version4)
    assert(!EcoreHelper.isEqual(aggregate1, aggregate2), "Aggregates referencing different versions with different order are not equal")
  }
  
  test("Equality between Entity Versions") {
      
      var version1 = entityVersion(attribute("name", primitiveType("String")), attribute("lastname", primitiveType("String")))
      var version2 = entityVersion(attribute("name", primitiveType("String")), attribute("lastname", primitiveType("String")))
      var entity1 = entity("author", version1)
      var entity2 = entity("author", version2)
      assert(EcoreHelper.isEqual(version1, version2), "Entity versions with same properties are equal")
      version2 = entityVersion(attribute("lastname", primitiveType("String")), attribute("name", primitiveType("String")))
      entity2 = entity("author", version2)
      assert(EcoreHelper.isEqual(version1, version2), "Entity versions with same properties but different order are equal")
      version2 = entityVersion(attribute("name", primitiveType("String")))
      entity2 = entity("author", version2)
      assert(!EcoreHelper.isEqual(version1, version2), "Entity versions with different properties are not equal")
      // FIXME: Unresolved test case
      version2 = entityVersion(attribute("name", primitiveType("String")), attribute("lastname", primitiveType("String")))
      entity2 = entity("publisher", version2)
      assert(!EcoreHelper.isEqual(version1, version2), "Entity versions from different entities are not equal")
  }
  
  test("Equality between Entities") {
    
    var version1 = entityVersion(attribute("name", primitiveType("String")), attribute("lastname", primitiveType("String")))
    var version2 = entityVersion(attribute("name", primitiveType("String")), attribute("lastname", primitiveType("String")))
    var version3 = entityVersion(attribute("name", primitiveType("String")))
    var version4 = entityVersion(attribute("name", primitiveType("String")))
    var version5 = entityVersion(attribute("name", primitiveType("String")), attribute("age", primitiveType("Number")))
    var entity1 = entity("author", version1, version3)
    var entity2 = entity("author", version2, version4)
    assert(EcoreHelper.isEqual(entity1, entity2), "Entities with the same entity versions are equal")  
    entity2 = entity("author", version4, version2)
    assert(EcoreHelper.isEqual(entity1, entity2), "Entities with the same entity versions but different order are equal")  
    entity2 = entity("author", version2)
    assert(!EcoreHelper.isEqual(entity1, entity2), "Entities with different number of versions are not equal")  
    entity2 = entity("author", version2, version5)
    assert(!EcoreHelper.isEqual(entity1, entity2), "Entities with different versions are not equal")  
    entity2 = entity("publisher", version2, version5)
    assert(!EcoreHelper.isEqual(entity1, entity2), "Entities with different name are not equal") 
    
  }

}
