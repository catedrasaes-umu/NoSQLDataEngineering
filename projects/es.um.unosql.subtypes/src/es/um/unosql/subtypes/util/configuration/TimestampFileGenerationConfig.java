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

import es.um.unosql.subtypes.util.configuration.defaults.DefaultTimestampFileGenerationConfig;

public class TimestampFileGenerationConfig
{
  private Properties defaultProperties;
  private Path outputPath;

  public TimestampFileGenerationConfig()
  {
    this.defaultProperties = DefaultTimestampFileGenerationConfig.getDefaultConfig();
    this.outputPath = Paths.get(defaultProperties.getProperty("default_folder"));
  }

  public TimestampFileGenerationConfig(String[] args)
  {
    this();

    Options options = new Options();

    options.addOption(Option.builder("h").longOpt("help").desc("Prints help").build());
    options.addOption(Option.builder("o").longOpt("output").desc("The path to write Timestamp files").hasArg().argName("folder").build());

    CommandLineParser parser = new DefaultParser();
    HelpFormatter formatter = new HelpFormatter();

    try
    {
      CommandLine cmd = parser.parse(options, args);

      if (cmd.hasOption("h"))
      {
        formatter.printHelp("<Timestamp File Generation>", options);
        System.exit(0);
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

  public void setOutputPath(Path outputPath)
  {
    this.outputPath = outputPath;
  }

  public Path getOutputPath()
  {
    return this.outputPath;
  }
}
