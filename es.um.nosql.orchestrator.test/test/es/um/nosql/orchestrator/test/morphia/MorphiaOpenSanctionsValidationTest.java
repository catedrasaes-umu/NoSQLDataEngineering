package es.um.nosql.orchestrator.test.morphia;

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

import es.um.nosql.schemainference.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.schemainference.db.adapters.mongodb.MongoDbClient;
import es.um.nosql.schemainference.opensanctions.Address;
import es.um.nosql.schemainference.opensanctions.Alias;
import es.um.nosql.schemainference.opensanctions.Birth_date;
import es.um.nosql.schemainference.opensanctions.Birth_place;
import es.um.nosql.schemainference.opensanctions.Identifier;
import es.um.nosql.schemainference.opensanctions.Nationality;
import es.um.nosql.schemainference.opensanctions.Sanctions;

/**
 * Class used to validate the Morphia code generation for the OpenSanctions schema.
 * @author Alberto Hernández Chillón
 */
public class MorphiaOpenSanctionsValidationTest
{
  private Datastore datastore;
  private MongoDbClient client;
  private String dbName;
  private Validator validator;

  @Before
  public void setUp() throws Exception
  {
    Morphia morphia = new Morphia();
    morphia = morphia.mapPackage("es.um.nosql.schemainference.opensanctions");
    new ValidationExtension(morphia);
    dbName = "opensanctions";
    client = MongoDbAdapter.getMongoDbClient("localhost");
    datastore = morphia.createDatastore(client, dbName);
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @After
  public void tearDown() throws Exception
  {
    client.close();
  }

  @Test
  public void testRetrieveSanctions()
  {
    Query<Sanctions> qSanctions = datastore.createQuery(Sanctions.class);
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