package es.um.nosql.schemainference.db.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"course_id", "userid_DI", "registered", "viewed", "explored", "certified", "final_cc_cname_DI", "LoE_DI", "YoB", "gender",
  "grade", "start_time_DI", "last_event_DI", "nevents", "ndays_act", "nplay_video", "nchapters", "nforum_posts", "roles", "incomplete_flag"})
public class HarvardPOJO
{
  private String course_id;

  private String userid_DI;

  private Integer registered;

  private Integer viewed;

  private Integer explored;

  private Integer certified;

  private String final_cc_cname_DI;

  private String LoE_DI;

  private String YoB;

  private String gender;

  private String grade;

  private String start_time_DI;

  private String last_event_DI;

  private Integer nevents;

  private Integer ndays_act;

  private Integer nplay_video;

  private Integer nchapters;

  private Integer nforum_posts;

  private String roles;

  private Integer incomplete_flag;

  @JsonProperty("course_id")
  public String getCourse_id() {
    return course_id;
  }

  public void setCourse_id(String course_id) {
    this.course_id = course_id;
  }

  @JsonProperty("userid_DI")
  public String getUserid_DI() {
    return userid_DI;
  }

  public void setUserid_DI(String userid_DI) {
    this.userid_DI = userid_DI;
  }

  @JsonProperty("registered")
  public Integer getRegistered() {
    return registered;
  }

  public void setRegistered(Integer registered) {
    this.registered = registered;
  }

  @JsonProperty("viewed")
  public Integer getViewed() {
    return viewed;
  }

  public void setViewed(Integer viewed) {
    this.viewed = viewed;
  }

  @JsonProperty("explored")
  public Integer getExplored() {
    return explored;
  }

  public void setExplored(Integer explored) {
    this.explored = explored;
  }

  @JsonProperty("certified")
  public Integer getCertified() {
    return certified;
  }

  public void setCertified(Integer certified) {
    this.certified = certified;
  }

  @JsonProperty("final_cc_cname_DI")
  public String getFinal_cc_cname_DI() {
    return final_cc_cname_DI;
  }

  public void setFinal_cc_cname_DI(String final_cc_cname_DI) {
    this.final_cc_cname_DI = final_cc_cname_DI;
  }

  @JsonProperty("LoE_DI")
  public String getLoE_DI() {
    return LoE_DI;
  }

  public void setLoE_DI(String loE_DI) {
    LoE_DI = loE_DI;
  }

  @JsonProperty("YoB")
  public String getYoB() {
    return YoB;
  }

  public void setYoB(String yoB) {
    YoB = yoB;
  }

  @JsonProperty("gender")
  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  @JsonProperty("grade")
  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  @JsonProperty("start_time_DI")
  public String getStart_time_DI() {
    return start_time_DI;
  }

  public void setStart_time_DI(String start_time_DI) {
    this.start_time_DI = start_time_DI;
  }

  @JsonProperty("last_event_DI")
  public String getLast_event_DI() {
    return last_event_DI;
  }

  public void setLast_event_DI(String last_event_DI) {
    this.last_event_DI = last_event_DI;
  }

  @JsonProperty("nevents")
  public Integer getNevents() {
    return nevents;
  }

  public void setNevents(Integer nevents) {
    this.nevents = nevents;
  }

  @JsonProperty("ndays_act")
  public Integer getNdays_act() {
    return ndays_act;
  }

  public void setNdays_act(Integer ndays_act) {
    this.ndays_act = ndays_act;
  }

  @JsonProperty("nplay_video")
  public Integer getNplay_video() {
    return nplay_video;
  }

  public void setNplay_video(Integer nplay_video) {
    this.nplay_video = nplay_video;
  }

  @JsonProperty("nchapters")
  public Integer getNchapters() {
    return nchapters;
  }

  public void setNchapters(Integer nchapters) {
    this.nchapters = nchapters;
  }

  @JsonProperty("nforum_posts")
  public Integer getNforum_posts() {
    return nforum_posts;
  }

  public void setNforum_posts(Integer nforum_posts) {
    this.nforum_posts = nforum_posts;
  }

  @JsonProperty("roles")
  public String getRoles() {
    return roles;
  }

  public void setRoles(String roles) {
    this.roles = roles;
  }

  @JsonProperty("incomplete_flag")
  public Integer getIncomplete_flag() {
    return incomplete_flag;
  }

  public void setIncomplete_flag(Integer incomplete_flag) {
    this.incomplete_flag = incomplete_flag;
  }
}
