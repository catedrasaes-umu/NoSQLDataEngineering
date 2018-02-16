package es.um.nosql.s13e.db.gen.config.pojo;

public class OutputOptions
{
  private String file;

  public void setFile(String file) {this.file = file;}

  public String getFile() {return this.file;}

  private Boolean console;

  public void setConsole(Boolean console) {this.console = console;}

  public Boolean getConsole() {return this.console;}

  private String database;

  public void setDatabase(String database) {this.database = database;}

  public String getDatabase() {return this.database;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();

    if (this.getFile() != null)
      result.append("  -File: " + this.getFile() + "\n");

    if (this.getConsole() != null)
      result.append("  -Console: " + this.getConsole() + "\n");

    if (this.getDatabase() != null)
      result.append("  -Database: " + this.getDatabase() + "\n");

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