package es.um.nosql.schemainference.m2t.config.pojo;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Entity
{
  private String name;

  public void setName(String name) {this.name = name;}

  public String getName() {return this.name;}

  private Option[] validators;

  public void setValidators(Option[] validators) {this.validators = validators;}

  public Option[] getValidators() {return this.validators;}

  private String[] uniques;

  public void setUniques(String[] uniques) {this.uniques = uniques;}

  public String[] getUniques() {return this.uniques;}

  private String[] updates;

  public void setUpdates(String[] updates) {this.updates = updates;}

  public String[] getUpdates() {return this.updates;}

  private Option[] indexes;

  public void setIndexes(Option[] indexes) {this.indexes = indexes;}

  public Option[] getIndexes() {return this.indexes;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();
    result.append("  -Name: " + this.getName() + "\n");
    if (validators != null)
      result.append("  Validators:\n" + Arrays.stream(this.getValidators()).map(v -> v.toString()).collect(Collectors.joining()));
    if (uniques != null)
      result.append("  Uniques: " + Arrays.stream(this.getUniques()).collect(Collectors.joining(", ")) + "\n");
    if (updates != null)
      result.append("  Updates: " + Arrays.stream(this.getUpdates()).collect(Collectors.joining(", ")) + "\n");
    if (indexes != null)
      result.append("  Indexes:\n" + Arrays.stream(this.getIndexes()).map(i -> i.toString()).collect(Collectors.joining()));

    return result.toString();
  }
}
