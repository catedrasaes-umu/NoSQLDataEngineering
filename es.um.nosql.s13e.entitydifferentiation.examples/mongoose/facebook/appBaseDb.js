var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/facebook', function(err)
{
  if (err)
    console.log(err);
  else
    console.log('Connected to 127.0.0.1/facebook');
});
mongoose.set('debug', true);
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error: '));

var Pages = require('./app/models/PagesSchema');
var Comments = require('./app/models/CommentsSchema');
var Posts = require('./app/models/PostsSchema');

Pages.find(function(err, result)
{
  if (err)
    return console.error(err);

  console.log("Checking consistency of the \"Pages\" table");
  var errorNumber = 0;

  result.forEach(function(pages)
  {
    var validation = pages.validateSync();
    if (typeof validation !== "undefined")
    {
      console.log(validation);
      errorNumber++;
    }
  });

  if (errorNumber)
    console.log("\"Pages\" table: " + errorNumber + " errors found");
  else
    console.log("\"Pages\" table: No errors found!");
});

Comments.find(function(err, result)
{
  if (err)
    return console.error(err);

  console.log("Checking consistency of the \"Comments\" table");
  var errorNumber = 0;

  result.forEach(function(comments)
  {
    var validation = comments.validateSync();
    if (typeof validation !== "undefined")
    {
      console.log(validation);
      errorNumber++;
    }
  });

  if (errorNumber)
    console.log("\"Comments\" table: " + errorNumber + " errors found");
  else
    console.log("\"Comments\" table: No errors found!");
});

Posts.find(function(err, result)
{
  if (err)
    return console.error(err);

  console.log("Checking consistency of the \"Posts\" table");
  var errorNumber = 0;

  result.forEach(function(posts)
  {
    var validation = posts.validateSync();
    if (typeof validation !== "undefined")
    {
      console.log(validation);
      errorNumber++;
    }
  });

  if (errorNumber)
    console.log("\"Posts\" table: " + errorNumber + " errors found");
  else
    console.log("\"Posts\" table: No errors found!");
});

