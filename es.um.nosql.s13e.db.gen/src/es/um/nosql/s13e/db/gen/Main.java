package es.um.nosql.s13e.db.gen;

import java.io.File;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.db.gen.generator.PrimitiveTypeGen;
import es.um.nosql.s13e.db.gen.utils.IOUtils;

public class Main
{
  
  public static void main(String[] args)
  {
    File modelFile = new File("source/oneofeach.xmi");
   
    PrimitiveTypeGen pTypeGen = new PrimitiveTypeGen();
  }
}