package es.um.nosql.s13e.evolution.m2m;

import java.util.List;

import es.um.nosql.s13e.NoSQLSchema.EntityType;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.RelationshipType;
import es.um.nosql.s13e.NoSQLSchema.SchemaType;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.evolution.analyzer.DependencyAnalyzer;
import es.um.nosql.s13e.evolution.types.EntitySubtype;

public class NoSQLSchemaToEvolNoSQLSchema
{
  public NoSQLSchema m2m(NoSQLSchema schema, List<DependencyAnalyzer> analyzers)
  {
    for (DependencyAnalyzer analyzer : analyzers)
      for (EntitySubtype subtype : analyzer.getSubtypes())
      {
        String newName = analyzer.getSchemaType().getName() + "_" + analyzer.getDiscriminatorSeeker().getDiscriminatorValues().get(subtype);
        if (analyzer.getSchemaType() instanceof EntityType)
          createEntityType(schema, analyzer.getSchemaType(), newName, ((EntityType)analyzer.getSchemaType()).isRoot(), subtype.getVariations());
        else if (analyzer.getSchemaType() instanceof RelationshipType)
          createRelationshipType(schema, analyzer.getSchemaType(), newName, subtype.getVariations());
      }

    return schema;
  }

  private void createEntityType(NoSQLSchema schema, SchemaType parent, String name, boolean isRoot, List<StructuralVariation> variations)
  {
    EntityType newEntityType = NoSQLSchemaFactory.eINSTANCE.createEntityType();
    newEntityType.setName(name);
    newEntityType.setRoot(isRoot);
    newEntityType.getVariations().addAll(variations);
    newEntityType.getParents().add(parent);
    schema.getEntities().add(newEntityType);
  }

  private void createRelationshipType(NoSQLSchema schema, SchemaType parent, String name, List<StructuralVariation> variations)
  {
    RelationshipType newRelationshipType = NoSQLSchemaFactory.eINSTANCE.createRelationshipType();
    newRelationshipType.setName(name);
    newRelationshipType.getVariations().addAll(variations);
    newRelationshipType.getParents().add(parent);
    schema.getRelationships().add(newRelationshipType);
  }
}
