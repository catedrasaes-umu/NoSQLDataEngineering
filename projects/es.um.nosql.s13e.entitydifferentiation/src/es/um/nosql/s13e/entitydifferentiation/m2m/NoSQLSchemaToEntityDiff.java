package es.um.nosql.s13e.entitydifferentiation.m2m;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.set.strategy.mutable.UnifiedSetWithHashingStrategy;
import org.eclipse.collections.impl.tuple.Tuples;

import es.um.nosql.s13e.NoSQLSchema.EntityType;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.entitydifferentiation.m2m.hashing.PropertyHashingStrategy;
import es.um.nosql.s13e.entitydifferentiation.m2m.hashing.PropertyJustNameHashingStrategy;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDiff;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiation;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiationFactory;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.StructuralVariationDiff;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.PropertySpec;
import es.um.nosql.s13e.util.ModelLoader;

public class NoSQLSchemaToEntityDiff
{
  private Map<EntityType, Set<Property>> commonEntityProperties;
  private Map<StructuralVariation, Set<Property>> evOwnProperties;
  private Map<StructuralVariation, StructuralVariationDiff> evPropsByEv;
  private HashingStrategy<Property> propertyHashing;

  public NoSQLSchemaToEntityDiff()
  {
    // Sometimes a property seems like not required, but actually it is.
    // This might happen if 50% of the objects have a String field, and the other 50% have it as a Number
    // Then there should be a needsTypeCheck Union of some kind..too complex for now.
    // Maybe it is worth trying to use another hashing strategy
    propertyHashing = new PropertyHashingStrategy();
  }

  public EntityDifferentiation m2m(File modelFile)
  {
    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema = loader.load(modelFile, NoSQLSchema.class);

    return m2m(schema);
  }

  /*
   * The following code takes some assumptions.
   *
   * - All the objects are represented, so just storing their differences at the level of attributes contained or not contained is enough to discern them
   * - In cases of same name attributes with different types between entity variations, more complete type enabled tests are output
   *
   * So what we do is:
   *
   * 1. For each Entity, check all the common properties of all the StructuralVariations (name and type). This will generate simple checks against property names.
   * 2. For each StructuralVariation, record the specific properties of that entity. There will be two types: Those with properties that don't appear in any other EVs
   *    (we have to check just the existence of the property with its name), and those with properties that exist in any other of the EVs. For those, we will have to check for the type.
   */
  public EntityDifferentiation m2m(NoSQLSchema schema)
  {
    EntityDifferentiation differentiation = EntityDifferentiationFactory.eINSTANCE.createEntityDifferentiation();

    differentiation.setName(schema.getName());
    differentiation.setSchema(schema);

    initCalculations(schema);

    // Generate entity References
    for (EntityType e: schema.getEntities())
      transformEntity(e, differentiation);

    return differentiation;
  }

  /**
   * Initialize calculations for properties, etc.
   */
  private void initCalculations(NoSQLSchema schema)
  {
    commonEntityProperties = schema.getEntities().stream()
        .collect(toMap(identity(), this::calcCommonProperties));

    evOwnProperties = schema.getEntities().stream()
        .flatMap(e -> e.getVariations().stream().map(ev -> Tuples.pair(ev,
            ev.getProperties().stream().filter(p -> !commonEntityProperties.get(e).contains(p))
            .collect(toCollection(() -> new UnifiedSetWithHashingStrategy<>(propertyHashing))))))
        .collect(toMap(Pair::getOne, Pair::getTwo));

    evPropsByEv = new HashMap<>();
  }

