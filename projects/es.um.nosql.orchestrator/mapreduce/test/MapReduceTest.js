"use strict"

var fileLoader = require("fs");
var MongoClient = require("mongodb").MongoClient;
var mapString = fileLoader.readFileSync("../mongodb/v2/map.js", "utf8");
var reduceString = fileLoader.readFileSync("../mongodb/v2/reduce.js", "utf8");
var mapFunction = new Function(mapString.substring(mapString.indexOf('\n')));
var reduceFunction = new Function("key", "values", reduceString.substring(reduceString.indexOf('\n')));
var databaseIP  = "mongodb://localhost:27017/";
var dbName = "DEBUG_MapReduceTimestamp";

MongoClient.connect(databaseIP, {useNewUrlParser: true}, function(error, dbConnection)
{
  if (error)
    throw error;

  var database = dbConnection.db(dbName);
  var collList = database.listCollections();
//  var collList = ["areas"]; // Just the collections we want to mapReduce, or every collection.

  collList.forEach(function(coll)
  {
    var collName = (coll instanceof Object && 'name' in coll) ? coll.name : coll;

    database.collection(collName).mapReduce(mapFunction, reduceFunction, {out: {inline: 1}},
    function(error, results)
    {
      if (error)
        throw error;

      var varId = 1;

      results.forEach(function(result)
      {
        console.log(collName + "_" + varId++ + ":");
        console.log(result.value);
      })
    });
  });
//  dbConnection.close();
});
