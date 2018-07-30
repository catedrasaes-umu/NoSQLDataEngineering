package es.um.nosql.s13e.decisiontree.m2t

import java.io.File
import es.um.nosql.s13e.util.ModelLoader
import es.um.nosql.s13e.DecisionTree.DecisionTrees
import es.um.nosql.s13e.DecisionTree.DecisionTreePackage
import es.um.nosql.s13e.decisiontree.m2t.commons.Commons
import es.um.nosql.s13e.NoSQLSchema.EntityClass
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation

class DecisionTreeJSBaseGen
{
  var modelName = "";

  def m2t(File modelFile, File outputFolder)
  {
    val loader = new ModelLoader(DecisionTreePackage.eINSTANCE);
    val decTrees = loader.load(modelFile, DecisionTrees);

    m2t(decTrees, outputFolder);
  }

  def void m2t(DecisionTrees decTrees, File outputFolder)
  {
    modelName = decTrees.name + "Classifier";
    Commons.WRITE_TO_FILE(outputFolder, "testClassifier.js", generateClassificationFile(decTrees));
  }

  private def generateClassificationFile(DecisionTrees decTrees)
  '''
  var «modelName» = require('./«modelName»');
  «FOR EntityClass entity : decTrees.trees.map[tree | tree.entity].filter[e | e.root]»
  var «entity.name.toLowerCase»Json = require('./json/«entity.name».json');
  «ENDFOR»

  «FOR EntityClass entity : decTrees.trees.map[tree | tree.entity].filter[e | e.root]»
  test«entity.name»Classifier();
  «ENDFOR»

  «FOR EntityClass entity : decTrees.trees.map[tree | tree.entity].filter[e | e.root] SEPARATOR "\n"»
  function test«entity.name»Classifier()
  {
    var «entity.name.toLowerCase»Counter = { «FOR StructuralVariation ev : entity.variations SEPARATOR ', '»«entity.name»_«ev.variationId» : 0«ENDFOR»}

    for («entity.name.toLowerCase» of «entity.name.toLowerCase»Json)
    {
      var key = «modelName».«entity.name».entityVariationForObject(«entity.name.toLowerCase»);
      «entity.name.toLowerCase»Counter[key] = «entity.name.toLowerCase»Counter[key] + 1;
    }

    console.log(«entity.name.toLowerCase»Counter);
  }
  «ENDFOR»
  '''
}
