package es.um.unosql.subtypes.discovery.dependencies;

import java.util.List;
import java.util.stream.Collectors;

import es.um.unosql.subtypes.util.types.EntitySubtype;
import es.um.unosql.subtypes.util.types.changes.SchemaAdd;
import es.um.unosql.subtypes.util.types.changes.SchemaChange;
import es.um.unosql.subtypes.util.types.changes.SchemaRemove;
import es.um.unosql.uNoSQLSchema.Feature;
import es.um.unosql.uNoSQLSchema.StructuralVariation;
import es.um.unosql.utils.compare.CompareFeature;

public class SchemaChangeDetector
{
    private CompareFeature comparer;

    public SchemaChangeDetector(List<EntitySubtype> subtypes)
    {
        comparer = new CompareFeature();

        for (EntitySubtype subtype : subtypes)
        {
            detectSchemaAdds(subtype);
            detectSchemaRemoves(subtype);
            detectSchemaChanges(subtype);
        }
    }

    private void detectSchemaAdds(EntitySubtype subtype)
    {
        boolean itAppears = false;

        for (Feature optional : subtype.getSubtypeOptionalFeatures())
        {
            for (StructuralVariation variation : subtype.getVariations())
                if (variation.getFeatures().stream().anyMatch(feat -> comparer.compare(feat, optional)))
                    itAppears = true;
                else if (itAppears)
                {
                    itAppears = false;
                    break;
                }
            if (itAppears)
                subtype.addSchemaAdd(new SchemaAdd(optional, subtype.getVariations().stream()
                        .filter(var -> var.getFeatures().stream().anyMatch(feat -> comparer.compare(feat, optional)))
                        .findFirst().get()));
        }
    }

    private void detectSchemaRemoves(EntitySubtype subtype)
    {
        boolean itDissapears = false;

        for (Feature optional : subtype.getSubtypeOptionalFeatures())
        {
            for (StructuralVariation variation : subtype.getVariations())
                if (variation.getFeatures().stream().noneMatch(feat -> comparer.compare(feat, optional)))
                    itDissapears = true;
                else if (itDissapears)
                {
                    itDissapears = false;
                    break;
                }
            if (itDissapears)
            {
                subtype.addSchemaRemove(new SchemaRemove(optional, subtype.getVariations().stream()
                        .filter(var -> var.getFeatures().stream().anyMatch(feat -> comparer.compare(feat, optional)))
                        .reduce((var1, var2) -> var1.getVariationId() > var2.getVariationId() ? var1 : var2).get()));
            }
        }
    }

    private void detectSchemaChanges(EntitySubtype subtype)
    {
        for (SchemaRemove schemaRem : subtype.getSchemaRemoves())
        {
            int index = subtype.getVariations().indexOf(schemaRem.getLastVariation());
            if (subtype.getVariations().size() > index)
                for (SchemaAdd schemaAdd : subtype.getSchemaAdds().stream()
                        .filter(schAdd -> schAdd.getFirstVariation() == subtype.getVariations().get(index + 1))
                        .collect(Collectors.toList()))
                    subtype.addSchemaChange(new SchemaChange(schemaRem, schemaAdd));
        }
    }
}
