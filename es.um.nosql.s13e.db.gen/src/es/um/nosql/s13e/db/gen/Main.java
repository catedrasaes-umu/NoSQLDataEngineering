package es.um.nosql.s13e.db.gen;

import java.io.File;

import es.um.nosql.s13e.db.gen.config.pojo.DbGenOptions;
import es.um.nosql.s13e.db.gen.utils.IOUtils;

public class Main
{
  public static void main(String[] args)
  {
    File configFile = new File("config/config.yaml");

    DbGenOptions options = IOUtils.PARSE_CONFIG_FILE(configFile);

    System.out.println(options.toString());
  }
}