var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/mongosongs', function(err)
{
  if (err)
    console.log(err);
  else
    console.log('Connected to 127.0.0.1/mongosongs');
});
mongoose.set('debug', true);
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error: '));

var Artist = require('./app/models/ArtistSchema');
var Album = require('./app/models/AlbumSchema');
var Track = require('./app/models/TrackSchema');
var Prize = require('./app/models/PrizeSchema');
var Review = require('./app/models/ReviewSchema');
var Media = require('./app/models/MediaSchema');
var Rating = require('./app/models/RatingSchema');

var N_ARTISTS = 2000;
var N_ALBUMS = 4000;
var N_TRACKS = 2000;

testCheckConsistency();
//testDuplicateDb();
testAddErrorAndCheck();

function testCheckConsistency()
{
  Artist.find(function(err, result)
  {
    if (err)
      return console.error(err);

    if (result.length !== N_ARTISTS)
      console.error("Error: There should be " + N_ARTISTS + " Artists instances, but there are " + result.length + ".");

    testCollection(result, "Artist");
  });

  Album.find(function(err, result)
  {
    if (err)
      return console.error(err);

    if (result.length !== N_ALBUMS)
      console.error("Error: There should be " + N_ALBUMS + " Album instances, but there are " + result.length + ".");

    testCollection(result, "Album");
  });

  Track.find(function(err, result)
  {
    if (err)
      return console.error(err);

      if (result.length !== N_TRACKS)
        console.error("Error: There should be " + N_TRACKS + " Track instances, but there are " + result.length + ".");

      testCollection(result, "Track");
  });
}

function testDuplicateDb()
{
  Artist.find(function(err, result)
  {
    if (err)
      return console.error(err);

    result.forEach(function(item)
    {
      var newA = new Artist(); newA._id = item._id + "_TEST"; newA._type = item._type; newA.name = item.name; newA.startingYear = item.startingYear;
      newA.albums = item.albums; newA.composedTracks = item.composedTracks; newA.lyricsTracks = item.lyricsTracks;
      newA.save();
    });
  });

  Album.find(function(err, result)
  {
    if (err)
      return console.error(err);

    result.forEach(function(item)
    {
      var newAl = new Album(); newAl._id = item._id + "_TEST"; newAl._type = item._type; newAl.formats = item.formats; newAl.name = item.name;
      newAl.popularity = item.popularity; newAl.releaseYear = item.releaseYear; newAl.tracks = item.tracks; newAl.availability = item.availability;
      newAl.genre = item.genre; newAl.prizes = item.prizes; newAl.reviews = item.reviews; newAl.genres = item.genres;
      newAl.save();
    });
  });

  Track.find(function(err, result)
  {
    if (err)
      return console.error(err);

    result.forEach(function(item)
    {
      var newT = new Track(); newT._id = item._id + "_TEST"; newT._type = item._type; newT.genres = item.genres; newT.length = item.length;
      newT.name = item.name; newT.popularity = item.popularity; newT.artist_id = item.artist_id; newT.ratings = item.ratings;
      newT.save();
    });
  });
}

function testAddErrorAndCheck()
{
  console.log("Starting TestAddErrorAndCheck...");

  var a1 = new Artist();
  var a2 = new Artist(); a2._id = (new mongoose.Types.ObjectId).toString(); a2.name = "name"; a2.startingYear = 1999;

  if (a1.validateSync() === undefined || Object.values(a1.validateSync().errors).length !== 3)
    console.error("Error: artist1 was not correctly validated.");
  if (a2.validateSync() !== undefined)
    console.error("Error: artist2 was not correctly validated.");

  var m1 = new Media(); m1.name = "name1"; m1.url = "url1";
  var m2 = new Media(); m2.name = "name2"; m2.url = "url2";

  if (m1.validateSync() !== undefined)
    console.error("Error: media1 was not correctly validated.");
  if (m2.validateSync() !== undefined)
    console.error("Error: media2 was not correctly validated.");

  var r1 = new Review(); r1.journalist = "journalist1"; r1.rank = "high"; r1.media = [m1, m2];
  var r2 = new Review(); r2.journalist = "journalist2"; r2.rank = "medium"; r2.media = "media2";
  var r3 = new Review(); r3.journalist = "journalist3";

  if (r1.validateSync() !== undefined)
    console.error("Error: review1 was not correctly validated.");
  if (r2.validateSync() !== undefined)
    console.error("Error: review2 was not correctly validated.");
  if (r3.validateSync() === undefined || Object.values(r3.validateSync().errors).length !== 1)
    console.error("Error: review3 was not correctly validated.");

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