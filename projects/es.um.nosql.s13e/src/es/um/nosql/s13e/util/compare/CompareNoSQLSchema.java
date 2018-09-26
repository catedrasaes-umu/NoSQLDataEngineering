package es.um.nosql.s13e.util.compare;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.ReferenceClass;

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

    if (s1.getRefClasses() == null ^ s2.getRefClasses() == null)
      return false;

    if (s1.getRefClasses() != null && s2.getRefClasses() != null)
    {
      if (s1.getRefClasses().size() != s2.getRefClasses().size())
        return false;

      List<ReferenceClass> s2RefCopy = new ArrayList<ReferenceClass>(s2.getRefClasses());

      for (ReferenceClass ref1 : s1.getRefClasses())
      {
        Optional<ReferenceClass> refToErase = s2RefCopy.stream().filter(ref2 ->
        {
          return new CompareClassifier().compare(ref1, ref2);
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

      List<EntityClass> s2EntityCopy = new ArrayList<EntityClass>(s2.getEntities());

      for (EntityClass e1 : s1.getEntities())
      {
        Optional<EntityClass> entityToErase = s2EntityCopy.stream().filter(e2 ->
        {
          return new CompareClassifier().compare(e1, e2);
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
