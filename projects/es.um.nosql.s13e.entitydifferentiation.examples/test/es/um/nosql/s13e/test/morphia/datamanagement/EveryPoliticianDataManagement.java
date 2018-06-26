package es.um.nosql.s13e.test.morphia.datamanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;

import es.um.nosql.s13e.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbClient;
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
    System.out.println("Breakdown of events in 15-year stripes:");
    int startingYear = 1818;
    int currentYear = 2018;
    for (int i = startingYear; i <= currentYear; i = i + 10)
      System.out.println("\t(" + (i - 15) + "-" + i + ") Events: " + getEventsBreakdown(i - 15, i).size());
  }

  /**
   * Gets all the events between two dates.
   */
  private List<Events> getEventsBreakdown(int startingYear, int endingYear)
  {
    List<Events> result = new ArrayList<Events>();

    result.addAll(datastore.createQuery(Events.class).asList().stream()
        .filter(ev ->
        {
          int date = Integer.parseInt(ev.getStart_date().substring(0, 4));
          return date > startingYear && date <= endingYear;
        })
        .collect(Collectors.toList()));

    return result;
  }

  public static void main(String[] args)
  {
    EveryPoliticianDataManagement manager = new EveryPoliticianDataManagement("localhost", "everypolitician");
    manager.startDemo();
  }
}