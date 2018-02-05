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

import es.um.nosql.schemainference.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.schemainference.db.adapters.mongodb.MongoDbClient;
import es.um.nosql.schemainference.everypolitician.Areas;
import es.um.nosql.schemainference.everypolitician.Events;
import es.um.nosql.schemainference.everypolitician.Memberships;
import es.um.nosql.schemainference.everypolitician.Organizations;
import es.um.nosql.schemainference.everypolitician.Persons;

public class EveryPoliticianTest
{
  private MongoDbClient client;
  private Datastore datastore;
  private String dbName;
  private Validator validator;

  private Datastore newDatastore;
  private String newDbName;

  @Before
  public void setUp() throws Exception
  {
    Morphia morphia = new Morphia();
    morphia = morphia.mapPackage("es.um.nosql.schemainference.everypolitician");
    new ValidationExtension(morphia);
    dbName = "everypolitician";
    client = MongoDbAdapter.getMongoDbClient("localhost");
    datastore = morphia.createDatastore(client, dbName);
    validator = Validation.buildDefaultValidatorFactory().getValidator();
    newDbName = "neweverypolitician";
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
    List<Areas> lAreas = new ArrayList<Areas>();
    lAreas.addAll(datastore.createQuery(Areas.class).asList());
    newDatastore.save(lAreas);

    List<Organizations> lOrganizations = new ArrayList<Organizations>();
    lOrganizations.addAll(datastore.createQuery(Organizations.class).asList());
    newDatastore.save(lOrganizations);

    List<Events> lEvents = new ArrayList<Events>();
    lEvents.addAll(datastore.createQuery(Events.class).asList());
    newDatastore.save(lEvents);

    List<Persons> lPersons = new ArrayList<Persons>();
    lPersons.addAll(datastore.createQuery(Persons.class).asList());
    newDatastore.save(lPersons);

    List<Memberships> lMemberships = new ArrayList<Memberships>();
    lMemberships.addAll(datastore.createQuery(Memberships.class).asList());
    newDatastore.save(lMemberships);
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