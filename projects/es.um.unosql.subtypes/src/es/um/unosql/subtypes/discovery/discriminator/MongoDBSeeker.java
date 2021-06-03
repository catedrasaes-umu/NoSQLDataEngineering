package es.um.unosql.subtypes.discovery.discriminator;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.common.collect.Streams;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Projections;

import es.um.unosql.documents.injectors.adapters.mongodb.MongoDbAdapter;
import es.um.unosql.documents.injectors.adapters.mongodb.MongoDbClient;
import es.um.unosql.subtypes.util.types.EntitySubtype;
import es.um.unosql.uNoSQLSchema.Feature;

public class MongoDBSeeker implements DBDiscriminatorSeeker
{
    private MongoDbClient client;
    private String dbName;
    private List<EntitySubtype> subtypes;
    private List<Feature> candidates;
    private Feature discriminator;
    private Map<EntitySubtype, Object> discriminatorValues;

    public MongoDBSeeker(String dbName)
    {
        this(dbName, "localhost");
    }

    public MongoDBSeeker(String ip, String dbName)
    {
        this.client = MongoDbAdapter.getMongoDbClient(ip);
        this.dbName = dbName;
    }

    @Override
    public List<EntitySubtype> getSubtypes()
    {
        return this.subtypes;
    }

    @Override
    public List<Feature> getCandidates()
    {
        return this.candidates;
    }

    @Override
    public Feature getDiscriminator()
    {
        return this.discriminator;
    }

    @Override
    public Map<EntitySubtype, Object> getDiscriminatorValues()
    {
        return this.discriminatorValues;
    }

    @Override
    public void doSeek(String collectionName, List<EntitySubtype> subtypes, List<Feature> candidates)
    {
        this.subtypes = subtypes;
        this.candidates = candidates;

        MongoCollection<Document> documents = client.getDatabase(dbName).getCollection(collectionName);
        Map<String, Map<EntitySubtype, Object>> candidateMap = new HashMap<String, Map<EntitySubtype, Object>>();
        var featureNamesToBeRemoved = new HashSet<String>();

        var requiredFeatureNamesForSubtype = subtypes.stream()
                .map(st -> new AbstractMap.SimpleEntry<EntitySubtype, Set<String>>(st,
                        st.getSubtypeRequiredFeatures().stream().map(Feature::getName).collect(Collectors.toSet())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // For each candidate feature we create a map entry: <Feature>: Map<Subtype,
        // Value>
        for (Feature candidate : this.candidates)
            candidateMap.put(candidate.getName(), new HashMap<EntitySubtype, Object>());

        Bson projection = genProjectionForFeatures(requiredFeatureNamesForSubtype, candidateMap);

        for (Document doc : documents.find().projection(projection))
        {
            // For each candidate feature, get this document subtype
            // Was the candidate feature initialized?
            // No: Then initialize the feature value with the read value
            // Yes: Are the stored value and the read value the same?
            // Yes: Go on!
            // No: Then eliminate this feature as candidate

            // When finishing, among all remaining candidate feature, get the one with all
            // distinct values between subtypes.

            detectSubtype(requiredFeatureNamesForSubtype, doc).ifPresent(theSubtype -> {
                for (String candidate : candidateMap.keySet())
                {
                    Map<EntitySubtype, Object> auxMap = candidateMap.get(candidate);
                    var value = doc.get(candidate);

                    Object storedValue;
                    storedValue = auxMap.get(theSubtype);
                    if (storedValue == null)
                        auxMap.put(theSubtype, value);
                    else if (!storedValue.equals(value))
                        featureNamesToBeRemoved.add(candidate);
                }
            });

            if (!featureNamesToBeRemoved.isEmpty())
            {
                candidateMap.keySet().removeAll(featureNamesToBeRemoved);
                featureNamesToBeRemoved.removeAll(featureNamesToBeRemoved);
            }

        }

        client.shutdown();

        // From all remaining candidates, get the one with all distinct values for each
        // subtype.
        candidateMap.keySet()
                .removeIf(k -> candidateMap.get(k).values().stream().distinct().count() != candidateMap.get(k).size());

        // At this point the temporary candidate list should contain only one element.
        if (candidateMap.size() == 1)
        {
            final String discriminatorFeatureName = candidateMap.keySet().iterator().next();

            discriminatorValues = candidateMap.values().stream().findFirst().get();
            discriminator = candidates.stream().filter(f -> f.getName().equals(discriminatorFeatureName)).findFirst()
                    .get();
        }
    }

    private Bson genProjectionForFeatures(Map<EntitySubtype, Set<String>> requiredFeatureNamesForSubtype,
            Map<String, Map<EntitySubtype, Object>> candidateMap)
    {
        Bson projection = Projections.include(Streams.concat(
                candidateMap.keySet().stream(),
                requiredFeatureNamesForSubtype.values().stream().flatMap(Set::stream))
                .collect(Collectors.toList()));
        return projection;
    }

    private Optional<EntitySubtype> detectSubtype(Map<EntitySubtype, Set<String>> requiredFeatureNamesForSubtype,
            Document doc)
    {
        return requiredFeatureNamesForSubtype.entrySet().stream()
                .filter(e -> doc.keySet().containsAll(e.getValue()))
                .map(Map.Entry::getKey).findFirst();
    }
}
