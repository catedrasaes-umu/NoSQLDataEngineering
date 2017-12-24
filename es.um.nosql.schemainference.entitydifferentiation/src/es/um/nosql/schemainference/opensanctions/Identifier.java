package es.um.nosql.schemainference.opensanctions;

import org.mongodb.morphia.annotations.PreLoad;
import com.mongodb.DBObject;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;


@Embedded
public class Identifier
{
  @Property
  private String country;
  public String getCountry() {return this.country;}
  public void setCountry(String country) {this.country = country;}
  
  @Property
  private String country_code;
  public String getCountry_code() {return this.country_code;}
  public void setCountry_code(String country_code) {this.country_code = country_code;}
  
  @Property
  private String description;
  public String getDescription() {return this.description;}
  public void setDescription(String description) {this.description = description;}
  
  @Property
  private String issued_at;
  public String getIssued_at() {return this.issued_at;}
  public void setIssued_at(String issued_at) {this.issued_at = issued_at;}
  
  // @Union_String_Integer
  @Property
  private Object number;
  public Object getNumber() {return this.number;}
  public void setNumber(Object number)
  {
    if (number instanceof String || number instanceof Integer)
      this.number = number;
    else
      throw new ClassCastException("number must be of type String or Integer");
  }
  
  @PreLoad
  private void preLoadUnion_String_Integer(DBObject dbObj)
  {
    if (!dbObj.containsField("number"))
      return;
  
    Object fieldObj = dbObj.get("number");
  
    if (fieldObj instanceof String)
      this.number = (String)fieldObj;
    else 
    if (fieldObj instanceof Integer)
      this.number = (Integer)fieldObj;
    else
      throw new ClassCastException("number must be of type String or Integer");
  
    dbObj.removeField("number");
  }
  
  @Property
  private String type;
  public String getType() {return this.type;}
  public void setType(String type) {this.type = type;}
}
