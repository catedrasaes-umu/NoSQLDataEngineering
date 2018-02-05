var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/proteins',
{
  useMongoClient: true
}, function(err)
{
  if (err)
    console.log(err);
  else
    console.log('Connected to 127.0.0.1/proteins');
});
mongoose.set('debug', true);
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error: '));

var Proteins = require('./app/models/ProteinsSchema');

var err;
var proteins = new Proteins({});
err = proteins.validateSync();
if (err !== undefined)
  console.log(err);
