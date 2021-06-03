package es.um.unosql.subtypes.discovery.discriminator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

import es.um.unosql.documents.injectors.adapters.mongodb.MongoDbAdapter;
import es.um.unosql.documents.injectors.adapters.mongodb.MongoDbClient;
import es.um.unosql.subtypes.util.types.EntitySubtype;
import es.um.unosql.uNoSQLSchema.Feature;

public class MongoDBSeeker_Filter implements DBDiscriminatorSeeker
{
    private MongoDbClient client;
    private String dbName;
    private String collectionName;
    private List<EntitySubtype> subtypes;
    private List<Feature> candidates;
    private Feature discriminator;
    private Map<EntitySubtype, Object> discriminatorValues;

    public MongoDBSeeker_Filter(String dbName)
    {
        this(dbName, "localhost");
    }

    public MongoDBSeeker_Filter(String dbName, String ip)
    {
        this.dbName = dbName;
        this.client = MongoDbAdapter.getMongoDbClient(ip);
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
        this.collectionName = collectionName;
        this.subtypes = subtypes;
        this.candidates = candidates;

        MongoCollection<Document> documents = client.getDatabase(dbName).getCollection(this.collectionName);
        Map<Feature, Map<EntitySubtype, Object>> candidateMap = new HashMap<Feature, Map<EntitySubtype, Object>>();
        List<Feature> candidatesToBeRemoved = new ArrayList<Feature>();

        // For each candidate feature we create a map entry: <Feature>:
        // List<Entry<Subtype, Value>>
        for (Feature candidate : this.candidates)
            candidateMap.put(candidate, new HashMap<EntitySubtype, Object>());

        // Bring only fields that matter.
        Bson projection = Projections.include(candidateMap.keySet().stream()
                .map(Feature::getName).collect(Collectors.toList()));

        for (EntitySubtype subtype : this.subtypes)
        {
            // Create an AND filter with each required feature that only exists on a certain
            // subtype.
            Bson filter = null;
            for (Feature feat : subtype.getSubtypeRequiredFeatures())
                if (filter == null)
                    filter = Filters.exists(feat.getName());
                else
                    filter = Filters.and(filter, Filters.exists(feat.getName()));

            // For each document that matches a subtype...
            AggregateIterable<Document> aggrResult = documents
                    .aggregate(Arrays.asList(Aggregates.match(filter), Aggregates.project(projection)))
                    .allowDiskUse(true);
            Document firstDocument = aggrResult.first();

            // Discriminator values should match the ones of the first document
            for (Feature candidate : candidateMap.keySet())
                candidateMap.get(candidate).put(subtype, firstDocument.get(candidate.getName()));

            for (Document doc : aggrResult)
            {
                // For each candidate feature to be discriminator...
                for (Feature candidate : candidateMap.keySet())
                    if (!candidateMap.get(candidate).get(subtype).equals(doc.get(candidate.getName())))
                        candidatesToBeRemoved.add(candidate);
            }

            if (!candidatesToBeRemoved.isEmpty())
            {
                candidatesToBeRemoved
                        .forEach(c1 -> candidateMap.keySet().removeIf(c2 -> c1.getName().equals(c2.getName())));
                candidatesToBeRemoved.clear();
                projection = Projections.include(candidateMap.keySet().stream()
                        .map(f -> f.getName()).collect(Collectors.toList()));
            }
        }

        client.shutdown();

        // From all remaining candidates, get the one with all distinct values for each
        // subtype.
        candidateMap.keySet()
                .removeIf(k -> candidateMap.get(k).values().stream().distinct().count() != candidateMap.get(k).size());

        // At this point the temporary candidate list should contain only one element.
        if (candidateMap.keySet().size() == 1)
        {
            discriminatorValues = candidateMap.values().stream().findFirst().get();
            discriminator = candidateMap.keySet().stream().findFirst().get();
        }
    }
}
