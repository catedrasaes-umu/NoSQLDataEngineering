package es.um.nosql.s13e.entitydifferentiation.m2t.config;

import java.util.Arrays;
import java.util.stream.Collectors;

import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.entitydifferentiation.m2t.config.pojo.ConfigEntity;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiation;

public class ConfigMorphia extends BaseConfig
{
  private String mapper;

  public void setMapper(String mapper) {this.mapper = mapper;}

  public String getMapper() {return this.mapper;}

  private String discriminator;

  public void setDiscriminator(String discriminator) {this.discriminator = discriminator;}

  public String getDiscriminator() {return this.discriminator;}

  private ConfigEntity[] entities;

  public void setEntities(ConfigEntity[] entities) {this.entities = entities;}

  public ConfigEntity[] getEntities()
  {
    if (this.entities == null)
      return new ConfigEntity[0];
    else
      return this.entities;
  }

  public boolean needToGenerateIndexesFor(String entityName)
  {
    if (entities == null)
      return false;
    return Arrays.stream(getEntities()).anyMatch(e -> e.getName().equals(entityName) && e.getIndexes() != null);
  }

  public boolean needToGenerateValidatorsFor(String entityName)
  {
    if (entities == null)
      return false;
    return Arrays.stream(getEntities()).anyMatch(e -> e.getName().equals(entityName) && e.getValidators() != null);
  }

  public String toString()
  {
    StringBuilder result = new StringBuilder();
    result.append("-Mapper: " + this.getMapper() + "\n");
    if (this.getEntities() != null)
      result.append("-Entities:\n" + Arrays.stream(this.getEntities()).map(e -> e.toString()).collect(Collectors.joining("\n")));
    result.append("-Discriminator: " + this.getDiscriminator() + "\n");

    return result.toString();
  }

  @Override
  public boolean doCheck(EntityDifferentiation diff)
  {
    if (!mapper.toLowerCase().equals("morphia"))
      throw new IllegalArgumentException("The config file is not suitable for Morphia generation.\nNeeds: \"mapper: Morphia\"");

    for (ConfigEntity e : getEntities())
    {
      EntityClass schemaE =
    		  diff.getSchema().getEntities().stream()
    		    .filter(innerE -> innerE.getName().equals(e.getName())).findFirst()
    		    .orElseThrow(() -> 
    		      new IllegalArgumentException("Entity " + e.getName() + " defined on the config file must exist on the Entity model"));

      e.doCheck(schemaE);
    }

    return true;
  }
}