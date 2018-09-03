package es.um.nosql.s13e.evolution.output;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.ECollections;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
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

  public void genOutput(NoSQLSchema schema)
  {
    if (ConfigConstants.OUTPUT_CSV)
      genCSVFile(schema);

    if (ConfigConstants.OUTPUT_CONSOLE)
      genConsole(schema);

    if (ConfigConstants.OUTPUT_MODEL)
      genModelFile(schema);

    //TODO: Plots
/*    if (ConfigConstants.OUTPUT_PLOT)
      genPlot(schema);

    if (ConfigConstants.OUTPUT_PLOT_FILE)
      genPlotFile(schema);*/
  }

  private void genCSVFile(NoSQLSchema schema)
  {
    String outputRoute = ConfigConstants.OUTPUT_FOLDER + schema.getName() + ".csv";
    Map<EntityClass, List<StructuralVariation>> orderedMap = genOrderedMap(schema);

    CsvSchema csvSchema = CsvSchema.builder()
        .addColumn("entityName", CsvSchema.ColumnType.STRING)
        .addColumn("variationId", CsvSchema.ColumnType.NUMBER)
        .addColumn("count", CsvSchema.ColumnType.NUMBER)
        .addColumn("timestamp", CsvSchema.ColumnType.NUMBER)
        .build()
        .withHeader();

    List<List<Object>> objects = new ArrayList<List<Object>>();

    for (EntityClass entity : orderedMap.keySet())
      for (StructuralVariation variation : orderedMap.get(entity))
        objects.add(Arrays.asList(entity.getName(), variation.getVariationId(), variation.getCount(), variation.getTimestamp()));

    try
    {
      OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outputRoute));
      mapper.writer(csvSchema).writeValue(writer, objects);
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private void genModelFile(NoSQLSchema schema)
  {
    String outputRoute = ConfigConstants.OUTPUT_FOLDER + schema.getName() + ".xmi";
    writer.write(schema, outputRoute);
  }

  private void genConsole(NoSQLSchema schema)
  {
    StringBuilder result = new StringBuilder();
    Map<EntityClass, List<StructuralVariation>> orderedMap = genOrderedMap(schema);

    for (EntityClass entity : orderedMap.keySet())
    {
      result.append("Entity: " + entity.getName() + "\n");
      for (StructuralVariation var : orderedMap.get(entity))
      {
        result.append("  variationid: " + var.getVariationId());
        result.append(", count: " + var.getCount());
        result.append(", timestamp: " + var.getTimestamp());
        result.append("\n");
      }
    }

    System.out.println(result.toString());
  }

  private Map<EntityClass, List<StructuralVariation>> genOrderedMap(NoSQLSchema schema)
  {
    Map<EntityClass, List<StructuralVariation>> result = new HashMap<EntityClass, List<StructuralVariation>>();

    for (EntityClass entity : schema.getEntities())
    {
      ECollections.sort(entity.getVariations(), (var1, var2) -> Long.compare(var1.getTimestamp(), var2.getTimestamp()));
      result.put(entity, entity.getVariations());
    }

    return result;
  }
}
