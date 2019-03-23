package es.um.nosql.s13e.util.compare;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.um.nosql.s13e.NoSQLSchema.SchemaType;
import es.um.nosql.s13e.NoSQLSchema.EntityType;
import es.um.nosql.s13e.NoSQLSchema.RelationshipType;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;

public class CompareSchemaType extends Comparator<SchemaType>
{
  @Override
  public boolean compare(SchemaType c1, SchemaType c2)
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

      List<SchemaType> s2ParentsCopy = new ArrayList<SchemaType>(c2.getParents());

      for (SchemaType p1 : c1.getParents())
      {
        Optional<SchemaType> parentToErase = s2ParentsCopy.stream().filter(p2 ->
        {
          return new CompareSchemaType().compare(p1, p2);
        }).findFirst();

        if (parentToErase.isPresent())
          s2ParentsCopy.remove(parentToErase.get());
      }

      if (!s2ParentsCopy.isEmpty())
        return false;
    }

    if (c1 instanceof RelationshipType && c2 instanceof RelationshipType
        && !new CompareRelationshipType().compare((RelationshipType)c1, (RelationshipType)c2))
        return false;

    if (c1 instanceof EntityType && c2 instanceof EntityType
        && !new CompareEntityType().compare((EntityType)c1, (EntityType)c2))
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
