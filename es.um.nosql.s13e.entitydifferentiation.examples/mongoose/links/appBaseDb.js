var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/links', function(err)
{
  if (err)
    console.log(err);
  else
    console.log('Connected to 127.0.0.1/links');
});
mongoose.set('debug', true);
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error: '));

var Links = require('./app/models/LinksSchema');

Links.find(function(err, result)
{
  if (err)
    return console.error(err);

  console.log("Checking consistency of the \"Links\" table");
  var errorNumber = 0;

  result.forEach(function(links)
  {
    var validation = links.validateSync();
    if (typeof validation !== "undefined")
    {
      console.log(validation);
      errorNumber++;
    }
  });

  if (errorNumber)
    console.log("\"Links\" table: " + errorNumber + " errors found");
  else
    console.log("\"Links\" table: No errors found!");
});

