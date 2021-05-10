package es.um.unosql.xtext.skiql.generator.cypher

import es.um.unosql.xtext.skiql.generator.cypher.general.CreationTimeCypherGenerator
import es.um.unosql.xtext.skiql.generator.cypher.general.EntitySpecCypherGenerator
import es.um.unosql.xtext.skiql.generator.cypher.general.RelationshipSpecCypherGenerator

import es.um.unosql.xtext.skiql.metamodel.skiql.Query
import es.um.unosql.xtext.skiql.metamodel.skiql.EntityQuery
import es.um.unosql.xtext.skiql.metamodel.skiql.VersionHistory
import es.um.unosql.xtext.skiql.metamodel.skiql.PropertyFilter
import es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipQuery
import es.um.unosql.xtext.skiql.metamodel.skiql.CountOperation

import es.um.unosql.xtext.skiql.generator.cypher.model.ReturnClause
import es.um.unosql.xtext.skiql.generator.cypher.model.Neo4jSimpleQuery
import es.um.unosql.xtext.skiql.generator.cypher.model.Neo4jQueryPart
import es.um.unosql.xtext.skiql.generator.cypher.model.NodeMatch
import es.um.unosql.xtext.skiql.generator.cypher.model.Neo4jSimpleQuery.Neo4jQueryBuilder

import es.um.nosql.skiql.selenium.Neo4jBrowser

import org.eclipse.emf.ecore.resource.Resource

import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import es.um.unosql.xtext.skiql.metamodel.skiql.EntitySpec
import es.um.unosql.xtext.skiql.metamodel.skiql.PropertySelectorSpec
import es.um.unosql.xtext.skiql.metamodel.skiql.SkiQLModel

class CypherGenerator {

	RelationshipSpecCypherGenerator relationshipGenerator
	EntitySpecCypherGenerator entityGenerator
	CreationTimeCypherGenerator creationTimeGenerator

	new() {
		this.entityGenerator = new EntitySpecCypherGenerator();
		this.relationshipGenerator = new RelationshipSpecCypherGenerator(this, entityGenerator)
		this.creationTimeGenerator = new CreationTimeCypherGenerator();
	}

	def void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) {
		val SkiQLModel skiqlModel = resource.allContents.findFirst[e|SkiQLModel.isInstance(e)] as SkiQLModel
		val String cypher = generateQuery(skiqlModel).toString().replace("\t", "")

		println(cypher)

		Neo4jBrowser.getInstance().sendQuery(cypher)
	}

	def Neo4jQueryPart generateQuery(SkiQLModel skiqlModel) {
		val Query clause = skiqlModel.query
		if (clause instanceof EntityQuery) {
			if (clause.operation !== null) {
				return createQuery(clause.operation, clause)
			}
		}

		return createQuery(clause)
	}

	def dispatch Neo4jQueryPart createQuery(VersionHistory timeOperator, Query clause) {
		return creationTimeGenerator.generateCreationTimeQuery(timeOperator, clause)
	}

	def dispatch Neo4jQueryPart createQuery(PropertyFilter propertiesSelection, EntityQuery firstEntitySpec) {
		var Neo4jSimpleQuery neo4jQueryPart = null
		var int pathIndex = 0

		for (propertySelector : propertiesSelection.propertySelectorSpecs) {
			if (neo4jQueryPart === null) {
				neo4jQueryPart = getPropertyQuery(firstEntitySpec, propertySelector, pathIndex) as Neo4jSimpleQuery
				neo4jQueryPart.returnClause = new ReturnClause("RETURN *")
			} else {
				val newNeo4jQueryPart = getPropertyQuery(firstEntitySpec, propertySelector, pathIndex)
				neo4jQueryPart.addBody(newNeo4jQueryPart.body)
				neo4jQueryPart.addWith(newNeo4jQueryPart.with)
			}
			pathIndex++
		}

		return neo4jQueryPart
	}

	def Neo4jSimpleQuery getPropertyQuery(EntityQuery firstEntitySpec, PropertySelectorSpec propertySelector,
		int pathIndex) {
		switch (propertySelector.specifity) {
			case ALL: entityGenerator.generatePropertiesQuery(firstEntitySpec, propertySelector, pathIndex)
			case SHARED: entityGenerator.generateSharedPropertiesQuery(firstEntitySpec, propertySelector, pathIndex)
			case NON_SHARED: entityGenerator.generateNonSharedPropertiesQuery(firstEntitySpec, propertySelector,
				pathIndex)
			case SPECIFIC: entityGenerator.generateSpecificPropertiesQuery(firstEntitySpec, propertySelector, pathIndex)
		}
	}

	def dispatch Neo4jQueryPart createQuery(CountOperation countSelection, EntityQuery firstEntitySpec) {
		return entityGenerator.generateCountQuery(countSelection, firstEntitySpec)
	}

	def dispatch Neo4jQueryPart createQuery(EntityQuery entityQuery) {
		var Neo4jQueryPart query = entityGenerator.generateClause(entityQuery, "e", 1)
		if (query instanceof Neo4jSimpleQuery) {
			if (query.startsWithMatch()) {
				query.returnClause = new ReturnClause("RETURN *")
			}
		} else if (query instanceof NodeMatch) {
			query = new Neo4jQueryBuilder().addMatch("MATCH p = " + query.toString()).addReturn("RETURN *").build()
		}

		return query
	}

	def dispatch Neo4jQueryPart createQuery(RelationshipQuery relationshipQuery) {
		return relationshipGenerator.generateClause(relationshipQuery)
	}

	def dispatch Neo4jQueryPart generateClause(EntityQuery entityQuery) {
		entityGenerator.generateClause(entityQuery)
	}

	def dispatch Neo4jQueryPart generateClause(RelationshipQuery relationshipQuery) {
		relationshipGenerator.generateClause(relationshipQuery)
	}

	def dispatch Neo4jQueryPart generateClause(EntityQuery entityQuery, String variableName, int variableNumber) {
		entityGenerator.generateClause(entityQuery, variableName, variableNumber)
	}

	def dispatch Neo4jQueryPart generateClause(EntitySpec entitySpec, String variableName, int variableNumber) {
		entityGenerator.generateClause(entitySpec, variableName, variableNumber)
	}

	def dispatch Neo4jQueryPart generateClause(RelationshipQuery relationshipQuery, String variableName, int variableNumber) {
		relationshipGenerator.generateClause(relationshipQuery, variableName, variableNumber)
	}

}
