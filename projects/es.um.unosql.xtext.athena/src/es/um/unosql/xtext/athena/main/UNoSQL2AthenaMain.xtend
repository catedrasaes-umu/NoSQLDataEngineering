package es.um.unosql.xtext.athena.main

import es.um.unosql.xtext.athena.m2m.UNoSQL2Athena
import es.um.unosql.xtext.athena.utils.io.ModelWriter
import es.um.unosql.xtext.athena.utils.Constants
import java.io.File
import es.um.unosql.uNoSQLSchema.UNoSQLSchemaPackage
import es.um.unosql.uNoSQLSchema.uNoSQLSchema
import es.um.unosql.utils.ModelLoader

class UNoSQL2AthenaMain
{
  def static void main(String[] args)
  {
    val loader = new ModelLoader(UNoSQLSchemaPackage.eINSTANCE)
    val transformer = new UNoSQL2Athena()
    val writer = new ModelWriter()

    println("ATHENA> Generating Athena models from UNoSQL...")

    new File(Constants.UNOSQL2ATHENA_FOLDER).mkdirs

    for (folder : new File(Constants.UNOSQL_FOLDER).listFiles.filter[f | f.directory])
      for (model : folder.listFiles.filter[f | f.name.endsWith(".xmi")])
      {
        println("ATHENA>> Model: " + model.name)
        val schema = transformer.m2m(loader.load(model, uNoSQLSchema))
        writer.write(schema, Constants.UNOSQL2ATHENA_FOLDER + schema.name + ".athena")
      }

    println("ATHENA> Athena generation finished!")
  }
}
