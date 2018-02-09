package es.um.nosql.s13e.test3;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.bson.types.ObjectId;
import es.um.nosql.s13e.test3.commons.Commons;
import org.mongodb.morphia.annotations.PreLoad;
import com.mongodb.DBObject;
import com.mongodb.BasicDBList;

import org.mongodb.morphia.annotations.Converters;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import javax.validation.constraints.NotNull;

import es.um.nosql.s13e.test3.PersonalData;

@Entity(value = "persons", noClassnameStored = true)
@Converters(value = { null })
public class Persons
{
  @Id
  @NotNull(message = "_id can't be null")
  private ObjectId _id;
  public ObjectId get_id() {return this._id;}
  public void set_id(ObjectId _id) {this._id = _id;}
  
  // @Union_String_PersonalData[]
  @Embedded
  private Object data;
  public Object getData()
  {
    return this.data;
  }

  public void setData(Object data)
  {
    if (data instanceof String || data instanceof PersonalData[])
      this.data = data;
    else
      throw new ClassCastException("data must be of type String or PersonalData[]");
  }

  @PreLoad
  private void preLoadUnion_String_PersonalData(DBObject dbObj)
  {
    if (!dbObj.containsField("data"))
      return;
  
    Object fieldObj = dbObj.get("data");
  
    if (fieldObj instanceof String)
      this.data = (String)fieldObj;
    else 
    if (fieldObj instanceof BasicDBList && Commons.IS_CASTABLE_ARRAY(PersonalData.class, (BasicDBList)fieldObj))
      this.data = Commons.CAST_ARRAY(PersonalData.class, ((BasicDBList)fieldObj).toArray());
    else
      throw new ClassCastException("data must be of type String or PersonalData[]");
  
    dbObj.removeField("data");
  }
  
  @Reference(idOnly = true, lazy = true)
  private CustomDate1 dates;
  public CustomDate1 getDates() {return this.dates;}
  public void setDates(CustomDate1 dates) {this.dates = dates;}
}
