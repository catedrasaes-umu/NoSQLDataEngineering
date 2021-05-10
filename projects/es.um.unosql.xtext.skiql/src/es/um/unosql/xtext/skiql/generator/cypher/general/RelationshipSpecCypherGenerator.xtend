package es.um.unosql.xtext.skiql.generator.cypher.general

import es.um.unosql.xtext.skiql.generator.cypher.CypherGenerator

import java.util.List

import es.um.unosql.xtext.skiql.generator.cypher.model.Neo4jSimpleQuery.Neo4jQueryBuilder
import es.um.unosql.xtext.skiql.metamodel.skiql.EntitySpec
import es.um.unosql.xtext.skiql.generator.cypher.model.Neo4jQueryPart
import es.um.unosql.xtext.skiql.generator.cypher.model.NodeMatch
import es.um.unosql.xtext.skiql.generator.cypher.model.Neo4jSimpleQuery

import static es.um.unosql.visualization.neo4j.constants.ConstantsVisualization.*;

import es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipQuery
import es.um.unosql.xtext.skiql.metamodel.skiql.ReferenceSpec
import es.um.unosql.xtext.skiql.metamodel.skiql.RelationSpec
import es.um.unosql.xtext.skiql.metamodel.skiql.AggregationSpec
import es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipExpression
import es.um.unosql.xtext.skiql.metamodel.skiql.EntityExpression

class RelationshipSpecCypherGenerator {

	val int RETURN_POSITION = 7

	CypherGenerator cypherGenerator
	EntitySpecCypherGenerator entitySpecCypherGenerator

	new(CypherGenerator cypherGenerator, EntitySpecCypherGenerator entitySpecCypherGenerator) {
		this.cypherGenerator = cypherGenerator;
		this.entitySpecCypherGenerator = entitySpecCypherGenerator;
	}

	def Neo4jQueryPart generateClause(RelationshipQuery relationQuery) {
		val String variableName = "e"
		val int variableId = 1

		return generateClause(relationQuery, variableName, variableId)
	}

	def Neo4jQueryPart generateClause(RelationshipQuery relationQuery, String variableName, int variableId) {
		if (relationQuery.from.variationFilter !== null &&
			relationQuery.from.variationFilter.propertySpecs.size() == 0) {
			return generateAllClause(relationQuery, variableName, variableId)
		} else if (relationQuery.from.variationFilter !== null &&
			relationQuery.from.variationFilter.propertySpecs.size() > 0) {
			return generateAllClause(relationQuery, variableName, variableId)
		} else {
			return generateNotVariationsClause(relationQuery, variableName, variableId)
		}
	}

