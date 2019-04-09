package es.um.nosql.s13e.evolution.analyzer.outliers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Streams;

import es.um.nosql.s13e.NoSQLSchema.EntityType;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.RelationshipType;
import es.um.nosql.s13e.NoSQLSchema.SchemaType;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.util.compare.CompareSchemaType;

public class OutlierMigrator
{
  private NoSQLSchema schema;

  private NoSQLSchema outlierSchema;

  private Map<SchemaType, Map<StructuralVariation, List<StructuralVariation>>> outliersAlternatives;

  public OutlierMigrator(NoSQLSchema schema, Map<SchemaType, List<StructuralVariation>> outliers)
  {
    this.schema = schema;
    this.outlierSchema = createNoSQLSchemaFromOutliers(schema.getName() + "_outliers", outliers);
    outliersAlternatives = new HashMap<SchemaType, Map<StructuralVariation, List<StructuralVariation>>>();
  }

  private NoSQLSchema createNoSQLSchemaFromOutliers(String newName, Map<SchemaType, List<StructuralVariation>> outliers)
  {
    NoSQLSchema newSchema = NoSQLSchemaFactory.eINSTANCE.createNoSQLSchema();
    newSchema.setName(newName);

    for (SchemaType entity : outliers.keySet())
    {
      SchemaType newType = null;

      if (entity instanceof EntityType)
      {
        newType = NoSQLSchemaFactory.eINSTANCE.createEntityType();
        ((EntityType)newType).setRoot(((EntityType)entity).isRoot());
        newSchema.getEntities().add((EntityType)newType);
      }
      else if (entity instanceof RelationshipType)
      {
        newType = NoSQLSchemaFactory.eINSTANCE.createRelationshipType();
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
      Map<StructuralVariation, List<StructuralVariation>> varCandidates = new HashMap<StructuralVariation, List<StructuralVariation>>();
      outliersAlternatives.put(schType, varCandidates);
      SchemaType altSchType = schTypeStream.filter(schemaType -> schType.getName().equals(schemaType.getName())).findAny().get();
      for (StructuralVariation outlierVar : schType.getVariations())
        varCandidates.put(outlierVar, getClosestVariations(outlierVar, altSchType.getVariations(), numAlternatives));
    }
  }

  private List<StructuralVariation> getClosestVariations(StructuralVariation variation, List<StructuralVariation> candidates, int alternatives)
  {
    List<StructuralVariation> result = new ArrayList<StructuralVariation>();
    int propNumber = variation.getProperties().size();
    int factor = 1;

    if (alternatives <= candidates.size())
      result.addAll(candidates);

    for (int i = 0; i < alternatives; i++)
    {
      
    }

    return result;
  }
}
