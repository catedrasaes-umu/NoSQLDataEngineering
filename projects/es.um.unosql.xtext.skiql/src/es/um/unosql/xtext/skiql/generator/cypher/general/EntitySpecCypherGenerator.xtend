package es.um.unosql.xtext.skiql.generator.cypher.general

import es.um.unosql.xtext.skiql.generator.cypher.model.Neo4jQueryPart
import es.um.unosql.xtext.skiql.generator.cypher.model.Neo4jSimpleQuery
import es.um.unosql.xtext.skiql.generator.cypher.model.Neo4jSimpleQuery.Neo4jQueryBuilder
import es.um.unosql.xtext.skiql.generator.cypher.model.NodeMatch
import es.um.unosql.xtext.skiql.generator.cypher.model.ReturnClause
import es.um.unosql.xtext.skiql.metamodel.skiql.CountOperation
import es.um.unosql.xtext.skiql.metamodel.skiql.EntityExpression
import es.um.unosql.xtext.skiql.metamodel.skiql.EntityQuery
import es.um.unosql.xtext.skiql.metamodel.skiql.EntitySpec
import es.um.unosql.xtext.skiql.metamodel.skiql.HavingKeys
import es.um.unosql.xtext.skiql.metamodel.skiql.PropertySelectorSpec
import es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipQuery
import es.um.unosql.xtext.skiql.metamodel.skiql.SkiQLModel

import static es.um.unosql.visualization.neo4j.constants.ConstantsVisualization.*
import es.um.unosql.xtext.skiql.metamodel.skiql.HavingVariations
import es.um.unosql.xtext.skiql.metamodel.skiql.Having
import es.um.unosql.xtext.skiql.metamodel.skiql.HavingType

class EntitySpecCypherGenerator {

	val static String DOUBLE_QUOTE = "\""

	PropertySpecCypherGenerator propertySpecCypherGenerator

	new() {
		this.propertySpecCypherGenerator = new PropertySpecCypherGenerator()
	}

	def Neo4jQueryPart generateClause(EntityQuery entitySpec) {
		generateClause(entitySpec, "", 1)
	}

	def Neo4jQueryPart generateClause(EntityQuery entityQuery, String variableName, int variableNumber) {
		var Neo4jQueryPart nodeMatch = generateNodeMatch(entityQuery, variableName, variableNumber)
		if (entityQuery.entitySpec.variationFilter !== null) {
			if (entityQuery.entitySpec.variationFilter.only) {
				nodeMatch = propertySpecCypherGenerator.onlyCertainPropertiesSearchQuery(
					entityQuery.entitySpec.variationFilter, nodeMatch, entityQuery.entitySpec, variableName, variableNumber);
			} else if (entityQuery.entitySpec.variationFilter.propertySpecs.size > 0) {
				nodeMatch = propertySpecCypherGenerator.allPropertiesSearchQuery(entityQuery.entitySpec.variationFilter, nodeMatch, entityQuery.entitySpec, variableName, variableNumber);
			}  else if (entityQuery.entitySpec.variationFilter.propertySpecs.size() == 0 && entityQuery.eContainer instanceof SkiQLModel) {
				nodeMatch = new NodeMatch(
				'''(v:«VARIATION»«IF !entityQuery.entitySpec.name.equals("*")» {entity: «DOUBLE_QUOTE + entityQuery.entitySpec.name + DOUBLE_QUOTE»}«ENDIF»)<-[:«VARIATION_REF»]-
				(«variableName + variableNumber»:«ENTITY»«IF !entityQuery.entitySpec.name.equals("*")» {name: «DOUBLE_QUOTE + entityQuery.entitySpec.name + DOUBLE_QUOTE»}«ENDIF»)''');
			}
		} else if (entityQuery.having !== null) {
			nodeMatch = generateHaving(entityQuery.having, nodeMatch, variableName, variableNumber)
		}  

		return nodeMatch
	}
	
