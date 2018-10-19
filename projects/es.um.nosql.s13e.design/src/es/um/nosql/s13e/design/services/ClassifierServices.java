package es.um.nosql.s13e.design.services;

import java.util.List;

import es.um.nosql.s13e.NoSQLSchema.Association;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.Classifier;
import es.um.nosql.s13e.NoSQLSchema.Null;
import es.um.nosql.s13e.design.services.util.PropertyCollector;

public class ClassifierServices
{
  private PropertyCollector propCollector;

  public ClassifierServices()
  {
    propCollector = new PropertyCollector();
  }

  public boolean existsCommonPropertiesInClassifier(Classifier classifier)
  {
    return classifier.getVariations().stream()
        .anyMatch(var -> var.getProperties().stream().anyMatch(prop -> !prop.isOptional()));
  }

  public boolean existsOptionalPropertiesInClassifier(Classifier classifier)
  {
    return classifier.getVariations().stream()
        .anyMatch(var -> var.getProperties().stream().anyMatch(prop -> prop.isOptional()));
  }

  public List<Attribute> getAttributesFromClassifier(Classifier classifier)
  {
    return propCollector.getUnionProperties(classifier, Attribute.class);
  }

  public List<Association> getAssociationsFromClassifier(Classifier classifier)
  {
    return propCollector.getUnionProperties(classifier, Association.class);
  }

  public List<Null> getNullsFromClassifier(Classifier classifier)
  {
    return propCollector.getUnionProperties(classifier, Null.class);
  }

  public List<Attribute> getCommonAttributesFromClassifier(Classifier classifier)
  {
    return propCollector.getCommonProperties(classifier, Attribute.class);
  }

  public List<Association> getCommonAssociationsFromClassifier(Classifier classifier)
  {
    return propCollector.getCommonProperties(classifier, Association.class);
  }

  public List<Null> getCommonNullsFromClassifier(Classifier classifier)
  {
    return propCollector.getCommonProperties(classifier, Null.class);
  }

  public List<Attribute> getOptionalAttributesFromClassifier(Classifier classifier)
  {
    return propCollector.getOptionalProperties(classifier, Attribute.class);
  }

  public List<Association> getOptionalAssociationsFromClassifier(Classifier classifier)
  {
    return propCollector.getOptionalProperties(classifier, Association.class);
  }

  public List<Null> getOptionalNullsFromClassifier(Classifier classifier)
  {
    return propCollector.getOptionalProperties(classifier, Null.class);
  }
}