	def Neo4jQueryPart generateVariationsClause(RelationshipQuery relationQuery, String variableName, int variableId) {
		val Neo4jQueryBuilder neo4jQueryBuilder = new Neo4jQueryBuilder()

		var int pathIndex = 1;
		val String sourceVariable = variableName + variableId
		var String targetVariable = variableName + (variableId + pathIndex)

		val List<String> refsAndAggsWithMatch = newLinkedList
		val List<String> refsAndAggs = newLinkedList

		val List<String> returns = newLinkedList

		for (ref : relationQuery.to.filter[rs|rs.relationSpec instanceof ReferenceSpec]) {
			targetVariable = variableName + (variableId + pathIndex)
			var Neo4jQueryPart neo4jQueryPart
			val targetExpression = ref.targetExpression
			if (targetExpression instanceof RelationshipExpression) {
				neo4jQueryPart = generateVariationsClause(targetExpression.relationshipQuery, targetVariable, pathIndex)
			} else if (targetExpression instanceof EntityExpression) {
				neo4jQueryPart = cypherGenerator.generateClause(targetExpression.entitySpec, targetVariable, pathIndex)
			}

			if (neo4jQueryPart instanceof NodeMatch) {
				refsAndAggsWithMatch += '''
				MATCH p«variableId»«pathIndex» = («sourceVariable»)
				-[:«VARIATION_REF»|«SHARED_REF»|«SPECIFIC_REF»|«NON_SHARED_REF»|«REFS_TO_REF»*2..«IF !ref.indirect»3«ELSE»12«ENDIF»]->
				«neo4jQueryPart.toString»'''
				returns.add("p" + variableId + pathIndex)
			} else if (neo4jQueryPart instanceof Neo4jSimpleQuery) {
				neo4jQueryBuilder.addWith(neo4jQueryPart.with)
				returns.add(neo4jQueryPart.returnClause.toString().substring(RETURN_POSITION))
				neo4jQueryPart.clearReturn()
				neo4jQueryPart.clearWith()
				neo4jQueryBuilder.addMatch(neo4jQueryPart.toString)
				refsAndAggs += '''
				p«sourceVariable»«pathIndex» = («sourceVariable»)
				-[:«VARIATION_REF»|«SHARED_REF»|«SPECIFIC_REF»|«NON_SHARED_REF»|«REFS_TO_REF»*2..«IF !ref.indirect»3«ELSE»12«ENDIF»]->
				(«targetVariable + pathIndex»)'''
				returns.add("p" + sourceVariable + pathIndex)
			}

			pathIndex++
		}

		for (rel : relationQuery.to.filter[rs|rs.relationSpec === null || rs.relationSpec instanceof RelationSpec]) {
			targetVariable = variableName + (variableId + pathIndex)
			var Neo4jQueryPart neo4jQueryPart
			val targetExpression = rel.targetExpression
			if (targetExpression instanceof RelationshipExpression) {
				neo4jQueryPart = generateVariationsClause(targetExpression.relationshipQuery, targetVariable, pathIndex)
			} else if (targetExpression instanceof EntityExpression) {
				neo4jQueryPart = cypherGenerator.generateClause(targetExpression.entitySpec, targetVariable, pathIndex)
			}

			if (neo4jQueryPart instanceof NodeMatch) {
				refsAndAggsWithMatch += '''
				MATCH p«variableId»«pathIndex» = («sourceVariable»)
				-[:«VARIATION_REF»|«SHARED_REF»|«SPECIFIC_REF»|«NON_SHARED_REF»|«REFS_TO_REF»|«AGGREGATES_TO_REF»|«VARIATION_REF»*2..«IF !rel.indirect»4«ELSE»12«ENDIF»]-
				«neo4jQueryPart.toString»'''
				returns.add("p" + variableId + pathIndex)
			} else if (neo4jQueryPart instanceof Neo4jSimpleQuery) {
				neo4jQueryBuilder.addWith(neo4jQueryPart.with)
				returns.add(neo4jQueryPart.returnClause.toString().substring(RETURN_POSITION))
				neo4jQueryPart.clearReturn()
				neo4jQueryPart.clearWith()
				neo4jQueryBuilder.addMatch(neo4jQueryPart.toString)
				refsAndAggs += '''
				p«sourceVariable»«pathIndex» = («sourceVariable»)
				-[:«VARIATION_REF»|«SHARED_REF»|«SPECIFIC_REF»|«NON_SHARED_REF»|«REFS_TO_REF»|«AGGREGATES_TO_REF»|«VARIATION_REF»*2..«IF !rel.indirect»4«ELSE»12«ENDIF»]-
				(«targetVariable + pathIndex»)'''
				returns.add("p" + sourceVariable + pathIndex)
			}

			pathIndex++
		}

		for (agg : relationQuery.to.filter[rs|rs.relationSpec instanceof AggregationSpec]) {
			targetVariable = variableName + (variableId + pathIndex)
			var Neo4jQueryPart neo4jQueryPart
			val targetExpression = agg.targetExpression
			if (targetExpression instanceof RelationshipExpression) {
				neo4jQueryPart = generateVariationsClause(targetExpression.relationshipQuery, targetVariable, pathIndex)
			} else if (targetExpression instanceof EntityExpression) {
				neo4jQueryPart = cypherGenerator.generateClause(targetExpression.entitySpec, targetVariable, pathIndex)
			}

			if (neo4jQueryPart instanceof NodeMatch) {
				refsAndAggsWithMatch += '''
				MATCH p«variableId»«pathIndex» = («sourceVariable»)
				-[:«VARIATION_REF»|«SHARED_REF»|«SPECIFIC_REF»|«NON_SHARED_REF»|«AGGREGATES_TO_REF»*2..«IF !agg.indirect»3«ELSE»12«ENDIF»]->(:«VARIATION»)<-[:«VARIATION_REF»]-
				«neo4jQueryPart.toString»'''
				returns.add("p" + variableId + pathIndex)
			} else if (neo4jQueryPart instanceof Neo4jSimpleQuery) {
				neo4jQueryBuilder.addWith(neo4jQueryPart.with)
				returns.add(neo4jQueryPart.returnClause.toString().substring(RETURN_POSITION))
				neo4jQueryPart.clearReturn()
				neo4jQueryPart.clearWith()
				neo4jQueryBuilder.addMatch(neo4jQueryPart.toString)
				refsAndAggs += '''
				p«sourceVariable»«pathIndex» = («sourceVariable»)
				-[:«VARIATION_REF»|«SHARED_REF»|«SPECIFIC_REF»|«NON_SHARED_REF»|«AGGREGATES_TO_REF»*2..«IF !agg.indirect»3«ELSE»12«ENDIF»]->(:«VARIATION»)<-[:«VARIATION_REF»]-
				(«targetVariable + pathIndex»)'''

				returns.add("p" + sourceVariable + pathIndex)
			}

			pathIndex++
		}

		var Neo4jQueryPart match = entitySpecCypherGenerator.generateNodeMatch(relationQuery.from, variableName,
			variableId)
		if (match instanceof NodeMatch) {
			neo4jQueryBuilder.addMatch("MATCH " + match.toString())
		} else if (match instanceof Neo4jSimpleQuery) {
			neo4jQueryBuilder.addWith(match.getWith)
			neo4jQueryBuilder.addMatch(match.body)
		}

		return neo4jQueryBuilder.addBody(refsAndAggsWithMatch.join(" ")).
			addBody('''«IF !refsAndAggs.isEmpty»MATCH «refsAndAggs.join(", ")»«ENDIF»''').addReturn("RETURN " +
				returns.join(", ")).setMatchSeparator("").build()
	}

