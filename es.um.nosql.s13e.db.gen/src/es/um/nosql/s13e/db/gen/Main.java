package es.um.nosql.s13e.db.gen;

import java.io.File;

import org.bson.types.ObjectId;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbClient;
import es.um.nosql.s13e.db.gen.config.pojo.DbGenOptions;
import es.um.nosql.s13e.db.gen.generator.JsonGenerator;
import es.um.nosql.s13e.db.gen.utils.Constants;
import es.um.nosql.s13e.db.gen.utils.IOUtils;


public class Main
{
  public static void main(String[] args) throws Exception
  {
    //MongoDbClient client = MongoDbAdapter.getMongoDbClient("localhost");
    NoSQLSchema schema = IOUtils.READ_MODEL(new File("source/oneofeach.xmi"));
    JsonGenerator generator = new JsonGenerator();

    String jsonContent = generator.generate(schema);
    System.out.println(jsonContent);
    //client.insert("test", jsonContent);
  }
}