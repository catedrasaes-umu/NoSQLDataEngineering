package es.um.unosql.subtypes.util.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import es.um.unosql.subtypes.util.types.changes.SchemaAdd;
import es.um.unosql.subtypes.util.types.changes.SchemaChange;
import es.um.unosql.subtypes.util.types.changes.SchemaRemove;
import es.um.unosql.uNoSQLSchema.Feature;
import es.um.unosql.uNoSQLSchema.StructuralVariation;

public class EntitySubtype
{
    List<StructuralVariation> variations;
    private Set<Feature> subtypeRequiredFeatures;
    private Set<Feature> subtypeOptionalFeatures;
    private List<SchemaAdd> schemaAdds;
    private List<SchemaRemove> schemaRemoves;
    private List<SchemaChange> schemaChanges;

    public EntitySubtype(List<StructuralVariation> variations, Set<Feature> subtypeRequiredFeats,
            Set<Feature> subtypeOptionalFeats)
    {
        this.variations = variations;
        this.subtypeRequiredFeatures = subtypeRequiredFeats;
        this.subtypeOptionalFeatures = subtypeOptionalFeats;
        this.schemaAdds = new ArrayList<SchemaAdd>();
        this.schemaRemoves = new ArrayList<SchemaRemove>();
        this.schemaChanges = new ArrayList<SchemaChange>();
    }

    public List<StructuralVariation> getVariations()
    {
        return variations;
    }

    public Set<Feature> getSubtypeRequiredFeatures()
    {
        return subtypeRequiredFeatures;
    }

    public Set<Feature> getSubtypeOptionalFeatures()
    {
        return subtypeOptionalFeatures;
    }

    public List<SchemaAdd> getSchemaAdds()
    {
        return schemaAdds;
    }

    public List<SchemaRemove> getSchemaRemoves()
    {
        return schemaRemoves;
    }

    public List<SchemaChange> getSchemaChanges()
    {
        return schemaChanges;
    }

    public void addSchemaAdd(SchemaAdd schemaAdd)
    {
        schemaAdds.add(schemaAdd);
    }

    public void addSchemaRemove(SchemaRemove schemaRemove)
    {
        schemaRemoves.add(schemaRemove);
    }

    public void addSchemaChange(SchemaChange schemaChange)
    {
        schemaChanges.add(schemaChange);
    }
}