	def Neo4jQueryPart generateHaving(Having having, Neo4jQueryPart nodeMatch, String variableName, int variableNumber) {
		var HavingType havingType = having.havingType
		
		if (havingType instanceof HavingKeys) {
			if (having.negative) {
				return new Neo4jQueryBuilder().addBody('''MATCH «nodeMatch.toString» WHERE NOT «nodeMatch.toString»-[:«VARIATION_REF»|«SHARED_REF»|«NON_SHARED_REF»|«SPECIFIC_REF»*1..2]->(:«KEY»)''').build
			} else { 
				return new Neo4jQueryBuilder().addBody('''MATCH «nodeMatch.toString» WHERE «nodeMatch.toString»-[:«VARIATION_REF»|«SHARED_REF»|«NON_SHARED_REF»|«SPECIFIC_REF»*1..2]->(:«KEY»)''').build
			}
		} else if (havingType instanceof HavingVariations) {
			if (having.negative) {
				return new Neo4jQueryBuilder().addBody('''
					MATCH «nodeMatch.toString» 
					WHERE size((«variableName + variableNumber»)-[:VARIATION]->(:Variation)) < «havingType.lowerBounds» 
					OR size((«variableName + variableNumber»)-[:VARIATION]->(:Variation)) > «havingType.upperBounds»''')
					.build
			} else { 
				return new Neo4jQueryBuilder().addBody('''
					MATCH «nodeMatch.toString» 
					WHERE size((«variableName + variableNumber»)-[:VARIATION]->(:Variation)) >= «havingType.lowerBounds» 
					AND size((«variableName + variableNumber»)-[:VARIATION]->(:Variation)) <= «havingType.upperBounds»''')
					.build
			}
		}
	}
	
	def Neo4jQueryPart generateClause(EntitySpec entitySpec, String variableName, int variableNumber) {
		val Neo4jQueryPart nodeMatch = generateNodeMatch(entitySpec, variableName, variableNumber)
		if (entitySpec.variationFilter !== null && entitySpec.variationFilter.propertySpecs.size() > 0) {
			return propertySpecCypherGenerator.allPropertiesSearchQuery(entitySpec.variationFilter, nodeMatch,
				entitySpec, variableName, variableNumber);
		}

		return nodeMatch
	}

	def Neo4jQueryPart generateNodeMatch(EntityQuery entityQuery, String variableName, int variableNumber) {
		var Neo4jQueryPart nodeMatch = new NodeMatch('''
			(«variableName + variableNumber»:«ENTITY»
			«IF !entityQuery.entitySpec.name.equals("*") && !entityQuery.entitySpec.name.equals("_")» {name: «DOUBLE_QUOTE + entityQuery.entitySpec.name + DOUBLE_QUOTE»}«ENDIF»)
		''')
		
		if (entityQuery.entitySpec.variationFilter !== null) {
			if (entityQuery.entitySpec.variationFilter.propertySpecs.size() > 0) {
				nodeMatch = propertySpecCypherGenerator.allPropertiesSearchQuery(entityQuery.entitySpec.variationFilter, nodeMatch, entityQuery.entitySpec, variableName, variableNumber);
			} else if (entityQuery.entitySpec.variationFilter.propertySpecs.size() == 0) {
				nodeMatch = new NodeMatch('''(«variableName + variableNumber»:«ENTITY»«IF !entityQuery.entitySpec.name.equals("*")» {name: «DOUBLE_QUOTE + entityQuery.entitySpec.name + DOUBLE_QUOTE»}«ENDIF»)''');
			}
		} else if (entityQuery.entitySpec.variationFilter === null && entityQuery.having === null && entityQuery.operation === null) {
			nodeMatch = new NodeMatch('''
				(«variableName + variableNumber»:«ENTITY»
				«IF !entityQuery.entitySpec.name.contains("*") && !entityQuery.entitySpec.name.equals("_")» {name: «DOUBLE_QUOTE + entityQuery.entitySpec.name + DOUBLE_QUOTE»}«ENDIF»)
				«IF entityQuery.entitySpec.name.contains("*") && !entityQuery.entitySpec.name.equals("*") && !entityQuery.entitySpec.name.equals("_")»
					«IF entityQuery.entitySpec.name.startsWith("*") && entityQuery.entitySpec.name.endsWith("*")»
						WHERE «variableName + variableNumber».name CONTAINS \'«entityQuery.entitySpec.name.substring(1, entityQuery.entitySpec.name.length - 1)»\'
					«ELSEIF !entityQuery.entitySpec.name.startsWith("*") && entityQuery.entitySpec.name.endsWith("*")»
						WHERE «variableName + variableNumber».name STARTS WITH \'«entityQuery.entitySpec.name.substring(0, entityQuery.entitySpec.name.length - 1)»\'
					«ELSEIF entityQuery.entitySpec.name.startsWith("*") && !entityQuery.entitySpec.name.endsWith("*")»
						WHERE «variableName + variableNumber».name ENDS WITH \'«entityQuery.entitySpec.name.substring(1)»\'
					«ENDIF»
					
				«ENDIF»
			''')
		}

		return nodeMatch
	}

