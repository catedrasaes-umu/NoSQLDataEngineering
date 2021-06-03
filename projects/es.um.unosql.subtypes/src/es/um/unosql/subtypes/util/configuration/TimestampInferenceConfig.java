package es.um.unosql.subtypes.util.configuration;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import es.um.unosql.subtypes.timestamp.templates.BasicTimestampAnalyzer;
import es.um.unosql.subtypes.timestamp.templates.DateTimestampAnalyzer;
import es.um.unosql.subtypes.timestamp.templates.DefaultTimestampAnalyzer;
import es.um.unosql.subtypes.timestamp.templates.OIDTimestampAnalyzer;
import es.um.unosql.subtypes.timestamp.templates.TimestampAnalyzer;
import es.um.unosql.subtypes.util.configuration.defaults.DefaultTimestampInferenceConfig;

public class TimestampInferenceConfig
{
  private Properties defaultProperties;
  private String ip;
  private String dbName;
  private TimestampAnalyzer analyzer;
  private Path outputPath;
  private Path mapReducePath;

  public TimestampInferenceConfig()
  {
    this.defaultProperties = DefaultTimestampInferenceConfig.getDefaultConfig();
    this.outputPath = Paths.get(defaultProperties.getProperty("default_folder"));
    this.ip = defaultProperties.getProperty("database_ip");
    this.dbName = null;
    this.analyzer = null;
    this.mapReducePath = Paths.get(defaultProperties.getProperty("mongodb_mapreduce_folder"));
  }

  public TimestampInferenceConfig(String[] args)
  {
    this();

    Options options = new Options();

    options.addOption(Option.builder("h").longOpt("help").desc("Prints help").build());

    options.addOption(Option.builder("ip").desc("The ip").required().hasArg().argName("db_name").build());
    options.addOption(Option.builder("n").longOpt("db_name").desc("The database name").required().hasArg().argName("db_name").build());
    options.addOption(Option.builder("ts").longOpt("timestamp").desc("The timestamp type").required().numberOfArgs(2).argName("BASIC|DATE|DEFAULT|OID field").build());
    options.addOption(Option.builder("o").longOpt("output").desc("The path to an output folder").hasArg().argName("folder").build());
    options.addOption(Option.builder("mr").longOpt("map_reduce").desc("The path to the map reduce folder").hasArg().argName("folder").build());

    CommandLineParser parser = new DefaultParser();
    HelpFormatter formatter = new HelpFormatter();

    try
    {
      CommandLine cmd = parser.parse(options, args);

      if (cmd.hasOption("h"))
      {
        formatter.printHelp("<Timestamp inference>", options);
        System.exit(0);
      }

      if (cmd.hasOption("ip"))
        this.ip = cmd.getOptionValue("ip");

      this.dbName = cmd.getOptionValue("n");

      switch (cmd.getOptionValues("ts")[0].toLowerCase())
      {
        case "basic": { this.analyzer = new BasicTimestampAnalyzer(cmd.getOptionValues("ts")[1]); break;}
        case "date": { this.analyzer = new DateTimestampAnalyzer(cmd.getOptionValues("ts")[1]); break;}
        case "oid": { this.analyzer = new OIDTimestampAnalyzer(cmd.getOptionValues("ts")[1]); break;}
        case "default": { this.analyzer = new DefaultTimestampAnalyzer(); break;}
        default: { this.analyzer = new DefaultTimestampAnalyzer(); System.err.println("> WARNING: Default timestamp being used."); break;}
      }

      if (cmd.hasOption("o"))
        this.outputPath = Paths.get(cmd.getOptionValue("o"));

      if (cmd.hasOption("mr"))
        this.mapReducePath = Paths.get(cmd.getOptionValue("mr"));

    } catch (ParseException e)
    {
      System.out.println(e.getMessage());
      formatter.printHelp("<Timestamp inference>", options);

      System.exit(1);
    }
  }

  public void setIP(String ip)
  {
    this.ip = ip;
  }

  public String getIP()
  {
    return this.ip;
  }

  public void setDBName(String dbName)
  {
    this.dbName = dbName;
  }

  public String getDBName()
  {
    return this.dbName;
  }

  public void setTimestampAnalyzer(TimestampAnalyzer analyzer)
  {
    this.analyzer = analyzer;
  }

  public TimestampAnalyzer getTimestampAnalyzer()
  {
    return this.analyzer;
  }

  public void setOutputPath(Path outputPath)
  {
    this.outputPath = outputPath;
  }

  public Path getOutputPath()
  {
    return this.outputPath;
  }

  public void setMapReducePath(Path mapReducePath)
  {
    this.mapReducePath = mapReducePath;
  }

  public Path getMapReducePath()
  {
    return this.mapReducePath;
  }
}
