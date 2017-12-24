package es.um.nosql.schemainference.m2t.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MorphiaYAMLConfig
{
  private String mapper;
// Se puede sacar a una clase común, quizá...
  public void setMapper(String mapper) {this.mapper = mapper;}

  public String getMapper() {return this.mapper;}
}
