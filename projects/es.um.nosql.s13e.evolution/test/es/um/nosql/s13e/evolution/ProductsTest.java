package es.um.nosql.s13e.evolution;

import static org.junit.Assert.assertEquals;

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

public class ProductsTest
{
  private MongoDbClient client;
  private String dbName;
  private String inputModel;
  private NoSQLSchema schema;

  @Before
  public void setUp() throws Exception
  {
    client = MongoDbAdapter.getMongoDbClient("localhost");
    dbName = "products";
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
    String collName = "Products";
    // Detect and remove outliers given Coverage = 99.9%
    OutlierAnalyzer oAnalyzer = new OutlierAnalyzer(OutlierMode.COVERAGE);
    oAnalyzer.removeOutliers(schema);

    // Analyze each property
    for (SchemaType sType : schema.getEntities().stream().filter(ent -> ent.getName().equals(collName)).collect(Collectors.toList()))
    {
      DependencyAnalyzer depDetector = new DependencyAnalyzer(dbName, sType, true);
      assertEquals(4, depDetector.getSubtypes().size());

      EntitySubtype subtype0 = depDetector.getSubtypes().get(0);
      assertEquals(2, subtype0.getSubtypeRequiredProps().size());
      assertEquals(2, subtype0.getSubtypeOptionalProps().size());

      EntitySubtype subtype1 = depDetector.getSubtypes().get(1);
      assertEquals(2, subtype1.getSubtypeRequiredProps().size());
      assertEquals(0, subtype1.getSubtypeOptionalProps().size());

      EntitySubtype subtype2 = depDetector.getSubtypes().get(2);
      assertEquals(1, subtype2.getSubtypeRequiredProps().size());
      assertEquals(1, subtype2.getSubtypeOptionalProps().size());

      EntitySubtype subtype3 = depDetector.getSubtypes().get(3);
      assertEquals(1, subtype3.getSubtypeRequiredProps().size());
      assertEquals(0, subtype3.getSubtypeOptionalProps().size());

      Property discriminator = depDetector.getDiscriminatorSeeker().getDiscriminator();
      assertEquals("product_type", discriminator.getName());
      List<String> discriminatorValues = depDetector.getDiscriminatorSeeker().getDiscriminatorValues().values().stream().map(obj -> (String)obj).collect(Collectors.toList());
      Collections.sort(discriminatorValues);
      assertEquals(Arrays.asList("clothing", "laptops", "printed media", "toys"), discriminatorValues);

      MongoCollection<Document> documents = client.getDatabase(dbName).getCollection(collName);
      List<EntitySubtype> filteredSubtypes = depDetector.getSubtypes().stream().filter(subtype -> !subtype.getSubtypeRequiredProps().isEmpty()).collect(Collectors.toList());

      for (Document doc : documents.find())
      {
        EntitySubtype theSubtype = detectSubtype(filteredSubtypes, doc);
        if (theSubtype == null)
          continue;

        assertEquals(depDetector.getDiscriminatorSeeker().getDiscriminatorValues().get(theSubtype), doc.get("product_type"));
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
