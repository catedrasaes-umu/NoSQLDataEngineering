package es.um.nosql.schemainference.db.pojo.companies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"is_past", "title", "person"})
public class Relationship
{
  private Boolean is_past;

  private String title;

  private Person person;

  @JsonProperty("is_past")
  public Boolean getIs_past() {
    return is_past;
  }

  public void setIs_past(Boolean is_past) {
    this.is_past = is_past;
  }

  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @JsonProperty("person")
  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }
}