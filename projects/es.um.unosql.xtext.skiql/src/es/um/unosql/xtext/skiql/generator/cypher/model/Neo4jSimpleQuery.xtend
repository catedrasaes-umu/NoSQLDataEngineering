package es.um.unosql.xtext.skiql.generator.cypher.model

import java.util.List

class Neo4jSimpleQuery implements Neo4jQueryPart {
	
	List<MatchClause> matchClauses
	String with
	String matchSeparator
	String body
	ReturnClause returnClause
	
	new(String with, List<MatchClause> matchClauses, String matchSeparator, String body, ReturnClause returnClause) {
		this.with = with
		this.matchClauses = matchClauses
		this.matchSeparator = matchSeparator
		this.body = body
		this.returnClause = returnClause
	}
	
	def startsWithMatch() {
		matchClause !== null
	}
	
	def void setWith(String newWith) {
		this.with = newWith
	}
	
	def addWith(String newWith) {
		if (this.with.isEmpty)
			setWith(newWith)
		else
			this.with += ", " + newWith
	}
	
	def String getWith() {
		return with
	}
	
	def List<MatchClause> getMatchClause() {
		return matchClauses
	}
	
	def void addMatchClause(MatchClause matchClause) {
		this.matchClauses.add(matchClause)
	}
	
	def void setMatchSeparator(String separator) {
		this.matchSeparator = separator
	}
	
	def void setMatchClause(List<MatchClause> matchClauses) {
		this.matchClauses = matchClauses
	}
	
	def addBody(String newBody) {
		this.body = body + " " + newBody
	}
	
	def String getBody() {
		return body
	}
	
	def void setBody(String body) {
		this.body = body
	}
	
	def ReturnClause getReturnClause() {
		return returnClause
	}
	
	def void setReturnClause(ReturnClause returnClause) {
		this.returnClause = returnClause
	}
	
	def addReturn(String string) {
		this.returnClause = new ReturnClause(this.returnClause + string)
	}
	
	
	def void clearReturn() {
		this.returnClause = null
	}
	
	def void clearWith() {
		this.with = ""
	}
	
	override String toString() {
		'''«IF !with.isEmpty»WITH «with»«ENDIF»«matchClause?.join(matchSeparator)» «body» «returnClause?.toString()»'''
	}
	
	static class Neo4jQueryBuilder {
		String with
		List<MatchClause> matchClauses
		String matchSeparator
		String body
		ReturnClause returnClause
	
		new () {
			matchClauses = newLinkedList
			matchSeparator = ", "
			body = ""
			with = ""
		}
		
		def Neo4jQueryBuilder addWith(String newWith) {
			if (!newWith.isEmpty) {
				if (this.with.isEmpty) 
					this.with += newWith
				else 
					this.with += ", " + newWith
			}
				
			
			return this
		}
		
		def Neo4jQueryBuilder addMatch(String matchClause) {
			matchClauses.add(new MatchClause(matchClause))
			
			return this
		}
	
		def Neo4jQueryBuilder addBody(String bodyClause) {
			body += bodyClause + " "
			
			return this
		}
		
		def Neo4jQueryBuilder addReturn(String returnClause) {
			returnClause = new ReturnClause(returnClause)
			return this
		}
		
		def Neo4jQueryBuilder setMatchSeparator(String separator) {
			this.matchSeparator = separator
			return this
		}
		
		def Neo4jSimpleQuery build() {
			return new Neo4jSimpleQuery(with, matchClauses, matchSeparator, body, returnClause)
		}
		
	}
}