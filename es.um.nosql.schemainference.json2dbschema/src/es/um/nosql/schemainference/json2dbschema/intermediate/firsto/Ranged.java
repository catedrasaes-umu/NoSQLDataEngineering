package es.um.nosql.schemainference.json2dbschema.intermediate.firsto;

import com.google.common.collect.Range;

/**
 * @author dsevilla
 *
 */
public interface Ranged
{
	public abstract Range<? extends Comparable<?>> getRange();
}