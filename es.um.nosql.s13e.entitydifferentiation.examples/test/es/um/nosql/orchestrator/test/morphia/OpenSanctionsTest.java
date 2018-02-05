package es.um.nosql.orchestrator.test.morphia;

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
import org.junit.Assert;
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
    morphia = new Morphia();
    morphia = morphia.mapPackage("es.um.nosql.schemainference.opensanctions");
    new ValidationExtension(morphia);
    dbName = "opensanctions";
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
  public void testDuplicateBdAndCheck()
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

    List<Sanctions> lSanctions = new ArrayList<Sanctions>();
    lSanctions.addAll(datastore.createQuery(Sanctions.class).asList());
    newDatastore.save(lSanctions);

    Sanctions s1 = new Sanctions(); s1.set_id(new ObjectId().toString());
    newDatastore.save(s1);
/*
 //TODO: CREATE CORRECT AND ERRONEOUS SANCTIONS AND CHECK THEM AGAINST THE MAPPER! 
    newDatastore.save(org1);
    assertThrows(VerboseJSR303ConstraintViolationException.class, () -> {newDatastore.save(org2);});
*/
    Query<Sanctions> qSanctions = newDatastore.createQuery(Sanctions.class);
    assertEquals(N_SANCTIONS + 1, qSanctions.count());

    for (Sanctions org : qSanctions)
    {
      Set<ConstraintViolation<Sanctions>> violations = validator.validate(org);
      assertEquals(0, violations.size());
    }

    newDatastore.getDB().dropDatabase();
  }

  private void checkOpenSanctionsDb(Datastore theDatastore)
  {
    Query<Sanctions> qSanctions = datastore.createQuery(Sanctions.class);
    Assert.assertEquals(N_SANCTIONS, qSanctions.count());

    for (Sanctions sanction : qSanctions)
    {
      Set<ConstraintViolation<Sanctions>> violations = validator.validate(sanction);
      Assert.assertEquals(0, violations.size());

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