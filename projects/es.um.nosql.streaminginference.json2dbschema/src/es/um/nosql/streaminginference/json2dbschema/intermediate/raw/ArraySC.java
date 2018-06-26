/**
 *
 */
package es.um.nosql.streaminginference.json2dbschema.intermediate.raw;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author dsevilla
 *
 */
public class ArraySC extends SchemaComponent implements Serializable
{
	private static final long serialVersionUID = -1613686873425362794L;
	
	private List<SchemaComponent> inners;
	private boolean homogeneous;

	// In case of homogeneous arrays
	private int homogeneous_size;
	private int lowerBounds;
	private int upperBounds;

	public ArraySC()
	{
		inners = new ArrayList<SchemaComponent>(20);
		homogeneous = true;
		lowerBounds = 0;
		upperBounds = 0;
	}

	public boolean isHomogeneous() {
		return homogeneous;
	}

	private int incUpperBounds()
	{
		return ++upperBounds;
	}

	public void add(SchemaComponent sc)
	{
		if (isHomogeneous())
		{
			if (inners.isEmpty())
			{
				homogeneous_size++;
				inners.add(sc);
			}
			else
			{
				SchemaComponent firstSc = inners.get(0);
				if (sc.equals(firstSc))
					homogeneous_size++;
				else
				{
					// Convert to non-homogeneous, replicate
					homogeneous = false;
					inners = new ArrayList<SchemaComponent>(20);
					inners.addAll(Collections.nCopies(homogeneous_size, firstSc));
					inners.add(sc);
				}
			}
		}
		else
			inners.add(sc);

		// Finally, increase upperBounds
		upperBounds = incUpperBounds();
	}

	@Override
	public boolean equals(Object other)
	{
		if (this == other) 
			return true;
		
		if (other instanceof ArraySC)
		{
			ArraySC otherA = (ArraySC)other;
			// Empty arrays will be equal to any other array
			if (this.size() == 0 || otherA.size() == 0)
				return true;
			if (this.homogeneous != otherA.homogeneous)
				return false;

			// By ignoring the count we make all homogeneous arrays equivalent if the
			// inner element is the same (checked in the next instruction)
			// Another step is needed to reconcile zero size arrays with other lengths.
			// Also, in this step, lower and upper bounds have to be reconciled.
			//if (this.isHomogeneous() && this.homogeneous_size != otherA.homogeneous_size)
			//	return false;

			return inners.equals(otherA.inners);
		}

		return false;
	}

	public int size()
	{
		// In the case of a homogeneous array, return the number of elements
		if (homogeneous)
			return homogeneous_size;
		else
			return inners.size();
	}

	public final int getLowerBounds() {
		return lowerBounds;
	}

	public final int getUpperBounds() {
		return upperBounds;
	}

	public final void setLowerBounds(int lowerBounds) {
		this.lowerBounds = lowerBounds;
	}

	public final void setUpperBounds(int upperBounds) {
		this.upperBounds = upperBounds;
	}

	public List<SchemaComponent> getInners() {
		return inners;
	}
}
