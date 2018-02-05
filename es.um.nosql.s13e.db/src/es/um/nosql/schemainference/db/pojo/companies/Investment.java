package es.um.nosql.s13e.db.pojo.companies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"company", "financial_org", "person"})
public class Investment
{
  private NamePermalink company;

  private NamePermalink financial_org;

  private Person person;

  @JsonProperty("company")
  public NamePermalink getCompany() {
    return company;
  }

  public void setCompany(NamePermalink company) {
    this.company = company;
  }

  @JsonProperty("financial_org")
  public NamePermalink getFinancial_org() {
    return financial_org;
  }

  public void setFinancial_org(NamePermalink financial_org) {
    this.financial_org = financial_org;
  }

  @JsonProperty("person")
  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }
}
