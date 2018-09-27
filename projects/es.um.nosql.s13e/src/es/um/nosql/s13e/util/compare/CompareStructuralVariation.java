package es.um.nosql.s13e.util.compare;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;

public class CompareStructuralVariation extends Comparator<StructuralVariation>
{
  @Override
  public boolean compare(StructuralVariation v1, StructuralVariation v2)
  {
    if (super.checkNulls(v1, v2))
      return false;

    if (super.checkEquals(v1, v2))
      return true;

    // Please note we do not compare variationId, count nor timestamp.

    if (v1.getProperties() == null ^ v2.getProperties() == null)
      return false;

    if (v1.getProperties() != null && v2.getProperties() != null)
    {
      if (v1.getProperties().size() != v2.getProperties().size())
        return false;

      List<Property> s2PropertiesCopy = new ArrayList<Property>(v2.getProperties());

      for (Property p1 : v1.getProperties())
      {
        Optional<Property> propToErase = s2PropertiesCopy.stream().filter(p2 ->
        {
          return new CompareProperty().compare(p1, p2);
        }).findFirst();

        if (propToErase.isPresent())
          s2PropertiesCopy.remove(propToErase.get());
      }

      if (!s2PropertiesCopy.isEmpty())
        return false;
    }

    return true;
  }
}
