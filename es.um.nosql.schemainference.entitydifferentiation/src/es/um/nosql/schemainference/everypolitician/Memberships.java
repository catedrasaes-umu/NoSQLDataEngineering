package es.um.nosql.schemainference.everypolitician;

  import org.mongodb.morphia.annotations.Entity;
  import org.mongodb.morphia.annotations.Id;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;

import es.um.nosql.schemainference.everypolitician.Source;

@Entity(value = "memberships", noClassnameStored = true)
public class Memberships
{
  @Id
  @NotNull(message = "_id can't be null")
  private ObjectId _id;
  public ObjectId get_id() {return this._id;}
  public void set_id(ObjectId _id) {this._id = _id;}
  
  @Property
  @NotNull(message = "_type can't be null")
  private String _type;
  public String get_type() {return this._type;}
  public void set_type(String _type) {this._type = _type;}
  
  @Property
  private String area_id;
  public String getArea_id() {return this.area_id;}
  public void setArea_id(String area_id) {this.area_id = area_id;}
  
  @Property
  private String end_date;
  public String getEnd_date() {return this.end_date;}
  public void setEnd_date(String end_date) {this.end_date = end_date;}
  
  @Property
  @NotNull(message = "legislative_period_id can't be null")
  private String legislative_period_id;
  public String getLegislative_period_id() {return this.legislative_period_id;}
  public void setLegislative_period_id(String legislative_period_id) {this.legislative_period_id = legislative_period_id;}
  
  @Property
  @NotNull(message = "on_behalf_of_id can't be null")
  private String on_behalf_of_id;
  public String getOn_behalf_of_id() {return this.on_behalf_of_id;}
  public void setOn_behalf_of_id(String on_behalf_of_id) {this.on_behalf_of_id = on_behalf_of_id;}
  
  @Property
  @NotNull(message = "organization_id can't be null")
  private String organization_id;
  public String getOrganization_id() {return this.organization_id;}
  public void setOrganization_id(String organization_id) {this.organization_id = organization_id;}
  
  @Property
  @NotNull(message = "person_id can't be null")
  private String person_id;
  public String getPerson_id() {return this.person_id;}
  public void setPerson_id(String person_id) {this.person_id = person_id;}
  
  @Property
  @NotNull(message = "role can't be null")
  private String role;
  public String getRole() {return this.role;}
  public void setRole(String role) {this.role = role;}
  
  @Embedded
  @NotNull(message = "sources can't be null")
  private Source[] sources;
  public Source[] getSources() {return this.sources;}
  public void setSources(Source[] sources) {this.sources = sources;}
  
  @Property
  private String start_date;
  public String getStart_date() {return this.start_date;}
  public void setStart_date(String start_date) {this.start_date = start_date;}
}
