package es.um.nosql.streaminginference.spark.utils

import java.util.stream.Stream

import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.JavaConversions.seqAsJavaList

import org.apache.commons.lang3.tuple.Pair
import org.eclipse.emf.common.util.EList

import es.um.nosql.streaminginference.NoSQLSchema.Attribute
import es.um.nosql.streaminginference.NoSQLSchema.Entity
import es.um.nosql.streaminginference.NoSQLSchema.EntityVersion
import es.um.nosql.streaminginference.NoSQLSchema.NoSQLSchema
import es.um.nosql.streaminginference.NoSQLSchema.NoSQLSchemaPackage
import es.um.nosql.streaminginference.NoSQLSchema.PrimitiveType
import es.um.nosql.streaminginference.NoSQLSchema.Reference
import es.um.nosql.streaminginference.NoSQLSchema.Tuple
import es.um.nosql.streaminginference.json2dbschema.process.util.ReferenceMatcher
import es.um.nosql.streaminginference.json2dbschema.util.inflector.Inflector


class CrossReferenceMatcher 
{
  private val inflector:Inflector = Inflector.getInstance
  private val factory = NoSQLSchemaPackage.eINSTANCE.getNoSQLSchemaFactory
  private type PairEntity = Pair[String, Entity]
  
  private def buildStream(input: EList[Entity]): Stream[PairEntity] = {
    
    val collection = 
      input
      .filter(entity => entity.getEntityversions.exists(version => version.isRoot))
      .flatMap(entity => 
        List(entity.getName, 
             inflector.pluralize(entity.getName), 
             inflector.singularize(entity.getName))
          .distinct
          .map(name => Pair.of(name, entity))
      )
      
    seqAsJavaList(collection).stream()
  }
    
  private def isHomogeneous(tuple: Tuple): Boolean = {
    val elements = tuple.getElements
    elements.size match {
      case 0 => true
      // Nested tuples won't be homogeneous
      case 1 => !elements.get(0).isInstanceOf[Tuple] 
      case _ => 
        elements.map(elem => elem.getClass).distinct.size == 1 &&
        // Nested tuples won't be homogeneous
        elements.get(0).isInstanceOf[PrimitiveType]
    }
  }
  
  private def generateCandidateReferencePair(attribute: Attribute, entity: Entity): (Attribute, Reference) = 
  {  
    val reference = factory.createReference
    val attType = attribute.getType
    reference.setRefTo(entity)
    reference.setName(attribute.getName)
    if (attType.isInstanceOf[PrimitiveType]) {
      val primitive = attType.asInstanceOf[PrimitiveType]
      reference.setLowerBound(1)
      reference.setUpperBound(1)
      reference.setOriginalType(primitive.getName)
    } else { // Tuple
      val tuple = attType.asInstanceOf[Tuple]
      val elements = tuple.getElements
      val pType = 
        if (elements.size == 0 || !elements.get(0).isInstanceOf[PrimitiveType])
          "Unknown"
        else
          elements.get(0).asInstanceOf[PrimitiveType].getName
      reference.setLowerBound(0)
      reference.setUpperBound(-1)
      reference.setOriginalType(pType)
    }
    (attribute, reference)
  }
  
  private def updateVersionReferences(version: EntityVersion, matcher: ReferenceMatcher[Entity]): Unit =
  {  
    // Filter Attributes from properties
    val attributes = 
      version
        .getProperties
        .filter(prop => prop.isInstanceOf[Attribute])
        .map(_.asInstanceOf[Attribute])
    
    // Get possible entities referenced from attributes
    val entitiesReferenced = attributes.map(attribute => matcher.maybeMatch(attribute.getName))
    // Get attributes paired with their entities
    val candidatesWithEntities = 
      attributes
        .zip(entitiesReferenced)
        .filter { case (attribute, entity) => entity.isPresent()}
        // Non homogeneous tuples are filtered
        .filter { case (attribute, pair) => 
            !attribute.getType.isInstanceOf[Tuple] || 
             isHomogeneous(attribute.getType.asInstanceOf[Tuple])
        }
    
    // Get attributes paired with their references
    val candidatesWithReferences = 
      candidatesWithEntities
        .map { 
          case (attribute, entity) => 
            generateCandidateReferencePair(attribute,entity.get)
        }
    
    val properties = version.getProperties
    // Finally remove attributes from version and insert references instead    
    candidatesWithReferences.foreach {
      case (attribute, reference) => {
        val position = properties.indexOf(attribute)
        properties.set(position, reference)
      }
    }
  }
  
  private def updatePossibleReferences(schema: NoSQLSchema, matcher: ReferenceMatcher[Entity]):Unit = 
  {  
    schema
      .getEntities
      .foreach(entity => 
        entity
          .getEntityversions
          .foreach(
              updateVersionReferences(_, matcher)))
  }
  
  def setCrossReferences(currentState: NoSQLSchema, currentSchema:NoSQLSchema): Unit = 
  {  
    val firstMatcher = new ReferenceMatcher(buildStream(currentState.getEntities))
    val secondMatcher = new ReferenceMatcher(buildStream(currentSchema.getEntities))
    // Cross reference update of state against schema
    updatePossibleReferences(currentState, secondMatcher)
    // Cross reference update of schema against state
    updatePossibleReferences(currentSchema, firstMatcher)
    
  }
}