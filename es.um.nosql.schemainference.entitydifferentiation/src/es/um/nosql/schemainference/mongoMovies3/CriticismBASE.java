package es.um.nosql.schemainference.mongoMovies3;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.PreLoad;
import org.mongodb.morphia.annotations.Property;

import com.mongodb.DBObject;

import javax.validation.constraints.NotNull;

import es.um.nosql.schemainference.mongoMovies3.Medium;
import es.um.nosql.schemainference.mongoMovies3.commons.Commons;

@Embedded
public class CriticismBASE
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
  
  //@UnionMediumString
  @Embedded
  private Object media;
  public Object getMedia() {return this.media;}
  public void setMedia(Object media) throws ClassCastException
  {
    if (media instanceof Medium || media instanceof String)
      this.media = media;
    else
      throw new ClassCastException("Type must be Medium or String");
}

  @PreLoad
  private void preloadProcess(DBObject dbobj)
  {
    if (dbobj.containsField("media"))
    {
      if (dbobj.get("media") instanceof DBObject && ((DBObject)dbobj.get("media")).get("className").equals("es.um.nosql.schemainference.mongoMovies3.Medium"))
      {
        this.media = Commons.CAST(Medium.class, dbobj.get("media"));
        System.out.println(this.media);
      }
      else if (dbobj.get("media") instanceof String)
      {
        this.media = (String)dbobj.get("media");
      }
      dbobj.removeField("media");
    }
  }
}

