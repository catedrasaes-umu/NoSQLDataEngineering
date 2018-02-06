package es.um.nosql.s13e.mongomovies;

import es.um.nosql.s13e.mongomovies.commons.Commons;
import org.mongodb.morphia.annotations.PreLoad;
import com.mongodb.DBObject;
import com.mongodb.BasicDBList;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;

import es.um.nosql.s13e.mongomovies.Media;

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
  
  // @Union_Media[]_String
  @Embedded
  private Object media;
  public Object getMedia() {return this.media;}
  public void setMedia(Object media)
  {
    if (media instanceof Media[] || media instanceof String)
      this.media = media;
    else
      throw new ClassCastException("media must be of type Media[] or String");
  }
  
  @PreLoad
  private void preLoadUnion_Media_String(DBObject dbObj)
  {
    if (!dbObj.containsField("media"))
      return;
  
    Object fieldObj = dbObj.get("media");
  
    if (fieldObj instanceof BasicDBList && Commons.IS_CASTABLE_ARRAY(Media.class, (BasicDBList)fieldObj))
      this.media = Commons.CAST_ARRAY(Media.class, ((BasicDBList)fieldObj).toArray());
    else 
    if (fieldObj instanceof String)
      this.media = (String)fieldObj;
    else
      throw new ClassCastException("media must be of type Media[] or String");
  
    dbObj.removeField("media");
  }
}
