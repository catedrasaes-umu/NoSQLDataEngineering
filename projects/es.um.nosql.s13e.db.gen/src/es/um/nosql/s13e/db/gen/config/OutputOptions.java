package es.um.nosql.s13e.db.gen.config;

import es.um.nosql.s13e.db.gen.util.constants.ConfigConstants;

public class OutputOptions
{
  private String folder;
  public void setFolder(String folder) {this.folder = folder;}
  public String getFolder() {return this.folder;}

  private Boolean console;
  public void setConsole(Boolean console) {this.console = console;}
  public Boolean getConsole() {return this.console;}

  private String database;
  public void setDatabase(String database) {this.database = database;}
  public String getDatabase() {return this.database;}

  private String databaseCollection;
  public void setDatabaseCollection(String databaseCollection) {this.databaseCollection = databaseCollection;}
  public String getDatabaseCollection() {return this.databaseCollection;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();

    if (this.getFolder() != null)             result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Folder: " + this.getFolder() + "\n");
    if (this.getConsole() != null)            result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Console: " + this.getConsole() + "\n");
    if (this.getDatabase() != null)           result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Database: " + this.getDatabase() + "\n");
    if (this.getDatabaseCollection() != null) result.append(ConfigConstants.GET_TABS(this.getClass()) + "-DatabaseCollection: " + this.getDatabase() + "\n");

    return result.toString();
  }

  public boolean doCheck()
  {
    if (this.getDatabase() != null && !this.getDatabase().isEmpty() && !this.getDatabase().equals("localhost"))
    {
      String[] ipParts = this.getDatabase().split("\\.");

      if (ipParts.length != 4 || this.getDatabase().endsWith("."))
        throw new IllegalArgumentException(this.getDatabase() + " - Database not well defined. Database field must be a valid IP or \"localhost\".");

      for (String part : ipParts)
      {
        int i = 0;
        try {i = Integer.parseInt(part);} catch (NumberFormatException e)
        {throw new IllegalArgumentException(this.getDatabase() + " - Database not well defined. Database field must be a valid IP or \"localhost\".");}

        if (i < 0 || i > 255)
          throw new IllegalArgumentException(this.getDatabase() + " - Database not well defined. Database field must be a valid IP or \"localhost\".");
      }
    }

    return true;
  }
}
