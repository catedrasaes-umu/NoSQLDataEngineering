package es.um.nosql.s13e.test.morphia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;
import org.mongodb.morphia.VerboseJSR303ConstraintViolationException;
import org.mongodb.morphia.query.Query;

import es.um.nosql.s13e.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbClient;
import es.um.nosql.s13e.opensanctions.Address;
import es.um.nosql.s13e.opensanctions.Alias;
import es.um.nosql.s13e.opensanctions.Birth_date;
import es.um.nosql.s13e.opensanctions.Birth_place;
import es.um.nosql.s13e.opensanctions.Identifier;
import es.um.nosql.s13e.opensanctions.Nationality;
import es.um.nosql.s13e.opensanctions.Sanctions;

public class OpenSanctionsTest
{
  private final static int N_SANCTIONS = 32040;

  private Morphia morphia;
  private MongoDbClient client;
  private Datastore datastore;
  private String dbName;
  private Validator validator;

  @Before
  public void setUp() throws Exception
  {
    dbName = "opensanctions";
    morphia = new Morphia();
    morphia = morphia.mapPackage("es.um.nosql.s13e." + dbName);
    new ValidationExtension(morphia);
    client = MongoDbAdapter.getMongoDbClient("localhost");
    datastore = morphia.createDatastore(client, dbName);
    datastore.ensureIndexes();
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @After
  public void tearDown() throws Exception
  {
    client.close();
  }

  @Test
  public void testCheckConsistency()
  {
    checkOpenSanctionsDb(datastore);
  }

  @Test
  public void testDuplicateDbAndCheck()
  {
    String newDbName = dbName + "_test_1";
    Datastore newDatastore = morphia.createDatastore(client,  newDbName);

    List<Sanctions> lSanctions = new ArrayList<Sanctions>();
    lSanctions.addAll(datastore.createQuery(Sanctions.class).asList());
    newDatastore.save(lSanctions);

    checkOpenSanctionsDb(newDatastore);
    newDatastore.getDB().dropDatabase();
  }

  @Test
  public void testAddErrorAndCheck()
  {
    String newDbName = dbName + "_test_2";
    Datastore newDatastore = morphia.createDatastore(client,  newDbName);

    Identifier i1 = new Identifier(); i1.setNumber("aNumber");
    Identifier i2 = new Identifier(); i2.setNumber(33);
    Identifier i3 = new Identifier();
    assertThrows(ClassCastException.class, () -> {i3.setNumber(false);});
    assertThrows(ClassCastException.class, () -> {i3.setNumber(null);});
    assertEquals(0, validator.validate(i1).size());
    assertEquals(0, validator.validate(i2).size());

    Sanctions s1 = new Sanctions(); s1.set_id(new ObjectId().toString());
    Sanctions s2 = new Sanctions(); s2.set_id(new ObjectId().toString()); s2.setSource("source2");
    Sanctions s3 = new Sanctions(); s3.set_id(new ObjectId().toString()); s3.setTimestamp("timestamp3");
    Sanctions s4 = new Sanctions(); s4.set_id(new ObjectId().toString()); s4.setSource("source4"); s4.setTimestamp("timestamp4");

    assertEquals(2, validator.validate(s1).size());
    assertThrows(VerboseJSR303ConstraintViolationException.class, () -> {newDatastore.save(s1);});
    assertEquals(1, validator.validate(s2).size());
    assertThrows(VerboseJSR303ConstraintViolationException.class, () -> {newDatastore.save(s2);});
    assertEquals(1, validator.validate(s3).size());
    assertThrows(VerboseJSR303ConstraintViolationException.class, () -> {newDatastore.save(s3);});
    assertEquals(0, validator.validate(s4).size());

    newDatastore.getDB().dropDatabase();
  }

  private void checkOpenSanctionsDb(Datastore theDatastore)
  {
    Query<Sanctions> qSanctions = datastore.createQuery(Sanctions.class);
    assertEquals(N_SANCTIONS, qSanctions.count());
    testCollection(qSanctions.asList().toArray(new Sanctions[0]), Sanctions.class);

    for (Sanctions sanction : qSanctions)
    {
      testCollection(sanction.getBirth_dates(), Birth_date.class);
      testCollection(sanction.getNationalities(), Nationality.class);
      testCollection(sanction.getAliases(), Alias.class);
      testCollection(sanction.getIdentifiers(), Identifier.class);
      testCollection(sanction.getBirth_places(), Birth_place.class);
      testCollection(sanction.getAddresses(), Address.class);
    }
  }

  private <T> void testCollection(T[] collection, Class<T> className)
  {
    if (collection == null || collection.length == 0)
      return;

    for (T t : collection)
    {
      Set<ConstraintViolation<T>> violations = validator.validate(t);

      for (ConstraintViolation<T> cVio : violations)
        System.out.println(cVio.getMessage());

      assertEquals(0, violations.size());
    }
  }
}