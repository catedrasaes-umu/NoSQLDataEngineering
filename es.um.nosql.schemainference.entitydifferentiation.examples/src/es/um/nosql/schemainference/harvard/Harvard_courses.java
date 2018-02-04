package es.um.nosql.schemainference.harvard;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Entity(value = "harvard_courses", noClassnameStored = true)
public class Harvard_courses
{
  @Property
  private String LoE_DI;
  public String getLoE_DI() {return this.LoE_DI;}
  public void setLoE_DI(String LoE_DI) {this.LoE_DI = LoE_DI;}
  
  @Property
  private String YoB;
  public String getYoB() {return this.YoB;}
  public void setYoB(String YoB) {this.YoB = YoB;}
  
  @Id
  @NotNull(message = "_id can't be null")
  private ObjectId _id;
  public ObjectId get_id() {return this._id;}
  public void set_id(ObjectId _id) {this._id = _id;}
  
  @Property
  @NotNull(message = "certified can't be null")
  private Integer certified;
  public Integer getCertified() {return this.certified;}
  public void setCertified(Integer certified) {this.certified = certified;}
  
  @Property
  @NotNull(message = "course_id can't be null")
  private String course_id;
  public String getCourse_id() {return this.course_id;}
  public void setCourse_id(String course_id) {this.course_id = course_id;}
  
  @Property
  @NotNull(message = "explored can't be null")
  private Integer explored;
  public Integer getExplored() {return this.explored;}
  public void setExplored(Integer explored) {this.explored = explored;}
  
  @Property
  @NotNull(message = "final_cc_cname_DI can't be null")
  private String final_cc_cname_DI;
  public String getFinal_cc_cname_DI() {return this.final_cc_cname_DI;}
  public void setFinal_cc_cname_DI(String final_cc_cname_DI) {this.final_cc_cname_DI = final_cc_cname_DI;}
  
  @Property
  private String gender;
  public String getGender() {return this.gender;}
  public void setGender(String gender) {this.gender = gender;}
  
  @Property
  private String grade;
  public String getGrade() {return this.grade;}
  public void setGrade(String grade) {this.grade = grade;}
  
  @Property
  private Integer incomplete_flag;
  public Integer getIncomplete_flag() {return this.incomplete_flag;}
  public void setIncomplete_flag(Integer incomplete_flag) {this.incomplete_flag = incomplete_flag;}
  
  @Property
  private String last_event_DI;
  public String getLast_event_DI() {return this.last_event_DI;}
  public void setLast_event_DI(String last_event_DI) {this.last_event_DI = last_event_DI;}
  
  @Property
  private Integer nchapters;
  public Integer getNchapters() {return this.nchapters;}
  public void setNchapters(Integer nchapters) {this.nchapters = nchapters;}
  
  @Property
  private Integer ndays_act;
  public Integer getNdays_act() {return this.ndays_act;}
  public void setNdays_act(Integer ndays_act) {this.ndays_act = ndays_act;}
  
  @Property
  private Integer nevents;
  public Integer getNevents() {return this.nevents;}
  public void setNevents(Integer nevents) {this.nevents = nevents;}
  
  @Property
  @NotNull(message = "nforum_posts can't be null")
  private Integer nforum_posts;
  public Integer getNforum_posts() {return this.nforum_posts;}
  public void setNforum_posts(Integer nforum_posts) {this.nforum_posts = nforum_posts;}
  
  @Property
  private Integer nplay_video;
  public Integer getNplay_video() {return this.nplay_video;}
  public void setNplay_video(Integer nplay_video) {this.nplay_video = nplay_video;}
  
  @Property
  @NotNull(message = "registered can't be null")
  private Integer registered;
  public Integer getRegistered() {return this.registered;}
  public void setRegistered(Integer registered) {this.registered = registered;}
  
  @Property
  @NotNull(message = "start_time_DI can't be null")
  private String start_time_DI;
  public String getStart_time_DI() {return this.start_time_DI;}
  public void setStart_time_DI(String start_time_DI) {this.start_time_DI = start_time_DI;}
  
  @Property
  @NotNull(message = "userid_DI can't be null")
  private String userid_DI;
  public String getUserid_DI() {return this.userid_DI;}
  public void setUserid_DI(String userid_DI) {this.userid_DI = userid_DI;}
  
  @Property
  @NotNull(message = "viewed can't be null")
  private Integer viewed;
  public Integer getViewed() {return this.viewed;}
  public void setViewed(Integer viewed) {this.viewed = viewed;}
}
