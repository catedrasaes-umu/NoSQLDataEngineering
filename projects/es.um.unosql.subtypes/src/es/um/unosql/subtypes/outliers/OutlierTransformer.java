package es.um.unosql.subtypes.outliers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Streams;

import es.um.unosql.uNoSQLSchema.EntityType;
import es.um.unosql.uNoSQLSchema.Feature;
import es.um.unosql.uNoSQLSchema.RelationshipType;
import es.um.unosql.uNoSQLSchema.SchemaType;
import es.um.unosql.uNoSQLSchema.StructuralVariation;
import es.um.unosql.uNoSQLSchema.UNoSQLSchemaFactory;
import es.um.unosql.uNoSQLSchema.uNoSQLSchema;
import es.um.unosql.utils.compare.CompareFeature;

public class OutlierTransformer
{
  private uNoSQLSchema filteredSchema;

  private uNoSQLSchema outlierSchema;

  private Map<SchemaType, Map<StructuralVariation, List<StructuralVariation>>> outliersAlternatives;

  public OutlierTransformer(uNoSQLSchema schema, Map<SchemaType, List<StructuralVariation>> outliers)
  {
    this(schema, outliers, schema.getName() + "_outliers");
  }

  public OutlierTransformer(uNoSQLSchema schema, Map<SchemaType, List<StructuralVariation>> outliers, String outlierSchemaName)
  {
    this.filteredSchema = schema;
    this.outlierSchema = createUNoSQLSchemaFromOutliers(outlierSchemaName, outliers);
    outliersAlternatives = new HashMap<SchemaType, Map<StructuralVariation, List<StructuralVariation>>>();
  }

  public uNoSQLSchema getFilteredSchema()
  {
    return filteredSchema;
  }

  public uNoSQLSchema getOutlierSchema()
  {
    return outlierSchema;
  }

  public Map<SchemaType, Map<StructuralVariation, List<StructuralVariation>>> getOutliersAlternatives()
  {
    return outliersAlternatives;
  }

  private uNoSQLSchema createUNoSQLSchemaFromOutliers(String newName, Map<SchemaType, List<StructuralVariation>> outliers)
  {
    uNoSQLSchema newSchema = UNoSQLSchemaFactory.eINSTANCE.createuNoSQLSchema();
    newSchema.setName(newName);

    for (SchemaType entity : outliers.keySet())
    {
      SchemaType newType = null;

      if (entity instanceof EntityType)
      {
        newType = UNoSQLSchemaFactory.eINSTANCE.createEntityType();
        ((EntityType)newType).setRoot(((EntityType)entity).isRoot());
        newSchema.getEntities().add((EntityType)newType);
      }
      else if (entity instanceof RelationshipType)
      {
        newType = UNoSQLSchemaFactory.eINSTANCE.createRelationshipType();
        newSchema.getRelationships().add((RelationshipType)newType);
      }

      newType.setName(entity.getName());
      newType.getParents().addAll(entity.getParents());
      newType.getVariations().addAll(outliers.get(entity));
    }

    return newSchema;
  }

  public void analyzeAlternativeVariations(int numAlternatives)
  {
    Stream<SchemaType> schTypeStream = Streams.concat(outlierSchema.getEntities().stream(), outlierSchema.getRelationships().stream());

    for (SchemaType schType : schTypeStream.collect(Collectors.toList()))
    {
      if (schType.getVariations().isEmpty())
        continue;

      Map<StructuralVariation, List<StructuralVariation>> varCandidates = new HashMap<StructuralVariation, List<StructuralVariation>>();
      outliersAlternatives.put(schType, varCandidates);
      SchemaType altSchType = Streams.concat(filteredSchema.getEntities().stream(), filteredSchema.getRelationships().stream())
        .filter(schemaType -> schType.getName().equals(schemaType.getName())).findAny().get();

      for (StructuralVariation outlierVar : schType.getVariations())
        varCandidates.put(outlierVar, getClosestVariations(outlierVar, altSchType.getVariations(), numAlternatives));
    }
  }

  private List<StructuralVariation> getClosestVariations(StructuralVariation variation, List<StructuralVariation> candidates, int alternatives)
  {
    List<StructuralVariation> result = new ArrayList<StructuralVariation>();
    List<StructuralVariation> auxCandidates = new ArrayList<StructuralVariation>(candidates);
    // Option 1
    // Sort candidates by initialTimestamp: Most old variations will be the preferred ones.
    // Option 2
    // Sort candidates by count: Most popular variations will be the preferred ones.
    // auxCandidates.sort((var1, var2) -> var1.getCount() > var2.getCount() ? -1 : 1);
    // Option 3
    // Sort candidates by features number: Less new features variations will be the preferred ones.
    auxCandidates.sort((var1, var2) -> Integer.compare(var1.getFeatures().size(), var2.getFeatures().size()));

    if (alternatives == 0)
      return result;

    if (alternatives > auxCandidates.size())
    {
      result.addAll(candidates);
      return result;
    }

    for (int i = 0; i < alternatives; i++)
      result.add(getClosestVariation(variation, auxCandidates));

    return result;
  }

  private StructuralVariation getClosestVariation(StructuralVariation variation, List<StructuralVariation> candidates)
  {
    int factor = -1;
    StructuralVariation bestCandidate = null;

    for (StructuralVariation candidate : candidates)
    {
      int newFactor = getDifferenceFactor(variation, candidate);
      if (newFactor > factor)
      {
        factor = newFactor;
        bestCandidate = candidate;
      }
    }

    candidates.remove(bestCandidate);

    return bestCandidate;
  }

  private int getDifferenceFactor(StructuralVariation var1, StructuralVariation var2)
  {
    CompareFeature comparer = new CompareFeature();
    int factor = 0;

    for (Feature feat1 : var1.getFeatures())
      if (var2.getFeatures().stream().anyMatch(feat2 -> comparer.compare(feat1, feat2)))
        factor++;
    // TODO: Hmmm...is this really what we want...?
    return factor;// / var1.getFeatures().size();
  }
}
