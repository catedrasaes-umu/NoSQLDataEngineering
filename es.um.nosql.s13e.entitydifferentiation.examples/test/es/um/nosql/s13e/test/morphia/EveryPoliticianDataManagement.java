package es.um.nosql.s13e.test.morphia;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;

import es.um.nosql.s13e.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbClient;
import es.um.nosql.s13e.everypolitician.Areas;
import es.um.nosql.s13e.everypolitician.Events;

public class EveryPoliticianDataManagement
{
  private Morphia morphia;
  private MongoDbClient client;
  private Datastore datastore;
  private String dbName;

  public EveryPoliticianDataManagement(String ip, String dbName)
  {
    this.dbName = dbName;
    this.morphia = new Morphia();
    this.morphia = morphia.mapPackage("es.um.nosql.s13e." + this.dbName);
    new ValidationExtension(this.morphia);
    this.client = MongoDbAdapter.getMongoDbClient(ip);
    this.datastore = this.morphia.createDatastore(this.client, this.dbName);
    this.datastore.ensureIndexes();
  }

  public void startDemo()
  {
    for (Events ev : checkEventsNoId())
    {
      System.out.println(ev.get_id());System.out.println(ev.getClassification());System.out.println(ev.getName());System.out.println(ev.getStart_date());
      System.out.println("====");
    }

    int startingYear = 1818;
    int currentYear = 2018;
    for (int i = startingYear; i <= currentYear; i = i + 10)
      System.out.println("(" + (i - 15) + "-" + i + ") Events: " + getEventsBreakdown(i - 15, i).size());

//    print(checkEventsNoId());
    //for (Areas area : this.getCollection(Areas.class))
    //  System.out.println(area.getName() + ": " + area.getType());
  }

  private List<Events> checkEventsNoId()
  {
    List<Events> result = new ArrayList<Events>();

    result.addAll(this.getList(Events.class).stream()
        .filter(ev -> ev.getIdentifiers() != null && !ev.getIdentifiers().isEmpty())
        .collect(Collectors.toList()));

    return result;
  }

  private List<Events> getEventsBreakdown(int startingYear, int endingYear)
  {
    List<Events> result = new ArrayList<Events>();

    result.addAll(this.getList(Events.class).stream()
        .filter(ev ->
        {
          int date = Integer.parseInt(ev.getStart_date().substring(0, 4));
          return date > startingYear && date <= endingYear;
        })
        .collect(Collectors.toList()));

    return result;
  }

  private void print(List theList)
  {
    
  }

  private <T> List<T> getList(Class<T> className)
  {
    return datastore.createQuery(className).asList();
  }

  public static void main(String[] args)
  {
    EveryPoliticianDataManagement manager = new EveryPoliticianDataManagement("localhost", "everypolitician");
    manager.startDemo();
  }
}