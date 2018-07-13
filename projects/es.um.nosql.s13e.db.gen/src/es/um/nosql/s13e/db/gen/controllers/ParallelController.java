package es.um.nosql.s13e.db.gen.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.db.gen.generator.ObjectGen;
import es.um.nosql.s13e.db.gen.generator.primitivetypes.NumberGen;
import es.um.nosql.s13e.db.gen.output.OutputGen;
import es.um.nosql.s13e.db.gen.util.DebugLog;
import es.um.nosql.s13e.db.gen.util.IOUtils;
import es.um.nosql.s13e.db.gen.util.constants.ConfigConstants;
import es.um.nosql.s13e.db.gen.util.constants.FixedConstants;

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
    int objectsVariations = numGen.getInclusiveRandom(ConfigConstants.GET_MIN_INSTANCES(), ConfigConstants.GET_MAX_INSTANCES());
    int objectsIteration = objectsVariations / ConfigConstants.GET_SPLITS();
    int floor = Math.floorMod(objectsVariations, ConfigConstants.GET_SPLITS());

    DebugLog.PRINTOUT("Starting generation for " + modelRoute + " @ 0 seconds");
    DebugLog.PRINTOUT("Objects per variation being generated: " + objectsVariations + " in " + ConfigConstants.GET_SPLITS() + " splits");

    int index = 0;

    if (objectsVariations >= ConfigConstants.GET_SPLITS())
      for (int i = 0; i < ConfigConstants.GET_SPLITS(); i++)
      {
        DebugLog.PRINTOUT("Iteration " + (i+1) + "/" + ConfigConstants.GET_SPLITS() + " @ " + ((System.currentTimeMillis() - startTime)/1000) + " seconds...");
        for (int j = 0; j < FixedConstants.GET_THREADS(); j++)
        {
          Thread thread = new Thread(new RunnableGenerator(index++, objectsIteration / FixedConstants.GET_THREADS(), schema, new ObjectGen(), new OutputGen()));
          threadList.add(thread);
          thread.start();          
        }

        // We have to block after each split, just to make sure we do not fill all the available memory
        for (Thread t : threadList)
          try
          {
            t.join();
          } catch(InterruptedException e) {e.printStackTrace();}
        threadList.clear();
      }

    if (floor != 0)
    {
      DebugLog.PRINTOUT("Floor iteration @ " + ((System.currentTimeMillis() - startTime)/1000) + " seconds...");
      outputModule.genOutput(oGen.generateBulk(schema, floor));
    }

    DebugLog.PRINTOUT("Elapsed time: " + ((System.currentTimeMillis() - startTime)/1000) + " seconds");
    DebugLog.PRINTOUT("Generation for " + ConfigConstants.GET_INPUT_FILE() + " finished.");
  }
}