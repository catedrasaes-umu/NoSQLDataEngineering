package es.um.nosql.streaminginference.spark


import org.scalatest.{FunSuite, BeforeAndAfterAll}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import scala.collection.JavaConversions._
import es.um.nosql.streaminginference.json2dbschema.process.SchemaInference
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.ArraySC
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.BooleanSC
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.NullSC
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.NumberSC
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.ObjectSC
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.SchemaComponent
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.StringSC
import java.util.ArrayList

import org.apache.commons.lang3.tuple.Pair;

@RunWith(classOf[JUnitRunner])
class SchemaInferenceSuite extends StreamingInferenceSuite 
{
  
  trait Equal {
    val one:SchemaInference = new SchemaInference
    val other:SchemaInference = new SchemaInference
    val oneVersions = new ArrayList[SchemaComponent](10)
    val otherVersions = new ArrayList[SchemaComponent](10)
  }

  trait Merge {
    val merged:SchemaInference = new SchemaInference
    val mergedVersions = new ArrayList[SchemaComponent](10)
  }

  test("Equality between Schemas")
  {
    val one:SchemaInference = new SchemaInference
    val other:SchemaInference = new SchemaInference
    
    val oneObject = new ObjectSC()
    oneObject.add(Pair.of("title", new StringSC))
    oneObject.add(Pair.of("author", new StringSC))
    oneObject.add(Pair.of("price", new NumberSC))

    val oneVersions = new ArrayList[SchemaComponent](10);
    val otherVersions = new ArrayList[SchemaComponent](10);
    
    assert(one.equals(other), "Empty Schemas must be equal")
    
    one.getEntities.put("book", oneVersions)
    other.getEntities.put("client", otherVersions)
    assert(!one.equals(other), "Schemas with different entities are not equal")
    
    other.getEntities.clear
    other.getEntities.put("book", otherVersions)
    assert(one.equals(other), "Schemas with same versions and entities must be equal")
  }
  
  test("Equality between Object Entity Version Schemas") 
  {
    val one:SchemaInference = new SchemaInference
    val other:SchemaInference = new SchemaInference
    
    val oneObject = new ObjectSC()
    oneObject.add(Pair.of("title", new StringSC))
    oneObject.add(Pair.of("price", new NumberSC))
    
    val otherObject = new ObjectSC()
    otherObject.add(Pair.of("title", new StringSC))
    otherObject.add(Pair.of("price", new NumberSC))
    
    val differentObject = new ObjectSC()
    differentObject.add(Pair.of("title", new StringSC))
    differentObject.add(Pair.of("price", new StringSC))

    val oneVersions = new ArrayList[SchemaComponent](10);
    val otherVersions = new ArrayList[SchemaComponent](10);
    
    one.getEntities.put("book", oneVersions)
    other.getEntities.put("book", otherVersions)
    oneVersions.append(oneObject)
    otherVersions.append(otherObject)
    assert(one.equals(other), "Object Versions with same properties are equal")
    otherObject.add(Pair.of("author", new StringSC))
    assert(!one.equals(other), "Object Versions with different number of properties are not equal")
    otherVersions.clear()
    otherVersions.append(differentObject)
    assert(!one.equals(other), "Object Versions with different property types not equal")      
  }
  
  test("Equality between ArraySC Entity Version Schemas") 
  {
    val one:SchemaInference = new SchemaInference
    val other:SchemaInference = new SchemaInference
    
    var oneArray = new ArraySC()
    var otherArray = new ArraySC()
    val oneVersions = new ArrayList[SchemaComponent](10);
    val otherVersions = new ArrayList[SchemaComponent](10);
    
    oneVersions.append(oneArray)
    otherVersions.append(otherArray)
    
    one.getEntities.put("array", oneVersions)
    other.getEntities.put("array", otherVersions)
    
    assert(one.equals(other), "Array Empty Versions are equal")
    oneArray.add(new StringSC)
    otherArray.add(new StringSC)
    assert(one.equals(other), "Array Versions with same elements are equal")
    oneArray.add(new StringSC)
    assert(one.equals(other), "Homogeneous Array Versions with different number of elements are equal")
    otherArray.add(new NumberSC)
    assert(!one.equals(other), "Non Homogeneous Array Versions with different elements are not equal")
    oneArray.add(new NumberSC)
    otherArray.add(new StringSC)
    assert(!one.equals(other), "Non Homogeneous Array Versions are not equal even if they have the same elements but in different order")
    oneArray.getInners.clear
    oneArray.add(new StringSC)
    oneArray.add(new NumberSC)
    otherArray.getInners.clear
    otherArray.add(new StringSC)
    otherArray.add(new NumberSC)
    assert(one.equals(other), "Non Homogeneous Array Versions are equal if they have the same elements in the same order")
  }
  
