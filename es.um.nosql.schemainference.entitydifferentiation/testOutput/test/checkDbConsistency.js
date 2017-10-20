var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/test2',
{
  useMongoClient: true
}, function(err)
{
  if (err)
    console.log(err);
  else
    console.log('Connected to 127.0.0.1/test2');
});
mongoose.set('debug', true);
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error: '));

var Persons = require('./app/models/PersonsSchema');
var PersonData = require('./app/models/PersonDataSchema');

var pData = new PersonData({val1: 33, val2: false});
var err = pData.validateSync();
if (err !== undefined)
  console.log(err);

var p = new Persons({id: "anId", dates: [33]});
err = p.validateSync();
if (err !== undefined)
  console.log(err);

console.log(p)
