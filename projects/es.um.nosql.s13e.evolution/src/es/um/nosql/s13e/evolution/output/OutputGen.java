package es.um.nosql.s13e.evolution.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import es.um.nosql.s13e.NoSQLSchema.EntityType;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.evolution.output.chart.TimestampLineChart;
import es.um.nosql.s13e.evolution.util.constants.ConfigConstants;
import es.um.nosql.s13e.util.NoSQLSchemaWriter;

public class OutputGen
{
  private NoSQLSchemaWriter writer;
  private CsvMapper mapper;

  public OutputGen()
  {
    writer = new NoSQLSchemaWriter();
    mapper = new CsvMapper();
  }

  public void genOutput(String route, NoSQLSchema schema)
  {
    if (ConfigConstants.OUTPUT_CSV)
      genCSVFile(route, schema);

    if (ConfigConstants.OUTPUT_CONSOLE)
      genConsole(schema);

    if (ConfigConstants.OUTPUT_CHART)
      genChart(schema);

    if (ConfigConstants.OUTPUT_CHART_FILE)
      genChartFile(route, schema);
  }

  public void genCSVFile(String route, NoSQLSchema schema)
  {
    new File(route).mkdirs();
    String outputRoute = Paths.get(route).resolve(schema.getName()) + ".csv";
    Map<EntityType, EList<StructuralVariation>> orderedMap = genOrderedMap(schema);

    if (ConfigConstants.DEBUG)
      System.out.println(schema.getName() + " > Generating CSV file...");

    CsvSchema csvSchema = CsvSchema.builder()
      .addColumn("entityName", CsvSchema.ColumnType.STRING)
      .addColumn("variationId", CsvSchema.ColumnType.NUMBER)
      .addColumn("count", CsvSchema.ColumnType.NUMBER)
      .addColumn("firstTimestamp", CsvSchema.ColumnType.NUMBER)
      .addColumn("lastTimestamp", CsvSchema.ColumnType.NUMBER)
      .build()
      .withHeader();

    EList<EList<String>> csvContent = new BasicEList<EList<String>>();

    for (EntityType entity : orderedMap.keySet())
      for (StructuralVariation variation : orderedMap.get(entity))
      {
        EList<String> list = new BasicEList<String>();
        list.add(entity.getName());
        list.add(String.valueOf(variation.getVariationId()));
        list.add(String.valueOf(variation.getCount()));
        list.add(String.valueOf(variation.getFirstTimestamp()));
        list.add(String.valueOf(variation.getLastTimestamp()));

        csvContent.add(list);
      }

    ECollections.sort(csvContent, (l1, l2) -> l1.get(0).compareTo(l2.get(0)));

    try
    {
      OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outputRoute));
      mapper.writer(csvSchema).writeValue(writer, csvContent);
    } catch (IOException e)
    {
      e.printStackTrace();
    }

    if (ConfigConstants.DEBUG)
      System.out.println(schema.getName() + " > CSV file created: " + outputRoute);
  }

  public void genModelFile(NoSQLSchema schema)
  {
    File schemaFolder = Paths.get(ConfigConstants.OUTPUT_FOLDER).resolve(schema.getName()).toFile();
    schemaFolder.mkdirs();
    String outputRoute = Paths.get(schemaFolder.getAbsolutePath()).resolve(schema.getName()) + ".xmi";

    writer.write(schema, outputRoute);
  }

  public void genConsole(NoSQLSchema schema)
  {
    StringBuilder result = new StringBuilder();
    Map<EntityType, EList<StructuralVariation>> orderedMap = genOrderedMap(schema);

    if (ConfigConstants.DEBUG)
      System.out.println(schema.getName() + " > Generating console output...");

    for (EntityType entity : orderedMap.keySet())
    {
      result.append("Entity: " + entity.getName() + "\n");
      for (StructuralVariation var : orderedMap.get(entity))
      {
        result.append("  variationid: " + var.getVariationId());
        result.append(", count: " + var.getCount());
        result.append(", firstTimestamp: " + var.getFirstTimestamp());
        result.append(", lastTimestamp: " + var.getLastTimestamp());
        result.append("\n");
      }
    }

    System.out.println(result.toString());

    if (ConfigConstants.DEBUG)
      System.out.println(schema.getName() + " > Console output finished.");
  }

  public void genChart(NoSQLSchema schema)
  {
    if (ConfigConstants.DEBUG)
      System.out.println(schema.getName() + " > Drawing timestamp charts...");

    Map<EntityType, EList<StructuralVariation>> orderedMap = genOrderedMap(schema);

    for (EntityType entity : orderedMap.keySet())
    {
      if (ConfigConstants.DEBUG)
        System.out.println(schema.getName() + " > " + entity.getName() + " chart drawn.");

      new TimestampLineChart(schema.getName(), entity.getName(), orderedMap.get(entity)).showChart();
    }

    if (ConfigConstants.DEBUG)
      System.out.println(schema.getName() + " > Finished drawing charts.");
  }

  public void genChartFile(String route, NoSQLSchema schema)
  {
    if (ConfigConstants.DEBUG)
      System.out.println(schema.getName() + " > Creating timestamp chart images...");

    new File(route).mkdirs();

    Map<EntityType, EList<StructuralVariation>> orderedMap = genOrderedMap(schema);

    for (EntityType entity : orderedMap.keySet())
    {
      if (ConfigConstants.DEBUG)
        System.out.println(schema.getName() + " > " + entity.getName() + " chart created.");

      new TimestampLineChart(schema.getName(), entity.getName(), orderedMap.get(entity)).saveChart(route);
    }

    if (ConfigConstants.DEBUG)
      System.out.println(schema.getName() + " > Finished creating charts.");
  }

  private Map<EntityType, EList<StructuralVariation>> genOrderedMap(NoSQLSchema schema)
  {
    Map<EntityType, EList<StructuralVariation>> result = new HashMap<EntityType, EList<StructuralVariation>>();

    for (EntityType entity : schema.getEntities())
    {
      ECollections.sort(entity.getVariations(), (var1, var2) -> Integer.compare(var1.getVariationId(), var2.getVariationId()));
      result.put(entity, entity.getVariations());
    }

    return result;
  }
}
