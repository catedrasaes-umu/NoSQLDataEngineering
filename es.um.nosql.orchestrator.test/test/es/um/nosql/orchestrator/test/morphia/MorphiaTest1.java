package es.um.nosql.orchestrator.test.morphia;

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
import org.mongodb.morphia.query.Query;

import es.um.nosql.schemainference.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.schemainference.db.adapters.mongodb.MongoDbClient;
import es.um.nosql.schemainference.test1.CustomDate1;
import es.um.nosql.schemainference.test1.CustomDate2;
import es.um.nosql.schemainference.test1.CustomDate3;
import es.um.nosql.schemainference.test1.PersonalData;
import es.um.nosql.schemainference.test1.Persons;

public class MorphiaTest1
{
  private Datastore datastore;
  private MongoDbClient client;
  private String dbName;
  private Validator validator;

  @Before
  public void setUp() throws Exception
  {
    Morphia morphia = new Morphia();
    morphia = morphia.mapPackage("es.um.nosql.schemainference.test1");
    new ValidationExtension(morphia);
    dbName = "test1";
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
  public void testCreateObjects()
  {
    CustomDate3 cd31 = new CustomDate3();
    CustomDate3 cd32 = new CustomDate3();
    CustomDate3 cd33 = new CustomDate3();
    cd31.setDate3("date3_1"); cd31.set_id(new ObjectId().toString());
    cd32.setDate3("date3_2"); cd32.set_id(new ObjectId().toString());
    cd33.setDate3("date3_3"); cd33.set_id(new ObjectId().toString());

    List<CustomDate3> list = new ArrayList<CustomDate3>();
    list.add(cd31); list.add(cd32); list.add(cd33);
    datastore.save(list);

    CustomDate1 cd11 = new CustomDate1();
    cd11.setDate1(11);

    CustomDate2 cd21 = new CustomDate2();
    cd21.setDate2(true);

    PersonalData pData1 = new PersonalData();
    pData1.setAge(1); pData1.setName("pData1");

    PersonalData pData2 = new PersonalData();
    pData2.setAge(2); pData2.setName("pData2");

    // p1: v1
    // p2: v2
    // p3: v3
    Persons p1 = new Persons(); p1.set_id(new ObjectId().toString()); p1.setDates(cd11); p1.setData(pData1);
    Persons p2 = new Persons(); p2.set_id(new ObjectId().toString()); p2.setDates(cd21); p2.setData(pData2);
    Persons p3 = new Persons(); p3.set_id(new ObjectId().toString()); p3.setData("data_raw");

    Query<CustomDate3> qCustomDate = datastore.createQuery(CustomDate3.class);
    p3.setDates(qCustomDate.asList().get(0));
    //TODO: Error. Cuando se agrega una referencia en una union, el objeto referenciado se agrega como embebido.
    //Estrategia: Cuando una referencia esta en una union, si la referencia era de tipo string | number, se debe tratar en un preSave como string o number, y olvidarnos del objeto...
    //¿Como comprobamos todo esto? Quiza sea mas sencillo en mongoose...

    List<Persons> lPersons = new ArrayList<Persons>();
    lPersons.add(p1); lPersons.add(p2); lPersons.add(p3);
    datastore.save(lPersons);
  }

  @Test
  public void testRetrieveObjects()
  {
    Query<CustomDate3> qCustomDate = datastore.createQuery(CustomDate3.class);
    Assert.assertEquals(3, qCustomDate.count());

    for (CustomDate3 cd : qCustomDate)
    {
      Set<ConstraintViolation<CustomDate3>> violations = validator.validate(cd);
      Assert.assertEquals(0, violations.size());
    }

    Query<Persons> qPersons = datastore.createQuery(Persons.class);
    Assert.assertEquals(3, qPersons.count());
    for (Persons p : qPersons)
    {
      Set<ConstraintViolation<Persons>> violations = validator.validate(p);
      Assert.assertEquals(0, violations.size());
      System.out.println(p.get_id() + ": " + p.getData() + p.getDates());
    }
  }
}