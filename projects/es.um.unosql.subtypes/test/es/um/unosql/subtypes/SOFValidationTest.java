package es.um.unosql.subtypes;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.client.MongoCollection;

import es.um.unosql.documents.injectors.adapters.mongodb.MongoDbAdapter;
import es.um.unosql.documents.injectors.adapters.mongodb.MongoDbClient;
import es.um.unosql.subtypes.discovery.DependencyAnalyzer;
import es.um.unosql.subtypes.discovery.discriminator.MongoDBSeeker;
import es.um.unosql.subtypes.outliers.impl.CoverageOutlierDetector;
import es.um.unosql.subtypes.outliers.OutlierDetector;
import es.um.unosql.subtypes.util.types.EntitySubtype;
import es.um.unosql.uNoSQLSchema.Feature;
import es.um.unosql.uNoSQLSchema.SchemaType;
import es.um.unosql.uNoSQLSchema.UNoSQLSchemaPackage;
import es.um.unosql.uNoSQLSchema.uNoSQLSchema;
import es.um.unosql.utils.ModelLoader;

public class SOFValidationTest
{
    private MongoDbClient client;
    private String dbName;
    private String inputModel;
    private uNoSQLSchema schema;
    private Map<Integer, String> postTypeIdMap;
    private MongoDBSeeker seeker;

    @Before
    public void setUp() throws Exception
    {
        client = MongoDbAdapter.getMongoDbClient("localhost");
        dbName = "stackoverflow";
        inputModel = "../es.um.unosql.models/" + dbName + "/" + dbName + ".xmi";

        ModelLoader loader = new ModelLoader(UNoSQLSchemaPackage.eINSTANCE);
        schema = loader.load(new File(inputModel), uNoSQLSchema.class);

        postTypeIdMap = new HashMap<Integer, String>();
        postTypeIdMap.put(1, "Question");
        postTypeIdMap.put(2, "Answer");
        postTypeIdMap.put(3, "Wiki");
        postTypeIdMap.put(4, "TagWikiExcerpt");
        postTypeIdMap.put(5, "TagWiki");
        postTypeIdMap.put(6, "ModeratorNomination");
        postTypeIdMap.put(7, "WikiPlaceholder");
        postTypeIdMap.put(8, "PrivilegeWiki");

        seeker = null;
    }

    @After
    public void tearDown() throws Exception
    {
        client.shutdown();
    }

