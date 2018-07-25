package es.um.nosql.s13e.json2dbschema.intermediate.raw;

public abstract class SchemaComponent
{	
	@Override
	public boolean equals(Object other)
	{
		return getClass().getName().equals(other.getClass().getName());
	}
}
