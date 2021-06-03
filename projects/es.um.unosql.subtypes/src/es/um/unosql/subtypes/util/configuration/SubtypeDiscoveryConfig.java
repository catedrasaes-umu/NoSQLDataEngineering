package es.um.unosql.subtypes.util.configuration;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import es.um.unosql.subtypes.discovery.discriminator.DBDiscriminatorSeeker;
import es.um.unosql.subtypes.discovery.discriminator.MongoDBSeeker;
import es.um.unosql.subtypes.discovery.discriminator.Neo4jDBSeeker;
import es.um.unosql.subtypes.outliers.OutlierDetector;
import es.um.unosql.subtypes.outliers.impl.CoverageOutlierDetector;
import es.um.unosql.subtypes.outliers.impl.EpsilonOutlierDetector;
import es.um.unosql.subtypes.util.configuration.defaults.DefaultSubtypeDiscoveryConfig;

public class SubtypeDiscoveryConfig
{
  private Path inputPath;
  private String entityName;
  private OutlierDetector outlierDetector;
  private DBDiscriminatorSeeker discriminatorSeeker;
  private Path outputPath;
  private Properties defaultProperties;

  public SubtypeDiscoveryConfig()
  {
    this.defaultProperties = DefaultSubtypeDiscoveryConfig.getDefaultConfig();
    this.inputPath = null;
    this.entityName = null;
    this.outlierDetector = null;
    this.discriminatorSeeker = null;
    this.outputPath = null;
  }

  public SubtypeDiscoveryConfig(String[] args)
  {
    this();

    Options options = new Options();

    options.addOption(Option.builder("h").longOpt("help").desc("Prints help").build());
    options.addOption(Option.builder("i").longOpt("input").desc("Path to the input U-Schema model").required().hasArg().argName("model").build());
    options.addOption(Option.builder("e").longOpt("entity").desc("Entity type to analyze").required().hasArg().argName("string").build());

    OptionGroup outlierMode = new OptionGroup();
    outlierMode.addOption(Option.builder("ep").longOpt("epsilon").desc("Apply epsilon outlier detection").numberOfArgs(1).optionalArg(true).argName("value").build());
    outlierMode.addOption(Option.builder("cov").longOpt("coverage").desc("Apply coverage outlier detection").numberOfArgs(1).optionalArg(true).argName("value").build());
    options.addOptionGroup(outlierMode);

    options.addOption(Option.builder("d").longOpt("discriminator").desc("Discriminator seeker").numberOfArgs(3).argName("mongodb|neo4j ip db_name").build());

    options.addOption(Option.builder("o").longOpt("output").desc("Path to an output folder").hasArg().argName("folder").build());

    CommandLineParser parser = new DefaultParser();
    HelpFormatter formatter = new HelpFormatter();

    try
    {
      CommandLine cmd = parser.parse(options, args);

      if (cmd.hasOption("h"))
      {
        formatter.printHelp("<Subtype discovery>", options);
        System.exit(0);
      }

      this.inputPath = Paths.get(cmd.getOptionValue("i"));
      this.entityName = cmd.getOptionValue("e");

      if (cmd.hasOption("cov"))
      {
        String strThr = cmd.getOptionValue("cov");
        double coverageThr = strThr != null ? Double.parseDouble(strThr) : Double.parseDouble(this.defaultProperties.getProperty("outlier_coverage"));

        this.outlierDetector = new CoverageOutlierDetector(coverageThr);
      }
      if (cmd.hasOption("ep"))
      {
        String strThr = cmd.getOptionValue("ep");
        double epsilonThr = strThr != null ? Double.parseDouble(strThr) : Double.parseDouble(this.defaultProperties.getProperty("outlier_epsilon"));

        this.outlierDetector = new EpsilonOutlierDetector(epsilonThr);
      }

      if (cmd.hasOption("d"))
      {
        String db = cmd.getOptionValues("d")[0];
        String ip = cmd.getOptionValues("d")[1];
        String dbName = cmd.getOptionValues("d")[2];

        if (db.equals("mongodb"))
          this.discriminatorSeeker = new MongoDBSeeker(ip, dbName);

        if (db.equals("neo4j"))
          this.discriminatorSeeker = new Neo4jDBSeeker(ip);
      }

      if (cmd.hasOption("o"))
        this.outputPath = Paths.get(cmd.getOptionValue("o"));
    } catch (ParseException e)
    {
        System.out.println(e.getMessage());
        formatter.printHelp("<Subtype discovery>", options);

        System.exit(1);
    }
  }

  public void setInputPath(Path inputPath)
  {
    this.inputPath = inputPath;
  }

  public Path getInputPath()
  {
    return this.inputPath;
  }

  public void setEntityName(String entityName)
  {
    this.entityName = entityName;
  }

  public String getEntityName()
  {
    return this.entityName;
  }

  public void setOutlierDetector(OutlierDetector outlierDetector)
  {
    this.outlierDetector = outlierDetector;
  }

  public OutlierDetector getOutlierDetector()
  {
    return this.outlierDetector;
  }

  public void setDiscriminatorSeeker(DBDiscriminatorSeeker discriminatorSeeker)
  {
    this.discriminatorSeeker = discriminatorSeeker;
  }

  public DBDiscriminatorSeeker getDiscriminatorSeeker()
  {
    return this.discriminatorSeeker;
  }

  public void setOutputPath(Path outputPath)
  {
    this.outputPath = outputPath;
  }

  public Path getOutputPath()
  {
    return this.outputPath;
  }
}
