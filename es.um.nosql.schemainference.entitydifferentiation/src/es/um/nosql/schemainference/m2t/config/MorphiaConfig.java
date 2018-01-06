package es.um.nosql.schemainference.m2t.config;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation;
import es.um.nosql.schemainference.m2t.config.pojo.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MorphiaConfig extends BaseConfig
{
  private String mapper;

  public void setMapper(String mapper)
  {
    this.mapper = mapper;
  }

  public String getMapper() {return this.mapper;}

  private String discriminator;

  public void setDiscriminator(String discriminator) {this.discriminator = discriminator;}

  public String getDiscriminator() {return this.discriminator;}

  private Entity[] entities;

  public void setEntities(Entity[] entities) {this.entities = entities;}

  public Entity[] getEntities() {return this.entities;}

  public boolean needToGenerateIndexes()
  {
    if (entities == null)
      return false;
    return Arrays.stream(getEntities()).anyMatch(e -> e.getUniques() != null || e.getIndexes() != null);
    //TODO: Maybe we have to add e.getUpdates != null
  }

  public boolean needToGenerateValidators()
  {
    if (entities == null)
      return false;
    return Arrays.stream(getEntities()).anyMatch(e -> e.getValidators() != null);
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
/*    if (!mapper.toLowerCase().equals("morphia"))
      throw new IllegalArgumentException("The config file is not suitable for Morphia generation.\nNeeds: \"mapper: Morphia\"");

    System.out.println(diff.getSchema().getEntities());
    if (!diff.getSchema().getEntities().stream().anyMatch(e -> e.getName().equals(discriminator)))
      throw new IllegalArgumentException("Discriminator " + discriminator + " doesn't exist on the Entity model");

    for (Entity configEntity : getEntities())
    {
      if (!diff.getSchema().getEntities().stream().anyMatch(modelEntity -> modelEntity.getName().equals(configEntity.getName())))
        throw new IllegalArgumentException("Entity " + configEntity.getName() + " defined on the config file must exist on the Entity model");

      es.um.nosql.schemainference.NoSQLSchema.Entity modelEntity = diff.getSchema().getEntities().stream()
          .filter(e -> e.getName().equals(configEntity.getName())).findFirst().get();

      List<es.um.nosql.schemainference.NoSQLSchema.Property> modelProperties = modelEntity.getEntityversions().stream().flatMap(ev -> ev.getProperties().stream()).collect(Collectors.toList());

      // For each Validator, check if the property being validated exists on the model.
      if (configEntity.getValidators() != null
          && !Arrays.stream(configEntity.getValidators()).allMatch(validator -> modelProperties.stream().anyMatch(mp -> mp.getName().equals(validator.getAttr()))))
        throw new IllegalArgumentException("Validator values in Entity " + configEntity.getName() + " must be properties of the Entity model");

      // For each Unique, check if the property being declared Unique exists on the model.
      if (configEntity.getUniques() != null
          && !Arrays.stream(configEntity.getUniques()).allMatch(propName -> modelProperties.stream().anyMatch(mp -> mp.getName().equals(propName))))
        throw new IllegalArgumentException("Unique values in Entity " + configEntity.getName() + " must be properties of the Entity model");

      // For each Update, check if the property being declared Update exists on the model.
      if (configEntity.getUpdates() != null
          && !Arrays.stream(configEntity.getUpdates()).allMatch(propName -> modelProperties.stream().anyMatch(mp -> mp.getName().equals(propName))))
        throw new IllegalArgumentException("Update values in Entity " + configEntity.getName() + " must be properties of the Entity model");

      // For each Index, check if the properties being Indexed exists on the model, and also if the index is a well known type.
//      if (configEntity.getIndexes() != null
//          && !Arrays.stream(configEntity.getIndexes()).allMatch(index -> modelProperties.stream().anyMatch(mp -> mp.getName().equals(index.getAttr()))))
//        throw new IllegalArgumentException("Index values in Entity " + configEntity.getName() + " must be properties of the Entity model");

      if (configEntity.getIndexes() != null &&
          !Arrays.stream(configEntity.getIndexes()).allMatch(
              index ->
              {
                String type = index.getType().toLowerCase();
                return type.equals("text") || type.equals("hashed") || type.equals("asc") || type.equals("desc") || type.equals("geo2d") || type.equals("geo2dsphere");
              }))
        throw new IllegalArgumentException("Index types in Entity " + configEntity.getName() + " must be of type Sorted, Hashed, ASC, DESC, GEO2D or GEO2DSPHERE");
    }
*/
    return true;
  }
}
