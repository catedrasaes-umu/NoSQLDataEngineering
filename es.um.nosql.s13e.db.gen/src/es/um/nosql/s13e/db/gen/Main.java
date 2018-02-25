package es.um.nosql.s13e.db.gen;

import java.io.File;
import java.util.Map;

import com.fasterxml.jackson.databind.node.ArrayNode;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.db.gen.generator.ObjectGen;
import es.um.nosql.s13e.db.gen.output.OutputGen;
import es.um.nosql.s13e.db.gen.utils.Constants;
import es.um.nosql.s13e.db.gen.utils.IOUtils;

public class Main
{
  public static void main(String[] args) throws Exception
  {
    long startTime = System.currentTimeMillis();

    NoSQLSchema schema = IOUtils.READ_MODEL(new File(Constants.GET_INPUT_FILE()));
    ObjectGen generator = new ObjectGen();
    OutputGen outputModule = new OutputGen();

    Map<String, ArrayNode> jsonContent = generator.generate(schema);
    outputModule.genOutput(jsonContent);

    System.out.println("Elapsed time: " + ((System.currentTimeMillis() - startTime)/1000));
  }
}