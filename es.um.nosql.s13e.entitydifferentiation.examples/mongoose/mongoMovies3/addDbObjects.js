var mongoose = require('mongoose');
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

var Movie = require('./app/models/MovieSchema');
var Movietheater = require('./app/models/MovietheaterSchema');
var Rating = require('./app/models/RatingSchema');
var Media = require('./app/models/MediaSchema');
var Director = require('./app/models/DirectorSchema');
var Criticism = require('./app/models/CriticismSchema');
var Prize = require('./app/models/PrizeSchema');

var err;
var movie = new Movie({});
err = movie.validateSync();
if (err !== undefined)
  console.log(err);

var movietheater = new Movietheater({});
err = movietheater.validateSync();
if (err !== undefined)
  console.log(err);

var rating = new Rating({});
err = rating.validateSync();
if (err !== undefined)
  console.log(err);

var media = new Media({});
err = media.validateSync();
if (err !== undefined)
  console.log(err);

var director = new Director({});
err = director.validateSync();
if (err !== undefined)
  console.log(err);

var criticism = new Criticism({});
err = criticism.validateSync();
if (err !== undefined)
  console.log(err);

var prize = new Prize({});
err = prize.validateSync();
if (err !== undefined)
  console.log(err);
