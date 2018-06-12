package es.um.nosql.s13e.test.morphia.datamanagement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;
import org.mongodb.morphia.query.FindOptions;

import es.um.nosql.s13e.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbClient;
import es.um.nosql.s13e.stackoverflow.Badges;
import es.um.nosql.s13e.stackoverflow.Postlinks;
import es.um.nosql.s13e.stackoverflow.Posts;
import es.um.nosql.s13e.stackoverflow.Tags;
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
    /**
     * First test: Check how many distinct PostTypeIds (could have been done with the distinct Morphia operator)
     */
    List<String> sPostTypeIds = getDistinctPostTypeIds();
    System.out.print("Distinct PostTypeIds: ");
    System.out.println(String.join(" ", sPostTypeIds));

    /**
     * Second test: Check how many distinct VoteTypeIds (could have been done with the distinct Morphia operator)
     */
    List<String> sVoteTypeIds = getDistinctVoteTypeIds();
    System.out.print("Distinct VoteTypeIds: ");
    System.out.println(String.join(" ", sVoteTypeIds));

    /**
     * Third test: Check how many distinct LinkTypeIds (could have been done with the distinct Morphia operator)
     */
    List<String> sPostlinksIds = getDistinctPostlinksIds();
    System.out.print("Distinct LinkTypeIds: ");
    System.out.println(String.join(" ", sPostlinksIds));

    /**
     * Fourth test: Check which months generated the top 25% posts in this StackOverflow subdataset.
     */
    topMonthsOfActivity();

    /**
     * Fifth test: Check which are the top 25% most commonly used tags in this StackOverflow subdataset.
     */
    mostPopularTags();

    /**
     * Sixth test: Check the average close question post time from posts closed in less than a year.
     */
    avgTimeToCloseQuestion();

    /**
     * Seventh test: For each badge check the recent date in which some User earned it.
     */
    mostRecentBadges();

    this.client.close();
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

  private void topMonthsOfActivity()
  {
    Map<String, Integer> mPostsByYear = getPostsByYear();

    List<Entry<String, Integer>> lPosts = new ArrayList<Entry<String, Integer>>(mPostsByYear.entrySet());
    Collections.sort(lPosts, new Comparator<Entry<String, Integer>>()
    {
      @Override
      public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2)
      {
        return e2.getValue().compareTo(e1.getValue());
      }
    });

    System.out.println("Top twenty-five percent months of activity: ");
    int sumPosts = mPostsByYear.values().stream().reduce((x,y) -> {return x + y;}).get();
    int percPosts = (sumPosts * 25) / 100;
    int aux = 0;

    for (Entry<String, Integer> entry : lPosts)
    {
      aux += entry.getValue();
      System.out.println(entry.getKey() + ": " + entry.getValue());

      if (aux >= percPosts)
        break;
    }
  }

  // Take into account that "Count" is stored as strings in the SOF dataset, so ordering/limiting
  // the number of responses by using the Morphia API is not useful. We have to query each object and map them to our POJO.
  private void mostPopularTags()
  {
    Map<String, Integer> mTagsByCount = new HashMap<String, Integer>();

    datastore.createQuery(Tags.class).project("Count", true).project("TagName", true)
      .fetch(new FindOptions().batchSize(25000)).iterator()
      .forEachRemaining(tag ->
      {
        mTagsByCount.put(tag.getTagName(), tag.getCount());
      });

    List<Entry<String, Integer>> lTags = new ArrayList<Entry<String, Integer>>(mTagsByCount.entrySet());
    Collections.sort(lTags, new Comparator<Entry<String, Integer>>()
    {
      @Override
      public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2)
      {
        return e2.getValue().compareTo(e1.getValue());
      }
    });

    System.out.println("Top twenty-five percent most commonly used tags: ");
    int sumTags = mTagsByCount.values().stream().reduce((x, y) -> {return x + y;}).get();
    int percPosts = (sumTags * 25) / 100;
    int aux = 0;

    for (Entry<String, Integer> entry : lTags)
    {
      aux += entry.getValue();
      System.out.println(entry.getKey() + ": " + entry.getValue());

      if (aux >= percPosts)
        break;
    }
  }

  private void avgTimeToCloseQuestion()
  {
    List<Long> diffLongs = new ArrayList<Long>();
    long avgTime = 0;
    int TOO_LONG_FILTER = 365; // We're going to remove posts not closed in a year since creation... 
    DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    datastore.createQuery(Posts.class).filter("CreationDate exists", true).filter("ClosedDate exists", true)
      .project("CreationDate", true).project("ClosedDate", true)
      .fetch(new FindOptions().batchSize(25000)).iterator()
      .forEachRemaining(post ->
      {
        try
        {
          Date creationDate = dFormat.parse(post.getCreationDate());
          Date closedDate = dFormat.parse(post.getClosedDate());
          long diffInMillies = closedDate.getTime() - creationDate.getTime();

          if (TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) <= TOO_LONG_FILTER)
            diffLongs.add(TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS));
        } catch (ParseException e)
        {
          e.printStackTrace();
        }
      });

    for (int i = 0; i < diffLongs.size(); i++)
      avgTime = ((avgTime * i) + diffLongs.get(i)) / (i + 1);

    System.out.println("Average closing time of posts in seconds is " + avgTime);
    System.out.println("(Minutes: " + TimeUnit.MINUTES.convert(avgTime, TimeUnit.SECONDS) + ")");
    System.out.println("(Hours: " + TimeUnit.HOURS.convert(avgTime, TimeUnit.SECONDS) + ")");
    System.out.println("(Days: " + TimeUnit.DAYS.convert(avgTime, TimeUnit.SECONDS) + ")");
  }

  private void mostRecentBadges()
  {
    SortedMap<String, Date> mEarliestBadge = new TreeMap<String, Date>();
    DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    // Since we are working with a small dataset, some badge users won't be on the database...
    datastore.createQuery(Badges.class).filter("UserId <", "2000000").project("Name", true).project("UserId", true)
      .fetch(new FindOptions().batchSize(25000)).iterator()
      .forEachRemaining(badge ->
      {
        try
        {
          if (!Character.isUpperCase(badge.getName().charAt(0)))
            return;

          Date theDate = dFormat.parse(badge.getUserId().getCreationDate());

          if (!mEarliestBadge.containsKey(badge.getName()))
            mEarliestBadge.put(badge.getName(), theDate);
          else
            if (mEarliestBadge.get(badge.getName()).before(theDate))
              mEarliestBadge.put(badge.getName(), theDate);
        } catch(Exception e)
        {
          e.printStackTrace();
        }
      });

    for (String badge : mEarliestBadge.keySet())
      System.out.println(badge + ": " + mEarliestBadge.get(badge));
  }

  public static void main(String[] args)
  {
    StackOverflowDataManagement manager = new StackOverflowDataManagement("localhost", "stackoverflow");
    manager.startDemo();
  }
}