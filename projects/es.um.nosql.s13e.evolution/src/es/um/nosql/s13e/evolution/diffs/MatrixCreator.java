package es.um.nosql.s13e.evolution.diffs;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.emf.common.util.ECollections;

import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.util.ModelLoader;
import es.um.nosql.s13e.util.Serializer;
import es.um.nosql.s13e.util.compare.CompareProperty;

public class MatrixCreator
{
  private EntityClass entity;
  private CompareProperty pComparer;
  private Map<Property, List<Integer>> propMatrix;

  public MatrixCreator()
  {
    propMatrix = new HashMap<Property, List<Integer>>();
    pComparer = new CompareProperty();
  }

  public void analyze(NoSQLSchema schema)
  {
    entity = schema.getEntities().stream().filter(e -> e.getName().equals("Posts")).findFirst().get();

    entity.getVariations().forEach(var ->
    {
      var.getProperties().forEach(prop1 ->
      {
        Optional<Property> propKey = propMatrix.keySet().stream().filter(prop2 -> pComparer.compare(prop1, prop2)).findFirst();
        if (propKey.isPresent())
          propMatrix.get(propKey.get()).add(var.getVariationId());
        else
        {
          List<Integer> varList = new ArrayList<Integer>();
          varList.add(var.getVariationId());
          propMatrix.put(prop1, varList);
        }
      });
    });

    ECollections.sort(entity.getVariations(), (var1, var2) -> var1.getCount() > var2.getCount() ? -1 : 1);
  }

  public void printMatrix()
  {
    System.out.print("\t\t\t\t\t\t");

    for (StructuralVariation var: entity.getVariations())
      System.out.print("_" + var.getVariationId() + "  ");
    System.out.println();

    for (Property prop : propMatrix.keySet())
    {
      System.out.print(Serializer.serialize(prop) + "\t\t");

      for (int i = 0; i < entity.getVariations().size(); i++)
      {
        int varId = entity.getVariations().get(i).getVariationId();
        if (propMatrix.get(prop).contains(varId))
          System.out.print("X");
        else
          System.out.print("-");
        System.out.print("   ");
        if (varId >= 10)
          System.out.print(" ");
        if (varId >= 100)
          System.out.print(" ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args)
  {
    MatrixCreator matrix = new MatrixCreator();
    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema = loader.load(new File("../es.um.nosql.models/stackoverflow/stackoverflow.xmi"), NoSQLSchema.class);

    matrix.analyze(schema);
    matrix.printMatrix();
  }
}