    @Test
    public void testSubtypes()
    {
        String collName = "Posts";
        // Detect and remove outliers given Coverage = 99%
        // Be careful: Changing the coverage will change the results.
        OutlierDetector oAnalyzer = new CoverageOutlierDetector(99);
        oAnalyzer.removeOutliers(schema);

        // Analyze each feature
        for (SchemaType sType : schema.getEntities().stream().filter(ent -> ent.getName().equals(collName))
                .collect(Collectors.toList()))
        {
            DependencyAnalyzer depDetector = new DependencyAnalyzer(sType, seeker);
            depDetector.doDependencyAnalysis();
            assertEquals(2, depDetector.getSubtypes().size());

            EntitySubtype subtype0 = depDetector.getSubtypes().get(0);
            assertEquals(4, subtype0.getSubtypeRequiredFeatures().size());
            assertEquals(3, subtype0.getSubtypeOptionalFeatures().size());

            EntitySubtype subtype1 = depDetector.getSubtypes().get(1);
            assertEquals(1, subtype1.getSubtypeRequiredFeatures().size());
            assertEquals(2, subtype1.getSubtypeOptionalFeatures().size());

            if (seeker != null)
            {
                Feature discriminator = depDetector.getDiscriminatorSeeker().getDiscriminator();
                assertEquals("PostTypeId", discriminator.getName());
                List<String> discriminatorValues = depDetector.getDiscriminatorSeeker().getDiscriminatorValues()
                        .values().stream().map(obj -> ((String) obj)).collect(Collectors.toList());
                Collections.sort(discriminatorValues);
                assertEquals(Arrays.asList("1", "2"), discriminatorValues);
            }

            MongoCollection<Document> documents = client.getDatabase(dbName).getCollection(collName.toLowerCase());
            List<EntitySubtype> filteredSubtypes = depDetector.getSubtypes().stream()
                    .filter(subtype -> !subtype.getSubtypeRequiredFeatures().isEmpty()).collect(Collectors.toList());

            List<Document> rareNoSubtypes = new ArrayList<Document>();
            List<Document> rareSubtypes = new ArrayList<Document>();

            int readDocuments = 0;
            for (Document doc : documents.find())
            {
                readDocuments++;
                if (readDocuments % 100000 == 0)
                    System.out.println("Read documents: " + readDocuments);

                EntitySubtype theSubtype = detectSubtype(filteredSubtypes, doc);
                if (theSubtype == null)
                {
                    if (doc.getString("PostTypeId").equals("1") || doc.getString("PostTypeId").equals("2"))
                    {
                        rareNoSubtypes.add(doc);
                        System.err.println((rareNoSubtypes.size() + rareSubtypes.size())
                                + ") Error with a no subtyped document with wrong PostTypeId.");
                    }
                    if (doc.containsKey("ParentId"))
                    {
                        rareNoSubtypes.add(doc);
                        System.err.println((rareNoSubtypes.size() + rareSubtypes.size())
                                + ") Error with a no subtyped document with ParentId.");
                    }
                    if (doc.containsKey("Title") || doc.containsKey("Tags"))
                    {
                        rareNoSubtypes.add(doc);
                        System.err.println((rareNoSubtypes.size() + rareSubtypes.size())
                                + ") Error with a no subtyped document with Title|Tags.");
                    }
                    if (doc.containsKey("ViewCount") || doc.containsKey("AnswerCount"))
                    {
                        rareNoSubtypes.add(doc);
                        System.err.println((rareNoSubtypes.size() + rareSubtypes.size())
                                + ") Error with a no subtyped document with Viewcount|AnswerCount.");
                    }
                } else
                {
                    switch (doc.getString("PostTypeId"))
                    {
                    case "1":
                    {
                        if (!doc.containsKey("Title") || !doc.containsKey("Tags") || !doc.containsKey("ViewCount")
                                || !doc.containsKey("AnswerCount"))
                        {
                            rareSubtypes.add(doc);
                            System.err.println((rareNoSubtypes.size() + rareSubtypes.size())
                                    + ") Error with a subtyped document without Title|Tags|Viewcount|AnswerCount.");
                        }
                        if (doc.containsKey("ParentId"))
                        {
                            rareSubtypes.add(doc);
                            System.err.println((rareNoSubtypes.size() + rareSubtypes.size())
                                    + ") Error with a subtyped document with ParentId.");
                        }
                        break;
                    }
                    case "2":
                    {
                        if (!doc.containsKey("ParentId"))
                        {
                            rareSubtypes.add(doc);
                            System.err.println((rareNoSubtypes.size() + rareSubtypes.size())
                                    + ") Error with a subtyped document without ParentId.");
                        }
                        if (doc.containsKey("Title") || doc.containsKey("Tags") || doc.containsKey("ViewCount")
                                || doc.containsKey("AnswerCount"))
                        {
                            rareSubtypes.add(doc);
                            System.err.println((rareNoSubtypes.size() + rareSubtypes.size())
                                    + ") Error with a subtyped document with Title|Tags|Viewcount|AnswerCount.");
                        }
                        break;
                    }
                    default:
                    {
                        rareSubtypes.add(doc);
                        System.err.println((rareNoSubtypes.size() + rareSubtypes.size())
                                + ") Error with a subtyped document with rare PostTypeId.");
                    }
                    }
                }
            }

            System.out.println("====================================");
            System.out.println("Report of Rare no subtypes: ");
            for (Document doc : rareNoSubtypes)
            {
                doc.remove("Body");
                System.out.println(doc);
            }

            System.out.println("Report of Rare subtypes: ");
            for (Document doc : rareSubtypes)
            {
                doc.remove("Body");
                System.out.println(doc);
            }
        }
    }

    private EntitySubtype detectSubtype(List<EntitySubtype> subtypes, Document doc)
    {
        Optional<EntitySubtype> optional = subtypes.stream()
                .filter(subtype -> {
                    // Check each subtype identifying feature appears on the document, regardless of its value.
                    return subtype.getSubtypeRequiredFeatures().stream()
                            .allMatch(feat -> doc.containsKey(feat.getName()));
                    // Apparently we only have to check features by name, not by type value, so...
                })
                .findAny();

        if (optional.isPresent())
            return optional.get();
        return null;
    }
}
