package es.um.nosql.schemainference.m2t.config;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.um.nosql.schemainference.m2t.config.pojo.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MorphiaConfig
{
  private String mapper;

  public void setMapper(String mapper)
  {
    if (!mapper.toLowerCase().equals("morphia"))
      throw new IllegalArgumentException("The config file is not suitable for Morphia generation.\nNeeds: \"mapper: Morphia\"");
    else
      this.mapper = mapper;
  }

  public String getMapper() {return this.mapper;}

  private String discriminator;

  public void setDiscriminator(String discriminator) {this.discriminator = discriminator;}

  public String getDiscriminator() {return this.discriminator;}

  private Entity[] entities;

  public void setEntities(Entity[] entities) {this.entities = entities;}

  public Entity[] getEntities() {return this.entities;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();
    result.append("-Mapper: " + this.getMapper() + "\n");
    if (this.getEntities() != null)
      result.append("-Entities:\n" + Arrays.stream(this.getEntities()).map(e -> e.toString()).collect(Collectors.joining("\n")));
    result.append("-Discriminator: " + this.getDiscriminator() + "\n");

    return result.toString();
  }
}
