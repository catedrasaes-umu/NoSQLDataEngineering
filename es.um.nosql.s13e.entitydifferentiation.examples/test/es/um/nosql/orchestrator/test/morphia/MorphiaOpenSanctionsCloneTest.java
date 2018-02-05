package es.um.nosql.orchestrator.test.morphia;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;
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

public class MorphiaOpenSanctionsCloneTest
{
  private MongoDbClient client;
  private Datastore datastore;
  private String dbName;
  private Datastore newDatastore;
  private String newDbName;
  private Validator validator;

  @Before
  public void setUp() throws Exception
  {
    Morphia morphia = new Morphia();
    morphia = morphia.mapPackage("es.um.nosql.s13e.opensanctions");
    new ValidationExtension(morphia);
    dbName = "opensanctions";
    client = MongoDbAdapter.getMongoDbClient("localhost");
    datastore = morphia.createDatastore(client, dbName);
    validator = Validation.buildDefaultValidatorFactory().getValidator();
    newDbName = "newopensanctions";
    newDatastore = morphia.createDatastore(client,  newDbName);
  }

  @After
  public void tearDown() throws Exception
  {
    client.close();
  }

  @Test
  public void testCopyDatabase()
  {
    List<Sanctions> lSanctions = new ArrayList<Sanctions>();
    lSanctions.addAll(datastore.createQuery(Sanctions.class).asList());
    newDatastore.save(lSanctions);
  }

  @Test
  public void testRetrieveSanctions()
  {
    Query<Sanctions> qSanctions = newDatastore.createQuery(Sanctions.class);
    Assert.assertEquals(32040, qSanctions.count());

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
        System.out.println(cVio);

      Assert.assertEquals(0, violations.size());
    }
  }
}