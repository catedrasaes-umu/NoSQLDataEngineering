package es.um.unosql.xtext.skiql.generator.cypher.general

import es.um.unosql.xtext.skiql.generator.cypher.model.Neo4jSimpleQuery.Neo4jQueryBuilder
import es.um.unosql.xtext.skiql.generator.cypher.model.Neo4jQueryPart

import static es.um.unosql.visualization.neo4j.constants.ConstantsVisualization.*;
import es.um.unosql.xtext.skiql.metamodel.skiql.VariationFilter
import es.um.unosql.xtext.skiql.metamodel.skiql.PrimitiveType
import es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipType
import es.um.unosql.xtext.skiql.metamodel.skiql.EntitySpec
import es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipTypeEnum

class PropertySpecCypherGenerator {
	
	val static String DOUBLE_QUOTE = "\""

	def Neo4jQueryPart onlyCertainPropertiesSearchQuery(VariationFilter variationFilter, Neo4jQueryPart nodeMatch, EntitySpec entityQuery, String variableName, int variableNumber)  {
		val attributeNames = variationFilter.propertySpecs
				.map[p | '''
					«IF p.type === null»
						«DOUBLE_QUOTE»«p.name»«DOUBLE_QUOTE»
					«ELSE»
						«DOUBLE_QUOTE»«p.name»
						«val type = p.type»
						«if(type instanceof PrimitiveType) { 
							if (type.array) ":" + type.type.literal.toLowerCase.toFirstUpper + "[]"
							else ":" + type.type.literal.toLowerCase.toFirstUpper
						} else if(type instanceof RelationshipType) { 
							if (type.relationType.equals(RelationshipTypeEnum.RELATIONSHIP)) {
								if (type.targetEntityName !== null)
									":" + type.targetEntityName 
							} else if (type.targetEntityName !== null) {
								":" + type.relationType + ":" + type.targetEntityName
							} else {
								":" + type.relationType
							}
						}»«DOUBLE_QUOTE»
					«ENDIF»
					'''.toString.replace("\r", "").replace("\n", "").replace("\t", "")
				].toList

		new Neo4jQueryBuilder()
			.addWith('''
				[«FOR attributeName : attributeNames SEPARATOR ","»«attributeName»«ENDFOR»] AS attributeNames«variableName + variableNumber», 
				«attributeNames.length» AS num«variableName + variableNumber» ''')
			.addBody(
				'''
				MATCH (e«variableName + variableNumber»:«ENTITY» {name: "«entityQuery.name»"})-[:«SHARED_REF»|«VARIATION_REF»|«NON_SHARED_REF»|«SPECIFIC_REF»*1..2]->(a«variableName + variableNumber»:«FEATURE») 
					WHERE a«variableName + variableNumber».name IN attributeNames«variableName + variableNumber» 
						OR a«variableName + variableNumber».nameType IN attributeNames«variableName + variableNumber» 
						OR a«variableName + variableNumber».relationNameTypeTarget IN attributeNames«variableName + variableNumber» 
						OR a«variableName + variableNumber».relationNameType IN attributeNames«variableName + variableNumber» 
				WITH collect(a«variableName + variableNumber») AS attrs, num«variableName + variableNumber» 
				WHERE size(attrs) = num«variableName + variableNumber» 
				MATCH p = (e«variableName + variableNumber»:«ENTITY» {name: "«entityQuery.name»"})-[:«SHARED_REF»|«VARIATION_REF»|«NON_SHARED_REF»|«SPECIFIC_REF»*1..2]->(:«FEATURE») 
					WHERE size(attrs) > 0 AND ALL (at IN attrs WHERE (e«variableName + variableNumber»)-[:«SHARED_REF»|«VARIATION_REF»|«NON_SHARED_REF»|«SPECIFIC_REF»*1..2]-(at:«FEATURE»)) 
				'''
			)
			.addReturn("RETURN p")
			.build()
	}
	
	def Neo4jQueryPart allPropertiesSearchQuery(VariationFilter variationFilter, Neo4jQueryPart nodeMatch, EntitySpec entityQuery, String variableName, int variableNumber)  {
		val attributeNames = variationFilter.propertySpecs
					.map[p | '''
						«IF p.type === null»
							«DOUBLE_QUOTE»«p.name»«DOUBLE_QUOTE»
						«ELSE»
							«DOUBLE_QUOTE»«p.name»
							«val type = p.type»
							«if(type instanceof PrimitiveType) { 
								if (type.array) ":" + type.type.literal.toLowerCase.toFirstUpper + "[]"
								else ":" + type.type.literal.toLowerCase.toFirstUpper
							} else if(type instanceof RelationshipType) { 
								if (type.relationType.equals(RelationshipTypeEnum.RELATIONSHIP)) {
									if (type.targetEntityName !== null)
										":" + type.targetEntityName 
								} else if (type.targetEntityName !== null) {
									":" + type.relationType + ":" + type.targetEntityName
								} else {
									":" + type.relationType
								}
							}»«DOUBLE_QUOTE»
						«ENDIF»
						'''.toString.replace("\r", "").replace("\n", "").replace("\t", "")
					].toList
				
		new Neo4jQueryBuilder()
			.addWith('''
				[«FOR attributeName : attributeNames SEPARATOR ","»«attributeName»«ENDFOR»] AS attributeNames«variableName + variableNumber» ''')
			.addBody(
				'''
				MATCH ip«variableName + variableNumber» = 
«««				(v«variableName»«variableNumber»:«VARIATION»«IF !entityQuery.name.equals("*")» {entity: «DOUBLE_QUOTE + entityQuery.name + DOUBLE_QUOTE»}«ENDIF»)<-[:«VARIATION_REF»]-
				(«variableName + variableNumber»«IF !entityQuery.name.equals("*")»:«ENTITY» {name: "«entityQuery.name»"}«ENDIF»)
				-[:«SHARED_REF»|«NON_SHARED_REF»|«SPECIFIC_REF»|«VARIATION_REF»*1..2]->
				(a«variableName + variableNumber»:«FEATURE» «IF !entityQuery.name.equals("*")»{«ENTITY_ATTRIBUTE»: "«entityQuery.name»"}«ENDIF») 
				WHERE a«variableName + variableNumber».name IN attributeNames«variableName + variableNumber» 
				OR a«variableName + variableNumber».nameType IN attributeNames«variableName + variableNumber» 
				OR a«variableName + variableNumber».relationNameTypeTarget IN attributeNames«variableName + variableNumber» 
				OR a«variableName + variableNumber».relationNameType IN attributeNames«variableName + variableNumber» 
				'''
			)
			.addReturn("RETURN ip" + variableName + variableNumber)
			.build()
	}
	
	
}
