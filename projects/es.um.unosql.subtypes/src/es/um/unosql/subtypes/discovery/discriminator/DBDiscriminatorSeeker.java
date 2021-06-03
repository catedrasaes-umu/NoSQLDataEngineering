package es.um.unosql.subtypes.discovery.discriminator;

import java.util.List;
import java.util.Map;

import es.um.unosql.subtypes.util.types.EntitySubtype;
import es.um.unosql.uNoSQLSchema.Feature;

public interface DBDiscriminatorSeeker
{
  public List<EntitySubtype> getSubtypes();

  public List<Feature> getCandidates();

  public Feature getDiscriminator();

  public Map<EntitySubtype, Object> getDiscriminatorValues();

  public void doSeek(String collectionName, List<EntitySubtype> subtypes, List<Feature> candidates);
}
