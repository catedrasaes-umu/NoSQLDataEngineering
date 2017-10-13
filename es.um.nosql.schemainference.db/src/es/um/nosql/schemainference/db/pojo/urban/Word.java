package es.um.nosql.schemainference.db.pojo.urban;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonNode;

@JsonPropertyOrder({"_id", "definition", "permalink", "thumbs_up", "author", "word", "defid", "current_vote", "example", "thumbs_down", "tags", "sounds", "lowercase_word"})
public class Word
{
  private JsonNode _id;

  private String definition;

  private String permalink;

  private Integer thumps_up;

  private String author;

  private String word;

  private Integer defid;

  private String current_vote;

  private String example;

  private Integer thumbs_down;

  private String[] tags;

  private String[] sounds;

  private String lowercase_word;

  @JsonProperty("_id")
  public JsonNode get_id() {
    return _id;
  }

  public void set_id(JsonNode _id) {
    this._id = _id;
  }

  @JsonProperty("definition")
  public String getDefinition() {
    return definition;
  }

  public void setDefinition(String definition) {
    this.definition = definition;
  }

  @JsonProperty("permalink")
  public String getPermalink() {
    return permalink;
  }

  public void setPermalink(String permalink) {
    this.permalink = permalink;
  }

  @JsonProperty("thumbs_up")
  public Integer getThumps_up() {
    return thumps_up;
  }

  public void setThumps_up(Integer thumps_up) {
    this.thumps_up = thumps_up;
  }

  @JsonProperty("author")
  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  @JsonProperty("word")
  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  @JsonProperty("defid")
  public Integer getDefid() {
    return defid;
  }

  public void setDefid(Integer defid) {
    this.defid = defid;
  }

  @JsonProperty("current_vote")
  public String getCurrent_vote() {
    return current_vote;
  }

  public void setCurrent_vote(String current_vote) {
    this.current_vote = current_vote;
  }

  @JsonProperty("example")
  public String getExample() {
    return example;
  }

  public void setExample(String example) {
    this.example = example;
  }

  @JsonProperty("thumbs_down")
  public Integer getThumbs_down() {
    return thumbs_down;
  }

  public void setThumbs_down(Integer thumbs_down) {
    this.thumbs_down = thumbs_down;
  }

  @JsonProperty("tags")
  public String[] getTags() {
    return tags;
  }

  public void setTags(String[] tags) {
    this.tags = tags;
  }

  @JsonProperty("sounds")
  public String[] getSounds() {
    return sounds;
  }

  public void setSounds(String[] sounds) {
    this.sounds = sounds;
  }

  @JsonProperty("lowercase_word")
  public String getLowercase_word() {
    return lowercase_word;
  }

  public void setLowercase_word(String lowercase_word) {
    this.lowercase_word = lowercase_word;
  }
}