  test("Merge Empty Schemas") 
  {
    val one:SchemaInference = new SchemaInference
    val other:SchemaInference = new SchemaInference
    val merged:SchemaInference = new SchemaInference
    
    val oneVersions = new ArrayList[SchemaComponent](10)
    val otherVersions = new ArrayList[SchemaComponent](10)
    val mergedVersions = new ArrayList[SchemaComponent](10)
    
    one.getEntities.put("book", oneVersions)
    other.getEntities.put("book", otherVersions)
    merged.getEntities.put("book", mergedVersions)
    
    assert(merged.equals(one.merge(other)), "Merging two empty Schemas will return an empty Schema")
  }
  
  test("Merge Same-Object Schemas")  
  {
    val one:SchemaInference = new SchemaInference
    val other:SchemaInference = new SchemaInference
    val merged:SchemaInference = new SchemaInference
    
    val oneVersions = new ArrayList[SchemaComponent](10)
    val otherVersions = new ArrayList[SchemaComponent](10)
    val mergedVersions = new ArrayList[SchemaComponent](10)
    
    val oneObject = new ObjectSC()
    oneObject.add(Pair.of("title", new StringSC))
    oneObject.add(Pair.of("price", new NumberSC))
    
    val otherObject = new ObjectSC()
    otherObject.add(Pair.of("title", new StringSC))
    otherObject.add(Pair.of("price", new NumberSC))
    
    val mergedObject = new ObjectSC()
    mergedObject.add(Pair.of("title", new StringSC))
    mergedObject.add(Pair.of("price", new NumberSC))
    
    oneVersions.append(oneObject)
    otherVersions.append(otherObject)
    mergedVersions.append(mergedObject)
    
    one.getEntities.put("book", oneVersions)
    other.getEntities.put("book", otherVersions)
    merged.getEntities.put("book", mergedVersions)
    
    assert(merged.equals(one.merge(other)), "Merging two same object Schemas will return that object Schema")
    
  }
  
  test("Merge Different-Object Schemas")
  {
    val one:SchemaInference = new SchemaInference
    val other:SchemaInference = new SchemaInference
    val merged:SchemaInference = new SchemaInference
    
    val oneVersions = new ArrayList[SchemaComponent](10)
    val otherVersions = new ArrayList[SchemaComponent](10)
    val mergedVersions = new ArrayList[SchemaComponent](10)
    
    val oneObject = new ObjectSC()
    oneObject.add(Pair.of("title", new StringSC))
    oneObject.add(Pair.of("price", new NumberSC))
    
    val otherObject = new ObjectSC()
    otherObject.add(Pair.of("title", new StringSC))
    otherObject.add(Pair.of("price", new StringSC))
    
    val mergedObject1 = new ObjectSC()
    mergedObject1.add(Pair.of("title", new StringSC))
    mergedObject1.add(Pair.of("price", new NumberSC))
    
    val mergedObject2 = new ObjectSC()
    mergedObject2.add(Pair.of("title", new StringSC))
    mergedObject2.add(Pair.of("price", new StringSC))
    
    oneVersions.append(oneObject)
    otherVersions.append(otherObject)
    mergedVersions.append(mergedObject1)
    mergedVersions.append(mergedObject2)
    
    one.getEntities.put("book", oneVersions)
    other.getEntities.put("book", otherVersions)
    merged.getEntities.put("book", mergedVersions)
    
    assert(merged.equals(one.merge(other)), "Merging two different object Schemas will return an Schema with both objects")
  }
  
  test("Merge Homogeneous Array different size Schemas")
  {
    val one:SchemaInference = new SchemaInference
    val other:SchemaInference = new SchemaInference
    val merged:SchemaInference = new SchemaInference
    
    val oneVersions = new ArrayList[SchemaComponent](10)
    val otherVersions = new ArrayList[SchemaComponent](10)
    val mergedVersions = new ArrayList[SchemaComponent](10)
    
    val oneArray = new ArraySC()
    oneArray.add(new StringSC)
    oneArray.add(new StringSC)

    val otherArray = new ArraySC()
    otherArray.add(new StringSC)
    otherArray.add(new StringSC)
    otherArray.add(new StringSC)
    
    val mergedArray = new ArraySC()
    mergedArray.add(new StringSC)
    mergedArray.add(new StringSC)

    
    oneVersions.append(oneArray)
    otherVersions.append(otherArray)
    mergedVersions.append(mergedArray)
    
    one.getEntities.put("array", oneVersions)
    other.getEntities.put("array", otherVersions)
    merged.getEntities.put("array", mergedVersions)
    
    assert(merged.equals(one.merge(other)), "Merging two schemas with same homogeneous arrays but with different size will return a Schema with one homogeneous array")
  }

}
