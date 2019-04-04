package es.um.nosql.s13e.db.interfaces;

import java.nio.file.Paths;

import es.um.nosql.s13e.db.interfaces.themoviedb.Companies2Db;
import es.um.nosql.s13e.db.interfaces.themoviedb.Companies_Images2Db;
import es.um.nosql.s13e.db.interfaces.themoviedb.Episodes2Db;
import es.um.nosql.s13e.db.interfaces.themoviedb.Genres2Db;
import es.um.nosql.s13e.db.interfaces.themoviedb.Keywords2Db;
import es.um.nosql.s13e.db.interfaces.themoviedb.Movies2Db;
import es.um.nosql.s13e.db.interfaces.themoviedb.Networks2Db;
import es.um.nosql.s13e.db.interfaces.themoviedb.People2Db;
import es.um.nosql.s13e.db.interfaces.themoviedb.Reviews2Db;
import es.um.nosql.s13e.db.interfaces.themoviedb.Seasons2Db;
import es.um.nosql.s13e.db.interfaces.themoviedb.TV2Db;
import es.um.nosql.s13e.db.util.DbType;

public class TheMovieDb2Db extends Source2Db
{
  public TheMovieDb2Db(DbType db, String ip)
  {
    super(db, ip);
  }

  public void run(String jsonRoute, String dbName)
  {
    long startTime = System.currentTimeMillis();
    String jsonFolderName = Paths.get(jsonRoute).getFileName().toString();

    System.out.println("Reading folder " + jsonRoute + "...");

    if (jsonFolderName.startsWith("_"))
    {
      System.out.println("Folder " + jsonFolderName + " is not a valid folder. Ignoring...");
    }
    else
    {
      storeJSONContent(jsonRoute, dbName);
      System.out.println(dbName + ":" + jsonFolderName + " table created in " + (System.currentTimeMillis() - startTime) + " ms");
    }
  }

  private void storeJSONContent(String jsonRoute, String dbName)
  {
    String jsonFolderName = Paths.get(jsonRoute).getFileName().toString();

    switch (jsonFolderName)
    {
      case "companies": { new Companies2Db(this.getClient()).inject(jsonRoute, dbName); break; }
      case "companies_images": { new Companies_Images2Db(this.getClient()).inject(jsonRoute, dbName); break; }
      case "genres": { new Genres2Db(this.getClient()).inject(jsonRoute, dbName); break; }
      case "keywords": { new Keywords2Db(this.getClient()).inject(jsonRoute, dbName); break; }
      case "networks": { new Networks2Db(this.getClient()).inject(jsonRoute, dbName); break; }
      case "people": { new People2Db(this.getClient()).inject(jsonRoute, dbName); break; }
      case "movies":
      {
        //////////new Videos2Db(this.getClient()).inject(jsonRoute, dbName);
        new Movies2Db(this.getClient()).inject(jsonRoute, dbName);
        break;
      }
      case "seasons":
      {
        new Episodes2Db(this.getClient()).inject(jsonRoute, dbName);
        new Seasons2Db(this.getClient()).inject(jsonRoute, dbName);
        break;
      }
      case "tv":
      {
        new Reviews2Db(this.getClient()).inject(jsonRoute, dbName);
        //////////new Changes2Db(this.getClient()).inject(jsonRoute, dbName);
        //////////new EpisodeGroups2Db(this.getClient()).inject(jsonRoute, dbName);
        //////////new ScreenedTheatrically2Db(this.getClient()).inject(jsonRoute, dbName);
        new TV2Db(this.getClient()).inject(jsonRoute, dbName);
        break;
      }
    }
  }
}
