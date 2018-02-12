package es.um.nosql.s13e.mongomovies;

import es.um.nosql.s13e.mongomovies.commons.Commons;
import org.mongodb.morphia.annotations.PreLoad;
import org.mongodb.morphia.annotations.PreSave;
import com.mongodb.DBObject;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;
import java.util.List;

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
  
  private List<Media> __media1;
  private String __media2;
  
  // @Union_List<Media>_String
  @SuppressWarnings("unused")
  private Object media;
  public Object getMedia()
  {
    if (__media1 != null) return __media1;
    if (__media2 != null) return __media2;
    return null;
  }
  
  public void setMedia(Object media)
  {
    if (Commons.IS_CASTABLE_LIST(Media.class, media))
    {
      this.__media1 = Commons.CAST_LIST(Media.class, media);
      this.__media2 = null;
      this.media = media;
    }
    else  if (media instanceof String)
    {
      this.__media2 = (String)media;
      this.__media1 = null;
      this.media = media;
    }
    else
      throw new ClassCastException("media must be of type List<Media> or String");
  }
  
  @PreLoad
  private void preLoadUnion_ListMedia_String(DBObject dbObj)
  {
    if (!dbObj.containsField("media"))
      return;
  
    Object fieldObj = dbObj.get("media");
  
    if (Commons.IS_CASTABLE_LIST_OBJDB(Media.class, fieldObj))
      this.__media1 = Commons.CAST_LIST_OBJDB(Media.class, fieldObj);
    else 
    if (fieldObj instanceof String)
      this.__media2 = (String)fieldObj;
    else
      throw new ClassCastException("media must be of type List<Media> or String");
  
    dbObj.removeField("media");
  }
  
  @PreSave
  private void preSaveUnion_ListMedia_String(DBObject dbObj)
  {
    if (__media1 != null)
    {
      dbObj.put("media", dbObj.get("__media1"));
      dbObj.removeField("__media1");
    }
    else  if (__media2 != null)
    {
      dbObj.put("media", dbObj.get("__media2"));
      dbObj.removeField("__media2");
    }
  }
}
