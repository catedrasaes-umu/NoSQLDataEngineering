package es.um.unosql.xtext.athena.main

import es.um.unosql.xtext.athena.utils.Constants
import java.io.File
import es.um.unosql.xtext.athena.m2m.AthenaNormalizer
import es.um.unosql.xtext.athena.utils.io.ModelWriter
import es.um.unosql.xtext.athena.utils.io.ModelLoader

class AthenaNormalizerMain
{
  def static void main(String[] args)
  {
    val loader = new ModelLoader()
    val normalizer = new AthenaNormalizer()
    val writer = new ModelWriter()

    println("ATHENA> Normalizing Athena schemas...")

    new File(Constants.NORMALIZED_FOLDER).mkdirs

    for (model : new File(Constants.NORMALIZATION_FOLDER).listFiles.filter[f | f.name.endsWith(".athena")])
    {
      println("ATHENA>> Model: " + model.name)
      val schema = normalizer.m2m(loader.load(model))
      writer.write(schema, Constants.NORMALIZED_FOLDER + schema.name + ".athena")
    }

    println("ATHENA> Athena generation finished!")
  }
}
