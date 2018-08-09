package es.um.nosql.s13e.nosqlimport.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class MapReduceSources 
{
  private String mapJSCode;
  private String reduceJSCode;

  public MapReduceSources(String mapReduceDir)
  {
    try
    {
      Path _dir = new File(mapReduceDir).toPath();

      // Read the map file
      Path mapFile = _dir.resolve("map.js");
      mapJSCode = new String(Files.readAllBytes(mapFile));

      // Read the reduce file
      Path reduceFile = _dir.resolve("reduce.js");
      reduceJSCode = new String(Files.readAllBytes(reduceFile));
    } catch(Exception e)
    {
      throw new IllegalArgumentException("MapReduce could not be found in " + mapReduceDir);
    }
  }

  public String getMapJSCode()
  {
    return mapJSCode;
  }

  public String getReduceJSCode()
  {
    return reduceJSCode;
  }
}
