var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/publications', function(err)
{
  if (err)
    console.log(err);
  else
    console.log('Connected to 127.0.0.1/publications');
});
mongoose.set('debug', true);
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error: '));

var Publications = require('./app/models/PublicationsSchema');

Publications.find(function(err, result)
{
  if (err)
    return console.error(err);

  console.log("Checking consistency of the \"Publications\" table");
  var errorNumber = 0;

  result.forEach(function(publications)
  {
    var validation = publications.validateSync();
    if (typeof validation !== "undefined")
    {
      console.log(validation);
      errorNumber++;
    }
  });

  if (errorNumber)
    console.log("\"Publications\" table: " + errorNumber + " errors found");
  else
    console.log("\"Publications\" table: No errors found!");
});

