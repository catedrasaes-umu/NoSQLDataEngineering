package es.um.nosql.schemainference.db.pojo.companies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"_id", "name", "permalink", "crunchbase_url", "homepage_url", "blog_url", "blog_feed_url", "twitter_username", "category_code",
  "number_of_employees", "founded_year", "founded_month", "founded_day", "deadpooled_year", "deadpooled_month", "deadpooled_day", "deadpooled_url",
  "tag_list", "alias_list", "email_address", "phone_number", "description", "created_at", "updated_at", "overview", "image", "products",
  "relationships", "competitions", "providerships", "total_money_raised", "funding_rounds", "investments", "acquisition", "acquisitions",
  "offices", "milestones", "ipo", "video_embeds", "screenshots", "external_links", "partners"})
public class Company
{
  private ObjectId _id;
  private String name;
  private String permalink;
  private String crunchbase_url;
  private String homepage_url;
  private String blog_url;
  private String blog_feed_url;
  private String twitter_username;
  private String category_code;
  private Integer number_of_employees;
  private Integer founded_year;
  private Integer founded_month;
  private Integer founded_day;
  private Integer deadpooled_year;
  private Integer deadpooled_month;
  private Integer deadpooled_day;
  private String deadpooled_url;
  private String tag_list;
  private String alias_list;
  private String email_address;
  private String phone_number;
  private String description;
  private Object created_at;
  private String updated_at;
  private String overview;
  private Image image;
  private NamePermalink[] products;
//  private Relationship[] relationships;
  private Competition[] competitions;
  private Providership[] providerships;
  private String total_money_raised;
//  private Funding_round[] funding_rounds;
//  private Object[] investments;
  private Acquisition acquisition;
//  private Acquisition2[] acquisitions;
  private Office[] offices;
//  private Object[] milestones;
//  private Ipo[] ipo;
  private Video[] video_embeds;
  private Image[] screenshots;
  private External_link[] external_links;
  private Partner[] partners;

  @JsonProperty("_id")
  public ObjectId get_id() {
    return _id;
  }

  public void set_id(ObjectId _id) {
    this._id = _id;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("permalink")
  public String getPermalink() {
    return permalink;
  }

  public void setPermalink(String permalink) {
    this.permalink = permalink;
  }

  @JsonProperty("crunchbase_url")
  public String getCrunchbase_url() {
    return crunchbase_url;
  }

  public void setCrunchbase_url(String crunchbase_url) {
    this.crunchbase_url = crunchbase_url;
  }

  @JsonProperty("homepage_url")
  public String getHomepage_url() {
    return homepage_url;
  }

  public void setHomepage_url(String homepage_url) {
    this.homepage_url = homepage_url;
  }

  @JsonProperty("blog_url")
  public String getBlog_url() {
    return blog_url;
  }

  public void setBlog_url(String blog_url) {
    this.blog_url = blog_url;
  }

  @JsonProperty("blog_feed_url")
  public String getBlog_feed_url() {
    return blog_feed_url;
  }

  public void setBlog_feed_url(String blog_feed_url) {
    this.blog_feed_url = blog_feed_url;
  }

  @JsonProperty("twitter_username")
  public String getTwitter_username() {
    return twitter_username;
  }

  public void setTwitter_username(String twitter_username) {
    this.twitter_username = twitter_username;
  }

  @JsonProperty("category_code")
  public String getCategory_code() {
    return category_code;
  }

  public void setCategory_code(String category_code) {
    this.category_code = category_code;
  }

  @JsonProperty("number_of_employees")
  public Integer getNumber_of_employees() {
    return number_of_employees;
  }

  public void setNumber_of_employees(Integer number_of_employees) {
    this.number_of_employees = number_of_employees;
  }

  @JsonProperty("founded_year")
  public Integer getFounded_year() {
    return founded_year;
  }

  public void setFounded_year(Integer founded_year) {
    this.founded_year = founded_year;
  }

  @JsonProperty("founded_month")
  public Integer getFounded_month() {
    return founded_month;
  }

  public void setFounded_month(Integer founded_month) {
    this.founded_month = founded_month;
  }

  @JsonProperty("founded_day")
  public Integer getFounded_day() {
    return founded_day;
  }

  public void setFounded_day(Integer founded_day) {
    this.founded_day = founded_day;
  }

  @JsonProperty("deadpooled_year")
  public Integer getDeadpooled_year() {
    return deadpooled_year;
  }

  public void setDeadpooled_year(Integer deadpooled_year) {
    this.deadpooled_year = deadpooled_year;
  }

  @JsonProperty("deadpooled_month")
  public Integer getDeadpooled_month() {
    return deadpooled_month;
  }

  public void setDeadpooled_month(Integer deadpooled_month) {
    this.deadpooled_month = deadpooled_month;
  }

  @JsonProperty("deadpooled_day")
  public Integer getDeadpooled_day() {
    return deadpooled_day;
  }

  public void setDeadpooled_day(Integer deadpooled_day) {
    this.deadpooled_day = deadpooled_day;
  }
 
  @JsonProperty("deadpooled_url")
  public String getDeadpooled_url() {
    return deadpooled_url;
  }

  public void setDeadpooled_url(String deadpooled_url) {
    this.deadpooled_url = deadpooled_url;
  }

  @JsonProperty("tag_list")
  public String getTag_list() {
    return tag_list;
  }

  public void setTag_list(String tag_list) {
    this.tag_list = tag_list;
  }

  @JsonProperty("alias_list")
  public String getAlias_list() {
    return alias_list;
  }

  public void setAlias_list(String alias_list) {
    this.alias_list = alias_list;
  }

  @JsonProperty("email_address")
  public String getEmail_address() {
    return email_address;
  }

  public void setEmail_address(String email_address) {
    this.email_address = email_address;
  }

  @JsonProperty("phone_number")
  public String getPhone_number() {
    return phone_number;
  }

  public void setPhone_number(String phone_number) {
    this.phone_number = phone_number;
  }

  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @JsonProperty("created_at")
  public Object getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Object created_at) {
    this.created_at = created_at;
  }

