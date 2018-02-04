var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/proteins',
{
  useMongoClient: true
}, function(err)
{
  if (err)
    console.log(err);
  else
    console.log('Connected to 127.0.0.1/proteins');
});
mongoose.set('debug', true);
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error: '));

var Proteins = require('./app/models/ProteinsSchema');

db.once('open', function()
{
  Proteins.find(function(err, result)
  {
    if (err)
      return console.error(err);

    console.log("Checking consistency of the \"Proteins\" table");
    var errorNumber = 0;

    result.forEach(function(proteins)
    {
      var validation = proteins.validateSync();
      if (typeof validation !== "undefined")
      {
        console.log(validation);
        errorNumber++;
      }
    });

    if (errorNumber)
      console.log("\"Proteins\" table: " + errorNumber + " errors found");
    else
      console.log("\"Proteins\" table: No errors found!");
  });

});
