var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/mongomovies',
{
  useMongoClient: true
}, function(err)
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

db.once('open', function()
{
  Movie.find(function(err, result)
  {
    if (err)
      return console.error(err);

    console.log("Checking consistency of the \"Movie\" table");
    var errorNumber = 0;

    result.forEach(function(movie)
    {
      var validation = movie.validateSync();
      if (typeof validation !== "undefined")
      {
        console.log(validation);
        errorNumber++;
      }
    });

    if (errorNumber)
      console.log("\"Movie\" table: " + errorNumber + " errors found");
    else
      console.log("\"Movie\" table: No errors found!");
  });

  Movietheater.find(function(err, result)
  {
    if (err)
      return console.error(err);

    console.log("Checking consistency of the \"Movietheater\" table");
    var errorNumber = 0;

    result.forEach(function(movietheater)
    {
      var validation = movietheater.validateSync();
      if (typeof validation !== "undefined")
      {
        console.log(validation);
        errorNumber++;
      }
    });

    if (errorNumber)
      console.log("\"Movietheater\" table: " + errorNumber + " errors found");
    else
      console.log("\"Movietheater\" table: No errors found!");
  });

  Director.find(function(err, result)
  {
    if (err)
      return console.error(err);

    console.log("Checking consistency of the \"Director\" table");
    var errorNumber = 0;

    result.forEach(function(director)
    {
      var validation = director.validateSync();
      if (typeof validation !== "undefined")
      {
        console.log(validation);
        errorNumber++;
      }
    });

    if (errorNumber)
      console.log("\"Director\" table: " + errorNumber + " errors found");
    else
      console.log("\"Director\" table: No errors found!");
  });

});
