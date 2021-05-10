package es.um.unosql.xtext.skiql.generator.cypher.model

class ReturnClause {
	
	String returnClause
	
	new (String returnClause) {
		this.returnClause = returnClause
	}
	
	override String toString() {
		return returnClause
	}
	
}