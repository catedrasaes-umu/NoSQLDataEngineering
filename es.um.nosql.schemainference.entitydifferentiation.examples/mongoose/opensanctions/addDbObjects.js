var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/opensanctions',
{
  useMongoClient: true
}, function(err)
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

var err;
var identifier = new Identifier({});
err = identifier.validateSync();
if (err !== undefined)
  console.log(err);

var address = new Address({});
err = address.validateSync();
if (err !== undefined)
  console.log(err);

var birth_date = new Birth_date({});
err = birth_date.validateSync();
if (err !== undefined)
  console.log(err);

var alias = new Alias({});
err = alias.validateSync();
if (err !== undefined)
  console.log(err);

var nationality = new Nationality({});
err = nationality.validateSync();
if (err !== undefined)
  console.log(err);

var birth_place = new Birth_place({});
err = birth_place.validateSync();
if (err !== undefined)
  console.log(err);

var sanctions = new Sanctions({});
err = sanctions.validateSync();
if (err !== undefined)
  console.log(err);
