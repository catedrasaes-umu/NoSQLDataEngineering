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
var CustomDate3 = require('./app/models/CustomDate3Schema');
var PersonalData = require('./app/models/PersonalDataSchema');

var p = new PersonalData({Name:"yodawg", Age: 33});
var err = p.validateSync();
if (err !== undefined)
  console.log(err);

var cDate1 = new CustomDate1({date1: 33})
err = cDate1.validateSync();
if (err !== undefined)
  console.log(err);

var cDate2 = new CustomDate2({date2: false})
err = cDate2.validateSync();
if (err !== undefined)
  console.log(err);

var cDate3 = new CustomDate3({date3: false})
err = cDate3.validateSync();
if (err !== undefined)
  console.log(err);

var person = new Persons({data: p, dates: cDate3});
err = person.validateSync();
if (err !== undefined)
  console.log(err);