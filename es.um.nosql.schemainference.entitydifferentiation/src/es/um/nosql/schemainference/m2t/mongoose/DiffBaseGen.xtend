package es.um.nosql.schemainference.m2t.mongoose

import java.io.File
import es.um.nosql.schemainference.util.emf.ModelLoader
import es.um.nosql.schemainference.entitydifferentiation.EntitydifferentiationPackage
import es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation
import java.io.PrintStream
import es.um.nosql.schemainference.NoSQLSchema.Entity

class DiffBaseGen
{
  var modelName = "";
  static File outputDir;

  def m2t(File modelFile, File outputFolder)
  {
    val loader = new ModelLoader(EntitydifferentiationPackage.eINSTANCE);
    val diff = loader.load(modelFile, EntityDifferentiation);

    m2t(diff, outputFolder);
  }

  /**
   * Method used to start the generation process from an EntityDifferentiation object
   */
  def void m2t(EntityDifferentiation diff, File outputFolder)
  {
    outputDir = outputFolder;
    modelName = diff.name;

    outputDir.toPath.resolve("app").resolve("models").toFile.mkdirs;
    writeToFile("package.json", generatePackageFile());
    writeToFile("main.js", generateMainFile(diff.name, diff.entityDiffSpecs.filter[ed | ed.entity.entityversions.exists[ev | ev.isRoot]].map[ed | ed.entity]))
  }

  def generatePackageFile()
  '''
  {
    "name": "node-api",
    "main": "server.js",
    "dependencies": {
      "body-parser": "^1.16.1",
      "express": "^4.14.1",
      "mongoose": "*"
    }
  }
  '''

  def generateMainFile(String modelName, Iterable<Entity> rootEntities)
  '''
  var mongoose   = require('mongoose');
  mongoose.Promise = require('bluebird');
  «FOR e : rootEntities»
    var «e.name» = mongoose.model('«e.name»', require('./app/models/«e.name»Schema'));
  «ENDFOR»

  mongoose.connect('mongodb://127.0.0.1/«modelName»', function(err)
  {
    if (err)
      console.log(err);
    else
      console.log('Connected to 127.0.0.1/«modelName»');
  });

  mongoose.set('debug', true);
  var db = mongoose.connection;
  db.on('error', console.error.bind(console, 'connection error: '));

  db.once('open', function()
  {
    «FOR e : rootEntities»
      «e.name».find(function(err, result)
      {
        if (err)
          return console.error(err);

        result.forEach(function(«e.name.toLowerCase»)
        {
          var validation = «e.name.toLowerCase».validateSync();
          if (typeof validation !== "undefined")
            console.log(validation);
        });
      });

    «ENDFOR»
  });
  '''

  // For the base generation we need three items:
  // - The folder to output the generation
  // - The model name to name the files and variables
  // - The root entities (entities with at least one root version), so we can include some variables and generate base validators.
  /**
   * Method used to write a generated CharSequence to a file
   */
  private def writeToFile(String filename, CharSequence toWrite)
  {
    val outFile = outputDir.toPath().resolve(filename).toFile()
    val outFileWriter = new PrintStream(outFile)
    outFileWriter.print(toWrite)
    outFileWriter.close()
  }
}