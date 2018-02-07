package es.um.nosql.s13e.everypolitician;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import javax.validation.constraints.NotNull;

import es.um.nosql.s13e.everypolitician.Identifier;

@Entity(value = "events", noClassnameStored = true)
public class Events
{
  @Id
  @NotNull(message = "_id can't be null")
  private String _id;
  public String get_id() {return this._id;}
  public void set_id(String _id) {this._id = _id;}
  
  @Property
  @NotNull(message = "classification can't be null")
  private String classification;
  public String getClassification() {return this.classification;}
  public void setClassification(String classification) {this.classification = classification;}
  
  @Property
  @NotNull(message = "end_date can't be null")
  private String end_date;
  public String getEnd_date() {return this.end_date;}
  public void setEnd_date(String end_date) {this.end_date = end_date;}
  
  @Embedded
  private Identifier[] identifiers;
  public Identifier[] getIdentifiers() {return this.identifiers;}
  public void setIdentifiers(Identifier[] identifiers) {this.identifiers = identifiers;}
  
  @Property
  @NotNull(message = "name can't be null")
  private String name;
  public String getName() {return this.name;}
  public void setName(String name) {this.name = name;}
  
  @Reference(idOnly = true, lazy = true)
  private Organizations organization_id;
  public Organizations getOrganization_id() {return this.organization_id;}
  public void setOrganization_id(Organizations organization_id) {this.organization_id = organization_id;}
  
  @Property
  @NotNull(message = "start_date can't be null")
  private String start_date;
  public String getStart_date() {return this.start_date;}
  public void setStart_date(String start_date) {this.start_date = start_date;}
}
