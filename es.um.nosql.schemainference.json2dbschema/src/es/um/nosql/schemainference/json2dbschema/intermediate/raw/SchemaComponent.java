/**
 *
 */
package es.um.nosql.schemainference.json2dbschema.intermediate.raw;

/**
 * @author dsevilla
 *
 */
public abstract class SchemaComponent
{	
	@Override
	public boolean equals(Object other)
	{
		return getClass().getName().equals(other.getClass().getName());
	}
}
