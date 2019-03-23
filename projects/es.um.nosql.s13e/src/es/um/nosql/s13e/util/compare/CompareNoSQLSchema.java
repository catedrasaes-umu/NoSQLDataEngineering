package es.um.nosql.s13e.util.compare;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.um.nosql.s13e.NoSQLSchema.EntityType;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.RelationshipType;

public class CompareNoSQLSchema extends Comparator<NoSQLSchema>
{
  @Override
  public boolean compare(NoSQLSchema s1, NoSQLSchema s2)
  {
    if (super.checkNulls(s1, s2))
      return false;

    if (super.checkEquals(s1, s2))
      return true;

    if (s1.getName() == null ^ s2.getName() == null)
      return false;

    if (s1.getName() != null && !s1.getName().equals(s2.getName()))
      return false;

    if (s1.getRelationships() == null ^ s2.getRelationships() == null)
      return false;

    if (s1.getRelationships() != null && s2.getRelationships() != null)
    {
      if (s1.getRelationships().size() != s2.getRelationships().size())
        return false;

      List<RelationshipType> s2RefCopy = new ArrayList<RelationshipType>(s2.getRelationships());

      for (RelationshipType ref1 : s1.getRelationships())
      {
        Optional<RelationshipType> refToErase = s2RefCopy.stream().filter(ref2 ->
        {
          return new CompareSchemaType().compare(ref1, ref2);
        }).findFirst();

        if (refToErase.isPresent())
          s2RefCopy.remove(refToErase.get());
      }

      if (!s2RefCopy.isEmpty())
        return false;
    }

    if (s1.getEntities() == null ^ s2.getEntities() == null)
      return false;

    if (s1.getEntities() != null && s2.getEntities() != null)
    {
      if (s1.getEntities().size() != s2.getEntities().size())
        return false;

      List<EntityType> s2EntityCopy = new ArrayList<EntityType>(s2.getEntities());

      for (EntityType e1 : s1.getEntities())
      {
        Optional<EntityType> entityToErase = s2EntityCopy.stream().filter(e2 ->
        {
          return new CompareSchemaType().compare(e1, e2);
        }).findFirst();

        if (entityToErase.isPresent())
          s2EntityCopy.remove(entityToErase.get());
      }

      if (!s2EntityCopy.isEmpty())
        return false;
    }

    return true;
  }
}
