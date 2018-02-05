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

public class MorphiaEveryPolCloneTest
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

  @Test
  // Exactly the same as the other test case...just to ensure the two databases are identical.
  public void testCheckCopy()
  {
    Query<Areas> qAreas = newDatastore.createQuery(Areas.class);
    Assert.assertEquals(29, qAreas.count());

    for (Areas area : qAreas)
    {
      Set<ConstraintViolation<Areas>> violations = validator.validate(area);
      Assert.assertEquals(0, violations.size());
    }

    Query<Organizations> qOrganizations = newDatastore.createQuery(Organizations.class);
    Assert.assertEquals(11, qOrganizations.count());

    for (Organizations organization : qOrganizations)
    {
      Set<ConstraintViolation<Organizations>> violations = validator.validate(organization);
      Assert.assertEquals(0, violations.size());

      testCollection(organization.getOther_names(), Other_name.class);
      testCollection(organization.getIdentifiers(), Identifier.class);
      testCollection(organization.getLinks(), Link.class);
    }

    Query<Events> qEvents = newDatastore.createQuery(Events.class);
    Assert.assertEquals(62, qEvents.count());

    for (Events event : qEvents)
    {
      Set<ConstraintViolation<Events>> violations = validator.validate(event);
      Assert.assertEquals(0, violations.size());

      testCollection(event.getIdentifiers(), Identifier.class);

      // Dont have to check if the Organization is valid, since we just validated it on the previous method...
      if (event.getOrganization_id() != null)
        Assert.assertEquals(1, newDatastore.createQuery(Organizations.class).filter("_id =", event.getOrganization_id().get_id()).count());
    }

    Query<Persons> qPersons = newDatastore.createQuery(Persons.class);
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

    Query<Memberships> qMemberships = newDatastore.createQuery(Memberships.class);
    Assert.assertEquals(5149, qMemberships.count());

    for (Memberships membership : qMemberships)
    {
      Set<ConstraintViolation<Memberships>> violations = validator.validate(membership);
      Assert.assertEquals(0, violations.size());

      testCollection(membership.getSources(), Source.class);

      // Dont have to check if the references are valid themselves, since we just validated them on the previous step...
      if (membership.getArea_id() != null)
        Assert.assertEquals(1, newDatastore.createQuery(Areas.class).filter("_id =", membership.getArea_id().get_id()).count());

      // Dont panic it this runs for several seconds. It does about 5000 queries...
      if (membership.getPerson_id() != null)
        Assert.assertEquals(1, newDatastore.createQuery(Persons.class).filter("_id =", membership.getPerson_id().get_id()).count());

      if (membership.getOrganization_id() != null)
        Assert.assertEquals(1, newDatastore.createQuery(Organizations.class).filter("_id =", membership.getOrganization_id().get_id()).count());
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