package es.um.unosql.subtypes.m2m;

import java.util.List;

import es.um.unosql.subtypes.discovery.DependencyAnalyzer;
import es.um.unosql.subtypes.util.types.EntitySubtype;
import es.um.unosql.uNoSQLSchema.EntityType;
import es.um.unosql.uNoSQLSchema.RelationshipType;
import es.um.unosql.uNoSQLSchema.SchemaType;
import es.um.unosql.uNoSQLSchema.StructuralFeature;
import es.um.unosql.uNoSQLSchema.StructuralVariation;
import es.um.unosql.uNoSQLSchema.uNoSQLSchema;
import es.um.unosql.utils.UNoSQLFactory;
import es.um.unosql.utils.compare.CompareFeature;

public class UNoSQL2SubtypeUNoSQL
{
    private UNoSQLFactory factory = new UNoSQLFactory();
    private CompareFeature comparer = new CompareFeature();

    public uNoSQLSchema m2m(uNoSQLSchema schema, List<DependencyAnalyzer> analyzers)
    {
        for (DependencyAnalyzer analyzer : analyzers)
            m2m(schema, analyzer);

        return schema;
    }

    public uNoSQLSchema m2m(uNoSQLSchema schema, DependencyAnalyzer analyzer)
    {
        for (EntitySubtype subtype : analyzer.getSubtypes())
        {
            String newName = analyzer.getSchemaType().getName() + "_"
                    + analyzer.getDiscriminatorSeeker().getDiscriminatorValues().get(subtype);

            if (analyzer.getSchemaType() instanceof EntityType)
                createEntityType(schema, analyzer.getSchemaType(), newName,
                        ((EntityType) analyzer.getSchemaType()).isRoot(), subtype.getVariations());

            else if (analyzer.getSchemaType() instanceof RelationshipType)
                createRelationshipType(schema, analyzer.getSchemaType(), newName, subtype.getVariations());
        }

        return schema;
    }

    private void createEntityType(uNoSQLSchema schema, SchemaType parent, String name, boolean isRoot,
            List<StructuralVariation> variations)
    {
        EntityType newEntityType = factory.createEntityType(name, isRoot);
        newEntityType.getVariations().addAll(variations);
        newEntityType.getParents().add(parent);

        // For each optional structural feature, if all other variations do contain an
        // equal feature, then this feature is no longer optional.
        for (StructuralVariation v : variations)
            for (StructuralFeature f : v.getStructuralFeatures())
                if (f.isOptional() && variations.stream().filter(v2 -> v != v2)
                        .allMatch(v2 -> v2.getFeatures().stream().anyMatch(f2 -> comparer.compare(f, f2))))
                    f.setOptional(false);

        schema.getEntities().add(newEntityType);
    }

    private void createRelationshipType(uNoSQLSchema schema, SchemaType parent, String name,
            List<StructuralVariation> variations)
    {
        RelationshipType newRelationshipType = factory.createRelationshipType(name);
        newRelationshipType.getVariations().addAll(variations);
        newRelationshipType.getParents().add(parent);

        // For each optional structural feature, if all other variations do contain an
        // equal feature, then this feature is no longer optional.
        for (StructuralVariation v : variations)
            for (StructuralFeature f : v.getStructuralFeatures())
                if (variations.stream().filter(v2 -> v != v2)
                        .allMatch(v2 -> v2.getFeatures().stream().anyMatch(f2 -> comparer.compare(f, f2))))
                    f.setOptional(false);

        schema.getRelationships().add(newRelationshipType);
    }
}