  @JsonProperty("updated_at")
  public String getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(String updated_at) {
    this.updated_at = updated_at;
  }

  @JsonProperty("overview")
  public String getOverview() {
    return overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  @JsonProperty("image")
  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }

  @JsonProperty("products")
  public NamePermalink[] getProducts() {
    return products;
  }

  public void setProducts(NamePermalink[] products) {
    this.products = products;
  }
/*
  @JsonProperty("relationships")
  public Object[] getRelationships() {
    return relationships;
  }

  public void setRelationships(Relationship[] relationships) {
    this.relationships = relationships;
  }
*/
  @JsonProperty("competitions")
  public Competition[] getCompetitions() {
    return competitions;
  }

  public void setCompetitions(Competition[] competitions) {
    this.competitions = competitions;
  }

  @JsonProperty("providerships")
  public Providership[] getProviderships() {
    return providerships;
  }

  public void setProviderships(Providership[] providerships) {
    this.providerships = providerships;
  }

  @JsonProperty("total_money_raised")
  public String getTotal_money_raised() {
    return total_money_raised;
  }

  public void setTotal_money_raised(String total_money_raised) {
    this.total_money_raised = total_money_raised;
  }
/*
  @JsonProperty("funding_rounds")
  public Funding_round[] getFunding_rounds() {
    return funding_rounds;
  }

  public void setFunding_rounds(Funding_round[] funding_rounds) {
    this.funding_rounds = funding_rounds;
  }

  @JsonProperty("investments")
  public Object[] getInvestments() {
    return investments;
  }

  public void setInvestments(Object[] investments) {
    this.investments = investments;
  }
*/
  @JsonProperty("acquisition")
  public Acquisition getAcquisition() {
    return acquisition;
  }

  public void setAcquisition(Acquisition acquisition) {
    this.acquisition = acquisition;
  }
/*
  @JsonProperty("acquisitions")
  public Acquisition2[] getAcquisitions() {
    return acquisitions;
  }

  public void setAcquisitions(Acquisition2[] acquisitions) {
    this.acquisitions = acquisitions;
  }
*/
  @JsonProperty("offices")
  public Office[] getOffices() {
    return offices;
  }

  public void setOffices(Office[] offices) {
    this.offices = offices;
  }
/*
  @JsonProperty("milestones")
  public Object[] getMilestones() {
    return milestones;
  }

  public void setMilestones(Object[] milestones) {
    this.milestones = milestones;
  }

  @JsonProperty("ipo")
  public Ipo[] getIpo() {
    return ipo;
  }

  public void setIpo(Ipo[] ipo) {
    this.ipo = ipo;
  }
*/
  @JsonProperty("video_embeds")
  public Video[] getVideo_embeds() {
    return video_embeds;
  }

  public void setVideo_embeds(Video[] video_embeds) {
    this.video_embeds = video_embeds;
  }

  @JsonProperty("screenshots")
  public Image[] getScreenshots() {
    return screenshots;
  }

  public void setScreenshots(Image[] screenshots) {
    this.screenshots = screenshots;
  }

  @JsonProperty("external_links")
  public External_link[] getExternal_links() {
    return external_links;
  }

  public void setExternal_links(External_link[] external_links) {
    this.external_links = external_links;
  }

  @JsonProperty("partners")
  public Partner[] getPartners() {
    return partners;
  }

  public void setPartners(Partner[] partners) {
    this.partners = partners;
  }
}