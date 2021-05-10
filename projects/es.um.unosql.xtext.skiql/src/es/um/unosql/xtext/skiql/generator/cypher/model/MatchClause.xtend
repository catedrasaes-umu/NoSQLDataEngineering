package es.um.unosql.xtext.skiql.generator.cypher.model

class MatchClause {
	
	String matchClause
	
	new (String matchClause) {
		this.matchClause = matchClause
	}
	
	override String toString() {
		return matchClause
	}
	
}