package es.um.nosql.s13e.evolution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.client.MongoCollection;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.SchemaType;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbClient;
import es.um.nosql.s13e.evolution.analyzer.DependencyAnalyzer;
import es.um.nosql.s13e.evolution.analyzer.outliers.OutlierAnalyzer;
import es.um.nosql.s13e.evolution.analyzer.outliers.modes.OutlierMode;
import es.um.nosql.s13e.evolution.types.EntitySubtype;
import es.um.nosql.s13e.util.ModelLoader;

public class StackoverflowTest
{
  private MongoDbClient client;
  private String dbName;
  private String inputModel;
  private NoSQLSchema schema;

  @Before
  public void setUp() throws Exception
  {
    client = MongoDbAdapter.getMongoDbClient("localhost");
    dbName = "stackoverflow";
    inputModel = "../es.um.nosql.models/" + dbName + "/" + dbName + ".xmi";

    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    schema = loader.load(new File(inputModel), NoSQLSchema.class);
  }

  @After
  public void tearDown() throws Exception
  {
    client.close();
  }

  @Test
  public void testSubtypes()
  {
    String collName = "Posts";
    // Detect and remove outliers given Coverage = 99.5%
    // Be careful: Changing the coverage will change the results.
    OutlierAnalyzer oAnalyzer = new OutlierAnalyzer(OutlierMode.COVERAGE);
    oAnalyzer.removeOutliers(schema);

    // Analyze each property
    for (SchemaType sType : schema.getEntities().stream().filter(ent -> ent.getName().equals(collName)).collect(Collectors.toList()))
    {
      DependencyAnalyzer depDetector = new DependencyAnalyzer(dbName, sType, true);
      assertEquals(2, depDetector.getSubtypes().size());

      EntitySubtype subtype0 = depDetector.getSubtypes().get(0);
      assertEquals(4, subtype0.getSubtypeRequiredProps().size());
      assertEquals(3, subtype0.getSubtypeOptionalProps().size());

      EntitySubtype subtype1 = depDetector.getSubtypes().get(1);
      assertEquals(1, subtype1.getSubtypeRequiredProps().size());
      assertEquals(1, subtype1.getSubtypeOptionalProps().size());

      Property discriminator = depDetector.getDiscriminatorSeeker().getDiscriminator();
      assertEquals("PostTypeId", discriminator.getName());
      List<String> discriminatorValues = depDetector.getDiscriminatorSeeker().getDiscriminatorValues().values().stream().map(obj -> ((String)obj)).collect(Collectors.toList());
      Collections.sort(discriminatorValues);
      assertEquals(Arrays.asList("1", "2"), discriminatorValues);

      MongoCollection<Document> documents = client.getDatabase(dbName).getCollection(collName);
      List<EntitySubtype> filteredSubtypes = depDetector.getSubtypes().stream().filter(subtype -> !subtype.getSubtypeRequiredProps().isEmpty()).collect(Collectors.toList());

      for (Document doc : documents.find())
      {
        EntitySubtype theSubtype = detectSubtype(filteredSubtypes, doc);
        if (theSubtype == null)
          assertFalse(Arrays.asList("1", "2").contains(doc.get("PostTypeId")));

        assertEquals(depDetector.getDiscriminatorSeeker().getDiscriminatorValues().get(theSubtype), doc.get("PostTypeId"));
      }
    }
  }

  private EntitySubtype detectSubtype(List<EntitySubtype> subtypes, Document doc)
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
