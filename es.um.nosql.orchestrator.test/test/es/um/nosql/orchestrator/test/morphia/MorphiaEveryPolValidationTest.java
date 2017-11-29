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

/**
 * Class used to validate the Morphia code generation for the EveryPolitician(Sweden) schema.
 * @author Alberto Hernández Chillón
 */
public class MorphiaEveryPolValidationTest
{
  private Datastore datastore;
  private MongoDbClient client;
  private String dbName;
  private Validator validator;

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
  }

  @After
  public void tearDown() throws Exception
  {
    client.close();
  }

  @Test
  public void testRetrieveAreas()
  {
    Query<Areas> qAreas = datastore.createQuery(Areas.class);
    Assert.assertEquals(29, qAreas.count());

    for (Areas area : qAreas)
    {
      Set<ConstraintViolation<Areas>> violations = validator.validate(area);
      for (ConstraintViolation<Areas> violation : violations)
        System.out.println(violation.getMessage());

      Assert.assertEquals(0, violations.size());
    }
  }

  @Test
  public void testRetrieveOrganizations()
  {
    // We actually detected here a bug. When an aggregate/reference cardinality is 0..1, it may actually be an array.
    Query<Organizations> qOrganizations = datastore.createQuery(Organizations.class);
    Assert.assertEquals(11, qOrganizations.count());

    for (Organizations organization : qOrganizations)
    {
      Set<ConstraintViolation<Organizations>> violations = validator.validate(organization);
      Assert.assertEquals(0, violations.size());

      testCollection(organization.getOther_names(), Other_name.class);
      testCollection(organization.getIdentifiers(), Identifier.class);
      testCollection(organization.getLinks(), Link.class);
    }
  }

  @Test
  public void testRetrieveEvents()
  {
    Query<Events> qEvents = datastore.createQuery(Events.class);
    Assert.assertEquals(59, qEvents.count());

    for (Events event : qEvents)
    {
      Set<ConstraintViolation<Events>> violations = validator.validate(event);
      Assert.assertEquals(0, violations.size());

      testCollection(event.getIdentifiers(), Identifier.class);

      // Dont have to check if the Organization is valid, since we just validated it on the previous method...
      if (event.getOrganization_id() != null)
        Assert.assertEquals(1, datastore.createQuery(Organizations.class).filter("id =", event.getOrganization_id()).count());
    }
  }

  @Test
  public void testRetrievePersons()
  {
    Query<Persons> qPersons = datastore.createQuery(Persons.class);
    Assert.assertEquals(1625, qPersons.count());

    for (Persons person : qPersons)
    {
      Set<ConstraintViolation<Persons>> violations = validator.validate(person);
      Assert.assertEquals(0, violations.size());

      testCollection(person.getOther_names(), Other_name.class);
      testCollection(person.getIdentifiers(), Identifier.class);
      testCollection(person.getLinks(), Link.class);
      testCollection(person.getContact_details(), Contact_detail.class);
      testCollection(person.getImages(), Image.class);
    }
  }

  @Test
  public void testRetrieveMemberships()
  {
    Query<Memberships> qMemberships = datastore.createQuery(Memberships.class);
    Assert.assertEquals(5149, qMemberships.count());

    for (Memberships membership : qMemberships)
    {
      Set<ConstraintViolation<Memberships>> violations = validator.validate(membership);
      Assert.assertEquals(0, violations.size());

      testCollection(membership.getSources(), Source.class);

      // Dont have to check if the references are valid themselves, since we just validated them on the previous method...
      if (membership.getArea_id() != null)
        Assert.assertEquals(1, datastore.createQuery(Areas.class).filter("id =", membership.getArea_id()).count());

      // Dont panic it this runs for several seconds. It does about 5000 queries...
      if (membership.getPerson_id() != null)
        Assert.assertEquals(1, datastore.createQuery(Persons.class).filter("id =", membership.getPerson_id()).count());

      if (membership.getOrganization_id() != null)
        Assert.assertEquals(1, datastore.createQuery(Organizations.class).filter("id =", membership.getOrganization_id()).count());
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