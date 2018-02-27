package es.um.nosql.s13e.db.gen;

import java.io.File;
import java.util.Map;

import com.fasterxml.jackson.databind.node.ArrayNode;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.db.gen.generator.ObjectGen;
import es.um.nosql.s13e.db.gen.output.OutputGen;
import es.um.nosql.s13e.db.gen.utils.Constants;
import es.um.nosql.s13e.db.gen.utils.IOUtils;

public class Controller
{
  ObjectGen oGen;
  OutputGen outputModule;

  public Controller()
  {
    oGen = new ObjectGen();
    outputModule = new OutputGen();
  }

  public void start(String modelRoute)
  {
    NoSQLSchema schema = IOUtils.READ_MODEL(new File(Constants.GET_INPUT_FILE()));

    Map<String, ArrayNode> jsonContent = oGen.generate(schema, 1);
    outputModule.genOutput(jsonContent);
  }
}