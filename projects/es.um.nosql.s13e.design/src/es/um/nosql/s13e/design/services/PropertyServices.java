package es.um.nosql.s13e.design.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Association;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.NoSQLSchema.PList;

public class PropertyServices
{
  public List<Attribute> getAttributeList(EntityClass entity)
  {
    List<Attribute> result = new ArrayList<Attribute>();

    for (StructuralVariation eVariation : entity.getVariations())
    {
      eVariation.getProperties().stream().filter(prop -> prop instanceof Attribute).forEach(prop -> {
        Attribute attr = (Attribute) prop;

        if (attr.getType() instanceof PrimitiveType)
        {
          PrimitiveType attr1Type = (PrimitiveType) attr.getType();

          if (!result.stream()
              .anyMatch(attr2 -> attr2.getType() instanceof PrimitiveType && attr2.getName().equals(attr.getName())
                  && ((PrimitiveType) attr2.getType()).getName().equals(attr1Type.getName())))
            result.add(attr);
        } else if (attr.getType() instanceof PList)
        {
          PList attr1Type = (PList) attr.getType();

          if (!result.stream()
              .anyMatch(attr2 -> attr2.getType() instanceof PList
                  && attr2.getName().equals(attr.getName())
                  && ((PrimitiveType)attr1Type.getElementType()).getName().equals(((PrimitiveType)((PList)attr2.getType()).getElementType()).getName())))
            result.add(attr);

        }
      });
    }
    result.sort((attr1, attr2) -> attr1.getName().compareTo(attr2.getName()));

    return result;
  }

  public List<Association> getAssociationList(EntityClass entity)
  {
    List<Association> result = new ArrayList<Association>();

    for (StructuralVariation eVariation : entity.getVariations())
    {
      eVariation.getProperties().stream().filter(prop -> prop instanceof Association).forEach(prop -> {
        Association assoc = (Association) prop;

        if (assoc instanceof Reference)
        {
          Reference ref = (Reference) assoc;

          if (result.stream().noneMatch(ref2 -> ref2 instanceof Reference && ref.getName().equals(ref2.getName())
              && (ref.getRefsTo().getName().equals(((Reference) ref2).getRefsTo().getName()))))
            result.add(ref);
        } else if (assoc instanceof Aggregate)
        {
          Aggregate aggr = (Aggregate) assoc;

          // If there was no other Aggregation with the name, just add the Aggregation
          if (result.stream().noneMatch(aggr2 -> aggr2 instanceof Aggregate && aggr.getName().equals(aggr2.getName())))
            result.add(aggr);
          else
          {
            // Otherwise, get the Aggregation with the same name and add all the aggregated
            // objects to the Aggregation
            // Please note that we don't really care about the cardinality of the
            // Aggregation. Otherwise we would need to adjust that...
            Aggregate aggr2 = (Aggregate) result.stream()
                .filter(any -> any instanceof Aggregate && aggr.getName().equals(any.getName())).findFirst().get();
            aggr2.getAggregates().addAll(aggr.getAggregates());
            aggr2.setUpperBound(-1);
          }
        }
      });
    }
    result.sort((assoc1, assoc2) -> assoc1.getName().compareTo(assoc2.getName()));

    return result;
  }

  public List<Attribute> getCommonAttributeList(EntityClass entity)
  {
    List<Attribute> result = new ArrayList<Attribute>();

    if (entity.getVariations().isEmpty())
      return result;

    StructuralVariation eVariation = entity.getVariations().get(0);

    for (Property prop : eVariation.getProperties())
      if (prop instanceof Attribute)
        result.add((Attribute) prop);

    if (entity.getVariations().size() == 1)
      return result;

    result = result.stream().filter(attr1 -> {
      return entity.getVariations().stream().skip(1).allMatch(ev -> {
        for (Property p : ev.getProperties())
          if (p instanceof Attribute && checkAttributesEqual(attr1, (Attribute) p))
            return true;

        return false;
      });
    }).collect(Collectors.toList());

    result.sort((attr1, attr2) -> attr1.getName().compareTo(attr2.getName()));

    return result;
  }

