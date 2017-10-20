package es.um.nosql.schemainference.mongoMovies3;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Entity(noClassnameStored = true)
public class Rating
{
  @Property("score")
  @NotNull(message = "score can't be null")
  private Integer score;
  public Integer getScore() {return this.score;}
  public void setScore(Integer score) {this.score = score;}
  
  @Property("voters")
  @NotNull(message = "voters can't be null")
  private Integer voters;
  public Integer getVoters() {return this.voters;}
  public void setVoters(Integer voters) {this.voters = voters;}
}
