package es.um.nosql.s13e.db.pojo.companies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"$oid"})
public class ObjectId
{
  private String $oid;

  @JsonProperty("$oid")
  public String get$oid() {
    return $oid;
  }

  public void set$oid(String $oid) {
    this.$oid = $oid;
  }
}
