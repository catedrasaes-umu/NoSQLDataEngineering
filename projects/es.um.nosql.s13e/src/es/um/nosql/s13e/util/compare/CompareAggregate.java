package es.um.nosql.s13e.util.compare;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;

public class CompareAggregate extends Comparator<Aggregate>
{
  @Override
  public boolean compare(Aggregate a1, Aggregate a2)
  {
    if (super.checkNulls(a1, a2))
      return false;

    if (super.checkEquals(a1, a2))
      return true;

    if (a1.getAggregates() == null ^ a2.getAggregates() == null)
      return false;

    if (a1.getAggregates() != null && a2.getAggregates() != null)
    {
      if (a1.getAggregates().size() != a2.getAggregates().size())
        return false;

      List<StructuralVariation> s2VariationsCopy = new ArrayList<StructuralVariation>(a2.getAggregates());

      for (StructuralVariation v1 : a1.getAggregates())
      {
        Optional<StructuralVariation> variationToErase = s2VariationsCopy.stream().filter(v2 ->
        {
          return new CompareStructuralVariation().compare(v1, v2);
        }).findFirst();

        if (variationToErase.isPresent())
          s2VariationsCopy.remove(variationToErase.get());
      }

      if (!s2VariationsCopy.isEmpty())
        return false;
    }

    return true;
  }
}
