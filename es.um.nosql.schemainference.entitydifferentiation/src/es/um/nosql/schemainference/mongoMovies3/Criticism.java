package es.um.nosql.schemainference.mongoMovies3;

import es.um.nosql.schemainference.mongoMovies3.commons.Commons;
import org.mongodb.morphia.annotations.PreLoad;
import com.mongodb.DBObject;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;

import es.um.nosql.schemainference.mongoMovies3.Medium;

@Embedded
public class Criticism
{
  @Property
  @NotNull(message = "color can't be null")
  private String color;
  public String getColor() {return this.color;}
  public void setColor(String color) {this.color = color;}
  
  @Property
  @NotNull(message = "journalist can't be null")
  private String journalist;
  public String getJournalist() {return this.journalist;}
  public void setJournalist(String journalist) {this.journalist = journalist;}
  
  // @Union_Medium_String
  @Embedded
  private Object media;
  public Object getMedia() {return this.media;}
  public void setMedia(Object media)
  {
    if (media instanceof Medium || media instanceof String)
      this.media = media;
    else
      throw new ClassCastException("media must be of type Medium or String");
  }
  
  @PreLoad
  private void processUnion_Medium_String(DBObject dbObj)
  {
    if (!dbObj.containsField("media"))
      return;
  
    Object fieldObj = dbObj.get("media");
  
    if (fieldObj instanceof DBObject && ((DBObject)fieldObj).get("className").equals(Medium.class.getCanonicalName()))
      this.media = Commons.CAST(Medium.class, fieldObj);
    else 
    if (fieldObj instanceof String)
      this.media = (String)fieldObj;
  
    dbObj.removeField("media");
  }
}
