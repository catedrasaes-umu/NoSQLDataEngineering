package es.um.nosql.s13e.json2dbschema.process.util;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;

import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;

public class StructuralVariationSorter
{
  public void sort(EList<StructuralVariation> vars)
  {
    if (vars.stream().anyMatch(var -> var.getFirstTimestamp() != 0))
      sortByFirstTimestamp(vars);
    else if (vars.stream().anyMatch(var -> var.getLastTimestamp() != 0))
      sortByLastTimestamp(vars);
    else if (vars.stream().anyMatch(var -> var.getCount() != 0))
      sortByCount(vars);
    else
      sortByPropertyNumber(vars);
  }

  public void sortByFirstTimestamp(EList<StructuralVariation> vars)
  {
    ECollections.sort(vars, (var1, var2) -> var1.getFirstTimestamp() < var2.getFirstTimestamp() ? -1 : 1);
    renameVariationIds(vars);
  }

  public void sortByLastTimestamp(EList<StructuralVariation> vars)
  {
    ECollections.sort(vars, (var1, var2) -> var1.getLastTimestamp() < var2.getLastTimestamp() ? -1 : -1);
    renameVariationIds(vars);
  }

  public void sortByCount(EList<StructuralVariation> vars)
  {
    ECollections.sort(vars, (var1, var2) -> var1.getCount() > var2.getCount() ? -1 : 1);
    renameVariationIds(vars);
  }

  public void sortByPropertyNumber(EList<StructuralVariation> vars)
  {
    ECollections.sort(vars, (var1, var2) -> var1.getProperties().size() < var2.getProperties().size() ? -1 : 1);
    renameVariationIds(vars);
  }

  private void renameVariationIds(EList<StructuralVariation> vars)
  {
    for (int i = 1; i <= vars.size(); i++)
      vars.get(i - 1).setVariationId(i);
  }
}
