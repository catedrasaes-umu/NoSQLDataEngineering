package es.um.nosql.s13e.evolution.analyzer.outliers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.um.nosql.s13e.NoSQLSchema.EntityType;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.RelationshipType;
import es.um.nosql.s13e.NoSQLSchema.SchemaType;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;

public class OutlierMigrator
{
  private NoSQLSchema schema;

  private Map<SchemaType, List<StructuralVariation>> outliers;

  private Map<StructuralVariation, List<StructuralVariation>> variationsToMigrate;

  public OutlierMigrator(NoSQLSchema schema, Map<SchemaType, List<StructuralVariation>> outliers)
  {
    this.schema = schema;
    this.outliers = outliers;
    variationsToMigrate = new HashMap<StructuralVariation, List<StructuralVariation>>();
  }

  public NoSQLSchema createNoSQLSchemaFromOutliers(String newName)
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

  public void analyzeAlternativeMigrations(int numAlternatives)
  {
    //TODO: For each variation in "outliers", calculate up to numAlternatives entityVariations alternatives.
  }
}
