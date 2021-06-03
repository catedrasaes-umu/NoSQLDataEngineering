package es.um.unosql.subtypes.discovery;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import es.um.unosql.uNoSQLSchema.EntityType;
import es.um.unosql.uNoSQLSchema.Feature;
import es.um.unosql.uNoSQLSchema.StructuralVariation;
import es.um.unosql.uNoSQLSchema.UNoSQLSchemaPackage;
import es.um.unosql.uNoSQLSchema.uNoSQLSchema;
import es.um.unosql.utils.ModelLoader;
import es.um.unosql.utils.Serializer;
import es.um.unosql.utils.compare.CompareFeature;

public class VariationDifferentiator
{
  private CompareFeature comparer;

  public static void main(String[] args)
  {
    VariationDifferentiator differentiator = new VariationDifferentiator();
    ModelLoader loader = new ModelLoader(UNoSQLSchemaPackage.eINSTANCE);
    uNoSQLSchema schema = loader.load(new File("../es.um.unosql.models/stackoverflow/stackoverflow.xmi"), uNoSQLSchema.class);

    differentiator.analyze(schema);
  }

  public VariationDifferentiator()
  {
    comparer = new CompareFeature();
  }

  public void analyze(uNoSQLSchema schema)
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
    List<Feature> var1OnlyFeats = new ArrayList<Feature>();
    List<Feature> var2OnlyFeats = new ArrayList<Feature>();

    var1OnlyFeats = var1.getFeatures().stream().filter(feat -> var2.getFeatures().stream().noneMatch(feat2 -> comparer.compare(feat, feat2))).collect(Collectors.toList());
    var2OnlyFeats = var2.getFeatures().stream().filter(feat -> var1.getFeatures().stream().noneMatch(feat2 -> comparer.compare(feat, feat2))).collect(Collectors.toList());

    System.out.println("Change report between " + var1.getVariationId() + " and " + var2.getVariationId());
    var1OnlyFeats.forEach(feat -> System.out.println("-   " + Serializer.serialize(feat)));
    var2OnlyFeats.forEach(feat -> System.out.println("+   " + Serializer.serialize(feat)));
  }
}
