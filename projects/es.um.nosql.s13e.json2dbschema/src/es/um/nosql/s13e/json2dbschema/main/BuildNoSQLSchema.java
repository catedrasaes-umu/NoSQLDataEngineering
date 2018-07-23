package es.um.nosql.s13e.json2dbschema.main;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.node.ArrayNode;

import com.google.gson.JsonArray;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.json2dbschema.main.util.JSON2Schema;
import es.um.nosql.s13e.json2dbschema.util.abstractjson.IAJAdapter;
import es.um.nosql.s13e.json2dbschema.util.abstractjson.IAJArray;
import es.um.nosql.s13e.json2dbschema.util.abstractjson.impl.gson.GsonAdapter;
import es.um.nosql.s13e.json2dbschema.util.abstractjson.impl.gson.GsonArray;
import es.um.nosql.s13e.json2dbschema.util.abstractjson.impl.jackson.JacksonAdapter;
import es.um.nosql.s13e.json2dbschema.util.abstractjson.impl.jackson.JacksonArray;
import es.um.nosql.s13e.util.NoSQLSchemaWriter;

/**
 * @author dsevilla
 *
 */
public class BuildNoSQLSchema
{
  public NoSQLSchema buildFromGsonArray(String schemaName, JsonArray jArray)
  {
    return buildFromArray(schemaName, new GsonArray(jArray), new GsonAdapter());
  }

  public NoSQLSchema buildFromJacksonArray(String schemaName, ArrayNode jArray)
  {
    return buildFromArray(schemaName, new JacksonArray(jArray), new JacksonAdapter());
  }

  private NoSQLSchema buildFromArray(String schemaName, IAJArray objRows, IAJAdapter<?> adapter)
  {
    return new JSON2Schema<>(NoSQLSchemaPackage.eINSTANCE.getNoSQLSchemaFactory(), adapter).fromJSONArray(schemaName, objRows);
  }

  public void buildFromGsonArray(String schemaName, JsonArray jArray, String outputFile)
  {
    buildFromArray(schemaName, new GsonArray(jArray), outputFile, new GsonAdapter());
  }

  public void buildFromJacksonArray(String schemaName, ArrayNode jArray, String outputFile)
  {
    buildFromArray(schemaName, new JacksonArray(jArray), outputFile, new JacksonAdapter());
  }

  private void buildFromArray(String schemaName, IAJArray objRows, String outputFile, IAJAdapter<?> adapter)
  {
    NoSQLSchema schema = new JSON2Schema<>(NoSQLSchemaPackage.eINSTANCE.getNoSQLSchemaFactory(), adapter).fromJSONArray(schemaName, objRows);
    schema2File(schema, outputFile);
  }

  public void buildFromGsonFile(String inputFile, String outputFile)
  {
    buildFromFile(inputFile, outputFile, new GsonAdapter());
  }

  public void buildFromJacksonFile(String inputFile, String outputFile)
  {
    buildFromFile(inputFile, outputFile, new JacksonAdapter());
  }

  private void buildFromFile(String inputFile, String outputFile, IAJAdapter<?> adapter)
  {
    NoSQLSchema schema = null;
    try
    {
      schema = new JSON2Schema<>(NoSQLSchemaPackage.eINSTANCE.getNoSQLSchemaFactory(), adapter).fromJSONFile(inputFile);
    } catch (JsonProcessingException e)
    {
      e.printStackTrace();
    } catch (IOException e)
    {
      e.printStackTrace();
    }

    schema2File(schema, outputFile);
  }

  private void schema2File(NoSQLSchema schema, String outputFile)
  {
    NoSQLSchemaWriter writer = new NoSQLSchemaWriter();
    writer.write(schema,  outputFile);
  }

  public static void main(String[] args) throws IOException
  {
    String inputFile;
    String outputFile;

    if (args.length < 2)
    {
      System.err.println("At least the JSON file must be specified.");
      System.err.println("Usage: BuildNoSQLSchema JSONfile [outputXMIfile]");
      System.exit(-1);
      return;
    } else
    {
      inputFile = args[0];
      outputFile = args[1];
      File file = new File(outputFile);
      file.getParentFile().mkdirs();
      file.createNewFile();
    }

    BuildNoSQLSchema builder = new BuildNoSQLSchema();
    builder.buildFromJacksonFile(inputFile, outputFile);
  }
}
