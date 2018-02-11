package es.um.nosql.s13e.test3;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.bson.types.ObjectId;
import es.um.nosql.s13e.test3.commons.Commons;
import org.mongodb.morphia.annotations.PreLoad;
import com.mongodb.DBObject;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import javax.validation.constraints.NotNull;
import java.util.List;

import es.um.nosql.s13e.test3.PersonalData;

@Entity(value = "persons", noClassnameStored = true)
public class Persons
{
  @Id
  @NotNull(message = "_id can't be null")
  private ObjectId _id;
  public ObjectId get_id() {return this._id;}
  public void set_id(ObjectId _id) {this._id = _id;}
  
  // @Union_String_List<PersonalData>
  @Embedded
  private Object data;
  public Object getData() {return this.data;}
  public void setData(Object data)
  {
    if (data instanceof String
     || Commons.IS_CASTABLE_LIST(PersonalData.class, data)
    )
      this.data = data;
    else
      throw new ClassCastException("data must be of type String or List<PersonalData>");
  }
  
  @PreLoad
  private void preLoadUnion_String_ListPersonalData(DBObject dbObj)
  {
    if (!dbObj.containsField("data"))
      return;
  
    Object fieldObj = dbObj.get("data");
  
    if (fieldObj instanceof String)
      this.data = (String)fieldObj;
    else 
    if (Commons.IS_CASTABLE_LIST_OBJDB(PersonalData.class, fieldObj))
      this.data = Commons.CAST_LIST(PersonalData.class, fieldObj);
    else
      throw new ClassCastException("data must be of type String or List<PersonalData>");
  
    dbObj.removeField("data");
  }
  
  @Reference(idOnly = true, lazy = true)
  private CustomDate1 dates;
  public CustomDate1 getDates() {return this.dates;}
  public void setDates(CustomDate1 dates) {this.dates = dates;}
}
