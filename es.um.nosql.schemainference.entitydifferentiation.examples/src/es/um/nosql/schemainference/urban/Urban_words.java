package es.um.nosql.schemainference.urban;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Entity(value = "urban_words", noClassnameStored = true)
public class Urban_words
{
  @Id
  @NotNull(message = "_id can't be null")
  private ObjectId _id;
  public ObjectId get_id() {return this._id;}
  public void set_id(ObjectId _id) {this._id = _id;}
  
  @Property
  private String author;
  public String getAuthor() {return this.author;}
  public void setAuthor(String author) {this.author = author;}
  
  @Property
  private Integer defid;
  public Integer getDefid() {return this.defid;}
  public void setDefid(Integer defid) {this.defid = defid;}
  
  @Property
  private String definition;
  public String getDefinition() {return this.definition;}
  public void setDefinition(String definition) {this.definition = definition;}
  
  @Property
  private String example;
  public String getExample() {return this.example;}
  public void setExample(String example) {this.example = example;}
  
  @Property
  private String lowercase_word;
  public String getLowercase_word() {return this.lowercase_word;}
  public void setLowercase_word(String lowercase_word) {this.lowercase_word = lowercase_word;}
  
  @Property
  @NotNull(message = "permalink can't be null")
  private String permalink;
  public String getPermalink() {return this.permalink;}
  public void setPermalink(String permalink) {this.permalink = permalink;}
  
  @Property
  private String[] sounds;
  public String[] getSounds() {return this.sounds;}
  public void setSounds(String[] sounds) {this.sounds = sounds;}
  
  @Property
  private String[] tags;
  public String[] getTags() {return this.tags;}
  public void setTags(String[] tags) {this.tags = tags;}
  
  @Property
  @NotNull(message = "thumbs_down can't be null")
  private Integer thumbs_down;
  public Integer getThumbs_down() {return this.thumbs_down;}
  public void setThumbs_down(Integer thumbs_down) {this.thumbs_down = thumbs_down;}
  
  @Property
  @NotNull(message = "thumbs_up can't be null")
  private Integer thumbs_up;
  public Integer getThumbs_up() {return this.thumbs_up;}
  public void setThumbs_up(Integer thumbs_up) {this.thumbs_up = thumbs_up;}
  
  @Property
  private String word;
  public String getWord() {return this.word;}
  public void setWord(String word) {this.word = word;}
}
