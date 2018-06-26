var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/mongomovies', function(err)
{
  if (err)
    console.log(err);
  else
    console.log('Connected to 127.0.0.1/mongomovies');
});
mongoose.set('debug', true);
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error: '));

var Movie = require('./app/models/MovieSchema');
var Movietheater = require('./app/models/MovietheaterSchema');
var Rating = require('./app/models/RatingSchema');
var Media = require('./app/models/MediaSchema');
var Director = require('./app/models/DirectorSchema');
var Criticism = require('./app/models/CriticismSchema');
var Prize = require('./app/models/PrizeSchema');

var N_MOVIETHEATER = 2000;
var N_MOVIE = 5000;
var N_DIRECTOR = 2000;

testCheckConsistency();
//testDuplicateDb();
testAddErrorAndCheck();

function testCheckConsistency()
{
  Movie.find(function(err, result)
  {
    if (err)
      return console.error(err);

    if (result.length !== N_MOVIE)
      console.error("Error: There should be " + N_MOVIE + " Movies instances, but there are " + result.length + ".");

    testCollection(result, "Movie");
  });

  Movietheater.find(function(err, result)
  {
    if (err)
      return console.error(err);

    if (result.length !== N_MOVIETHEATER)
      console.error("Error: There should be " + N_MOVIETHEATER + " Movietheater instances, but there are " + result.length + ".");

    testCollection(result, "Movietheater");
  });

  Director.find(function(err, result)
  {
    if (err)
      return console.error(err);

      if (result.length !== N_DIRECTOR)
        console.error("Error: There should be " + N_DIRECTOR + " Director instances, but there are " + result.length + ".");

      testCollection(result, "Director");
  });
}

function testDuplicateDb()
{
  Movietheater.find(function(err, result)
  {
    if (err)
      return console.error(err);

    result.forEach(function(item)
    {
      var newMt = new Movietheater(); newMt._id = item._id + "_TEST"; newMt.city = item.city; newMt.country = item.country;
      newMt.name = item.name; newMt.noOfRooms = item.noOfRooms;
      newMt.save();
    });
  });

  Director.find(function(err, result)
  {
    if (err)
      return console.error(err);

    result.forEach(function(item)
    {
      var newD = new Director(); newD._id = item._id + "_TEST"; newD.actor_movies = item.actor_movies; newD.directed_movies = item.directed_movies;
      newD.name = item.name;
      newD.save();
    });
  });

  Movie.find(function(err, result)
  {
    if (err)
      return console.error(err);

    result.forEach(function(item)
    {
      var newM = new Movie(); newM._id = item._id + "_TEST"; newM.criticisms = item.criticisms; newM.director_id = item.director_id;
      newM.genre = item.genre; newM.genres = item.genres; newM.prizes = item.prizes; newM.rating = item.rating; newM.running_time = item.running_time;
      newM.title = item.title; newM.writers = item.writers; newM.year = item.year;
      newM.save();
    });
  });
}

function testAddErrorAndCheck()
{
  console.log("Starting TestAddErrorAndCheck...");

  var mt1 = new Movietheater();
  var mt2 = new Movietheater(); mt2._id = (new mongoose.Types.ObjectId).toString(); mt2.city = "city"; mt2.country = "country"; mt2.name = "name";

  if (mt1.validateSync() === undefined || Object.values(mt1.validateSync().errors).length !== 4)
    console.error("Error: movietheater1 was not correctly validated.");
  if (mt2.validateSync() !== undefined)
    console.error("Error: movietheater2 was not correctly validated.");

  var m1 = new Media(); m1.name = "name1"; m1.url = "url1";
  var m2 = new Media(); m2.name = "name2"; m2.url = "url2";

  if (m1.validateSync() !== undefined)
    console.error("Error: media1 was not correctly validated.");
  if (m2.validateSync() !== undefined)
    console.error("Error: media2 was not correctly validated.");

  var c1 = new Criticism(); c1.color = "color1"; c1.journalist = "journalist1"; c1.media = [m1, m2];
  var c2 = new Criticism(); c2.color = "color2"; c2.journalist = "journalist2"; c2.media = "media2";
  var c3 = new Criticism(); c3.color = "color3";

  if (c1.validateSync() !== undefined)
    console.error("Error: criticism1 was not correctly validated.");
  if (c2.validateSync() !== undefined)
    console.error("Error: criticism2 was not correctly validated.");
  if (c3.validateSync() === undefined || Object.values(c3.validateSync().errors).length !== 2)
    console.error("Error: criticism3 was not correctly validated.");

  console.log("TestAddErrorAndCheck finished.");
}

function testCollection(collection, tableName)
{
  if (collection === null || collection.length === 0)
    return;

  console.log("Checking consistency of the \"" + tableName + "\" collection");
  var errorNumber = 0;

  collection.forEach(function(obj)
  {
    var validation = obj.validateSync();
    if (typeof validation !== "undefined")
    {
      console.error(validation);
      errorNumber++;
    }
  });

  if (errorNumber)
    console.error("-->\"" + tableName + "\" collection: " + errorNumber + " errors found");
  else
    console.log("-->\"" + tableName + "\" collection: No errors found!");
}