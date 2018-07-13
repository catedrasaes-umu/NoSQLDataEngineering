package es.um.nosql.s13e.db.gen.controllers;

import java.io.File;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.db.gen.generator.ObjectGen;
import es.um.nosql.s13e.db.gen.generator.primitivetypes.NumberGen;
import es.um.nosql.s13e.db.gen.output.OutputGen;
import es.um.nosql.s13e.db.gen.util.DebugLog;
import es.um.nosql.s13e.db.gen.util.IOUtils;
import es.um.nosql.s13e.db.gen.util.constants.ConfigConstants;

public class SerialController implements IController
{
  NumberGen numGen;
  ObjectGen oGen;
  OutputGen outputModule;

  public SerialController()
  {
    numGen = NumberGen.GET_INSTANCE();
    oGen = new ObjectGen();
    outputModule = new OutputGen();
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

    if (objectsVariations >= ConfigConstants.GET_SPLITS())
      for (int i = 0; i < ConfigConstants.GET_SPLITS(); i++)
      {
        DebugLog.PRINTOUT("Iteration " + (i+1) + "/" + ConfigConstants.GET_SPLITS() + " @ " + ((System.currentTimeMillis() - startTime)/1000) + " seconds...");
        outputModule.genOutput(oGen.generateBulk(schema, objectsIteration));
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