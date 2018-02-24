package es.um.nosql.s13e.db.gen;

import java.io.File;

import com.fasterxml.jackson.databind.node.ArrayNode;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.db.gen.generator.ObjectGen;
import es.um.nosql.s13e.db.gen.output.OutputGen;
import es.um.nosql.s13e.db.gen.utils.IOUtils;

public class Main
{
  private static final String modelName = "oneofeach";

  public static void main(String[] args) throws Exception
  {
    NoSQLSchema schema = IOUtils.READ_MODEL(new File("source/" + modelName + ".xmi"));
    ObjectGen generator = new ObjectGen();
    OutputGen outputModule = new OutputGen();

    ArrayNode jsonContent = generator.generate(schema);
    outputModule.genOutput(jsonContent);
  }
}