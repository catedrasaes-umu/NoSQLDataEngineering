package es.um.unosql.xtext.skiql.generator.cypher.model

class NodeMatch implements Neo4jQueryPart {
	
	String nodeMatch
	boolean match
	
	new(String body) {
		this.nodeMatch = body
	}
	
	def String getBody() {
		return nodeMatch
	}
	
	def void setBody(String body) {
		this.nodeMatch = body
	}
	
	def boolean getMatch() {
		return match
	}
	
	def void setMatch(boolean match) {
		this.match = match
	}
	
	override String toString() {
		'''«IF match»MATCH «ENDIF»«nodeMatch»'''
	}
	
}
