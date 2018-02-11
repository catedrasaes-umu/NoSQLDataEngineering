package es.um.nosql.s13e.test3;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Validation;
import javax.validation.Validator;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;
import org.mongodb.morphia.query.Query;

import es.um.nosql.s13e.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbClient;

public class MainTest3
{

  public static void main(String[] args)
  {
    String dbName = "test3";
    Morphia morphia = (new Morphia()).mapPackage("es.um.nosql.s13e." + dbName);
    new ValidationExtension(morphia);
    MongoDbClient client = MongoDbAdapter.getMongoDbClient("localhost");
    Datastore datastore = morphia.createDatastore(client, dbName);
    datastore.ensureIndexes();
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

//    CustomDate1 cd1 = new CustomDate1(); cd1.set_id(new ObjectId()); cd1.setDate1(1);
//    CustomDate1 cd2 = new CustomDate1(); cd2.set_id(new ObjectId()); cd2.setDate1(45);
//    datastore.save(cd1);
//    datastore.save(cd2);


/*    PersonalData pd1 = new PersonalData(); pd1.setAge(1); pd1.setName("name1");
    PersonalData pd2 = new PersonalData(); pd2.setAge(2); pd2.setName("name2");

    ArrayList<PersonalData> pd = new ArrayList<PersonalData>();
    pd.add(pd1);

    Persons p1 = new Persons(); p1.set_id(new ObjectId()); p1.setData("string");
    Persons p2 = new Persons(); p2.set_id(new ObjectId()); p2.setData(pd);

    datastore.save(p1);
    datastore.save(p2);*/
    Query<Persons> qPersons = datastore.createQuery(Persons.class);
    for (Persons p : qPersons)
    {System.out.println("<<<<<<");
      System.out.println(p.get_id());
      if (p.getData() instanceof List)
      {
        List<PersonalData> myList = (List)p.getData();
        for (PersonalData d : myList)
        {
          System.out.println(d.getAge());
          System.out.println(d.getName());          
        }
      }
      System.out.println(">>>>>");
    }
  }
}