	def Neo4jQueryPart generateNotVariationsClause(RelationshipQuery relationQuery, String variableName, int variableId) {
		val Neo4jQueryBuilder neo4jQueryBuilder = new Neo4jQueryBuilder()

		var int pathIndex = 1;
		val String sourceVariable = variableName + variableId
		var String targetVariable = variableName + (variableId + pathIndex)

		val List<String> refsAndAggsWithMatch = newLinkedList
		val List<String> refsAndAggs = newLinkedList

		val List<String> returns = newLinkedList

		for (ref : relationQuery.to.filter[rs|rs.relationSpec instanceof ReferenceSpec]) {
			targetVariable = variableName + (variableId + pathIndex)
			var Neo4jQueryPart neo4jQueryPart
			val targetExpression = ref.targetExpression
			if (targetExpression instanceof RelationshipExpression) {
				neo4jQueryPart = generateClause(targetExpression.relationshipQuery, targetVariable, pathIndex)
			} else if (targetExpression instanceof EntityExpression) {
				neo4jQueryPart = cypherGenerator.generateClause(targetExpression.entitySpec, targetVariable, pathIndex)
			}

			if (neo4jQueryPart instanceof NodeMatch) {
				
				if (targetExpression instanceof EntityExpression) {
					if (targetExpression.entitySpec.name.equals("_")) {
						refsAndAggsWithMatch += '''OPTIONAL MATCH p«variableId»«pathIndex» = («sourceVariable»)-[:«HAS_REFERENCE_TO»«IF ref.indirect»*«ENDIF»]->«neo4jQueryPart.toString»'''
						returns.add("e" + variableId + ", p" + variableId + pathIndex)
					} else if (relationQuery.from.name.equals("_")) {
						refsAndAggsWithMatch += '''MATCH «neo4jQueryPart.toString» OPTIONAL MATCH p«variableId»«pathIndex» = («sourceVariable»)-[:«HAS_REFERENCE_TO»«IF ref.indirect»*«ENDIF»]->«neo4jQueryPart.toString»'''
						returns.add(targetVariable + pathIndex + ", p" + variableId + pathIndex)
					} else {
						refsAndAggsWithMatch += '''MATCH p«variableId»«pathIndex» = («sourceVariable»)-[:«HAS_REFERENCE_TO»«IF ref.indirect»*«ENDIF»]->«neo4jQueryPart.toString»'''
						returns.add("p" + variableId + pathIndex)
					}
				} else {
					refsAndAggsWithMatch += '''OPTIONAL MATCH MATCH p«variableId»«pathIndex» = («sourceVariable»)-[:«HAS_REFERENCE_TO»«IF ref.indirect»*«ENDIF»]->«neo4jQueryPart.toString»'''
						returns.add("e" + variableId + ", p" + variableId + pathIndex)
				}
				
				
			} else if (neo4jQueryPart instanceof Neo4jSimpleQuery) {
				neo4jQueryBuilder.addWith(neo4jQueryPart.with)
				returns.add(neo4jQueryPart.returnClause.toString().substring(RETURN_POSITION))
				neo4jQueryPart.clearReturn()
				neo4jQueryPart.clearWith()
				neo4jQueryBuilder.addMatch(neo4jQueryPart.toString)
				refsAndAggs += '''p«sourceVariable»«pathIndex» = («sourceVariable»)-[:«HAS_REFERENCE_TO»«IF ref.indirect»*«ENDIF»]->(«targetVariable + pathIndex»)'''
				returns.add("p" + sourceVariable + pathIndex)
			}

			pathIndex++
		}

		for (rel : relationQuery.to.filter[rs|rs.relationSpec === null || rs.relationSpec.class == RelationSpec]) {
			targetVariable = variableName + (variableId + pathIndex)
			var Neo4jQueryPart neo4jQueryPart
			val targetExpression = rel.targetExpression
			if (targetExpression instanceof RelationshipExpression) {
				neo4jQueryPart = generateClause(targetExpression.relationshipQuery, targetVariable, pathIndex)
			} else if (targetExpression instanceof EntityExpression) {
				neo4jQueryPart = cypherGenerator.generateClause(targetExpression.entitySpec, targetVariable, pathIndex)
			}

			if (neo4jQueryPart instanceof NodeMatch) {
				if (targetExpression instanceof EntityExpression) {
					if (targetExpression.entitySpec.name.equals("_")) {
						refsAndAggsWithMatch += '''OPTIONAL MATCH p«variableId»«pathIndex» = («sourceVariable»)-[:«HAS_REFERENCE_TO»|«HAS_AGGREGATION_TO»«IF rel.indirect»*«ENDIF»]->«neo4jQueryPart.toString»'''
						returns.add("e" + variableId + ", p" + variableId + pathIndex)
					} else if (relationQuery.from.name.equals("_")) {
						refsAndAggsWithMatch += '''MATCH «neo4jQueryPart.toString» OPTIONAL MATCH p«variableId»«pathIndex» = («sourceVariable»)-[:«HAS_REFERENCE_TO»|«HAS_AGGREGATION_TO»«IF rel.indirect»*«ENDIF»]->«neo4jQueryPart.toString»'''
						returns.add(targetVariable + pathIndex + ", p" + variableId + pathIndex)
					} else {
						refsAndAggsWithMatch += '''MATCH p«variableId»«pathIndex» = («sourceVariable»)-[:«HAS_REFERENCE_TO»|«HAS_AGGREGATION_TO»«IF rel.indirect»*«ENDIF»]->«neo4jQueryPart.toString»'''
						returns.add("p" + variableId + pathIndex)
					}
				} else {
					refsAndAggsWithMatch += '''OPTIONAL MATCH p«variableId»«pathIndex» = («sourceVariable»)-[:«HAS_REFERENCE_TO»|«HAS_AGGREGATION_TO»«IF rel.indirect»*«ENDIF»]->«neo4jQueryPart.toString»'''
						returns.add("e" + variableId + ", p" + variableId + pathIndex)
				}
			} else if (neo4jQueryPart instanceof Neo4jSimpleQuery) {
				neo4jQueryBuilder.addWith(neo4jQueryPart.with)
				returns.add(neo4jQueryPart.returnClause.toString().substring(RETURN_POSITION))
				neo4jQueryPart.clearReturn()
				neo4jQueryPart.clearWith()
				neo4jQueryBuilder.addMatch(neo4jQueryPart.toString)
				refsAndAggs += '''p«sourceVariable»«pathIndex» = («sourceVariable»)-[:«HAS_REFERENCE_TO»|«HAS_AGGREGATION_TO»«IF rel.indirect»*«ENDIF»]->(«targetVariable + pathIndex»)'''
				returns.add("p" + sourceVariable + pathIndex)
			}

			pathIndex++
		}

		for (agg : relationQuery.to.filter[rs|rs.relationSpec instanceof AggregationSpec]) {
			targetVariable = variableName + (variableId + pathIndex)
			var Neo4jQueryPart neo4jQueryPart
			val targetExpression = agg.targetExpression
			if (targetExpression instanceof RelationshipExpression) {
				neo4jQueryPart = generateClause(targetExpression.relationshipQuery, targetVariable, pathIndex)
			} else if (targetExpression instanceof EntityExpression) {
				neo4jQueryPart = cypherGenerator.generateClause(targetExpression.entitySpec, targetVariable, pathIndex)
			}

			if (neo4jQueryPart instanceof NodeMatch) {
				
				if (targetExpression instanceof EntityExpression) {
					if (targetExpression.entitySpec.name.equals("_")) {
						refsAndAggsWithMatch += '''OPTIONAL MATCH p«variableId»«pathIndex» = («sourceVariable»)-[:«HAS_AGGREGATION_TO»«IF agg.indirect»*«ENDIF»]->«neo4jQueryPart.toString»'''
						returns.add("e" + variableId + ", p" + variableId + pathIndex)
					} else if (relationQuery.from.name.equals("_")) {
						refsAndAggsWithMatch += '''MATCH «neo4jQueryPart.toString» OPTIONAL MATCH p«variableId»«pathIndex» = («sourceVariable»)-[:«HAS_AGGREGATION_TO»«IF agg.indirect»*«ENDIF»]->«neo4jQueryPart.toString»'''
						returns.add(targetVariable + pathIndex + ", p" + variableId + pathIndex)
					} else {
						refsAndAggsWithMatch += '''MATCH p«variableId»«pathIndex» = («sourceVariable»)-[:«HAS_AGGREGATION_TO»«IF agg.indirect»*«ENDIF»]->«neo4jQueryPart.toString»'''
						returns.add("p" + variableId + pathIndex)
					}
				} else {
					refsAndAggsWithMatch += '''MATCH p«variableId»«pathIndex» = («sourceVariable»)-[:«HAS_AGGREGATION_TO»«IF agg.indirect»*«ENDIF»]->«neo4jQueryPart.toString»'''
					returns.add("p" + variableId + pathIndex)
				}
				
			} else if (neo4jQueryPart instanceof Neo4jSimpleQuery) {
				neo4jQueryBuilder.addWith(neo4jQueryPart.with)
				returns.add(neo4jQueryPart.returnClause.toString().substring(RETURN_POSITION))
				neo4jQueryPart.clearReturn()
				neo4jQueryPart.clearWith()
				neo4jQueryBuilder.addMatch(neo4jQueryPart.toString)
				refsAndAggs += '''p«sourceVariable»«pathIndex» = («sourceVariable»)-[:«HAS_AGGREGATION_TO»«IF agg.indirect»*«ENDIF»]->(«targetVariable + pathIndex»)'''
				returns.add("p" + sourceVariable + pathIndex)
			}

			pathIndex++
		}

		var Neo4jQueryPart match = entitySpecCypherGenerator.generateNodeMatch(relationQuery.from, variableName, variableId)
		if (match instanceof NodeMatch) {
			neo4jQueryBuilder.addMatch("MATCH " + match.toString())
		} else if (match instanceof Neo4jSimpleQuery) {
			neo4jQueryBuilder.addWith(match.getWith)
			neo4jQueryBuilder.addMatch(match.body)
		}

		return neo4jQueryBuilder.addBody(refsAndAggsWithMatch.join(" ")).
			addBody('''«IF !refsAndAggs.isEmpty»MATCH «refsAndAggs.join(", ")»«ENDIF»''').addReturn("RETURN " +
				returns.join(", ")).setMatchSeparator("").build()
	}

