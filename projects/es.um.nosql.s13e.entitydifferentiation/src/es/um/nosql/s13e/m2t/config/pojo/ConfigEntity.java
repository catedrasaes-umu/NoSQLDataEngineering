package es.um.nosql.s13e.m2t.config.pojo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.Property;

public class ConfigEntity
{
  private String name;

  public void setName(String name) {this.name = name;}

  public String getName() {return this.name;}

  private ConfigValidator[] validators;

  public void setValidators(ConfigValidator[] validators) {this.validators = validators;}

  public ConfigValidator[] getValidators() {return this.validators;}

  private ConfigIndex[] indexes;

  public void setIndexes(ConfigIndex[] indexes) {this.indexes = indexes;}

  public ConfigIndex[] getIndexes() {return this.indexes;}

  private String ignoreUnknown;

  public void setIgnoreUnknown(String ignoreUnknown) {this.ignoreUnknown = ignoreUnknown;}

  public String getIgnoreUnknown() {return this.ignoreUnknown;}

  private String[] populateReferences;

  public void setPopulateReferences(String[] populateReferences) {this.populateReferences = populateReferences;}

  public String[] getPopulateReferences() {return this.populateReferences;}

  public ConfigValidator[] getValidatorsFor(String field)
  {
    if (this.getValidators() == null)
      return new ConfigValidator[0];
    return Arrays.stream(this.getValidators()).filter(v -> v.getAttr().equals(field)).toArray(ConfigValidator[]::new);
  }

  public ConfigIndex[] getIndexesFor(String field)
  {
    if (this.getIndexes() == null)
      return new ConfigIndex[0];
    return Arrays.stream(this.getIndexes()).filter(i -> Arrays.asList(i.getAttr()).contains(field)).toArray(ConfigIndex[]::new);
  }

  public boolean getPopulateReferencesFor(String field)
  {
    if (this.getPopulateReferences() == null)
      return false;
    return Arrays.stream(this.getPopulateReferences()).anyMatch(i -> i.equals(field));
  }

  public String toString()
  {
    StringBuilder result = new StringBuilder();
    result.append("  -Name: " + this.getName() + "\n");
    if (validators != null)
      result.append("  Validators:\n" + Arrays.stream(this.getValidators()).map(v -> v.toString()).collect(Collectors.joining()));
    if (indexes != null)
      result.append("  Indexes:\n" + Arrays.stream(this.getIndexes()).map(i -> i.toString()).collect(Collectors.joining()));

    return result.toString();
  }

  public boolean doCheck(Entity e)
  {
    List<Property> eProperties = e.getEntityvariations().stream().flatMap(ev -> ev.getProperties().stream()).collect(Collectors.toList());

    if (this.getValidators() != null)
      for (ConfigValidator validator : this.getValidators())
      {
        validator.doCheck();

        if (!eProperties.stream().anyMatch(p -> p.getName().equals(validator.getAttr())))
          throw new IllegalArgumentException("Validator value " + validator.getAttr() + " must exist in the model Entity " + this.getName());
      }

    if (this.getIndexes() != null)
    {
      if (Arrays.stream(this.getIndexes()).filter(i -> i.getType() != null && i.getType()[0].toLowerCase().equals("text")).count() > 1)
        throw new IllegalArgumentException("Index types are not correct. There cannot be more than one index of type \"text\"");

      for (ConfigIndex index : this.getIndexes())
      {
        index.doCheck();

        if (!eProperties.stream().anyMatch(p -> Arrays.asList(index.getAttr()).contains(p.getName())))
          throw new IllegalArgumentException("Index value " + String.join(", ", index.getAttr()) + " must exist in the model Entity " + this.getName());

      }
    }
    return true;
  }
}