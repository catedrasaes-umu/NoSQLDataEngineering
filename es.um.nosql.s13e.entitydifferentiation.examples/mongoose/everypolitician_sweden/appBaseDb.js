var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/everypolitician', function(err)
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

var N_AREAS = 29;
var N_ORGANIZATIONS = 11;
var N_EVENTS = 62;
var N_PERSONS = 1625;
var N_MEMBERSHIPS = 5149;

testCheckConsistency();
//testDuplicateDb();
testAddErrorAndCheck();

function testCheckConsistency()
{
  Memberships.find(function(err, result)
  {
    if (err)
      return console.error(err);

    if (result.length !== N_MEMBERSHIPS)
      console.error("Error: There should be " + N_MEMBERSHIPS + " Memberships instances, but there are " + result.length + ".");

    testCollection(result, "Memberships");
  });

  Events.find(function(err, result)
  {
    if (err)
      return console.error(err);

    if (result.length !== N_EVENTS)
      console.error("Error: There should be " + N_EVENTS + " Events instances, but there are " + result.length + ".");

    testCollection(result, "Events");
  });

  Persons.find(function(err, result)
  {
    if (err)
      return console.error(err);

    if (result.length !== N_PERSONS)
      console.error("Error: There should be " + N_PERSONS + " Persons instances, but there are " + result.length + ".");

    testCollection(result, "Persons");
  });

  Organizations.find(function(err, result)
  {
    if (err)
      return console.error(err);

    if (result.length !== N_ORGANIZATIONS)
      console.error("Error: There should be " + N_ORGANIZATIONS + " Organizations instances, but there are " + result.length + ".");

    testCollection(result, "Organizations");
  });

  Areas.find(function(err, result)
  {
    if (err)
      return console.error(err);

    if (result.length !== N_AREAS)
      console.error("Error: There should be " + N_AREAS + " Areas instances, but there are " + result.length + ".");

    testCollection(result, "Areas");
  });
}

function testDuplicateDb()
{
  Events.find(function(err, result)
  {
    if (err)
      return console.error(err);

    result.forEach(function(item)
    {
      var newE = new Events(); newE._id = item._id + "_TEST"; newE.classification = item.classification; newE.end_date = item.end_date;
      newE.identifiers = item.identifiers; newE.name = item.name; newE.organization_id = item.organization_id; newE.start_date = item.start_date;
      newE.save();
    });
  });

  Persons.find(function(err, result)
  {
    if (err)
      return console.error(err);

    result.forEach(function(item)
    {
      var newP = new Persons(); newP._id = item._id + "_TEST"; newP.birth_date = item.birth_date; newP.contact_details = item.contact_details;
      newP.death_date = item.death_date; newP.email = item.email; newP.family_name = item.family_name; newP.gender = item.gender; newP.given_name = item.given_name;
      newP.identifiers = item.identifiers; newP.image = item.image; newP.images = item.images; newP.links = item.links; newP.name = item.name;
      newP.other_names = item.other_names; newP.sort_name = item.sort_name;
      newP.save();
    });
  });

  Organizations.find(function(err, result)
  {
    if (err)
      return console.error(err);

    result.forEach(function(item)
    {
      var newOrg = new Organizations(); newOrg._id = item._id + "_TEST"; newOrg.classification = item.classification; newOrg.identifiers = item.identifiers;
      newOrg.image = item.image; newOrg.links = item.links; newOrg.name = item.name; newOrg.other_names = item.other_names; newOrg.seats = item.seats;
      newOrg.srgb = item.srgb; newOrg.type = item.type;
      newOrg.save();
    });
  });

  Areas.find(function(err, result)
  {
    if (err)
      return console.error(err);

    result.forEach(function(item)
    {
      var newArea = new Areas(); newArea._id = item._id + "_TEST"; newArea.name = item.name; newArea.type = "TEST";
      newArea.save();
    });
  });
}

function testAddErrorAndCheck()
{
  console.log("Starting TestAddErrorAndCheck...");

  var area1 = new Areas(); area1._id = (new mongoose.Types.ObjectId).toString(); area1.name = "area_name"; // Type is missing, and it is defined as required on the schema.
  var area2 = new Areas(); area2._id = (new mongoose.Types.ObjectId).toString(); area2.type = "area_type"; // Name is missing, and it is defined as required on the schema.

  if (area1.validateSync() === undefined || Object.values(area1.validateSync().errors).length !== 1)
    console.error("Error: area1 was not correctly validated.");
  if (area2.validateSync() === undefined || Object.values(area2.validateSync().errors).length !== 1)
    console.error("Error: area2 was not correctly validated.");

  var org1 = new Organizations(); org1._id = (new mongoose.Types.ObjectId).toString(); org1.classification = "org_classification"; org1.name = "org_name";
  var org2 = new Organizations(); org2._id = (new mongoose.Types.ObjectId).toString(); org2.classification = "org_classification";

  if (org1.validateSync() !== undefined)
    console.error("Error: org1 was not correctly validated.");
  if (org2.validateSync() === undefined || Object.values(org2.validateSync().errors).length !== 1)
    console.error("Error: org2 was not correctly validated.");

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