package es.um.nosql.s13e.design.services;

import java.util.List;

import es.um.nosql.s13e.NoSQLSchema.Association;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.Classifier;
import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.Null;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.design.services.util.PropertyCollector;

public class PropertyServices
{
  private PropertyCollector propCollector;

  public PropertyServices()
  {
    propCollector = new PropertyCollector();
  }

  public List<Attribute> getAttributesFromClassifier(EntityClass classifier)
  {
    return propCollector.getUnionProperties(classifier, Attribute.class);
  }

  public List<Association> getAssociationsFromClassifier(EntityClass classifier)
  {
    return propCollector.getUnionProperties(classifier, Association.class);
  }

  public List<Null> getNullsFromClassifier(EntityClass classifier)
  {
    return propCollector.getUnionProperties(classifier, Null.class);
  }

  public List<Attribute> getCommonAttributesFromClassifier(EntityClass classifier)
  {
    return propCollector.getCommonProperties(classifier, Attribute.class);
  }

  public List<Association> getCommonAssociationsFromClassifier(EntityClass classifier)
  {
    return propCollector.getCommonProperties(classifier, Association.class);
  }

  public List<Null> getCommonNullsFromClassifier(EntityClass classifier)
  {
    return propCollector.getCommonProperties(classifier, Null.class);
  }

  public List<Attribute> getParticularAttributesFromVar(StructuralVariation var)
  {
    return propCollector.getParticularProperties(var, Attribute.class);
  }

  public List<Association> getParticularAssociationsFromVar(StructuralVariation var)
  {
    return propCollector.getParticularProperties(var, Association.class);
  }

  public List<Null> getParticularNullsFromVar(StructuralVariation var)
  {
    return propCollector.getParticularProperties(var, Null.class);
  }
}