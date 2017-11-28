package es.um.nosql.schemainference.opensanctions;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Embedded
public class Alias
{
  @Property
  private String title;
  public String getTitle() {return this.title;}
  public void setTitle(String title) {this.title = title;}
  
  @Property
  private String last_name;
  public String getLast_name() {return this.last_name;}
  public void setLast_name(String last_name) {this.last_name = last_name;}
  
  @Property
  private String father_name;
  public String getFather_name() {return this.father_name;}
  public void setFather_name(String father_name) {this.father_name = father_name;}
  
  @Property
  private String second_name;
  public String getSecond_name() {return this.second_name;}
  public void setSecond_name(String second_name) {this.second_name = second_name;}
  
  @Property
  private String quality;
  public String getQuality() {return this.quality;}
  public void setQuality(String quality) {this.quality = quality;}
  
  @Property
  private String description;
  public String getDescription() {return this.description;}
  public void setDescription(String description) {this.description = description;}
  
  @Property
  private String name;
  public String getName() {return this.name;}
  public void setName(String name) {this.name = name;}
  
  @Property
  private String first_name;
  public String getFirst_name() {return this.first_name;}
  public void setFirst_name(String first_name) {this.first_name = first_name;}
  
  @Property
  private String third_name;
  public String getThird_name() {return this.third_name;}
  public void setThird_name(String third_name) {this.third_name = third_name;}
  
  @Property
  private String type;
  public String getType() {return this.type;}
  public void setType(String type) {this.type = type;}
}
