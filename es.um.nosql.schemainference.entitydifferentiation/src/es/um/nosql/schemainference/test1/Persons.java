package es.um.nosql.schemainference.test1;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import es.um.nosql.schemainference.test1.commons.Commons;
import org.mongodb.morphia.annotations.PreLoad;
import com.mongodb.DBObject;
import com.mongodb.BasicDBList;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import javax.validation.constraints.NotNull;

import es.um.nosql.schemainference.test1.CustomDate1;
import es.um.nosql.schemainference.test1.PersonalData;
import es.um.nosql.schemainference.test1.CustomDate2;

@Entity(value = "persons", noClassnameStored = true)
public class Persons
{
  @Id
  @NotNull(message = "_id can't be null")
  private String _id;
  public String get_id() {return this._id;}
  public void set_id(String _id) {this._id = _id;}
  
  // @Union_PersonalData_String
  @Embedded
  private Object data;
  public Object getData() {return this.data;}
  public void setData(Object data)
  {
    if (data instanceof PersonalData || data instanceof String)
      this.data = data;
    else
      throw new ClassCastException("data must be of type PersonalData or String");
  }
  
  @PreLoad
  private void processUnion_PersonalData_String(DBObject dbObj)
  {
    if (!dbObj.containsField("data"))
      return;
  
    Object fieldObj = dbObj.get("data");
  
    if (fieldObj instanceof DBObject && Commons.IS_CASTABLE(PersonalData.class, (DBObject)fieldObj))
      this.data = Commons.CAST(PersonalData.class, fieldObj);
    else 
    if (fieldObj instanceof String)
      this.data = (String)fieldObj;
  
    dbObj.removeField("data");
  }
  
  // @Union_CustomDate1_CustomDate2_String
  @Embedded
  private Object dates;
  public Object getDates() {return this.dates;}
  public void setDates(Object dates)
  {
    if (dates instanceof CustomDate1 || dates instanceof CustomDate2 || dates instanceof CustomDate4)
      this.dates = dates;
    else
      throw new ClassCastException("dates must be of type CustomDate1 or CustomDate2 or CustomDate4");
  }
  
  @PreLoad
  private void processUnion_CustomDate1_CustomDate2_String(DBObject dbObj)
  {
    if (!dbObj.containsField("dates"))
      return;
  
    Object fieldObj = dbObj.get("dates");
  
    if (fieldObj instanceof DBObject && Commons.IS_CASTABLE(CustomDate1.class, (DBObject)fieldObj))
      this.dates = Commons.CAST(CustomDate1.class, fieldObj);
    else 
    if (fieldObj instanceof DBObject && Commons.IS_CASTABLE(CustomDate2.class, (DBObject)fieldObj))
      this.dates = Commons.CAST(CustomDate2.class, fieldObj);
    else 
    if (fieldObj instanceof String)
      this.dates = (String)fieldObj;
  
    dbObj.removeField("dates");
  }
}
