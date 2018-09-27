package es.um.nosql.s13e.util.compare;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.um.nosql.s13e.NoSQLSchema.Classifier;
import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.ReferenceClass;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;

public class CompareClassifier extends Comparator<Classifier>
{
  @Override
  public boolean compare(Classifier c1, Classifier c2)
  {
    if (super.checkNulls(c1, c2))
      return false;

    if (super.checkEquals(c1, c2))
      return true;

    if (c1.getName() == null ^ c2.getName() == null)
      return false;

    if (c1.getName() != null && !c1.getName().equals(c2.getName()))
      return false;

    if (c1.getParents() == null ^ c2.getParents() == null)
      return false;

    if (c1.getParents() != null && c2.getParents() != null)
    {
      if (c1.getParents().size() != c2.getParents().size())
        return false;

      List<Classifier> s2ParentsCopy = new ArrayList<Classifier>(c2.getParents());

      for (Classifier p1 : c1.getParents())
      {
        Optional<Classifier> parentToErase = s2ParentsCopy.stream().filter(p2 ->
        {
          return new CompareClassifier().compare(p1, p2);
        }).findFirst();

        if (parentToErase.isPresent())
          s2ParentsCopy.remove(parentToErase.get());
      }

      if (!s2ParentsCopy.isEmpty())
        return false;
    }

    if (c1 instanceof ReferenceClass && c2 instanceof ReferenceClass
        && !new CompareReferenceClass().compare((ReferenceClass)c1, (ReferenceClass)c2))
        return false;

    if (c1 instanceof EntityClass && c2 instanceof EntityClass
        && !new CompareEntityClass().compare((EntityClass)c1, (EntityClass)c2))
        return false;

    if (c1.getVariations() == null ^ c2.getVariations() == null)
      return false;

    if (c1.getVariations() != null && c2.getVariations() != null)
    {
      if (c1.getVariations().size() != c2.getVariations().size())
        return false;

      List<StructuralVariation> s2VariationsCopy = new ArrayList<StructuralVariation>(c2.getVariations());

      for (StructuralVariation v1 : c1.getVariations())
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
