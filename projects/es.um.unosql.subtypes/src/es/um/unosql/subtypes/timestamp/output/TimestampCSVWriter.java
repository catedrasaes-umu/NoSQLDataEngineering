package es.um.unosql.subtypes.timestamp.output;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import es.um.unosql.subtypes.timestamp.output.chart.TimestampLineChart;
import es.um.unosql.uNoSQLSchema.EntityType;
import es.um.unosql.uNoSQLSchema.StructuralVariation;
import es.um.unosql.uNoSQLSchema.uNoSQLSchema;

public class TimestampCSVWriter
{
  private CsvMapper mapper;

  public TimestampCSVWriter()
  {
    mapper = new CsvMapper();
  }

  public void generateOutput(Path outputFolder, uNoSQLSchema schema)
  {
    generateCSVFile(outputFolder, schema);
    generateConsoleOutput(schema);
    generateChartFile(outputFolder, schema);
  }

  private void generateCSVFile(Path outputFolder, uNoSQLSchema schema)
  {
    String outputRoute = outputFolder.resolve(schema.getName()) + ".csv";
    Map<EntityType, EList<StructuralVariation>> orderedMap = orderSchemaMap(schema);

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

    System.out.println(schema.getName() + " > CSV file created: " + outputRoute);
  }

  private void generateConsoleOutput(uNoSQLSchema schema)
  {
    StringBuilder result = new StringBuilder();
    Map<EntityType, EList<StructuralVariation>> orderedMap = orderSchemaMap(schema);

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
    System.out.println(schema.getName() + " > Console output finished.");
  }

  private void generateChartFile(Path outputFolder, uNoSQLSchema schema)
  {
    System.out.println(schema.getName() + " > Creating timestamp chart images...");

    Map<EntityType, EList<StructuralVariation>> orderedMap = orderSchemaMap(schema);

    for (EntityType entity : orderedMap.keySet())
      new TimestampLineChart(schema.getName(), entity.getName(), orderedMap.get(entity)).saveChart(outputFolder);

    System.out.println(schema.getName() + " > Finished creating charts.");
  }

  private Map<EntityType, EList<StructuralVariation>> orderSchemaMap(uNoSQLSchema schema)
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
