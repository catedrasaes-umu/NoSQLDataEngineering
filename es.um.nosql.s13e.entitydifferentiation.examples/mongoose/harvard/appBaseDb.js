var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/harvard', function(err)
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

Harvard_courses.find(function(err, result)
{
  if (err)
    return console.error(err);

  console.log("Checking consistency of the \"Harvard_courses\" table");
  var errorNumber = 0;

  result.forEach(function(harvard_courses)
  {
    var validation = harvard_courses.validateSync();
    if (typeof validation !== "undefined")
    {
      console.log(validation);
      errorNumber++;
    }
  });

  if (errorNumber)
    console.log("\"Harvard_courses\" table: " + errorNumber + " errors found");
  else
    console.log("\"Harvard_courses\" table: No errors found!");
});