	def Neo4jQueryPart generateSharedPropertyQuery(RelationshipQuery relationQuery) {
		new Neo4jQueryBuilder().addMatch('''MATCH p = «generateClause(relationQuery)»''').addBody('''
			WITH nodes(p) AS nodes 
			UNWIND nodes AS n 
			MATCH p = (n)-[:«SHARED_REF»]->(:«ATTRIBUTE»)
		''').addReturn("RETURN p").build()
	}

	def Neo4jQueryPart generateSpecificPropertyQuery(RelationshipQuery relationQuery) {
		new Neo4jQueryBuilder().addMatch('''MATCH p = «generateClause(relationQuery)»''').addBody('''
			WITH nodes(p) AS nodes 
				UNWIND nodes AS n 
				MATCH p = (n)-[:«VARIATION_REF»|«NON_SHARED_REF»*2..2]->(:«ATTRIBUTE»)
		''').addReturn("RETURN p").build()
	}

	def Neo4jQueryPart generatePropertiesQuery(RelationshipQuery relationQuery) {
		new Neo4jQueryBuilder().addMatch('''MATCH p = «generateClause(relationQuery)»''').addBody('''
			WITH nodes(p) AS nodes 
			UNWIND nodes AS n 
			MATCH p = (n)-[:«VARIATION_REF»|«SHARED_REF»|«NON_SHARED_REF»|«SPECIFIC_REF»*1..2]->(:«ATTRIBUTE»)
		''').addReturn("RETURN p").build()
	}

