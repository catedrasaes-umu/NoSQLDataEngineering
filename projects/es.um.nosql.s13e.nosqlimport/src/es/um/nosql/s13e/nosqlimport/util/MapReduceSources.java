package es.um.nosql.s13e.nosqlimport.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class MapReduceSources 
{
  private class MalformedDirectoryStructure extends RuntimeException
  {
    private static final long serialVersionUID = -8007805761335157818L;
  }

  private String dir;
  private String mapJSCode;
  private String reduceJSCode;

  public String getDir()
  {
    return dir;
  }
  public String getMapJSCode()
  {
    return mapJSCode;
  }

  public String getReduceJSCode()
  {
    return reduceJSCode;
  }

  public static MapReduceSources fromDir(String dir) throws MalformedDirectoryStructure
  {
    MapReduceSources ret = new MapReduceSources(dir);
    ret._init();
    return ret;
  }

  private MapReduceSources(String dir)
  {
    super();
    this.dir = dir;
  }

  private void _init() throws MalformedDirectoryStructure
  {
    try
    {
      Path _dir = new File(dir).toPath();

      // Read the map file
      Path mapFile = _dir.resolve("map.js");
      mapJSCode = new String(Files.readAllBytes(mapFile));

      // Read the reduce file
      Path reduceFile = _dir.resolve("reduce.js");
      reduceJSCode = new String(Files.readAllBytes(reduceFile));
    } catch(Exception e)
    {
      throw new MalformedDirectoryStructure();
    }
  }
}