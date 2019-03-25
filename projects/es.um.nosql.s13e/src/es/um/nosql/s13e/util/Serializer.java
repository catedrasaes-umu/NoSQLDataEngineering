package es.um.nosql.s13e.util;

import java.util.stream.Collectors;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Association;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.Null;
import es.um.nosql.s13e.NoSQLSchema.PList;
import es.um.nosql.s13e.NoSQLSchema.PMap;
import es.um.nosql.s13e.NoSQLSchema.PSet;
import es.um.nosql.s13e.NoSQLSchema.PTuple;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.NoSQLSchema.DataType;

//TODO: Can't stand this duplicates the code for something quite trivial. We should unify this serializer with the Xtext one.
//TODO: Also adapt decisionTree code because, for some reason, it uses this serializer to create the decision tree (¿?)
public class Serializer
{
  public static String serialize(StructuralVariation eVariation)
  {
    if (eVariation == null)
      return null;

    return eVariation.getContainer().getName() + "_" + eVariation.getVariationId();
  }

  public static String serialize(Property property)
  {
    if (property == null)
      return null;

    StringBuilder result = new StringBuilder();

    if (property instanceof Attribute)
      result.append(serialize((Attribute)property));
    else if (property instanceof Association)
      result.append(serialize((Association)property));
    else if (property instanceof Null)
      result.append(serialize((Null)property));

    return result.toString();
  }

  public static String serialize(Attribute attribute)
  {
    if (attribute == null)
      return null;

    StringBuilder result = new StringBuilder();
    result.append("(optional: " + attribute.isOptional() + (attribute.isOptional() ? " " : "") + ") "
      + attribute.getName() +  ": " + serialize(attribute.getType()));

    return result.toString();
  }

  public static String serialize(DataType type)
  {
    if (type == null)
      return null;

    StringBuilder result = new StringBuilder();

    if (type instanceof PrimitiveType)
      result.append(((PrimitiveType)type).getName());
    else if (type instanceof PList)
      result.append("PList<" + serialize(((PList)type).getElementType()) + ">");
    else if (type instanceof PSet)
        result.append("PSet<" + serialize(((PSet)type).getElementType()) + ">");
    else if (type instanceof PTuple)
      result.append("PTuple<" + ((PTuple)type).getElements().stream().map(e -> serialize(e)).collect(Collectors.joining(", ")) + ">");
    else if (type instanceof PMap)
        result.append("PMap<" + serialize(((PMap)type).getKeyType()) 
        	+ "," + serialize(((PMap)type).getValueType()) + ">");
    
    return result.toString();
  }

  public static String serialize(Association association)
  {
    if (association == null)
      return null;

    StringBuilder result = new StringBuilder();

    result.append("(optional: " + association.isOptional() + (association.isOptional() ? " " : "") + ") " + association.getName() + ": ");

    if (association instanceof Aggregate)
    {
      Aggregate aggregate = (Aggregate)association;
      result.append("[");

      result.append(
        aggregate.getAggregates().stream()
        .map(Serializer::serialize)
        .collect(Collectors.joining(";")));
      result.append("]:[" + association.getLowerBound() + ".." + association.getUpperBound() + "]");
    }
    else if (association instanceof Reference)
    {
      Reference reference = (Reference)association;
      result.append(reference.getRefsTo().getName());
      result.append(":[" + association.getLowerBound() + ".." + association.getUpperBound() + "]:");
      result.append("opp[");

      Reference oppositeRef = reference.getOpposite();

      if (oppositeRef == null)
        result.append(oppositeRef + "]");
      else
      {
        result.append(oppositeRef.getName() + ":" + oppositeRef.getRefsTo().getName());
        result.append(":[" + oppositeRef.getLowerBound() + ".." + oppositeRef.getUpperBound() + "]]");
      }

      if (!reference.getFeatures().isEmpty())
      {
        StructuralVariation feature = reference.getFeatures().get(0);
        result.append("feat[" + feature.getContainer().getName() + "_" + feature.getVariationId() + "]");
      }
    }

    return result.toString();
  }

  public static String serialize(Null theNull)
  {
    if (theNull == null)
      return null;

    StringBuilder result = new StringBuilder();
    result.append("(optional: " + theNull.isOptional() + (theNull.isOptional() ? " " : "") + ") " + theNull.getName() + ": Null");

    return result.toString();
  }
}
