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

  private Validator[] validators;

  public void setValidators(Validator[] validators) {this.validators = validators;}

  public Validator[] getValidators() {return this.validators;}

  private String[] uniques;

  public void setUniques(String[] uniques) {this.uniques = uniques;}

  public String[] getUniques() {return this.uniques;}

  private String[] updates;

  public void setUpdates(String[] updates) {this.updates = updates;}

  public String[] getUpdates() {return this.updates;}

  private Index[] indexes;

  public void setIndexes(Index[] indexes) {this.indexes = indexes;}

  public Index[] getIndexes() {return this.indexes;}

  public Validator getValidatorFor(String field)
  {
    if (validators == null)
      return null;
    return Arrays.stream(validators).filter(o -> o.getAttr().equals(field)).findFirst().orElse(null);
  }

  public boolean isFieldUnique(String field)
  {
    if (uniques == null)
      return false;
    return Arrays.asList(uniques).contains(field);
  }

  public boolean isFieldUpdate(String field)
  {
    if (updates == null)
      return false;
    return Arrays.asList(updates).contains(field);
  }

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
