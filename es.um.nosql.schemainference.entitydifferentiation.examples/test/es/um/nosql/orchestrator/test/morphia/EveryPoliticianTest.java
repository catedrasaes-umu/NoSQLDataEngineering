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
import es.um.nosql.schemainference.everypolitician.Areas;
import es.um.nosql.schemainference.everypolitician.Contact_detail;
import es.um.nosql.schemainference.everypolitician.Events;
import es.um.nosql.schemainference.everypolitician.Identifier;
import es.um.nosql.schemainference.everypolitician.Image;
import es.um.nosql.schemainference.everypolitician.Link;
import es.um.nosql.schemainference.everypolitician.Memberships;
import es.um.nosql.schemainference.everypolitician.Organizations;
import es.um.nosql.schemainference.everypolitician.Other_name;
import es.um.nosql.schemainference.everypolitician.Persons;
import es.um.nosql.schemainference.everypolitician.Source;

public class EveryPoliticianTest
{
  private final static int N_AREAS = 29;
  private final static int N_ORGANIZATIONS = 11;
  private final static int N_EVENTS = 62;
  private final static int N_PERSONS = 1625;
  private final static int N_MEMBERSHIPS = 5149;

  private Morphia morphia;
  private MongoDbClient client;
  private Datastore datastore;
  private String dbName;
  private Validator validator;

  private Datastore newDatastore;
  private String newDbName;

  @Before
  public void setUp() throws Exception
  {
    morphia = new Morphia();
    morphia = morphia.mapPackage("es.um.nosql.schemainference.everypolitician");
    new ValidationExtension(morphia);
    dbName = "everypolitician";
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
  public void testCheckConsistency()
  {
    Query<Areas> qAreas = datastore.createQuery(Areas.class);
    Assert.assertEquals(N_AREAS, qAreas.count());
    testCollection(qAreas.asList().toArray(new Areas[0]), Areas.class);

    // We actually detected here a bug. When an aggregate/reference cardinality is 0..1, it may actually be an array.
    Query<Organizations> qOrganizations = datastore.createQuery(Organizations.class);
    Assert.assertEquals(N_ORGANIZATIONS, qOrganizations.count());
    testCollection(qOrganizations.asList().toArray(new Organizations[0]), Organizations.class);

    for (Organizations organization : qOrganizations)
    {
      testCollection(organization.getOther_names(), Other_name.class);
      testCollection(organization.getIdentifiers(), Identifier.class);
      testCollection(organization.getLinks(), Link.class);
    }

    Query<Events> qEvents = datastore.createQuery(Events.class);
    Assert.assertEquals(N_EVENTS, qEvents.count());
    testCollection(qEvents.asList().toArray(new Events[0]), Events.class);

    for (Events event : qEvents)
    {
      testCollection(event.getIdentifiers(), Identifier.class);

      // Dont have to check if the Organization is valid, since we just validated it on the previous step...
      if (event.getOrganization_id() != null)
        Assert.assertEquals(1, datastore.createQuery(Organizations.class).filter("_id =", event.getOrganization_id().get_id()).count());
    }

    Query<Persons> qPersons = datastore.createQuery(Persons.class);
    Assert.assertEquals(N_PERSONS, qPersons.count());
    testCollection(qPersons.asList().toArray(new Persons[0]), Persons.class);

    for (Persons person : qPersons)
    {
      testCollection(person.getOther_names(), Other_name.class);
      testCollection(person.getIdentifiers(), Identifier.class);
      testCollection(person.getLinks(), Link.class);
      testCollection(person.getContact_details(), Contact_detail.class);
      testCollection(person.getImages(), Image.class);
    }

    Query<Memberships> qMemberships = datastore.createQuery(Memberships.class);
    Assert.assertEquals(N_MEMBERSHIPS, qMemberships.count());
    testCollection(qMemberships.asList().toArray(new Memberships[0]), Memberships.class);

    for (Memberships membership : qMemberships)
    {
      testCollection(membership.getSources(), Source.class);

      // Dont have to check if the references are valid themselves, since we just validated them on the previous method...
      if (membership.getArea_id() != null)
        Assert.assertEquals(1, datastore.createQuery(Areas.class).filter("_id =", membership.getArea_id().get_id()).count());

      // Dont panic it this runs for several seconds. It does about 5000 queries...
      if (membership.getPerson_id() != null)
        Assert.assertEquals(1, datastore.createQuery(Persons.class).filter("_id =", membership.getPerson_id().get_id()).count());

      if (membership.getOrganization_id() != null)
        Assert.assertEquals(1, datastore.createQuery(Organizations.class).filter("_id =", membership.getOrganization_id().get_id()).count());
    }
  }

  @Test
  public void testDuplicateBdAndCheck()
  {
    newDbName = "neweverypolitician";
    newDatastore = morphia.createDatastore(client,  newDbName);
  }

  @Test
  public void testAddErrorAndCheck()
  {
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

      Assert.assertEquals(0, violations.size());
    }
  }
}