  public List<Attribute> getParticularAttributeList(StructuralVariation eVariation)
  {
    List<Attribute> result = new ArrayList<Attribute>();
    List<Attribute> commonAttrs = getCommonAttributeList((EntityClass) eVariation.eContainer());

    for (Property prop : eVariation.getProperties())
      if (prop instanceof Attribute)
        result.add((Attribute) prop);

    result = result.stream().filter(attr1 -> {
      return !commonAttrs.stream().anyMatch(attr2 -> {
        return checkAttributesEqual(attr1, attr2);
      });
    }).collect(Collectors.toList());

    result.sort((attr1, attr2) -> attr1.getName().compareTo(attr2.getName()));

    return result;
  }

  public List<Association> getCommonAssociationList(EntityClass entity)
  {
    List<Association> result = new ArrayList<Association>();

    if (entity.getVariations().isEmpty())
      return result;

    StructuralVariation eVariation = entity.getVariations().get(0);

    for (Property prop : eVariation.getProperties())
      if (prop instanceof Association)
        result.add((Association) prop);

    if (entity.getVariations().size() == 1)
      return result;

    result = result.stream().filter(assc -> {
      return entity.getVariations().stream().skip(1).allMatch(ev -> {
        for (Property p : ev.getProperties())
          if (p instanceof Association && checkAssociationsEqual(assc, (Association) p))
            return true;

        return false;
      });
    }).collect(Collectors.toList());

    result.sort((assc1, assc2) -> assc1.getName().compareTo(assc2.getName()));

    return result;
  }

  public List<Association> getParticularAssociationList(StructuralVariation eVariation)
  {
    List<Association> result = new ArrayList<Association>();
    List<Association> commonAssc = getCommonAssociationList((EntityClass) eVariation.eContainer());

    for (Property prop : eVariation.getProperties())
      if (prop instanceof Association)
        result.add((Association) prop);

    result = result.stream().filter(assc1 -> {
      return !commonAssc.stream().anyMatch(assc2 -> {
        return checkAssociationsEqual(assc1, assc2);
      });
    }).collect(Collectors.toList());

    result.sort((assc1, assc2) -> assc1.getName().compareTo(assc2.getName()));

    return result;
  }

  private static boolean checkAttributesEqual(Attribute attr1, Attribute attr2)
  {
    if (attr1.getType() instanceof PrimitiveType && attr2.getType() instanceof PrimitiveType)
    {
      PrimitiveType atType1 = (PrimitiveType) attr1.getType();
      PrimitiveType atType2 = (PrimitiveType) attr2.getType();

      // Check the primitive attributes have the same name and the same type.
      if (attr1.getName().equals(attr2.getName()) && atType1.getName().equals(atType2.getName()))
        return true;
    } else if (attr1.getType() instanceof PList && attr2.getType() instanceof PList)
    {
      PList atType1 = (PList) attr1.getType();
      PList atType2 = (PList) attr2.getType();

      // TODO: We will forget about heterogeneous arrays for the moment...
      // Check tuples have the same name, the same size and the same first type.
      if (attr1.getName().equals(attr2.getName())
          && ((PrimitiveType) atType1.getElementType()).getName()
              .equals(((PrimitiveType) atType2.getElementType()).getName()))
        return true;
    }

    return false;
  }

  private static boolean checkAssociationsEqual(Association assc1, Association assc2)
  {
    if (assc1 instanceof Reference && assc2 instanceof Reference)
    {
      Reference ref1 = (Reference) assc1;
      Reference ref2 = (Reference) assc2;

      // Check the references have the same name, the same original type and are pointing to the same entity.
      if (ref1.getName().equals(ref2.getName()) && (ref1.getOriginalType() == null || ref2.getOriginalType() == null
          || ref1.getOriginalType().equals(ref2.getOriginalType())) && ref1.getRefsTo() == ref2.getRefsTo())
        return true;
    } else if (assc1 instanceof Aggregate && assc2 instanceof Aggregate)
    {
      Aggregate aggr1 = (Aggregate) assc1;
      Aggregate aggr2 = (Aggregate) assc2;

      // Check the aggregates have the same name, and the refTo points to variations of the same entity
      if (aggr1.getName().equals(aggr2.getName())
          && aggr1.getAggregates().get(0).eContainer() == aggr2.getAggregates().get(0).eContainer()
          && aggr1.getAggregates().size() == aggr2.getAggregates().size())
        return true;
    }

    return false;
  }
}