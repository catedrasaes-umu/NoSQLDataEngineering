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
import es.um.nosql.schemainference.everypolitician.Areas;
import es.um.nosql.schemainference.mongoMovies3.Criticism;
import es.um.nosql.schemainference.mongoMovies3.Director;
import es.um.nosql.schemainference.mongoMovies3.Movie;
import es.um.nosql.schemainference.mongoMovies3.Movietheater;
import es.um.nosql.schemainference.mongoMovies3.Prize;
import es.um.nosql.schemainference.mongoMovies3.Rating;
import es.um.nosql.schemainference.test1.CustomDate1;
import es.um.nosql.schemainference.test1.CustomDate2;
import es.um.nosql.schemainference.test1.CustomDate4;
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
/*    CustomDate4 cd1 = new CustomDate4();
    CustomDate4 cd2 = new CustomDate4();
    CustomDate4 cd3 = new CustomDate4();
    CustomDate4 cd4 = new CustomDate4();
    cd1.setDate4("date4_1");
    cd1.set_id(new ObjectId().toString());
    cd2.setDate4("date4_2");
    cd2.set_id(new ObjectId().toString());
    cd3.setDate4("date4_3");
    cd3.set_id(new ObjectId().toString());
    cd4.setDate4("date4_4");
    cd4.set_id(new ObjectId().toString());

    List<CustomDate4> list = new ArrayList<CustomDate4>();
    list.add(cd1); list.add(cd2); list.add(cd3); list.add(cd4);
    datastore.save(list);
*/
    CustomDate1 cd1 = new CustomDate1();
    cd1.setDate1(1);

    CustomDate2 cd2 = new CustomDate2();
    cd2.setDate2(true);

    PersonalData pData1 = new PersonalData();
    pData1.setAge(11);
    pData1.setName("pData1");

    PersonalData pData2 = new PersonalData();
    pData2.setAge(2);
    pData2.setName("pData2");

    Persons p1 = new Persons(); p1.set_id(new ObjectId().toString()); p1.setData(pData1); p1.setDates(cd1);
    Persons p2 = new Persons(); p2.set_id(new ObjectId().toString()); p2.setData(pData2); p2.setDates(cd2);
    Persons p3 = new Persons(); p3.set_id(new ObjectId().toString()); p3.setData("data_raw");

    Query<CustomDate4> qCustomDate = datastore.createQuery(CustomDate4.class);
    p3.setDates(qCustomDate.asList().get(0));
    //TODO: Error. Cuando se agrega una referencia en una unión, el objeto referenciado se agrega como embebido.
    //Estrategia: Cuando una referencia está en una unión, si la referencia era de tipo string | number, se debe tratar en un preSave como string o number, y olvidarnos del objeto...
    //¿Cómo comprobamos todo esto? Quizá sea más sencillo en mongoose...

    List<Persons> lPersons = new ArrayList<Persons>();
    lPersons.add(p1); lPersons.add(p2); lPersons.add(p3);
    datastore.save(lPersons);
  }

  @Test
  public void testRetrieveObjects()
  {
    Query<CustomDate4> qCustomDate = datastore.createQuery(CustomDate4.class);
    Assert.assertEquals(4, qCustomDate.count());

    for (CustomDate4 cd : qCustomDate)
    {
      Set<ConstraintViolation<CustomDate4>> violations = validator.validate(cd);
      Assert.assertEquals(0, violations.size());
      System.out.println(cd.get_id() + ": " + cd.getDate4());
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