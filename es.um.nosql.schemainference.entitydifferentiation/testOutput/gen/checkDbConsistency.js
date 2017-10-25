var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/everypolitician',
{
  useMongoClient: true
}, function(err)
{
  if (err)
    console.log(err);
  else
    console.log('Connected to 127.0.0.1/everypolitician');
});
mongoose.set('debug', true);
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error: '));

var Memberships = require('./app/models/MembershipsSchema');
var Identifier = require('./app/models/IdentifierSchema');
var Events = require('./app/models/EventsSchema');
var Persons = require('./app/models/PersonsSchema');
var Organizations = require('./app/models/OrganizationsSchema');
var Other_name = require('./app/models/Other_nameSchema');
var Image = require('./app/models/ImageSchema');
var Contact_detail = require('./app/models/Contact_detailSchema');
var Link = require('./app/models/LinkSchema');
var Source = require('./app/models/SourceSchema');
var Areas = require('./app/models/AreasSchema');

db.once('open', function()
{
  Memberships.find({}, '-_id', function(err, result)
  {
    if (err)
      return console.error(err);

    console.log("Checking consistency of the \"Memberships\" table");
    var errorNumber = 0;

    result.forEach(function(memberships)
    {
      var validation = memberships.validateSync();
      if (typeof validation !== "undefined")
      {
        console.log(validation);
        errorNumber++;
      }
    });

    if (errorNumber)
      console.log("\"Memberships\" table: " + errorNumber + " errors found");
    else
      console.log("\"Memberships\" table: No errors found!");
  });

  Events.find({}, '-_id', function(err, result)
  {
    if (err)
      return console.error(err);

    console.log("Checking consistency of the \"Events\" table");
    var errorNumber = 0;

    result.forEach(function(events)
    {
      var validation = events.validateSync();
      if (typeof validation !== "undefined")
      {
        console.log(validation);
        errorNumber++;
      }
    });

    if (errorNumber)
      console.log("\"Events\" table: " + errorNumber + " errors found");
    else
      console.log("\"Events\" table: No errors found!");
  });

  Persons.find({}, '-_id', function(err, result)
  {
    if (err)
      return console.error(err);

    console.log("Checking consistency of the \"Persons\" table");
    var errorNumber = 0;

    result.forEach(function(persons)
    {
      var validation = persons.validateSync();
      if (typeof validation !== "undefined")
      {
        console.log(validation);
        errorNumber++;
      }
    });

    if (errorNumber)
      console.log("\"Persons\" table: " + errorNumber + " errors found");
    else
      console.log("\"Persons\" table: No errors found!");
  });

  Organizations.find({}, '-_id', function(err, result)
  {
    if (err)
      return console.error(err);

    console.log("Checking consistency of the \"Organizations\" table");
    var errorNumber = 0;

    result.forEach(function(organizations)
    {
      var validation = organizations.validateSync();
      if (typeof validation !== "undefined")
      {
        console.log(validation);
        errorNumber++;
      }
    });

    if (errorNumber)
      console.log("\"Organizations\" table: " + errorNumber + " errors found");
    else
      console.log("\"Organizations\" table: No errors found!");
  });

  Areas.find({}, '-_id', function(err, result)
  {
    if (err)
      return console.error(err);

    console.log("Checking consistency of the \"Areas\" table");
    var errorNumber = 0;

    result.forEach(function(areas)
    {
      var validation = areas.validateSync();
      if (typeof validation !== "undefined")
      {
        console.log(validation);
        errorNumber++;
      }
    });

    if (errorNumber)
      console.log("\"Areas\" table: " + errorNumber + " errors found");
    else
      console.log("\"Areas\" table: No errors found!");
  });

});
