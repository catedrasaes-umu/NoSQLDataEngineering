package es.um.nosql.s13e.mongosongs;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity(value = "artist", noClassnameStored = true)
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
  
  @Reference(idOnly = true, lazy = true)
  @NotNull(message = "composedTracks can't be null")
  private List<Track> composedTracks;
  public List<Track> getComposedTracks() {return this.composedTracks;}
  public void setComposedTracks(List<Track> composedTracks) {this.composedTracks = composedTracks;}
  
  @Reference(idOnly = true, lazy = true)
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
  private Integer startingYear;
  public Integer getStartingYear() {return this.startingYear;}
  public void setStartingYear(Integer startingYear) {this.startingYear = startingYear;}
}
