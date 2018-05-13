package es.um.nosql.s13e.db.gen.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.db.gen.generator.ObjectGen;
import es.um.nosql.s13e.db.gen.generator.primitivetypes.NumberGen;
import es.um.nosql.s13e.db.gen.output.OutputGen;
import es.um.nosql.s13e.db.gen.utils.DebugLog;
import es.um.nosql.s13e.db.gen.utils.IOUtils;
import es.um.nosql.s13e.db.gen.utils.constants.ConfigConstants;

public class ParallelController implements IController
{
  private class RunnableGenerator implements Runnable
  {
    private int id;
    private int objectsIteration;
    private NoSQLSchema schema;
    private ObjectGen oGen;
    private OutputGen outputModule;

    public RunnableGenerator(int id, int objectsIteration, NoSQLSchema schema, ObjectGen oGen, OutputGen outputModule)
    {
      this.id = id;
      this.objectsIteration = objectsIteration;
      this.schema = schema;
      this.oGen = oGen;
      this.outputModule = outputModule;
    }

    @Override
    public void run()
    {
      outputModule.genOutput(oGen.generateBulk(schema, objectsIteration), id);
    }
  }

  private NumberGen numGen;
  private ObjectGen oGen;
  private OutputGen outputModule;
  private List<Thread> threadList;

  public ParallelController()
  {
    numGen = NumberGen.GET_INSTANCE();
    oGen = new ObjectGen();
    outputModule = new OutputGen();
    threadList = new ArrayList<Thread>();
  }

  public void start(String modelRoute)
  {
    long startTime = System.currentTimeMillis();

    NoSQLSchema schema = IOUtils.READ_MODEL(new File(ConfigConstants.GET_INPUT_FILE()));
    int objectsVersion = numGen.getInclusiveRandom(ConfigConstants.GET_MIN_INSTANCES(), ConfigConstants.GET_MAX_INSTANCES());
    int objectsIteration = objectsVersion / ConfigConstants.GET_SPLITS();
    int floor = Math.floorMod(objectsVersion, ConfigConstants.GET_SPLITS());

    DebugLog.PRINTOUT("Starting generation for " + modelRoute + " @ 0 seconds");
    DebugLog.PRINTOUT("Objects per version being generated: " + objectsVersion + " in " + ConfigConstants.GET_SPLITS() + " splits");

    if (objectsVersion >= ConfigConstants.GET_SPLITS())
      for (int i = 0; i < ConfigConstants.GET_SPLITS(); i++)
      {
        DebugLog.PRINTOUT("Iteration " + (i+1) + "/" + ConfigConstants.GET_SPLITS() + " @ " + ((System.currentTimeMillis() - startTime)/1000) + " seconds...");
        Thread thread = new Thread(new RunnableGenerator(i, objectsIteration, schema, new ObjectGen(), new OutputGen()));
        threadList.add(thread);
        thread.start();
      }

    if (floor != 0)
    {
      DebugLog.PRINTOUT("Floor iteration @ " + ((System.currentTimeMillis() - startTime)/1000) + " seconds...");
      outputModule.genOutput(oGen.generateBulk(schema, floor));
    }

    for (Thread t : threadList)
      try
      {
        t.join();
      } catch(InterruptedException e) {e.printStackTrace();}

    DebugLog.PRINTOUT("Elapsed time: " + ((System.currentTimeMillis() - startTime)/1000) + " seconds");
    DebugLog.PRINTOUT("Generation for " + ConfigConstants.GET_INPUT_FILE() + " finished.");
  }

  private int calculateThreads(int splits)
  {
    //TODO: How should we calculate the necessary threads? Defining an IN_PARALLEL param seems too confusing with splits and threads everywhere..
    if (splits < 16)
      return 2;
    if (splits < 64)
      return 4;
    if (splits < 256)
      return 8;
    return 16;
  }
}