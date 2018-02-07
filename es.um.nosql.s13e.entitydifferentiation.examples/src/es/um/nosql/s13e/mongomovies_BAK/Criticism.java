package es.um.nosql.s13e.mongomovies_BAK;

import es.um.nosql.s13e.mongomovies_BAK.Media;
import es.um.nosql.s13e.mongomovies_BAK.commons.Commons;

import org.mongodb.morphia.annotations.PreLoad;
import org.mongodb.morphia.annotations.PrePersist;

import com.mongodb.DBObject;
import com.mongodb.BasicDBList;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;

import java.util.Arrays;
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
  public Object getMedia()
  {
    if (media instanceof String)
      return (String)this.media;
    if (media instanceof Media[])
      return Arrays.asList((Media[])media);

    return media;
  }

  public void setMedia(Object media)
  {
    if ((media instanceof BasicDBList && Commons.IS_CASTABLE_LIST(Media.class, (BasicDBList)media)))
      this.media = Commons.CAST_LIST(Media.class, (List<Media>)media).toArray(new Media[0]);
    else if (media instanceof String)
      this.media = (String)media;
    else
      throw new ClassCastException("media must be of type Media[] or String");
  }

  @PreLoad
  private void preLoadUnion_Media_String(DBObject dbObj)
  {
    if (!dbObj.containsField("media"))
      return;
  
    Object fieldObj = dbObj.get("media");

    if (fieldObj instanceof BasicDBList && Commons.IS_CASTABLE_LIST(Media.class, (BasicDBList)fieldObj))
      this.media = Commons.CAST_LIST(Media.class, ((List<Media>)fieldObj)).toArray(new Media[0]);
    else 
    if (fieldObj instanceof String)
      this.media = (String)fieldObj;
    else
      throw new ClassCastException("media must be of type Media[] or String");
  
    dbObj.removeField("media");
  }
}
