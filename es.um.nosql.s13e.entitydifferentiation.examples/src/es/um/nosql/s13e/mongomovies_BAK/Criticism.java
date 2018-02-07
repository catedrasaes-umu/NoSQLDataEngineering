package es.um.nosql.s13e.mongomovies_BAK;

import es.um.nosql.s13e.mongomovies_BAK.Media;
import es.um.nosql.s13e.mongomovies_BAK.commons.Commons;

import org.mongodb.morphia.annotations.PreLoad;
import org.mongodb.morphia.annotations.PrePersist;

import com.mongodb.DBObject;
import com.mongodb.BasicDBList;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;

import java.util.List;

import javax.validation.constraints.NotNull;

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
  
  // @Union_MediaList_String
  @Embedded
  private Object media;
  public Object getMedia() {return this.media;}
  public void setMedia(Object media)
  {
    if (Commons.IS_CASTABLE_LIST(Media.class, media) || media instanceof String)
      this.media = media;
    else
      throw new ClassCastException("media must be of type Media[] or String");
  }

  @PrePersist
  private void prePersistUnion_Media_String()
  {
    if (this.media != null && Commons.IS_CASTABLE_LIST(Media.class, media))
      this.media = ((List<?>)media).toArray(new Media[0]);
  }

  @PreLoad
  private void preLoadUnion_Media_String(DBObject dbObj)
  {
    if (!dbObj.containsField("media"))
      return;
  
    Object fieldObj = dbObj.get("media");
  
    if (fieldObj instanceof BasicDBList && Commons.IS_CASTABLE_ARRAY(Media.class, (BasicDBList)fieldObj))
      this.media = Commons.CAST_LIST(Media.class, ((BasicDBList)fieldObj));
    else 
    if (fieldObj instanceof String)
      this.media = (String)fieldObj;
    else
      throw new ClassCastException("media must be of type Media[] or String");
  
    dbObj.removeField("media");
  }
}
