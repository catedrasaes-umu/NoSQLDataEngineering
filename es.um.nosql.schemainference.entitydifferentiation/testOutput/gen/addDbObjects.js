var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/test3',
{
  useMongoClient: true
}, function(err)
{
  if (err)
    console.log(err);
  else
    console.log('Connected to 127.0.0.1/test3');
});
mongoose.set('debug', true);
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error: '));

var Persons = require('./app/models/PersonsSchema');
var CustomDate1 = require('./app/models/CustomDate1Schema');
var CustomDate2 = require('./app/models/CustomDate2Schema');
var PersonalData = require('./app/models/PersonalDataSchema');
var CustomDate3 = require('./app/models/CustomDate3Schema');

var err;
var persons = new Persons({dates: [33, "33", false]});
err = persons.validateSync();
if (err !== undefined)
  console.log(err);
/*
var customdate1 = new CustomDate1({});
err = customdate1.validateSync();
if (err !== undefined)
  console.log(err);

var customdate2 = new CustomDate2({});
err = customdate2.validateSync();
if (err !== undefined)
  console.log(err);

var personaldata = new PersonalData({});
err = personaldata.validateSync();
if (err !== undefined)
  console.log(err);

var customdate3 = new CustomDate3({});
err = customdate3.validateSync();
if (err !== undefined)
  console.log(err);
*/