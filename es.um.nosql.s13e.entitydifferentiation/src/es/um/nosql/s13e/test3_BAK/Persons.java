package es.um.nosql.s13e.test3_BAK;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

import es.um.nosql.s13e.test3_BAK.PersonalData;
import es.um.nosql.s13e.test3_BAK.commons.Commons;

import org.mongodb.morphia.annotations.PreLoad;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.PreSave;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import org.mongodb.morphia.annotations.Reference;

import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity(value = "persons", noClassnameStored = true)
public class Persons
{
  @Id
  @NotNull(message = "_id can't be null")
  private ObjectId _id;
  public ObjectId get_id() {return this._id;}
  public void set_id(ObjectId _id) {this._id = _id;}

  private String __field1;
  private List<PersonalData> __field2;

  @NotNull(message = "yodawg")
  @Indexed
  private Object field;

  // @Union_String_List<PersonalData>
  public Object getData()
  {
    if (__field1 != null) return __field1;
    if (__field2 != null) return __field2;
    return null;
  }

  public void setData(Object data)
  {
    if (data instanceof String)
    {
      __field1 = (String)data;
      __field2 = null;
      field  = data;
    }
    else if (Commons.IS_CASTABLE_LIST(PersonalData.class, data))
    {
      __field1 = null;
      __field2 = Commons.CAST_LIST(PersonalData.class, data);
      field = data;
    }
    else
      throw new ClassCastException("data must be of type String or List<PersonalData>");
  }
  
  @PreLoad
  private void preLoadUnion_String_ListPersonalData(DBObject dbObj)
  {
    if (!dbObj.containsField("field"))
      return;
  
    Object fieldObj = dbObj.get("field");

    if (fieldObj instanceof String)
    {
      this.__field1 = (String)fieldObj;
    }
    else if (Commons.IS_CASTABLE_LIST_OBJDB(PersonalData.class, fieldObj))
    {
      this.__field2 = Commons.CAST_LIST_OBJDB(PersonalData.class, fieldObj);
    }
    else
      throw new ClassCastException("data must be of type String or List<PersonalData>");

    dbObj.removeField("field");
  }

  @PreSave
  private void preSaveUnion_String_ListPersonalData(DBObject dbObj)
  {
    if (__field1 != null)
    {
      dbObj.put("field", dbObj.get("__field1"));
      dbObj.removeField("__field1");
    }
    else  if (__field2 != null)
    {
      dbObj.put("field", dbObj.get("__field2"));
      dbObj.removeField("__field2");
    }
  }

  @Reference(idOnly = true, lazy = true)
  private CustomDate1 dates;
  public CustomDate1 getDates() {return this.dates;}
  public void setDates(CustomDate1 dates) {this.dates = dates;}
}
