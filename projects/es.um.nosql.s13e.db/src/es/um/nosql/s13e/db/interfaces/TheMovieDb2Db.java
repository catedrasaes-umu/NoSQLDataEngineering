package es.um.nosql.s13e.db.interfaces;

import java.nio.file.Paths;

import es.um.nosql.s13e.db.interfaces.themoviedb.Companies2Db;
import es.um.nosql.s13e.db.interfaces.themoviedb.Companies_Images2Db;
import es.um.nosql.s13e.db.interfaces.themoviedb.Genres2Db;
import es.um.nosql.s13e.db.interfaces.themoviedb.Keywords2Db;
import es.um.nosql.s13e.db.interfaces.themoviedb.Media2Db;
import es.um.nosql.s13e.db.interfaces.themoviedb.Movies2Db;
import es.um.nosql.s13e.db.interfaces.themoviedb.Networks2Db;
import es.um.nosql.s13e.db.interfaces.themoviedb.People2Db;
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
      storeJsonContent(jsonRoute, dbName);
      System.out.println(dbName + ":" + jsonFolderName + " table created in " + (System.currentTimeMillis() - startTime) + " ms");
    }
  }

  private void storeJsonContent(String jsonRoute, String dbName)
  {
    String jsonFolderName = Paths.get(jsonRoute).getFileName().toString();

    switch (jsonFolderName)
    {
      case "companies": { new Companies2Db(this.getClient()).inject(jsonRoute, dbName); break; }
      case "companies_images": { new Companies_Images2Db(this.getClient()).inject(jsonRoute, dbName); break; }
      case "genres": { new Genres2Db(this.getClient()).inject(jsonRoute, dbName); break; }
      case "keywords": { new Keywords2Db(this.getClient()).inject(jsonRoute, dbName); break; }
      case "networks": { new Networks2Db(this.getClient()).inject(jsonRoute, dbName); break; }
      case "people":
      {
        new Media2Db(this.getClient()).inject(jsonRoute, dbName);
        new People2Db(this.getClient()).inject(jsonRoute, dbName);
        break;
      }
      case "movies":
      {
        //TODO: Videos?
        new Movies2Db(this.getClient()).inject(jsonRoute, dbName);
        break;
      }
      case "tv": case "seasons": default: {break; }
    }
  }
}
