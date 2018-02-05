/**
 *
 */
package es.um.nosql.s13e.json2dbschema.intermediate.firsto;

import java.util.Set;

/**
 * @author dsevilla
 *
 */
public interface MultiValued
{
	public abstract Set<? extends Comparable<?>> getValues();
}
