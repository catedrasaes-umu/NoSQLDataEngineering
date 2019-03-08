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

var Identifier = mongoose.model('Identifier', require('./app/models/IdentifierSchema'));
var Address = mongoose.model('Address', require('./app/models/AddressSchema'));
var Birth_date = mongoose.model('Birth_date', require('./app/models/Birth_dateSchema'));
var Alias = mongoose.model('Alias', require('./app/models/AliasSchema'));
var Nationality = mongoose.model('Nationality', require('./app/models/NationalitySchema'));
var Birth_place = mongoose.model('Birth_place', require('./app/models/Birth_placeSchema'));
var Sanctions = mongoose.model('Sanctions', require('./app/models/SanctionsSchema'));

var N_SANCTIONS = 30381;

testCheckConsistency();
//testDuplicateDb();
testAddErrorAndCheck();

function testCheckConsistency()
{
  Sanctions.find(function(err, result)
  {
    if (err)
      return console.error(err);

    if (result.length !== N_SANCTIONS)
      console.error("Error: There should be " + N_SANCTIONS + " Sanctions instances, but there are " + result.length + ".");

    testCollection(result, "Sanctions");
  });
}

function testDuplicateDb()
{
  Sanctions.find(function(err, result)
  {
    if (err)
      return console.error(err);

    result.forEach(function(item)
    {
      var newS = new Sanctions(); newS._id = item._id + "_TEST"; newS.addresses = item.addresses; newS.aliases = item.aliases; newS.birth_dates = item.aliases;
      newS.birth_places = item.birth_places; newS.father_name = item.father_name; newS.first_name = item.first_name; newS.function = item.function; newS.gender = item.gender;
      newS.identifiers = item.identifiers; newS.last_name = item.last_name; newS.listed_at = item.listed_at; newS.name = item.name; newS.nationalities = item.nationalities;
      newS.program = item.program; newS.second_name = item.second_name; newS.source = item.source; newS.summary = item.summary; newS.third_name = item.third_name;
      newS.timestamp = item.timestamp; newS.title = item.title; newS.type = item.type; newS.updated_at = item.updated_at; newS.url = item.url;
      newS.save();
    });
  });
}

function testAddErrorAndCheck()
{
  console.log("Starting TestAddErrorAndCheck...");

  var i1 = new Identifier(); i1.number = "aNumber";
  var i2 = new Identifier(); i1.number = 33;

  if (i1.validateSync() !== undefined)
    console.error("Error: identifier1 was not correctly validated.");
  if (i2.validateSync() !== undefined)
    console.error("Error: identifier2 was not correctly validated.");

  var s1 = new Sanctions(); s1._id = (new mongoose.Types.ObjectId).toString();
  var s2 = new Sanctions(); s2._id = (new mongoose.Types.ObjectId).toString(); s2.source = "source2";
  var s3 = new Sanctions(); s3._id = (new mongoose.Types.ObjectId).toString(); s3.timestamp = "timestamp3";
  var s4 = new Sanctions(); s4._id = (new mongoose.Types.ObjectId).toString(); s4.source = "source4"; s4.timestamp = "timestamp4";

  if (s1.validateSync() === undefined || Object.values(s1.validateSync().errors).length !== 2)
    console.error("Error: sanction1 was not correctly validated.");
  if (s2.validateSync() === undefined || Object.values(s2.validateSync().errors).length !== 1)
    console.error("Error: sanction2 was not correctly validated.");
  if (s3.validateSync() === undefined || Object.values(s3.validateSync().errors).length !== 1)
    console.error("Error: sanction3 was not correctly validated.");
  if (s4.validateSync() !== undefined)
    console.error("Error: sanction4 was not correctly validated.");

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