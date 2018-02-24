package es.um.nosql.s13e.db.gen.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import es.um.nosql.s13e.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbClient;
import es.um.nosql.s13e.db.gen.utils.Constants;

public class OutputGen
{
  public void genOutput(ArrayNode arrayNode)
  {
    if (Constants.IS_DEFINED_OUTPUT_CONSOLE())
    {
      String result = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(jsonContent);
      System.out.println(result);

    }
    if (Constants.IS_DEFINED_OUTPUT_DATABASE())
    {
      MongoDbClient client = MongoDbAdapter.getMongoDbClient("localhost");
      client.insert(modelName, result);
    }
    if (Constants.IS_DEFINED_OUTPUT_FILE())
  }
}