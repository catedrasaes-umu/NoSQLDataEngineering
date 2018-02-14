package es.um.nosql.s13e.test.morphia.datamanagement;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;
import org.mongodb.morphia.query.FindOptions;

import es.um.nosql.s13e.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbClient;
import es.um.nosql.s13e.stackoverflow.Posts;
import es.um.nosql.s13e.stackoverflow.Votes;

public class StackOverflowDataManagement
{
  private Morphia morphia;
  private MongoDbClient client;
  private Datastore datastore;
  private String dbName;

  public StackOverflowDataManagement(String ip, String dbName)
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
    List<Number> lPostTypeId = new ArrayList<Number>();

    datastore.createQuery(Posts.class).project("PostTypeId", true)
      .fetch(new FindOptions().batchSize(25000)).iterator()
      .forEachRemaining(post ->
      {
        if (post.getPostTypeId() != null)
          lPostTypeId.add(post.getPostTypeId());
      });

    System.out.print("Distinct PostTypeIds: ");
    lPostTypeId.stream().distinct().forEach(n -> System.out.print(n + " "));

    List<Number> lVoteTypeId = new ArrayList<Number>();

    datastore.createQuery(Votes.class).project("VoteTypeId", true)
      .fetch(new FindOptions().batchSize(25000)).iterator()
      .forEachRemaining(vote ->
      {
        if (vote.getVoteTypeId() != null)
          lVoteTypeId.add(vote.getVoteTypeId());
      });

    System.out.print("\nDistinct VoteTypeIds: ");
    lVoteTypeId.stream().distinct().forEach(n -> System.out.print(n + " "));
  }

  public static void main(String[] args)
  {
    StackOverflowDataManagement manager = new StackOverflowDataManagement("localhost", "stackoverflow");
    manager.startDemo();
  }
}