	def Neo4jQueryPart generateNodeMatch(EntitySpec entitySpec, String variableName, int variableNumber) {
		var Neo4jQueryPart nodeMatch = new NodeMatch('''(«variableName + variableNumber»:«ENTITY»«IF !entitySpec.name.equals("*") && !entitySpec.name.equals("_")» {name: «DOUBLE_QUOTE + entitySpec.name + DOUBLE_QUOTE»}«ENDIF»)''')

		if (entitySpec.variationFilter !== null) {
			if (entitySpec.variationFilter.propertySpecs.size() > 0) {
				nodeMatch = propertySpecCypherGenerator.allPropertiesSearchQuery(entitySpec.variationFilter, nodeMatch,
					entitySpec, variableName, variableNumber);
			} else if (entitySpec.variationFilter.propertySpecs.size() == 0) {
				if (entitySpec.eContainer instanceof EntityQuery) {
					nodeMatch = new NodeMatch(
						'''(«variableName + variableNumber»:«ENTITY»«IF !entitySpec.name.equals("*")» {name: «DOUBLE_QUOTE + entitySpec.name + DOUBLE_QUOTE»}«ENDIF»)''')
				} else if (entitySpec.eContainer instanceof RelationshipQuery ||
					entitySpec.eContainer instanceof EntityExpression) {
					nodeMatch = new NodeMatch('''(«variableName + variableNumber»:«ENTITY»«IF !entitySpec.name.equals("*")» {name: «DOUBLE_QUOTE + entitySpec.name + DOUBLE_QUOTE»}«ENDIF»)''')
				}
			}
		}

		return nodeMatch
	}

	def Neo4jSimpleQuery generateSharedPropertiesQuery(EntityQuery entityQuery,
		PropertySelectorSpec propertiesSelection, int pathIndex) {
		val variableName = "e"
		val variableNumber = pathIndex
		val Neo4jQueryPart neo4jQueryPart = generateNodeMatch(entityQuery, variableName, variableNumber)

		if (neo4jQueryPart instanceof Neo4jSimpleQuery) {
			neo4jQueryPart.clearReturn()
		}

		var String propertySelectionType = FEATURE
		switch (propertiesSelection.type) {
			case ALL: propertySelectionType = FEATURE
			case ATTRIBUTES: propertySelectionType = ATTRIBUTE
			case REFERENCES: propertySelectionType = REFERENCE
			case AGGREGATIONS: propertySelectionType = AGGREGATION
			case KEYS: propertySelectionType = KEY
		}

		if (neo4jQueryPart instanceof NodeMatch) {
			return new Neo4jQueryBuilder().
				addBody('''MATCH ps«pathIndex» = «neo4jQueryPart.toString()»-[:«SHARED_REF»]->(sh«pathIndex»:«propertySelectionType»)''').
				addReturn("RETURN ps").build()
		} else if (neo4jQueryPart instanceof Neo4jSimpleQuery) {
			val String with = neo4jQueryPart.with
			neo4jQueryPart.clearWith
			
			return new Neo4jQueryBuilder().addWith(with).addBody(neo4jQueryPart.toString()).
				addBody('''MATCH ps«pathIndex» = («variableName + variableNumber»)-[:«SHARED_REF»]->(sh:«propertySelectionType»)''').
				addReturn("RETURN ps").build()
		}
	}

	def Neo4jSimpleQuery generateNonSharedPropertiesQuery(EntityQuery entityQuery,
		PropertySelectorSpec propertiesSelection, int pathIndex) {
		val variableName = "e"
		val variableNumber = pathIndex
		val Neo4jQueryPart neo4jQueryPart = generateNodeMatch(entityQuery, variableName, variableNumber)

		if (neo4jQueryPart instanceof Neo4jSimpleQuery) {
			neo4jQueryPart.clearReturn()
		}

		var String propertySelectionType = FEATURE
		switch (propertiesSelection.type) {
			case ALL: propertySelectionType = FEATURE
			case ATTRIBUTES: propertySelectionType = ATTRIBUTE
			case REFERENCES: propertySelectionType = REFERENCE
			case AGGREGATIONS: propertySelectionType = AGGREGATION
			case KEYS: propertySelectionType = KEY
		}

		if (neo4jQueryPart instanceof NodeMatch) {
			return new Neo4jQueryBuilder().addBody('''
					MATCH ps«pathIndex» = «neo4jQueryPart.toString()»-[:«VARIATION_REF»|«NON_SHARED_REF»*2]->(nsh«pathIndex»:«propertySelectionType»)
			''').addReturn("RETURN ps2").build()
		} else if (neo4jQueryPart instanceof Neo4jSimpleQuery) {
			val String with = neo4jQueryPart.with
			neo4jQueryPart.clearWith
			
			return new Neo4jQueryBuilder().addWith(with).addBody('''
				MATCH ps«pathIndex»1 = («variableName + variableNumber»)-[*1..2]->(a«variableName + variableNumber»:«FEATURE»)
				MATCH ps«pathIndex»2 = (e:«ENTITY»)-->(v)-[:«NON_SHARED_REF»]->(:«propertySelectionType»)
			''').addReturn("RETURN ps" + pathIndex + "2").build()
		}

	}

