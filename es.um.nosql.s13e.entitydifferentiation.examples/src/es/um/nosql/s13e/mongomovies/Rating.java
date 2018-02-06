package es.um.nosql.s13e.mongomovies;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Embedded
public class Rating
{
  @Property
  @NotNull(message = "score can't be null")
  private Integer score;
  public Integer getScore() {return this.score;}
  public void setScore(Integer score) {this.score = score;}
  
  @Property
  @NotNull(message = "voters can't be null")
  private Integer voters;
  public Integer getVoters() {return this.voters;}
  public void setVoters(Integer voters) {this.voters = voters;}
}
