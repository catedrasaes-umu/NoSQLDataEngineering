var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/stackoverflow',
{
  useMongoClient: true
}, function(err)
{
  if (err)
    console.log(err);
  else
    console.log('Connected to 127.0.0.1/stackoverflow');
});
mongoose.set('debug', true);
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error: '));

var Badges = require('./app/models/BadgesSchema');
var Comments = require('./app/models/CommentsSchema');
var Posts = require('./app/models/PostsSchema');
var Votes = require('./app/models/VotesSchema');
var Users = require('./app/models/UsersSchema');
var Postlinks = require('./app/models/PostlinksSchema');
var Tags = require('./app/models/TagsSchema');

db.once('open', function()
{
  Badges.find(function(err, result)
  {
    if (err)
      return console.error(err);

    console.log("Checking consistency of the \"Badges\" table");
    var errorNumber = 0;

    result.forEach(function(badges)
    {
      var validation = badges.validateSync();
      if (typeof validation !== "undefined")
      {
        console.log(validation);
        errorNumber++;
      }
    });

    if (errorNumber)
      console.log("\"Badges\" table: " + errorNumber + " errors found");
    else
      console.log("\"Badges\" table: No errors found!");
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

  Votes.find(function(err, result)
  {
    if (err)
      return console.error(err);

    console.log("Checking consistency of the \"Votes\" table");
    var errorNumber = 0;

    result.forEach(function(votes)
    {
      var validation = votes.validateSync();
      if (typeof validation !== "undefined")
      {
        console.log(validation);
        errorNumber++;
      }
    });

    if (errorNumber)
      console.log("\"Votes\" table: " + errorNumber + " errors found");
    else
      console.log("\"Votes\" table: No errors found!");
  });

  Users.find(function(err, result)
  {
    if (err)
      return console.error(err);

    console.log("Checking consistency of the \"Users\" table");
    var errorNumber = 0;

    result.forEach(function(users)
    {
      var validation = users.validateSync();
      if (typeof validation !== "undefined")
      {
        console.log(validation);
        errorNumber++;
      }
    });

    if (errorNumber)
      console.log("\"Users\" table: " + errorNumber + " errors found");
    else
      console.log("\"Users\" table: No errors found!");
  });

  Postlinks.find(function(err, result)
  {
    if (err)
      return console.error(err);

    console.log("Checking consistency of the \"Postlinks\" table");
    var errorNumber = 0;

    result.forEach(function(postlinks)
    {
      var validation = postlinks.validateSync();
      if (typeof validation !== "undefined")
      {
        console.log(validation);
        errorNumber++;
      }
    });

    if (errorNumber)
      console.log("\"Postlinks\" table: " + errorNumber + " errors found");
    else
      console.log("\"Postlinks\" table: No errors found!");
  });

  Tags.find(function(err, result)
  {
    if (err)
      return console.error(err);

    console.log("Checking consistency of the \"Tags\" table");
    var errorNumber = 0;

    result.forEach(function(tags)
    {
      var validation = tags.validateSync();
      if (typeof validation !== "undefined")
      {
        console.log(validation);
        errorNumber++;
      }
    });

    if (errorNumber)
      console.log("\"Tags\" table: " + errorNumber + " errors found");
    else
      console.log("\"Tags\" table: No errors found!");
  });

});
