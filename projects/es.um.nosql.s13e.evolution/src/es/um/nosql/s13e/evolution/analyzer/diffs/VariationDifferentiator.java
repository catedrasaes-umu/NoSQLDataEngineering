package es.um.nosql.s13e.evolution.analyzer.diffs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import es.um.nosql.s13e.NoSQLSchema.Association;
import es.um.nosql.s13e.NoSQLSchema.EntityType;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.util.ModelLoader;
import es.um.nosql.s13e.util.Serializer;
import es.um.nosql.s13e.util.compare.CompareProperty;

public class VariationDifferentiator
{
  private CompareProperty propComparer;

  public static void main(String[] args)
  {
    VariationDifferentiator differentiator = new VariationDifferentiator();
    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema = loader.load(new File("../es.um.nosql.models/stackoverflow/stackoverflow.xmi"), NoSQLSchema.class);

    differentiator.analyze(schema);
  }

  public VariationDifferentiator()
  {
    propComparer = new CompareProperty();
  }

  public void analyze(NoSQLSchema schema)
  {
    EntityType entity = schema.getEntities().stream().filter(e -> e.getName().equals("Votes")).findFirst().get();

    getDifferences(entity.getVariations().get(3), entity.getVariations().get(0));

//    getDifferences(entity.getVariations().get(16), entity.getVariations().get(1));
//    getDifferences(entity.getVariations().get(16), entity.getVariations().get(12));
//    getDifferences(entity.getVariations().get(15), entity.getVariations().get(11));
//    for (int i = 1; i < entity.getVariations().size(); i++)
//      getDifferences(entity.getVariations().get(i - 1), entity.getVariations().get(i));
  }

  private void getDifferences(StructuralVariation var1, StructuralVariation var2)
  {
    List<Property> var1OnlyProps = new ArrayList<Property>();
    List<Property> var2OnlyProps = new ArrayList<Property>();

    var1OnlyProps = var1.getProperties().stream().filter(prop -> var2.getProperties().stream().noneMatch(prop2 -> propComparer.compare(prop, prop2))).collect(Collectors.toList());
    var2OnlyProps = var2.getProperties().stream().filter(prop -> var1.getProperties().stream().noneMatch(prop2 -> propComparer.compare(prop, prop2))).collect(Collectors.toList());

    System.out.println("Change report between " + var1.getVariationId() + " and " + var2.getVariationId());
    var1OnlyProps.forEach(prop -> System.out.println("-   " + serializeShort(prop)));
    var2OnlyProps.forEach(prop -> System.out.println("+   " + serializeShort(prop)));
  }

  private String serializeShort(Property property)
  {
    if (property == null)
      return null;

    String serializedProperty = Serializer.serialize(property);
    serializedProperty = serializedProperty.substring(serializedProperty.indexOf(")") + 2);

    if (property instanceof Association)
      serializedProperty = serializedProperty.substring(0, serializedProperty.indexOf("]") + 1);

    return serializedProperty;
  }
}
