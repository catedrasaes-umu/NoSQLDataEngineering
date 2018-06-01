package es.um.nosql.s13e.mongosongs;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PreLoad;
import org.mongodb.morphia.annotations.PreSave;
import com.mongodb.DBObject;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import javax.validation.constraints.NotNull;
import java.util.List;

import es.um.nosql.s13e.mongosongs.Prize;
import es.um.nosql.s13e.mongosongs.Review;
import es.um.nosql.s13e.mongosongs.commons.Commons;

@Entity(value = "album", noClassnameStored = true)
public class Album
{
  @Id
  @NotNull(message = "_id can't be null")
  private String _id;
  public String get_id() {return this._id;}
  public void set_id(String _id) {this._id = _id;}
  
  private String __availability1;
  private List<String> __availability2;
  
  // @Union_String_List<String>
  @SuppressWarnings("unused")
  private Object availability;
  public Object getAvailability()
  {
    if (__availability1 != null) return __availability1;
    if (__availability2 != null) return __availability2;
    return null;
  }
  
  public void setAvailability(Object availability)
  {
    if (availability instanceof String)
    {
      this.__availability1 = (String)availability;
      this.__availability2 = null;
      this.availability = availability;
    }
    else  if (Commons.IS_CASTABLE_LIST(String.class, availability))
    {
      this.__availability2 = Commons.CAST_LIST(String.class, availability);
      this.__availability1 = null;
      this.availability = availability;
    }
    else
      throw new ClassCastException("availability must be of type String or List<String>");
  }
  
  @PreLoad
  private void preLoadUnion_String_ListString(DBObject dbObj)
  {
    if (!dbObj.containsField("availability"))
      return;
  
    Object fieldObj = dbObj.get("availability");
  
    if (fieldObj instanceof String)
      this.__availability1 = (String)fieldObj;
    else 
    if (Commons.IS_CASTABLE_LIST(String.class, fieldObj))
      this.__availability2 = Commons.CAST_LIST(String.class, fieldObj);
    else
      throw new ClassCastException("availability must be of type String or List<String>");
  
    dbObj.removeField("availability");
  }
  
  @PreSave
  private void preSaveUnion_String_ListString(DBObject dbObj)
  {
    if (__availability1 != null)
    {
      dbObj.put("availability", dbObj.get("__availability1"));
      dbObj.removeField("__availability1");
    }
    else  if (__availability2 != null)
    {
      dbObj.put("availability", dbObj.get("__availability2"));
      dbObj.removeField("__availability2");
    }
  }
  
  @Property
  @NotNull(message = "formats can't be null")
  private List<String> formats;
  public List<String> getFormats() {return this.formats;}
  public void setFormats(List<String> formats) {this.formats = formats;}
  
  @Property
  private String genre;
  public String getGenre() {return this.genre;}
  public void setGenre(String genre) {this.genre = genre;}
  
  @Property
  private List<String> genres;
  public List<String> getGenres() {return this.genres;}
  public void setGenres(List<String> genres) {this.genres = genres;}
  
  @Property
  @NotNull(message = "name can't be null")
  private String name;
  public String getName() {return this.name;}
  public void setName(String name) {this.name = name;}
  
  @Embedded
  private List<Prize> prizes;
  public List<Prize> getPrizes() {return this.prizes;}
  public void setPrizes(List<Prize> prizes) {this.prizes = prizes;}
  
  @Property
  @NotNull(message = "releaseYear can't be null")
  private Integer releaseYear;
  public Integer getReleaseYear() {return this.releaseYear;}
  public void setReleaseYear(Integer releaseYear) {this.releaseYear = releaseYear;}
  
  @Embedded
  private List<Review> reviews;
  public List<Review> getReviews() {return this.reviews;}
  public void setReviews(List<Review> reviews) {this.reviews = reviews;}
  
  @Reference(idOnly = true, lazy = true)
  @NotNull(message = "tracks can't be null")
  private List<Track> tracks;
  public List<Track> getTracks() {return this.tracks;}
  public void setTracks(List<Track> tracks) {this.tracks = tracks;}
}
