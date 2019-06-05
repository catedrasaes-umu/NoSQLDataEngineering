package es.um.nosql.s13e.evolution.analyzer.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbClient;
import es.um.nosql.s13e.evolution.types.EntitySubtype;

public class DbDiscriminatorSeeker
{
  private List<EntitySubtype> subtypes;
  private Map<EntitySubtype, Object> discriminatorValues;
  private Property discriminator;

  public DbDiscriminatorSeeker(String dbName, String collName, List<EntitySubtype> subtypes, List<Attribute> candidates)
  {
    this.subtypes = subtypes;
    seekDiscriminator(dbName, collName, candidates);
  }

  public Map<EntitySubtype, Object> getDiscriminatorValues()
  {
    return discriminatorValues;
  }

  public Property getDiscriminator()
  {
    return discriminator;
  }

  private void seekDiscriminator(String dbName, String collName, List<Attribute> candidates)
  {
    MongoDbClient client = MongoDbAdapter.getMongoDbClient("localhost");
    MongoCollection<Document> documents = client.getDatabase(dbName).getCollection(collName);
    Map<Attribute, Map<EntitySubtype, Object>> candidateMap = new HashMap<Attribute, Map<EntitySubtype, Object>>();
    List<Attribute> candidatesToBeRemoved = new ArrayList<Attribute>();

    for (Attribute candidate : candidates)
      candidateMap.put(candidate, new HashMap<EntitySubtype, Object>());

    for (Document doc : documents.find())
    {
      // For each candidate property, get this document subtype
      // Was the candidate property initialized?
      // Yes: Are the stored value and the read value the same?
      //     Yes: Go on!
      //     No: Then eliminate this property as candidate
      // No: Then initialize the property value with the read value
      // When finishing, among all remaining candidate properties, get the one with all distinct values between subtypes.

      EntitySubtype theSubtype = detectSubtype(doc);
      if (theSubtype == null)
        continue;
      // If subtype couldnt be identified then the object is an outlier. Ignore it and continue.

      for (Attribute candidate : candidates)
      {
        Map<EntitySubtype, Object> auxMap = candidateMap.get(candidate);
        if (auxMap.containsKey(theSubtype))
        {
          // If the stored value is not the same as the value being analyzed, then the property is not the discriminator.
          if (!auxMap.get(theSubtype).equals(doc.get(candidate.getName())))
            candidatesToBeRemoved.add(candidate);
        }
        else
          auxMap.put(theSubtype, doc.get(candidate.getName()));
      }

      candidates.removeAll(candidatesToBeRemoved);
      candidatesToBeRemoved.clear();
    }

    client.close();

    // From all remaining candidates, get the one with all distinct values for each subtype.
    for (Property candidate : candidateMap.keySet())
      if (candidateMap.get(candidate).values().stream().distinct().count() != candidateMap.get(candidate).size())
        candidates.remove(candidate);

    discriminatorValues = candidateMap.get(candidates.get(0));
    discriminator = candidates.get(0);
  }

  private EntitySubtype detectSubtype(Document doc)
  {
    Optional<EntitySubtype> optional = subtypes.stream()
        .filter(subtype ->
        {
          // Check each subtype identifying property appears on the document, regardless of its value.
          return subtype.getSubtypeRequiredProps().stream().allMatch(prop -> doc.containsKey(prop.getName()));
          // Apparently we only have to check properties by name, not by type value, so...
        })
        .findAny();

    if (optional.isPresent())
      return optional.get();
    else
      return null;
  }
}
