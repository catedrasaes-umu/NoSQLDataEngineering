package es.um.nosql.s13e.evolution.analyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.SchemaType;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.evolution.analyzer.db.DbDiscriminatorSeeker;
import es.um.nosql.s13e.evolution.analyzer.detectors.DependentPropsDetector;
import es.um.nosql.s13e.evolution.analyzer.detectors.SchemaChangeDetector;
import es.um.nosql.s13e.evolution.analyzer.diffs.PropertyMatrix;
import es.um.nosql.s13e.evolution.types.EntitySubtype;

public class DependencyAnalyzer
{
  private String dbName;
  private SchemaType sType;
  private PropertyMatrix matrix;
  private DependentPropsDetector dPropsDetector;
  private SchemaChangeDetector sChangeDetector;
  private List<EntitySubtype> subtypes;
  private DbDiscriminatorSeeker discriminatorSeeker;

  public DependencyAnalyzer(String dbName, SchemaType sType, boolean detectDiscriminatorField)
  {
    this.dbName = dbName;
    this.sType = sType;
    this.matrix = new PropertyMatrix(sType);
    this.dPropsDetector = new DependentPropsDetector(matrix);
    this.subtypes = detectSubtypes();
    this.sChangeDetector = new SchemaChangeDetector(this.subtypes);

    if (detectDiscriminatorField)
      createDiscriminatorSeeker();
    else
      this.discriminatorSeeker = null;
  }

  public SchemaType getSchemaType()
  {
    return sType;
  }

  public PropertyMatrix getPropertyMatrix()
  {
    return matrix;
  }

  public DependentPropsDetector getDependentProps()
  {
    return dPropsDetector;
  }

  public SchemaChangeDetector getSchemaChanges()
  {
    return sChangeDetector;
  }

  public List<EntitySubtype> getSubtypes()
  {
    return subtypes;
  }

  public DbDiscriminatorSeeker getDiscriminatorSeeker()
  {
    return discriminatorSeeker;
  }

  private List<EntitySubtype> detectSubtypes()
  {
    List<EntitySubtype> subtypes = new ArrayList<EntitySubtype>();
    List<Property> allSubtypeProps = new ArrayList<Property>();
    List<StructuralVariation> remainingVars = new ArrayList<StructuralVariation>(sType.getVariations());

    // About to detect subtypes...
    for (List<Property> subtypeRequiredProps : dPropsDetector.getStrongDependencies())
    {
      List<Property> subtypeOptionalProps = dPropsDetector.getWeakDependencies().keySet().stream()
          .filter(weakProp -> dPropsDetector.getWeakDependencies().getOrDefault(weakProp, Arrays.asList()).containsAll(subtypeRequiredProps))
          .collect(Collectors.toList());
      allSubtypeProps.addAll(subtypeRequiredProps);
      allSubtypeProps.addAll(subtypeOptionalProps);
      // Variations belonging to a subtype are variations that have all of the required properties for that subtype.
      // Because of this we take the first of the subtype properties and then get variations with that first property.
      EntitySubtype subtype = new EntitySubtype(matrix.getVarsFromProperty(subtypeRequiredProps.get(0)), subtypeRequiredProps, subtypeOptionalProps);
      subtypes.add(subtype);
      remainingVars.removeAll(subtype.getVariations());
    }

    // Now get all properties independent of particularWeakDeps and subtypeProps...
    // and filter those independent properties with weak dependencies with another independent properties.
    // The remaining properties are the identifying ones.
    List<Property> tempList = dPropsDetector.getExcludingProps().keySet().stream()
        .filter(exclProp -> dPropsDetector.getExcludingProps().get(exclProp).containsAll(allSubtypeProps))
        .collect(Collectors.toList());

    List<Property> remainingProps = tempList.stream().filter(remProp ->
    {
      for (Property remProp2 : tempList)
        if (dPropsDetector.getWeakDependencies().getOrDefault(remProp, Arrays.asList()).contains(remProp2))
          return false;
      return true;
    }).collect(Collectors.toList());

    // TODO: So there is a small mistake here: Once a remainingProp is identified as exclusive for a subtype, the rest of remainingProps must be recalculated. Products2 model
    for (Property remProp : remainingProps)
    {
      List<Property> subtypeOptionalProps = dPropsDetector.getWeakDependencies().keySet().stream()
          .filter(weakProp -> dPropsDetector.getWeakDependencies().getOrDefault(weakProp, Arrays.asList()).contains(remProp))
          .collect(Collectors.toList());

      EntitySubtype subtype = new EntitySubtype(matrix.getVarsFromProperty(remProp), Arrays.asList(remProp), subtypeOptionalProps);
      subtypes.add(subtype);
      remainingVars.removeAll(subtype.getVariations());
    }

    // Finally, create a new subtype grouping all variations not belonging to any subtype.
    // Note that this subtype wont be used to detect the discriminator field.
    //    if (!remainingVars.isEmpty())
    //      subtypes.add(new EntitySubtype(remainingVars, new ArrayList<Property>(), new ArrayList<Property>()));

    return subtypes;
  }

  private void createDiscriminatorSeeker()
  {
    List<Attribute> candidates = new ArrayList<Attribute>(sType.getVariations().get(0).getProperties().stream()
        .filter(prop -> !prop.isOptional() && prop instanceof Attribute)
        .map(prop -> (Attribute)prop)
        .collect(Collectors.toList()));

    // In order to identify an object to a subtype, that subtype NEEDS to have some identifying properties
    // So if a subtype was created in order to group remaining variations, that subtype must be sustracted.
    List<EntitySubtype> filteredSubtypes = subtypes.stream().filter(subtype -> !subtype.getSubtypeRequiredProps().isEmpty()).collect(Collectors.toList());

    this.discriminatorSeeker = new DbDiscriminatorSeeker(dbName, sType.getName().toLowerCase(), filteredSubtypes, candidates);
  }
}