  private void transformEntity(EntityType e, EntityDifferentiation diff)
  {
    // Holds the set of properties to check for each StructuralVariation. It will be used to
    // not to create duplicate checks
    Map<StructuralVariation, Map<String, PropertySpec>> propSpecsByEV = new HashMap<>();

    EntityDiff de = EntityDifferentiationFactory.eINSTANCE.createEntityDiff();
    de.setEntity(e);

    Set<Property> commonProperties = commonEntityProperties.get(e);

    // Add Common Props
    de.getCommonProps().addAll(commonProperties.stream()
        .map(this::genPropertySpecNamed).collect(toList()));

    // Create and add all the StructuralVariationProp. This is because it is easier, when
    // identified the own attributes of an StructuralVariation, output "HasNotField" items
    // for the rest of StructuralVariations
    e.getVariations().forEach(ev -> {
      StructuralVariationDiff evp = EntityDifferentiationFactory.eINSTANCE.createStructuralVariationDiff();
      evp.setVariation(ev);
      de.getVariationDiffs().add(evp);

      // Set of properties by entity, for fast lookup
      propSpecsByEV.put(ev, new HashMap<>());

      // Add to the list of DiffSpecs for the decision tree afterwards
      evPropsByEv.put(ev, evp);
    });

    if (e.getVariations().size() > 1)
    {
      for (StructuralVariation ev : e.getVariations())
      {
        // For each property, check whether other entity variations having this property have also the same type for it
        for (Property p : evOwnProperties.get(ev))
        {
          // If this property appears in any other entity variation with a different type, needs to type check
          boolean hasToTypeCheck =
              !evOwnProperties.entrySet().stream().filter(en -> en.getKey() != ev)
              .flatMap(en -> en.getValue().stream().filter(p1 -> PropertyJustNameHashingStrategy.checkEquality(p, p1)))
              .allMatch(p2 -> PropertyHashingStrategy.checkEquality(p, p2));

          PropertySpec ps = hasToTypeCheck ? genPropertySpecTyped(p): genPropertySpecNamed(p);
          propSpecsByEV.get(ev).put(p.getName(), ps);
        }
      }

      for (StructuralVariationDiff evp: de.getVariationDiffs())
      {
        propSpecsByEV.get(evp.getVariation()).values().forEach(pe -> {
          evp.getPropertySpecs().add(pe);
        });
      }

      // Calc notProps

      // For each entity variation, for each property appearing in the rest 
      // of StructuralVariations but not in this one, add a "notProp"
      for (StructuralVariationDiff evp: de.getVariationDiffs())
      {
        Set<String> ownPropNames =
            evp.getPropertySpecs().stream().map(pe -> pe.getProperty().getName())
            .collect(Collectors.toSet());

        Map<String, PropertySpec> otherPropsByName = 
            de.getVariationDiffs().stream()
            .filter(_evp -> _evp != evp)
            .flatMap(_evp -> _evp.getPropertySpecs().stream())
            .filter(_ps -> !ownPropNames.contains(_ps.getProperty().getName()))
            .collect(Collectors.groupingBy(
                _ps -> _ps.getProperty().getName(),
                Collectors.reducing(null,(l,r) -> r)
                ));

        otherPropsByName.entrySet().stream().forEach(_e ->
        evp.getNotProps().add(genPropertySpecNamed(_e.getValue().getProperty()))
            );
      }
    }

    // Add it to the model
    diff.getEntityDiffs().add(de);
  }

  private Set<Property> calcCommonProperties(EntityType e)
  {
    Map<StructuralVariation, Set<Property>> allProperties =
        e.getVariations().stream()
        .collect(toMap(
            identity(),
            ev -> new UnifiedSetWithHashingStrategy<>(propertyHashing, ev.getProperties())));

    Set<Property> initCommonProperties = allProperties.values().stream().findFirst().get();
    final Set<Property> commonProperties = Collections.unmodifiableSet(
        allProperties.values().stream().skip(1)
        .reduce(initCommonProperties,
            (acc_set, sp) -> {acc_set.retainAll(sp); return acc_set;}));

    return commonProperties;
  }

  private PropertySpec genPropertySpecNamed(Property p)
  {
    PropertySpec ps = EntityDifferentiationFactory.eINSTANCE.createPropertySpec();
    ps.setProperty(p);
    return ps;
  }

  private PropertySpec genPropertySpecTyped(Property p)
  {
    PropertySpec ps = EntityDifferentiationFactory.eINSTANCE.createPropertySpec();
    ps.setNeedsTypeCheck(true);
    ps.setProperty(p);
    return ps;
  }
}
