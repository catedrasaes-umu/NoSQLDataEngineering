package es.um.nosql.s13e.db.gen.runnable;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.db.gen.generator.ObjectGen;
import es.um.nosql.s13e.db.gen.output.OutputGen;

public class RunnableParallelGen implements Runnable
{
  private int id;
  private int objectsIteration;
  private NoSQLSchema schema;
  private ObjectGen oGen;
  private OutputGen outputModule;

  public RunnableParallelGen(int id, int objectsIteration, NoSQLSchema schema, ObjectGen oGen, OutputGen outputModule)
  {
    this.id = id;
    this.objectsIteration = objectsIteration;
    this.schema = schema;
    this.oGen = oGen;
    this.outputModule = outputModule;
  }

  @Override
  public void run()
  {//TODO: Need to recheck the outputModule, because of the indexes used to name the files.
    outputModule.genOutput(oGen.generateBulk(schema, objectsIteration));
  }
}