	def Neo4jSimpleQuery generateSpecificPropertiesQuery(EntityQuery entityQuery,
		PropertySelectorSpec propertiesSelection, int pathIndex) {
		val variableName = "e"
		val variableNumber = pathIndex
		val Neo4jQueryPart neo4jQueryPart = generateNodeMatch(entityQuery, variableName, variableNumber)
		if (neo4jQueryPart instanceof Neo4jSimpleQuery) {
			neo4jQueryPart.clearReturn()
		}

		var String propertySelectionType = FEATURE
		switch (propertiesSelection.type) {
			case ALL: propertySelectionType = FEATURE
			case ATTRIBUTES: propertySelectionType = ATTRIBUTE
			case REFERENCES: propertySelectionType = REFERENCE
			case AGGREGATIONS: propertySelectionType = AGGREGATION
			case KEYS: propertySelectionType = KEY
		}

		if (neo4jQueryPart instanceof NodeMatch) {
			new Neo4jQueryBuilder().addBody('''
					MATCH ps«pathIndex» = «neo4jQueryPart.toString()»-[:«VARIATION_REF»|«SPECIFIC_REF»*2]->(:«propertySelectionType»)
			''').addReturn("RETURN ps" + pathIndex).build()
		} else if (neo4jQueryPart instanceof Neo4jSimpleQuery) {
			val String with = neo4jQueryPart.with
			neo4jQueryPart.clearWith
			
			new Neo4jQueryBuilder().addWith(with).addBody(neo4jQueryPart.toString()).addBody('''
				MATCH (v:«VARIATION»«IF !entityQuery.entitySpec.name.equals("*")» { «ENTITY_ATTRIBUTE»: «DOUBLE_QUOTE + entityQuery.entitySpec.name + DOUBLE_QUOTE»}«ENDIF»)
				-[*1..2]-(a«variableName + variableNumber»:«FEATURE»)
				MATCH ps«pathIndex» = («variableName + variableNumber»)-->(v)-[:«SPECIFIC_REF»]->(:«propertySelectionType»)
			''').addReturn("RETURN ps" + pathIndex).build()
		}
	}

	def Neo4jSimpleQuery generatePropertiesQuery(EntityQuery entitySpec, PropertySelectorSpec propertiesSelection,
		int pathIndex) {
		val variableName = "e"
		val variableNumber = pathIndex
		val Neo4jQueryPart neo4jQueryPart = generateNodeMatch(entitySpec, variableName, variableNumber)

		var String propertySelectionType = FEATURE
		switch (propertiesSelection.type) {
			case ALL: propertySelectionType = FEATURE
			case ATTRIBUTES: propertySelectionType = ATTRIBUTE
			case REFERENCES: propertySelectionType = REFERENCE
			case AGGREGATIONS: propertySelectionType = AGGREGATION
			case KEYS: propertySelectionType = KEY
		}

		if (neo4jQueryPart instanceof NodeMatch) {
			return new Neo4jQueryBuilder().
				addBody('''MATCH ps«pathIndex» = «neo4jQueryPart»-[:«VARIATION_REF»|«SHARED_REF»|«NON_SHARED_REF»|«SPECIFIC_REF»*1..2]->(fea«pathIndex»:«propertySelectionType»)''').
				addReturn("RETURN ps").build()
		} else if (neo4jQueryPart instanceof Neo4jSimpleQuery) {
			neo4jQueryPart.addBody('''
				MATCH p«pathIndex»1 = (ev)-[:«VARIATION_REF»|«SHARED_REF»|«NON_SHARED_REF»|«SPECIFIC_REF»*1]->(a«variableName + variableNumber»:«FEATURE»)  
				MATCH p«pathIndex»2 = (ev)-[:«VARIATION_REF»|«SHARED_REF»|«NON_SHARED_REF»|«SPECIFIC_REF»*1..2]->(f:«propertySelectionType») 
			''')
			neo4jQueryPart.returnClause = new ReturnClause("RETURN *")

			return neo4jQueryPart
		}
	}

	def Neo4jQueryPart generateCountQuery(CountOperation selection, EntityQuery entitySpec) {
		new Neo4jQueryBuilder().
			addBody('''MATCH (e:«ENTITY» {name: "«entitySpec.entitySpec.name»"})-[:«VARIATION_REF»]->(v:«VARIATION»)''').
			addReturn("RETURN v, v.count").build()
	}

}
