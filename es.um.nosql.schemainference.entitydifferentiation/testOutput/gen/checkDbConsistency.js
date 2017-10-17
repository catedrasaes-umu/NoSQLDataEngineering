var mongoose   = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/mongoMovies3',
{
  useMongoClient: true
}, function(err)
{
  if (err)
    console.log(err);
  else
    console.log('Connected to 127.0.0.1/mongoMovies3');
});
mongoose.set('debug', true);
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error: '));

var Movie = mongoose.model('Movie', require('./app/models/MovieSchema'));
var Movietheater = mongoose.model('Movietheater', require('./app/models/MovietheaterSchema'));
var Director = mongoose.model('Director', require('./app/models/DirectorSchema'));

db.once('open', function()
{/*
  Movie.find({}, '-_id', function(err, result)
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
*/
  var newTheater = new Movietheater({name: "thisName2", city: "thisCity2", country: "[false, true]"});
  newTheater.save(function(err, newTheater)
  {
    if (err)
      return console.error(err);
    else
      console.log("Inserción completada");
  })
/*var MovietheaterSchema = new mongoose.Schema({
  name: {type: String, required: true},
  type: String,
  city: {type: String, required: true},
  country: {type: String, required: true},
  noOfRooms: Number
}, {collection: 'Movietheater'})
*//*
  Movietheater.find({}, '-_id', function(err, result)
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
  });*/
/*
  Director.find({}, '-_id', function(err, result)
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
*/
});
