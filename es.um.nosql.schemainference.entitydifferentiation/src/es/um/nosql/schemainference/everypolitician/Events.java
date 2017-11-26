package es.um.nosql.schemainference.everypolitician;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;

import es.um.nosql.schemainference.everypolitician.Identifier;

@Entity(value = "events", noClassnameStored = true)
public class Events
{
  @Id
  private ObjectId _id;
  public ObjectId getObjectId() {return this._id;}
  public void setObjectId(ObjectId _id) {this._id = _id;}

  @Property
  @NotNull(message = "id can't be null")
  private String id;
  public String getId() {return this.id;}
  public void setId(String id) {this.id = id;}
  
  @Property
  @NotNull(message = "end_date can't be null")
  private String end_date;
  public String getEnd_date() {return this.end_date;}
  public void setEnd_date(String end_date) {this.end_date = end_date;}
  
  @Property
  @NotNull(message = "classification can't be null")
  private String classification;
  public String getClassification() {return this.classification;}
  public void setClassification(String classification) {this.classification = classification;}
  
  @Property
  @NotNull(message = "name can't be null")
  private String name;
  public String getName() {return this.name;}
  public void setName(String name) {this.name = name;}
  
  @Property
  private String type;
  public String getType() {return this.type;}
  public void setType(String type) {this.type = type;}
  
  @Property
  @NotNull(message = "start_date can't be null")
  private String start_date;
  public String getStart_date() {return this.start_date;}
  public void setStart_date(String start_date) {this.start_date = start_date;}
  
  @Embedded
  private Identifier[] identifiers;
  public Identifier[] getIdentifiers() {return this.identifiers;}
  public void setIdentifiers(Identifier[] identifiers) {this.identifiers = identifiers;}
  
  @Property
  private String organization_id;
  public String getOrganization_id() {return this.organization_id;}
  public void setOrganization_id(String organization_id) {this.organization_id = organization_id;}
}
