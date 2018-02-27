package es.um.nosql.s13e.db.gen;

import java.io.File;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.db.gen.generator.ObjectGen;
import es.um.nosql.s13e.db.gen.generator.primitivetypes.NumberGen;
import es.um.nosql.s13e.db.gen.output.OutputGen;
import es.um.nosql.s13e.db.gen.utils.Constants;
import es.um.nosql.s13e.db.gen.utils.DebugLog;
import es.um.nosql.s13e.db.gen.utils.IOUtils;

public class Controller
{
  NumberGen numGen;
  ObjectGen oGen;
  OutputGen outputModule;

  public Controller()
  {
    numGen = NumberGen.GET_INSTANCE();
    oGen = new ObjectGen();
    outputModule = new OutputGen();
  }

  public void start(String modelRoute)
  {
    long startTime = System.currentTimeMillis();

    NoSQLSchema schema = IOUtils.READ_MODEL(new File(Constants.GET_INPUT_FILE()));
    int objectsVersion = numGen.getInclusiveRandom(Constants.GET_MIN_INSTANCES(), Constants.GET_MAX_INSTANCES());
    int objectsIteration = objectsVersion / Constants.GET_SPLITS();
    int floor = Math.floorMod(objectsVersion, Constants.GET_SPLITS());

    DebugLog.PRINTOUT("Starting generation for " + modelRoute + " @ 0 seconds");
    DebugLog.PRINTOUT("Objects per version being generated: " + objectsVersion + " in " + Constants.GET_SPLITS() + " splits");

    if (objectsVersion >= Constants.GET_SPLITS())
      for (int i = 0; i < Constants.GET_SPLITS(); i++)
      {
        DebugLog.PRINTOUT("Iteration " + (i+1) + "/" + Constants.GET_SPLITS() + " @ " + ((System.currentTimeMillis() - startTime)/1000) + " seconds...");
        outputModule.genOutput(oGen.generate(schema, objectsIteration));
      }

    if (floor != 0)
    {
      DebugLog.PRINTOUT("Floor iteration @ " + ((System.currentTimeMillis() - startTime)/1000) + " seconds...");
      outputModule.genOutput(oGen.generate(schema, floor));
    }

    DebugLog.PRINTOUT("Elapsed time: " + ((System.currentTimeMillis() - startTime)/1000) + " seconds");
    DebugLog.PRINTOUT("Generation for " + Constants.GET_INPUT_FILE() + " finished.");
  }
}