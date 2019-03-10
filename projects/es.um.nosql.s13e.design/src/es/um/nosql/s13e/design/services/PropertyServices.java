package es.um.nosql.s13e.design.services;

import java.util.stream.Collectors;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Association;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.Classifier;
import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.Null;
import es.um.nosql.s13e.NoSQLSchema.PList;
import es.um.nosql.s13e.NoSQLSchema.PMap;
import es.um.nosql.s13e.NoSQLSchema.PSet;
import es.um.nosql.s13e.NoSQLSchema.PTuple;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.NoSQLSchema.Type;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.PropertySpec;

public class PropertyServices
{
  public String getNullLabel(Null nul)
  {
    StringBuilder result = new StringBuilder();
    result.append(nul.getName() + ": Null");

    return result.toString();
  }

  public String getAttributeLabelFromPropSpec(PropertySpec propSpec)
  {
   return getAttributeLabel((Attribute)propSpec.getProperty())
     + "\t(" + "TCheck: " + propSpec.isNeedsTypeCheck() + ")";
  }

  public String getAssociationLabelFromPropSpec(PropertySpec propSpec)
  {
   return getAssociationLabel((Association)propSpec.getProperty())
     + "\t(" + "TCheck: " + propSpec.isNeedsTypeCheck() + ")";
  }

  public String getAttributeLabel(Attribute attr)
  {
    StringBuilder result = new StringBuilder();
    result.append(attr.getName() + ": " + getTypeLabel(attr.getType()));

    return result.toString();
  }

  public String getTypeLabel(Type type)
  {
    if (type == null)
      return null;

    StringBuilder result = new StringBuilder();

    if (type instanceof PrimitiveType)
      result.append(((PrimitiveType)type).getName());
    else if (type instanceof PList)
      result.append("PList<" + getTypeLabel(((PList)type).getElementType()) + ">");
    else if (type instanceof PSet)
        result.append("PSet<" + getTypeLabel(((PSet)type).getElementType()) + ">");
    else if (type instanceof PTuple)
      result.append("PTuple<" + ((PTuple)type).getElements().stream().map(e -> getTypeLabel(e)).collect(Collectors.joining(", ")) + ">");
    else if (type instanceof PMap)
        result.append("PMap<" + getTypeLabel(((PMap)type).getKeyType()) 
          + "," + getTypeLabel(((PMap)type).getValueType()) + ">");

    return result.toString();
  }

  public String getAssociationLabel(Association association)
  {
    StringBuilder result = new StringBuilder();
    result.append(association.getName() + ": ");

    if (association instanceof Aggregate)
    {
      Aggregate aggr = (Aggregate)association;
      result.append(" [");
      result.append(aggr.getLowerBound() != -1 ? aggr.getLowerBound() : "*");
      result.append("..");
      result.append(aggr.getUpperBound() != -1 ? aggr.getUpperBound() : "*");
      result.append("] ");
      result.append(aggr.getAggregates().stream().map(theAggr ->
        ((Classifier)theAggr.eContainer()).getName() + "_" + theAggr.getVariationId()
      ).collect(Collectors.joining(", ")));
    }
    else if (association instanceof Reference)
    {
      Reference ref = (Reference)association;
      result.append(ref.getOriginalType() + " [");
      result.append(ref.getLowerBound() != -1 ? ref.getLowerBound() : "*");
      result.append("..");
      result.append(ref.getUpperBound() != -1 ? ref.getUpperBound() : "*");
      result.append("] " + ((EntityClass)ref.getRefsTo()).getName());

      if (ref.getOpposite() != null)
      {
        result.append("(opp: " + ref.getOpposite().getName() + ": " + ref.getOpposite().getOriginalType() + " [");
        result.append(ref.getOpposite().getLowerBound() != -1 ? ref.getOpposite().getLowerBound() : "*");
        result.append("..");
        result.append(ref.getOpposite().getUpperBound() != -1 ? ref.getOpposite().getUpperBound() : "*");
        result.append("] " + ((EntityClass)ref.getOpposite().getRefsTo()).getName() + ")");
      }

      if (ref.getFeatures() != null)
        result.append("(feat:" + ((Classifier)ref.getFeatures().eContainer()).getName()
            + "_" + ref.getFeatures().getVariationId() + ")");
    }

    return result.toString();
  }
}
