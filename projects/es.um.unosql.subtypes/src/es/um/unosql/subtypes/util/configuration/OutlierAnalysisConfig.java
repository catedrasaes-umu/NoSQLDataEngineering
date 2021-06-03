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

import es.um.unosql.subtypes.outliers.OutlierDetector;
import es.um.unosql.subtypes.outliers.impl.CoverageOutlierDetector;
import es.um.unosql.subtypes.outliers.impl.EpsilonOutlierDetector;
import es.um.unosql.subtypes.util.configuration.defaults.DefaultOutlierAnalysisConfig;

public class OutlierAnalysisConfig
{
  private Path inputPath;
  private OutlierDetector outlierDetector;
  private Path outputPath;
  private Properties defaultProperties;

  public OutlierAnalysisConfig()
  {
    this.defaultProperties = DefaultOutlierAnalysisConfig.getDefaultConfig();
    this.inputPath = null;
    this.outlierDetector = null;
    this.outputPath = Paths.get(defaultProperties.getProperty("default_folder"));
  }

  public OutlierAnalysisConfig(String[] args)
  {
    this();

    Options options = new Options();

    options.addOption(Option.builder("h").longOpt("help").desc("Prints help").build());
    options.addOption(Option.builder("i").longOpt("input").desc("Path to the input U-Schema model").required().hasArg().argName("model").build());

    OptionGroup outlierMode = new OptionGroup();
    outlierMode.setRequired(true);
    outlierMode.addOption(Option.builder("ep").longOpt("epsilon").desc("Apply epsilon outlier detection").numberOfArgs(1).optionalArg(true).argName("value").build());
    outlierMode.addOption(Option.builder("cov").longOpt("coverage").desc("Apply coverage outlier detection").numberOfArgs(1).optionalArg(true).argName("value").build());
    options.addOptionGroup(outlierMode);

    options.addOption(Option.builder("o").longOpt("output").desc("Path to an output folder").hasArg().argName("folder").build());

    CommandLineParser parser = new DefaultParser();
    HelpFormatter formatter = new HelpFormatter();

    try
    {
      CommandLine cmd = parser.parse(options, args);

      if (cmd.hasOption("h"))
      {
        formatter.printHelp("<Outlier analysis>", options);
        System.exit(0);
      }

      this.inputPath = Paths.get(cmd.getOptionValue("i"));

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

      if (cmd.hasOption("o"))
        this.outputPath = Paths.get(cmd.getOptionValue("o"));
    } catch (ParseException e)
    {
        System.out.println(e.getMessage());
        formatter.printHelp("<Outlier analysis>", options);

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

  public void setOutlierDetector(OutlierDetector outlierDetector)
  {
    this.outlierDetector = outlierDetector;
  }

  public OutlierDetector getOutlierDetector()
  {
    return this.outlierDetector;
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
