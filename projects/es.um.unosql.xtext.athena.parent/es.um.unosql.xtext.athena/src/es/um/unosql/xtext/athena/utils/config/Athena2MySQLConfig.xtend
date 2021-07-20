package es.um.unosql.xtext.athena.utils.config

import java.nio.file.Path
import java.util.Properties
import org.apache.commons.cli.Options
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.ParseException
import es.um.unosql.xtext.athena.utils.config.defaults.DefaultConfig

class Athena2MySQLConfig
{
  Path inputModel
  Path inputPath
  Path outputPath
  boolean normalizeRelations
  boolean normalizeCollections
  Properties defaultProperties

  new()
  {
    this.defaultProperties = DefaultConfig.getDefaultConfig()
    this.inputModel = null
    this.inputPath = null
    this.outputPath = Path.of(defaultProperties.getProperty("folder_athena2mongodb"))
    this.normalizeRelations = Boolean.valueOf(defaultProperties.getProperty("mysql_normalize_relations"))
    this.normalizeCollections = Boolean.valueOf(defaultProperties.getProperty("mysql_normalize_collections"))
  }

  new(String[] args)
  {
    this()

    val options = new Options()

    options.addOption(Option.builder("h").longOpt("help").desc("Prints help").build())
    options.addOption(Option.builder("o").longOpt("output").desc("Path to an output folder").hasArg().argName("output_folder").build())
    options.addOption(Option.builder("nr").longOpt("normalize_relations").desc("Normalize 1..* relations before generation").build())
    options.addOption(Option.builder("nc").longOpt("normalize_collections").desc("Normalize collections before generation").build())

    val inputMode = new OptionGroup();
    inputMode.setRequired(true);
    inputMode.addOption(Option.builder("i").longOpt("input").desc("Path to an input model").hasArg().argName("model").build())
    inputMode.addOption(Option.builder("f").longOpt("folder").desc("Path to an input folder").hasArg().argName("input_folder").build())
    options.addOptionGroup(inputMode)

    val parser = new DefaultParser()
    val formatter = new HelpFormatter()

    try
    {
      val cmd = parser.parse(options, args)

      if (cmd.hasOption("h"))
      {
        formatter.printHelp("<Athena2MongoDBSchemaValidator Main>", options)
        System.exit(0)
      }

      if (cmd.hasOption("i"))
        this.inputModel = Path.of(cmd.getOptionValue("i"))

      if (cmd.hasOption("f"))
        this.inputPath = Path.of(cmd.getOptionValue("f"))

      if (cmd.hasOption("o"))
        this.outputPath = Path.of(cmd.getOptionValue("o"))

      if (cmd.hasOption("nr"))
        this.normalizeRelations = true

      if (cmd.hasOption("nc"))
        this.normalizeCollections = true

    } catch (ParseException e)
    {
      System.out.println(e.getMessage());
      formatter.printHelp("<Athena2MongoDBSchemaValidator Main>", options);

      System.exit(1);
    }
  }

  def void setInputModel(Path inputModel)
  {
    this.inputModel = inputModel
  }

  def Path getInputModel()
  {
    return this.inputModel
  }

  def void setInputPath(Path inputPath)
  {
    this.inputPath = inputPath
  }

  def Path getInputPath()
  {
    return this.inputPath
  }

  def void setOutputPath(Path outputPath)
  {
    this.outputPath = outputPath
  }

  def Path getOutputPath()
  {
    return this.outputPath
  }

  def void setNormalizeRelations(boolean normalizeRelations)
  {
    this.normalizeRelations = normalizeRelations
  }

  def boolean getNormalizeRelations()
  {
    return this.normalizeRelations
  }

  def void setNormalizeCollections(boolean normalizeCollections)
  {
    this.normalizeCollections = normalizeCollections
  }

  def boolean getNormalizeCollections()
  {
    return this.normalizeCollections
  }
}
