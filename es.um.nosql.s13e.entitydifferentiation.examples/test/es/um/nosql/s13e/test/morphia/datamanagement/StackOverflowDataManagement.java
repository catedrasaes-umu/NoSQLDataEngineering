package es.um.nosql.s13e.test.morphia.datamanagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;
import org.mongodb.morphia.query.FindOptions;

import es.um.nosql.s13e.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbClient;
import es.um.nosql.s13e.stackoverflow.Postlinks;
import es.um.nosql.s13e.stackoverflow.Posts;
import es.um.nosql.s13e.stackoverflow.Users;
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
//    List<String> sPostTypeIds = getDistinctPostTypeIds();
//    System.out.print("Distinct PostTypeIds: ");
//    System.out.println(String.join(" ", sPostTypeIds));

//    List<String> sVoteTypeIds = getDistinctVoteTypeIds();
//    System.out.print("Distinct VoteTypeIds: ");
//    System.out.println(String.join(" ", sVoteTypeIds));

//    List<String> sPostlinksIds = getDistinctPostlinksIds();
//    System.out.print("Distinct LinkTypeIds: ");
//    System.out.println(String.join(" ", sPostlinksIds));

    analyzePosts();
  }

  private List<String> getDistinctPostTypeIds()
  {
    SortedSet<Integer> sPostTypeIds = new TreeSet<Integer>();

    datastore.createQuery(Posts.class).project("PostTypeId", true)
      .fetch(new FindOptions().batchSize(25000)).iterator()
      .forEachRemaining(post ->
      {
        if (post.getPostTypeId() != null)
          sPostTypeIds.add(post.getPostTypeId());
      });

    return sPostTypeIds.stream().map(n -> n.toString()).collect(Collectors.toList());
  }

  private List<String> getDistinctVoteTypeIds()
  {
    SortedSet<Integer> sVoteTypeIds = new TreeSet<Integer>();

    datastore.createQuery(Votes.class).project("VoteTypeId", true)
      .fetch(new FindOptions().batchSize(25000)).iterator()
      .forEachRemaining(vote ->
      {
        if (vote.getVoteTypeId() != null)
          sVoteTypeIds.add(vote.getVoteTypeId());
      });

    return sVoteTypeIds.stream().map(n -> n.toString()).collect(Collectors.toList());
  }

  private List<String> getDistinctPostlinksIds()
  {
    SortedSet<Integer> sPostlinksIds = new TreeSet<Integer>();

    datastore.createQuery(Postlinks.class).project("LinkTypeId", true)
      .fetch(new FindOptions().batchSize(25000)).iterator()
      .forEachRemaining(postlink ->
      {
        if (postlink.getLinkTypeId() != null)
          sPostlinksIds.add(postlink.getLinkTypeId());
      });

    return sPostlinksIds.stream().map(n -> n.toString()).collect(Collectors.toList());
  }

  private Map<String, Integer> getPostsByYear()
  {
    SortedMap<String, Integer> result = new TreeMap<String, Integer>();

    datastore.createQuery(Posts.class).project("CreationDate", true)
      .fetch(new FindOptions().batchSize(25000)).iterator()
      .forEachRemaining(post ->
      {
        String year = post.getCreationDate().substring(0, post.getCreationDate().lastIndexOf("-"));
        if (result.containsKey(year))
          result.put(year, result.get(year) + 1);
        else
          result.put(year, 0);
      });

    return result;
  }

  private void analyzePosts()
  {
    Map<String, Integer> mPostsByYear = getPostsByYear();

//  System.out.println("Posts created by year: ");
//  for (String key : mPostsByYear.keySet())
//    System.out.println(key + ": " + mPostsByYear.get(key));

    int sumPosts = mPostsByYear.values().stream().reduce((x,y) -> {return x + y;}).get();
    SortedMap<String, Integer> mSortedPosts = new TreeMap<String, Integer>();

    List<Entry<String, Integer>> lPosts = new ArrayList<Entry<String, Integer>>(mPostsByYear.entrySet());
    Collections.sort(lPosts, new Comparator<Entry<String, Integer>>()
    {
      @Override
      public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2)
      {
        return e2.getValue().compareTo(e1.getValue());
      }
    });

    for (Entry<String, Integer> entry : lPosts)
      System.out.println(entry.getKey() + ": " + entry.getValue());
    // Extract the top 10%...
  }
/*
  private List<Users> getUsersBreakdown(int startingYear, int endingYear)
  {
//    List<Events> result = new ArrayList<Events>();

    result.addAll(datastore.createQuery(Users.class).asList().stream()
        .filter(ev ->
        {
          return true;
        })
        .collect(Collectors.toList()));

    return null;
  }
*/
  public static void main(String[] args)
  {
    StackOverflowDataManagement manager = new StackOverflowDataManagement("localhost", "stackoverflow");
    manager.startDemo();
  }
}