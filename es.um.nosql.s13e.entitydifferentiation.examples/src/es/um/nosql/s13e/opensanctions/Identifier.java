package es.um.nosql.s13e.opensanctions;

import org.mongodb.morphia.annotations.PreLoad;
import org.mongodb.morphia.annotations.PreSave;
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
  
  private String __number1;
  private Integer __number2;
  
  // @Union_String_Integer
  @SuppressWarnings("unused")
  private Object number;
  public Object getNumber()
  {
    if (__number1 != null) return __number1;
    if (__number2 != null) return __number2;
    return null;
  }
  
  public void setNumber(Object number)
  {
    if (number instanceof String)
    {
      this.__number1 = (String)number;
      this.__number2 = null;
      this.number = number;
    }
    else  if (number instanceof Integer)
    {
      this.__number2 = (Integer)number;
      this.__number1 = null;
      this.number = number;
    }
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
      this.__number1 = (String)fieldObj;
    else 
    if (fieldObj instanceof Integer)
      this.__number2 = (Integer)fieldObj;
    else
      throw new ClassCastException("number must be of type String or Integer");
  
    dbObj.removeField("number");
  }
  
  @PreSave
  private void preSaveUnion_String_Integer(DBObject dbObj)
  {
    if (__number1 != null)
    {
      dbObj.put("number", dbObj.get("__number1"));
      dbObj.removeField("__number1");
    }
    else  if (__number2 != null)
    {
      dbObj.put("number", dbObj.get("__number2"));
      dbObj.removeField("__number2");
    }
  }
  
  @Property
  private String type;
  public String getType() {return this.type;}
  public void setType(String type) {this.type = type;}
}
