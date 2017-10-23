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

var personaldata = new PersonalData({name: "yodawg", age: 33});
err = personaldata.validateSync();
if (err !== undefined)
  console.log(err);

var customdate1 = new CustomDate1({date1: 1});
err = customdate1.validateSync();
if (err !== undefined)
  console.log(err);

var customdate2 = new CustomDate2({date2: true});
err = customdate2.validateSync();
if (err !== undefined)
  console.log(err);

var customdate3 = new CustomDate3({date3: "yodawg"});
err = customdate3.validateSync();
if (err !== undefined)
  console.log(err);

var persons = new Persons({data: personaldata, dates: [customdate2, customdate1], id: [3, "yodawg"]});
err = persons.validateSync();
if (err !== undefined)
  console.log(err);

persons.save(function (err, data) {
  if (err) return console.error(err);
});