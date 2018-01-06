package es.um.nosql.schemainference.test2;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Field;
import static org.mongodb.morphia.utils.IndexType.DESC;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Validation;

import javax.validation.constraints.NotNull;


@Entity(value = "persons", noClassnameStored = true)
@Indexes({
  @Index(fields = @Field(value = "age", type = DESC)),
  @Index(fields = @Field(value = "isEmployed"), options = @IndexOptions(name = "indexing_test"))
})
//@Validation("{ age : { $gt : 10 } }")
public class Persons
{
  @Id
  @NotNull(message = "_id can't be null")
  private ObjectId _id;
  public ObjectId get_id() {return this._id;}
  public void set_id(ObjectId _id) {this._id = _id;}
  
  @Property
  @NotNull(message = "age can't be null")
  private Integer age;
  public Integer getAge() {return this.age;}
  public void setAge(Integer age) {this.age = age;}
  
  @Property
  @NotNull(message = "isEmployed can't be null")
  private Boolean isEmployed;
  public Boolean getIsEmployed() {return this.isEmployed;}
  public void setIsEmployed(Boolean isEmployed) {this.isEmployed = isEmployed;}
  
  @Property
  @NotNull(message = "isMarried can't be null")
  private Boolean isMarried;
  public Boolean getIsMarried() {return this.isMarried;}
  public void setIsMarried(Boolean isMarried) {this.isMarried = isMarried;}
  
  @Property
  @NotNull(message = "name can't be null")
  private String name;
  public String getName() {return this.name;}
  public void setName(String name) {this.name = name;}
  
  @Property
  @NotNull(message = "status can't be null")
  private String status;
  public String getStatus() {return this.status;}
  public void setStatus(String status) {this.status = status;}
  
  @Property
  @NotNull(message = "surname can't be null")
  private String surname;
  public String getSurname() {return this.surname;}
  public void setSurname(String surname) {this.surname = surname;}
}
