/**
 *
 */
package es.um.nosql.s13e.json2dbschema.intermediate.firsto;

import org.apache.commons.lang3.Range;

import es.um.nosql.s13e.json2dbschema.intermediate.raw.NumberSC;

/**
 * @author dsevilla
 *
 */
public class NumberWithRangeSC extends NumberSC implements Ranged
{
	private Range<Double> range;

	/* (non-Javadoc)
	 * @see es.um.nosql.JSONSchema.Ranged#getRange()
	 */
	@Override
	public Range<Double> getRange() {
		return range;
	}

	/* (non-Javadoc)
	 * @see es.um.nosql.JSONSchema.Ranged#setRange(com.google.common.collect.Range)
	 */
	public void setRange(Range<Double> range) {
		this.range = range;
	}
}
