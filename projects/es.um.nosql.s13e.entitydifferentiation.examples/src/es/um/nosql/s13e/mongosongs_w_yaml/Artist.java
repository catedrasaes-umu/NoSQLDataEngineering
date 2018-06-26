package es.um.nosql.s13e.mongosongs_w_yaml;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import javax.validation.constraints.NotNull;
import java.util.List;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.utils.IndexType;
import org.mongodb.morphia.annotations.IndexOptions;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Entity(value = "artist", noClassnameStored = true)
@Indexes({
  @Index(fields = {@Field(value = "name", type = IndexType.DESC)}, options = @IndexOptions(sparse = true, background = false, unique = true))
})
public class Artist
{
  @Id
  @NotNull(message = "_id can't be null")
  private String _id;
  public String get_id() {return this._id;}
  public void set_id(String _id) {this._id = _id;}
  
  @Reference(idOnly = true, lazy = true)
  @NotNull(message = "albums can't be null")
  private List<Album> albums;
  public List<Album> getAlbums() {return this.albums;}
  public void setAlbums(List<Album> albums) {this.albums = albums;}
  
  @Reference(idOnly = true)
  @NotNull(message = "composedTracks can't be null")
  private List<Track> composedTracks;
  public List<Track> getComposedTracks() {return this.composedTracks;}
  public void setComposedTracks(List<Track> composedTracks) {this.composedTracks = composedTracks;}
  
  @Reference(idOnly = true)
  private List<Track> lyricsTracks;
  public List<Track> getLyricsTracks() {return this.lyricsTracks;}
  public void setLyricsTracks(List<Track> lyricsTracks) {this.lyricsTracks = lyricsTracks;}
  
  @Property
  @NotNull(message = "name can't be null")
  private String name;
  public String getName() {return this.name;}
  public void setName(String name) {this.name = name;}
  
  @Property
  @NotNull(message = "startingYear can't be null")
  @Max(value = 2018, message = "Starting year of a group must be > 1900 and < 2018")
  @Min(value = 1900, message = "Starting year of a group must be > 1900 and < 2018")
  private Integer startingYear;
  public Integer getStartingYear() {return this.startingYear;}
  public void setStartingYear(Integer startingYear) {this.startingYear = startingYear;}
}
