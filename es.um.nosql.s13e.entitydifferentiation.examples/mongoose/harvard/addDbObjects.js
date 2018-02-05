var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/harvard',
{
  useMongoClient: true
}, function(err)
{
  if (err)
    console.log(err);
  else
    console.log('Connected to 127.0.0.1/harvard');
});
mongoose.set('debug', true);
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error: '));

var Harvard_courses = require('./app/models/Harvard_coursesSchema');

var err;
var harvard_courses = new Harvard_courses({});
err = harvard_courses.validateSync();
if (err !== undefined)
  console.log(err);
