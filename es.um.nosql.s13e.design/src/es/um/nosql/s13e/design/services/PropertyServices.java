package es.um.nosql.s13e.design.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Association;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVersion;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.NoSQLSchema.Tuple;

public class PropertyServices
{
  public List<Attribute> getAttributeList(Entity entity)
  {
    List<Attribute> result = new ArrayList<Attribute>();

    for (EntityVersion eVersion : entity.getEntityversions())
    {
      eVersion.getProperties().stream().filter(prop -> prop instanceof Attribute).forEach(prop -> {
        Attribute attr = (Attribute) prop;

        if (attr.getType() instanceof PrimitiveType)
        {
          PrimitiveType attr1Type = (PrimitiveType) attr.getType();

          if (!result.stream()
              .anyMatch(attr2 -> attr2.getType() instanceof PrimitiveType && attr2.getName().equals(attr.getName())
                  && ((PrimitiveType) attr2.getType()).getName().equals(attr1Type.getName())))
            result.add(attr);
        } else if (attr.getType() instanceof Tuple)
        {
          Tuple attr1Type = (Tuple) attr.getType();

          if (!result.stream()
              .anyMatch(attr2 -> attr2.getType() instanceof Tuple
                  && attr2.getName().equals(attr.getName())
                  && attr1Type.getElements().size() == ((Tuple)attr2.getType()).getElements().size()
                  && ((PrimitiveType)attr1Type.getElements().get(0)).getName().equals(((PrimitiveType)((Tuple)attr2.getType()).getElements().get(0)).getName())))
            result.add(attr);

        }
      });
    }
    result.sort((attr1, attr2) -> attr1.getName().compareTo(attr2.getName()));

    return result;
  }

  public List<Association> getAssociationList(Entity entity)
  {
    List<Association> result = new ArrayList<Association>();

    for (EntityVersion eVersion : entity.getEntityversions())
    {
      eVersion.getProperties().stream().filter(prop -> prop instanceof Association).forEach(prop -> {
        Association assoc = (Association) prop;

        if (assoc instanceof Reference)
        {
          Reference ref = (Reference) assoc;

          if (result.stream().noneMatch(ref2 -> ref2 instanceof Reference && ref.getName().equals(ref2.getName())
              && (ref.getRefTo().getName().equals(((Reference) ref2).getRefTo().getName()))))
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
            aggr2.getRefTo().addAll(aggr.getRefTo());
            aggr2.setUpperBound(-1);
          }
        }
      });
    }
    result.sort((assoc1, assoc2) -> assoc1.getName().compareTo(assoc2.getName()));

    return result;
  }

  public List<Attribute> getCommonAttributeList(Entity entity)
  {
    List<Attribute> result = new ArrayList<Attribute>();

    if (entity.getEntityversions().isEmpty())
      return result;

    EntityVersion eVersion = entity.getEntityversions().get(0);

    for (Property prop : eVersion.getProperties())
      if (prop instanceof Attribute)
        result.add((Attribute) prop);

    if (entity.getEntityversions().size() == 1)
      return result;

    result = result.stream().filter(attr1 -> {
      return entity.getEntityversions().stream().skip(1).allMatch(ev -> {
        for (Property p : ev.getProperties())
          if (p instanceof Attribute && checkAttributesEqual(attr1, (Attribute) p))
            return true;

        return false;
      });
    }).collect(Collectors.toList());

    result.sort((attr1, attr2) -> attr1.getName().compareTo(attr2.getName()));

    return result;
  }

  public List<Attribute> getParticularAttributeList(EntityVersion eVersion)
  {
    List<Attribute> result = new ArrayList<Attribute>();
    List<Attribute> commonAttrs = getCommonAttributeList((Entity) eVersion.eContainer());

    for (Property prop : eVersion.getProperties())
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

  public List<Association> getCommonAssociationList(Entity entity)
  {
    List<Association> result = new ArrayList<Association>();

    if (entity.getEntityversions().isEmpty())
      return result;

    EntityVersion eVersion = entity.getEntityversions().get(0);

    for (Property prop : eVersion.getProperties())
      if (prop instanceof Association)
        result.add((Association) prop);

    if (entity.getEntityversions().size() == 1)
      return result;

    result = result.stream().filter(assc -> {
      return entity.getEntityversions().stream().skip(1).allMatch(ev -> {
        for (Property p : ev.getProperties())
          if (p instanceof Association && checkAssociationsEqual(assc, (Association) p))
            return true;

        return false;
      });
    }).collect(Collectors.toList());

    result.sort((assc1, assc2) -> assc1.getName().compareTo(assc2.getName()));

    return result;
  }

  public List<Association> getParticularAssociationList(EntityVersion eVersion)
  {
    List<Association> result = new ArrayList<Association>();
    List<Association> commonAssc = getCommonAssociationList((Entity) eVersion.eContainer());

    for (Property prop : eVersion.getProperties())
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
    } else if (attr1.getType() instanceof Tuple && attr2.getType() instanceof Tuple)
    {
      Tuple atType1 = (Tuple) attr1.getType();
      Tuple atType2 = (Tuple) attr2.getType();

      // TODO: We will forget about heterogeneous arrays for the moment...
      // Check tuples have the same name, the same size and the same first type.
      if (attr1.getName().equals(attr2.getName()) && atType1.getElements().size() == atType2.getElements().size()
          && ((PrimitiveType) atType1.getElements().get(0)).getName()
              .equals(((PrimitiveType) atType2.getElements().get(0)).getName()))
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
          || ref1.getOriginalType().equals(ref2.getOriginalType())) && ref1.getRefTo() == ref2.getRefTo())
        return true;
    } else if (assc1 instanceof Aggregate && assc2 instanceof Aggregate)
    {
      Aggregate aggr1 = (Aggregate) assc1;
      Aggregate aggr2 = (Aggregate) assc2;

      // Check the aggregates have the same name, and the refTo points to versions of the same entity
      if (aggr1.getName().equals(aggr2.getName())
          && aggr1.getRefTo().get(0).eContainer() == aggr2.getRefTo().get(0).eContainer()
          && aggr1.getRefTo().size() == aggr2.getRefTo().size())
        return true;
    }

    return false;
  }
}