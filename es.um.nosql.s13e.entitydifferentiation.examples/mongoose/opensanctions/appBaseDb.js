var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/opensanctions', function(err)
{
  if (err)
    console.log(err);
  else
    console.log('Connected to 127.0.0.1/opensanctions');
});
mongoose.set('debug', true);
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error: '));

var Identifier = require('./app/models/IdentifierSchema');
var Address = require('./app/models/AddressSchema');
var Birth_date = require('./app/models/Birth_dateSchema');
var Alias = require('./app/models/AliasSchema');
var Nationality = require('./app/models/NationalitySchema');
var Birth_place = require('./app/models/Birth_placeSchema');
var Sanctions = require('./app/models/SanctionsSchema');

Sanctions.find(function(err, result)
{
  if (err)
    return console.error(err);

  console.log("Checking consistency of the \"Sanctions\" table");
  var errorNumber = 0;

  result.forEach(function(sanctions)
  {
    var validation = sanctions.validateSync();
    if (typeof validation !== "undefined")
    {
      console.log(validation);
      errorNumber++;
    }
  });

  if (errorNumber)
    console.log("\"Sanctions\" table: " + errorNumber + " errors found");
  else
    console.log("\"Sanctions\" table: No errors found!");
});

