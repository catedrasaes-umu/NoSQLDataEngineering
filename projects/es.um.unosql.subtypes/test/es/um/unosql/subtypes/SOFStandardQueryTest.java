package es.um.unosql.subtypes;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

import es.um.unosql.documents.injectors.adapters.mongodb.MongoDbAdapter;
import es.um.unosql.documents.injectors.adapters.mongodb.MongoDbClient;

public class SOFStandardQueryTest
{
    private String dbName = "stackoverflow";
    private String collName = "posts";
    private final Integer POSTTYPE_ID_QUESTION = 1;
    private final Integer POSTTYPE_ID_ANSWER = 2;
    private final Integer POSTTYPE_ID_TEST = POSTTYPE_ID_ANSWER; // Answer.
    private long EXPECTED_RESULT = 31169429;
    private MongoDbClient client;
    private MongoCollection<Document> collection;

    @BeforeEach
    void beforeEach()
    {
        System.out.println("Client creation.");
        client = MongoDbAdapter.getMongoDbClient("localhost");
        collection = client.getDatabase(dbName).getCollection(collName);
    }

    @AfterEach
    void afterEach()
    {
        System.out.println("Client shutdown.");
        client.shutdown();
    }

//    @Test
//    public void testPostTypeIdQueryByHand()
//    {
//        long initialT = System.currentTimeMillis();
//
//        long totalCount = 0;
//        for (Document doc : collection.find(Filters.eq("PostTypeId", POSTTYPE_ID_TEST)).projection(Projections.include("PostTypeId")))
//            totalCount++;
//
//        long finalT = System.currentTimeMillis();
//        System.out.println("PostTypeIdTest (by hand): " + (finalT - initialT) + " ms");
//
//        System.out.println("For PostTypeId: " + POSTTYPE_ID_TEST + " we found " + totalCount + " items.");
//        assertEquals(EXPECTED_RESULT, totalCount);
//    }

    @Test
    public void testPostTypeIdQueryByCount()
    {
        Bson filter = Filters.eq("PostTypeId", POSTTYPE_ID_TEST);

        long initialT = System.currentTimeMillis();

        long totalCount = collection.countDocuments(filter);

        long finalT = System.currentTimeMillis();
        System.out.println("PostTypeIdTest (count): " + (finalT - initialT) + " ms");

        System.out.println("For PostTypeId: " + POSTTYPE_ID_TEST + " we found " + totalCount + " items.");
        assertEquals(EXPECTED_RESULT, totalCount);
    }

    @Test
    public void testPostQuestionMeanScore()
    {
        Bson expr[] = {
                Aggregates.project(Projections.fields(Projections.include("PostTypeId"), Projections.include("Score"))),
                Aggregates.match(Filters.eq("PostTypeId", POSTTYPE_ID_QUESTION)),
                Aggregates.group(null, Accumulators.avg("avgScore", "$Score"))
        };

        long initialT = System.currentTimeMillis();

        for (Document doc : collection.aggregate(Arrays.asList(expr)))
            System.out.println(doc);

        long finalT = System.currentTimeMillis();
        System.out.println("testPostQuestionsMeanScore: " + (finalT - initialT) + " ms");
    }

}
