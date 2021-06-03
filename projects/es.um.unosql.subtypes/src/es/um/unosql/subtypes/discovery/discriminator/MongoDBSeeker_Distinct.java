package es.um.unosql.subtypes.discovery.discriminator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import es.um.unosql.documents.injectors.adapters.mongodb.MongoDbAdapter;
import es.um.unosql.documents.injectors.adapters.mongodb.MongoDbClient;
import es.um.unosql.subtypes.util.types.EntitySubtype;
import es.um.unosql.uNoSQLSchema.Attribute;
import es.um.unosql.uNoSQLSchema.PrimitiveType;
import es.um.unosql.uNoSQLSchema.Feature;

public class MongoDBSeeker_Distinct implements DBDiscriminatorSeeker
{
  private MongoDbClient client;
  private String dbName;
  private String collectionName;
  private List<EntitySubtype> subtypes;
  private List<Feature> candidates;
  private Feature discriminator;
  private Map<EntitySubtype, Object> discriminatorValues;

  public MongoDBSeeker_Distinct(String dbName)
  {
    this(dbName, "localhost");
  }

  public MongoDBSeeker_Distinct(String dbName, String ip)
  {
    this.dbName = dbName;
    this.client = MongoDbAdapter.getMongoDbClient(ip);
  }

  public List<EntitySubtype> getSubtypes()
  {
    return this.subtypes;
  }

  public List<Feature> getCandidates()
  {
    return this.candidates;
  }

  public Feature getDiscriminator()
  {
    return this.discriminator;
  }

  public Map<EntitySubtype, Object> getDiscriminatorValues()
  {
    return this.discriminatorValues;
  }

  public void doSeek(String collectionName, List<EntitySubtype> subtypes, List<Feature> candidates)
  {
    this.collectionName = collectionName;
    this.subtypes = subtypes;
    this.candidates = candidates;

    MongoCollection<Document> documents = client.getDatabase(dbName).getCollection(this.collectionName);
    Map<Feature, Map<EntitySubtype, Object>> candidateMap = new HashMap<Feature, Map<EntitySubtype, Object>>();
    List<Feature> candidatesToBeRemoved = new ArrayList<Feature>();

    // For each candidate feature we create a map entry: <Feature>: List<Entry<Subtype, Value>>
    for (Feature candidate : this.candidates)
      candidateMap.put(candidate, new HashMap<EntitySubtype, Object>());

    for (EntitySubtype subtype : this.subtypes)
    {
      // Create an AND filter with each required feature that only exists on a certain subtype.
      Bson filter = null;
      for (Feature feat : subtype.getSubtypeRequiredFeatures())
        if (filter == null)
          filter = Filters.exists(feat.getName());
        else
          filter = Filters.and(filter, Filters.exists(feat.getName()));

      for (Feature candidate : candidateMap.keySet())
      {
        String theType = ((PrimitiveType)((Attribute)candidate).getType()).getName();
        List<Object> values = documents.distinct(candidate.getName(), filter, getTheClass(theType)).into(new ArrayList<Object>());

        if (values.size() == 1)
          candidateMap.get(candidate).put(subtype, values.get(0));
        else
          candidatesToBeRemoved.add(candidate);
      }

      if (!candidatesToBeRemoved.isEmpty())
      {
        candidatesToBeRemoved.forEach(c1 -> candidateMap.keySet().removeIf(c2 -> c1.getName().equals(c2.getName())));
        candidatesToBeRemoved.clear();
      }
    }

    client.shutdown();

    // From all remaining candidates, get the one with all distinct values for each subtype.
    candidateMap.keySet().removeIf(k -> candidateMap.get(k).values().stream().distinct().count() != candidateMap.get(k).size());

    if (candidateMap.keySet().size() == 1)
    {
      discriminatorValues = candidateMap.values().stream().findFirst().get();
      discriminator = candidateMap.keySet().stream().findFirst().get();
    }
  }

  private Class<?> getTheClass(String value)
  {
    switch (value.toLowerCase())
    {
    case "string": return String.class;
    case "number": return Double.class;
    case "boolean": return Boolean.class;
    default: return null;
    }
  }
}
