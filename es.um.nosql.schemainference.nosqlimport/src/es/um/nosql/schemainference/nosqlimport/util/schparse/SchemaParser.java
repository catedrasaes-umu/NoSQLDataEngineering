package es.um.nosql.schemainference.nosqlimport.util.schparse;

public class SchemaParser<StateT,StreamT, R>
{
	// 
	private char memory;
	
	private StateT state;
	
	public SchemaParser(StateT state)
	{
		this.state =state;
	}
}
