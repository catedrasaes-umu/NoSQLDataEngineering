/**
 *
 */
package es.um.nosql.streaminginference.json2dbschema.intermediate.raw;

import java.io.Serializable;

/**
 * @author dsevilla
 *
 */
public abstract class SchemaComponent implements Serializable
{	
	private static final long serialVersionUID = -7399333569307137538L;

	@Override
	public boolean equals(Object other)
	{
		if (this == other)
			return true;
		
		return getClass().getName().equals(other.getClass().getName());
	}
}
