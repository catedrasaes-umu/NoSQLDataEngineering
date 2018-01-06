package es.um.nosql.orchestrator.test.morphia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.metadata.BeanDescriptor;

import org.bson.types.ObjectId;
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
import es.um.nosql.schemainference.test2.Persons;

public class MorphiaTestConfig
{
  private Datastore datastore;
  private MongoDbClient client;
  private String dbName;
  private Validator validator;

  @Before
  public void setUp() throws Exception
  {
    Morphia morphia = new Morphia();
    morphia = morphia.mapPackage("es.um.nosql.schemainference.test2");
    new ValidationExtension(morphia);
    dbName = "test2";
    client = MongoDbAdapter.getMongoDbClient("localhost");
    datastore = morphia.createDatastore(client, dbName);
    datastore.ensureIndexes();
    validator = Validation.buildDefaultValidatorFactory().getValidator();
    //client.cleanDb(dbName);
  }

  @After
  public void tearDown() throws Exception
  {
    client.close();
  }

  @Test
  public void testStorePersons()
  {
    Persons p1 = new Persons();p1.set_id(new ObjectId());p1.setAge(1);p1.setName("name1");p1.setSurname("surname1");p1.setIsEmployed(false);p1.setIsMarried(false);p1.setStatus("status1");
    Persons p2 = new Persons();p2.set_id(new ObjectId());p2.setAge(2);p2.setName("name2");p2.setSurname("surname2");p2.setIsEmployed(false);p2.setIsMarried(true);p2.setStatus("status2");
    Persons p3 = new Persons();p3.set_id(new ObjectId());p3.setAge(3);p3.setName("name3");p3.setSurname("surname3");p3.setIsEmployed(true);p3.setIsMarried(false);p3.setStatus("status3");
    Persons p4 = new Persons();p4.set_id(new ObjectId());p4.setAge(4);p4.setName("name4");p4.setSurname("surname4");p4.setIsEmployed(true);p4.setIsMarried(true); p4.setStatus("status4");
    Persons p5 = new Persons();p5.set_id(new ObjectId());p5.setAge(15);p5.setName("name5");p5.setSurname("surname5");p5.setIsEmployed(false);p5.setIsMarried(false); p5.setStatus("status5");

    List<Persons> list = new ArrayList<Persons>();
    list.add(p1); list.add(p2); list.add(p3); list.add(p4); list.add(p5);

    Set<ConstraintViolation<Persons>> violations = validator.validate(p1); Assert.assertEquals(0, violations.size());
    violations = validator.validate(p2); Assert.assertEquals(0, violations.size());
    violations = validator.validate(p3); Assert.assertEquals(0, violations.size());
    violations = validator.validate(p4); Assert.assertEquals(0, violations.size());
    violations = validator.validate(p5); Assert.assertEquals(0, violations.size());

    datastore.save(list);

    Query<Persons> qPersons = datastore.createQuery(Persons.class);
    Assert.assertEquals(5, qPersons.count());

    for (Persons p : qPersons)
    {
      violations = validator.validate(p);
      Assert.assertEquals(0, violations.size());

      System.out.println(p.get_id() + ": " + p.getName() + " " + p.getSurname());
      System.out.println("    Age: " + p.getAge() + " - Married? " + p.getIsMarried() + " - Employed?" + p.getIsEmployed());
      System.out.println("    Status: " + p.getStatus());
    }
  }
}