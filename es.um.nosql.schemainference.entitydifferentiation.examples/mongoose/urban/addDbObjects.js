var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/urban',
{
  useMongoClient: true
}, function(err)
{
  if (err)
    console.log(err);
  else
    console.log('Connected to 127.0.0.1/urban');
});
mongoose.set('debug', true);
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error: '));

var Urban_words = require('./app/models/Urban_wordsSchema');

var err;
var urban_words = new Urban_words({});
err = urban_words.validateSync();
if (err !== undefined)
  console.log(err);
