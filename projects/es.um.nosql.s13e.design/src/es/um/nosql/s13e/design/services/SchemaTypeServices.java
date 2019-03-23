package es.um.nosql.s13e.design.services;

import java.util.List;

import es.um.nosql.s13e.NoSQLSchema.Association;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.SchemaType;
import es.um.nosql.s13e.NoSQLSchema.Null;
import es.um.nosql.s13e.design.services.util.PropertyCollector;

public class SchemaTypeServices
{
  private PropertyCollector propCollector;

  public SchemaTypeServices()
  {
    propCollector = new PropertyCollector();
  }

  public boolean existsCommonPropertiesInSchemaType(SchemaType schemaT)
  {
    return schemaT.getVariations().stream()
        .anyMatch(var -> var.getProperties().stream().anyMatch(prop -> !prop.isOptional()));
  }

  public boolean existsOptionalPropertiesInSchemaType(SchemaType schemaT)
  {
    return schemaT.getVariations().stream()
        .anyMatch(var -> var.getProperties().stream().anyMatch(prop -> prop.isOptional()));
  }

  public List<Attribute> getAttributesFromSchemaType(SchemaType schemaT)
  {
    return propCollector.getUnionProperties(schemaT, Attribute.class);
  }

  public List<Association> getAssociationsFromSchemaType(SchemaType schemaT)
  {
    return propCollector.getUnionProperties(schemaT, Association.class);
  }

  public List<Null> getNullsFromSchemaType(SchemaType schemaT)
  {
    return propCollector.getUnionProperties(schemaT, Null.class);
  }

  public List<Attribute> getCommonAttributesFromSchemaType(SchemaType schemaT)
  {
    return propCollector.getCommonProperties(schemaT, Attribute.class);
  }

  public List<Association> getCommonAssociationsFromSchemaType(SchemaType schemaT)
  {
    return propCollector.getCommonProperties(schemaT, Association.class);
  }

  public List<Null> getCommonNullsFromSchemaType(SchemaType schemaT)
  {
    return propCollector.getCommonProperties(schemaT, Null.class);
  }

  public List<Attribute> getOptionalAttributesFromSchemaType(SchemaType schemaT)
  {
    return propCollector.getOptionalProperties(schemaT, Attribute.class);
  }

  public List<Association> getOptionalAssociationsFromSchemaType(SchemaType schemaT)
  {
    return propCollector.getOptionalProperties(schemaT, Association.class);
  }

  public List<Null> getOptionalNullsFromSchemaType(SchemaType schemaT)
  {
    return propCollector.getOptionalProperties(schemaT, Null.class);
  }
}
