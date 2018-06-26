package es.um.nosql.s13e.mongosongs_w_yaml;

import es.um.nosql.s13e.mongosongs_w_yaml.Media;
import es.um.nosql.s13e.mongosongs_w_yaml.commons.Commons;

import org.mongodb.morphia.annotations.PreLoad;
import org.mongodb.morphia.annotations.PreSave;
import com.mongodb.DBObject;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Embedded
public class Review
{
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
  
  @Property
  @NotNull(message = "rank can't be null")
  @Pattern(regexp = "Excelent|Very good|Good|Poor|Terrible", flags = Pattern.Flag.CASE_INSENSITIVE, message = "A review rank may be \"Excelent\", \"Very good\", \"Good\", \"Poor\" or \"Terrible\"")
  private String rank;
  public String getRank() {return this.rank;}
  public void setRank(String rank) {this.rank = rank;}
  
  private Integer __stars1;
  private String __stars2;
  
  // @Union_Integer_String
  @Max(value = 5)
  @Min(value = 0)
  @SuppressWarnings("unused")
  private Object stars;
  public Object getStars()
  {
    if (__stars1 != null) return __stars1;
    if (__stars2 != null) return __stars2;
    return null;
  }
  
  public void setStars(Object stars)
  {
    if (stars instanceof Integer)
    {
      this.__stars1 = (Integer)stars;
      this.__stars2 = null;
      this.stars = stars;
    }
    else  if (stars instanceof String)
    {
      this.__stars2 = (String)stars;
      this.__stars1 = null;
      this.stars = stars;
    }
    else
      throw new ClassCastException("stars must be of type Integer or String");
  }
  
  @PreLoad
  private void preLoadUnion_Integer_String(DBObject dbObj)
  {
    if (!dbObj.containsField("stars"))
      return;
  
    Object fieldObj = dbObj.get("stars");
  
    if (fieldObj instanceof Integer)
      this.__stars1 = (Integer)fieldObj;
    else 
    if (fieldObj instanceof String)
      this.__stars2 = (String)fieldObj;
    else
      throw new ClassCastException("stars must be of type Integer or String");
  
    dbObj.removeField("stars");
  }
  
  @PreSave
  private void preSaveUnion_Integer_String(DBObject dbObj)
  {
    if (__stars1 != null)
    {
      dbObj.put("stars", dbObj.get("__stars1"));
      dbObj.removeField("__stars1");
    }
    else  if (__stars2 != null)
    {
      dbObj.put("stars", dbObj.get("__stars2"));
      dbObj.removeField("__stars2");
    }
  }
}