	def Neo4jQueryPart generateVariationsQuery(RelationshipQuery relationQuery) {
		new Neo4jQueryBuilder().addMatch('''MATCH p = «generateClause(relationQuery)»''').addBody('''
			WITH nodes(p) AS nodes 
			UNWIND nodes AS n 
			MATCH p = (n)-[:«VARIATION_REF»|«NON_SHARED_REF»*1..2]->(:«ATTRIBUTE») 
		''').addReturn("RETURN p").build()
	}

	def Neo4jSimpleQuery generateAllClause(RelationshipQuery relationQuery) {
		generateAllClause(relationQuery, "e", 1)
	}

	def Neo4jSimpleQuery generateAllClause(RelationshipQuery relationQuery, String variableName, int variableNumber) {
		val Neo4jQueryBuilder queryBuilder = new Neo4jQueryBuilder()

		val List<String> returns = newLinkedList

		val Neo4jQueryPart neo4jQuery = generateTarget(relationQuery.from, variableName, variableNumber)
		if (neo4jQuery instanceof Neo4jSimpleQuery) {
			returns.add(neo4jQuery.returnClause.toString().substring(RETURN_POSITION))
			neo4jQuery.clearReturn()
			queryBuilder.addWith(neo4jQuery.with)
			neo4jQuery.clearWith()
		} else if (neo4jQuery instanceof NodeMatch) {
			queryBuilder.addMatch("MATCH p" + variableName + variableNumber + " = ")
		}
		queryBuilder.addBody('''«neo4jQuery.toString()»''')

		var int pathIndex = 1;
		for (ref : relationQuery.to.filter[rs|rs.relationSpec instanceof ReferenceSpec]) {
			val targetExpression = ref.targetExpression
			var Neo4jQueryPart innerNeo4jQuery = null

			if (targetExpression instanceof EntityExpression) {
				innerNeo4jQuery = generateTarget(targetExpression.entitySpec, variableName, variableNumber + pathIndex)
			} else if (targetExpression instanceof RelationshipExpression) {
				innerNeo4jQuery = generateClause(targetExpression.relationshipQuery, variableName, variableNumber + pathIndex)
			}

			if (innerNeo4jQuery instanceof Neo4jSimpleQuery) {
				returns.add(innerNeo4jQuery.returnClause.toString().substring(RETURN_POSITION))
				innerNeo4jQuery.clearReturn()
				queryBuilder.addWith(innerNeo4jQuery.with)
				innerNeo4jQuery.clearWith()
			} else if (innerNeo4jQuery instanceof NodeMatch) {
				innerNeo4jQuery.body = "MATCH p" + variableName + variableNumber + pathIndex + " = " +
					innerNeo4jQuery.body
			}

			queryBuilder.addBody('''
				«innerNeo4jQuery.toString()»
				
				MATCH innerPath«variableName + variableNumber + pathIndex» = («variableName + variableNumber»)
				-[:«VARIATION_REF»|«SHARED_REF»|«NON_SHARED_REF»|«SPECIFIC_REF»|«REFS_TO_REF»*2..«IF !ref.indirect»3«ELSE»12«ENDIF»]->
				(«variableName + (variableNumber + pathIndex)»)
			''')

			returns.add("innerPath" + variableName + variableNumber + pathIndex)
			pathIndex++
		}

		for (rel : relationQuery.to.filter[rs|rs.relationSpec === null || rs.relationSpec.class == RelationSpec]) {
			val targetExpression = rel.targetExpression
			var Neo4jQueryPart innerNeo4jQuery = null

			if (targetExpression instanceof EntityExpression) {
				innerNeo4jQuery = generateTarget(targetExpression.entitySpec, variableName, variableNumber + pathIndex)
			} else if (targetExpression instanceof RelationshipExpression) {
				innerNeo4jQuery = generateClause(targetExpression.relationshipQuery, variableName, variableNumber + pathIndex)
			}

			if (innerNeo4jQuery instanceof Neo4jSimpleQuery) {
				returns.add(innerNeo4jQuery.returnClause.toString().substring(RETURN_POSITION))
				innerNeo4jQuery.clearReturn()
				queryBuilder.addWith(innerNeo4jQuery.with)
				innerNeo4jQuery.clearWith()
			} else if (innerNeo4jQuery instanceof NodeMatch) {
				innerNeo4jQuery.body = "MATCH p" + variableName + variableNumber + pathIndex + " = " +
					innerNeo4jQuery.body
			}

			queryBuilder.addBody('''
				«innerNeo4jQuery.toString()»
				
				OPTIONAL MATCH innerPath«variableName + "1" + variableNumber + pathIndex» = («variableName + variableNumber»)
					-[:«VARIATION_REF»|«SHARED_REF»|«NON_SHARED_REF»|«SPECIFIC_REF»|«REFS_TO_REF»*2..«IF !rel.indirect»3«ELSE»12«ENDIF»]->
					(«variableName + (variableNumber + pathIndex)»)
				OPTIONAL MATCH innerPath«variableName + "2" + variableNumber + pathIndex» = («variableName + variableNumber»)
					-[:«VARIATION_REF»|«SHARED_REF»|«NON_SHARED_REF»|«SPECIFIC_REF»|«AGGREGATES_TO_REF»*2..«IF !rel.indirect»3«ELSE»12«ENDIF»]->(:«VARIATION»)<-[:«VARIATION_REF»]-
					(«variableName + (variableNumber + pathIndex)»)
					
			''')

			returns.add("innerPath" + variableName + "1" + variableNumber + pathIndex)
			returns.add("innerPath" + variableName + "2" + variableNumber + pathIndex)
			pathIndex++
		}

		for (agg : relationQuery.to.filter[rs|rs.relationSpec instanceof AggregationSpec]) {
			val targetExpression = agg.targetExpression
			var Neo4jQueryPart innerNeo4jQuery = null

			if (targetExpression instanceof EntityExpression) {
				innerNeo4jQuery = generateTarget(targetExpression.entitySpec, variableName, variableNumber + pathIndex)
			} else if (targetExpression instanceof RelationshipExpression) {
				innerNeo4jQuery = generateClause(targetExpression.relationshipQuery, variableName, variableNumber + pathIndex)
			}

			if (innerNeo4jQuery instanceof Neo4jSimpleQuery) {
				returns.add(innerNeo4jQuery.returnClause.toString().substring(RETURN_POSITION))
				innerNeo4jQuery.clearReturn()
				queryBuilder.addWith(innerNeo4jQuery.with)
				innerNeo4jQuery.clearWith()
			} else if (innerNeo4jQuery instanceof NodeMatch) {
				innerNeo4jQuery.body = "MATCH p" + variableName + variableNumber + pathIndex + " = " + innerNeo4jQuery.body
			}

			queryBuilder.addBody('''
				«innerNeo4jQuery.toString()»
				
				MATCH innerPath«variableName + variableNumber + pathIndex» = («variableName + variableNumber»)
				-[:«VARIATION_REF»|«SHARED_REF»|«NON_SHARED_REF»|«SPECIFIC_REF»|«AGGREGATES_TO_REF»*2..«IF !agg.indirect»3«ELSE»12«ENDIF»]->(:«VARIATION»)<-[:«VARIATION_REF»]-
				(«variableName + (variableNumber + pathIndex)»)
			''')

			returns.add("innerPath" + variableName + variableNumber + pathIndex)
			pathIndex++
		}

		queryBuilder.addReturn("RETURN " + returns.join(", "))

		return queryBuilder.build()
	}

	def dispatch Neo4jQueryPart generateTarget(EntitySpec entitySpec, String variableName, int variableNumber) {
		return entitySpecCypherGenerator.generateNodeMatch(entitySpec, variableName, variableNumber)
	}

	def dispatch Neo4jQueryPart generateTarget(RelationshipQuery relationQuery, String variableName,
		int variableNumber) {
		return generateAllClause(relationQuery, variableName, variableNumber)
	}

}
