package es.um.unosql.xtext.skiql.generator.cypher.general

import es.um.unosql.xtext.skiql.metamodel.skiql.After
import es.um.unosql.xtext.skiql.metamodel.skiql.All
import es.um.unosql.xtext.skiql.metamodel.skiql.Before
import es.um.unosql.xtext.skiql.metamodel.skiql.Between
import es.um.unosql.xtext.skiql.metamodel.skiql.Query
import es.um.unosql.xtext.skiql.metamodel.skiql.DateTime

import java.time.LocalDateTime
import java.time.ZoneOffset
import es.um.unosql.xtext.skiql.metamodel.skiql.VersionHistory
import es.um.unosql.xtext.skiql.generator.cypher.model.Neo4jSimpleQuery.Neo4jQueryBuilder
import es.um.unosql.xtext.skiql.generator.cypher.model.Neo4jQueryPart
import es.um.unosql.xtext.skiql.metamodel.skiql.EntityQuery

class CreationTimeCypherGenerator {

	def Neo4jQueryPart generateCreationTimeQuery(VersionHistory operator, Query clause) {
		val entitySpec = clause as EntityQuery
		
		return creationTime(entitySpec.entitySpec.name, operator)
	}
	
	def dispatch Neo4jQueryPart creationTime(String name, Before before) {
		val date = createDateObject(before.dateTime)
		
		return new Neo4jQueryBuilder()
			.addMatch("MATCH ")
			.addBody('''(v:Variation) WHERE v.timestamp <= «date.toEpochSecond(ZoneOffset.UTC)»''')
			.addReturn("RETURN v")
			.build()
	}
	
	def dispatch Neo4jQueryPart creationTime(String name, After after) {
		val date = createDateObject(after.dateTime)
		
		
		return new Neo4jQueryBuilder()
			.addMatch("MATCH ")
			.addBody('''(v:Variation) WHERE v.timestamp >= «date.toEpochSecond(ZoneOffset.UTC)»''')
			.addReturn("RETURN v")
			.build()
	}
	
	def dispatch Neo4jQueryPart creationTime(String name, Between between) {
		val firstDateTime = createDateObject(between.afterDateTime)
		val secondDateTime = createDateObject(between.beforeDateTime)
		
		
		return new Neo4jQueryBuilder()
			.addMatch("MATCH ")
			.addBody('''(v:Variation) WHERE v.timestamp >= «firstDateTime.toEpochSecond(ZoneOffset.UTC)» AND v.timestamp <= «secondDateTime.toEpochSecond(ZoneOffset.UTC)»''')
			.addReturn("RETURN v")
			.build()
	}
	
	def dispatch Neo4jQueryPart creationTime(String name, All all) {
		return new Neo4jQueryBuilder()
			.addMatch("MATCH ")
			.addBody('''p = (:Variation «IF name != '*'»{entity: "«name»"}«ENDIF»)-[:NEXT_TIME_VARIATION]->(:Variation)''')
			.addReturn("RETURN p")
			.build()
	}
		
	
	def createDateObject(DateTime dateTime) {
		LocalDateTime.of(dateTime.year, dateTime.month, dateTime.day, dateTime.hour, dateTime.minutes, dateTime.seconds);
	}
	
}
