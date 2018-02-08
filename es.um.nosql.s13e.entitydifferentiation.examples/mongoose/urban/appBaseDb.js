var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/urban', function(err)
{
  if (err)
    console.log(err);
  else
    console.log('Connected to 127.0.0.1/urban');
});
mongoose.set('debug', true);
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error: '));

var Urban_words = require('./app/models/Urban_wordsSchema');

Urban_words.find(function(err, result)
{
  if (err)
    return console.error(err);

  console.log("Checking consistency of the \"Urban_words\" table");
  var errorNumber = 0;

  result.forEach(function(urban_words)
  {
    var validation = urban_words.validateSync();
    if (typeof validation !== "undefined")
    {
      console.log(validation);
      errorNumber++;
    }
  });

  if (errorNumber)
    console.log("\"Urban_words\" table: " + errorNumber + " errors found");
  else
    console.log("\"Urban_words\" table: No errors found!");
});

