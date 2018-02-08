var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/stackoverflow', function(err)
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

var N_BADGES = 25000;
var N_COMMENTS = 25000;
var N_POSTLINKS = 25000;
var N_TAGS = 48373;
var N_POSTS = 25000;
var N_USERS = 25000;
var N_VOTES = 25000;

testCheckConsistency();
//testDuplicateDb();
testAddErrorAndCheck();

function testCheckConsistency()
{
  Badges.find(function(err, result)
  {
    if (err)
      return console.error(err);

    if (result.length !== N_BADGES)
      console.error("Error: There should be " + N_BADGES + " Badges instances, but there are " + result.length + ".");

    testCollection(result, "Badges");
  }).limit(N_BADGES);

  Comments.find(function(err, result)
  {
    if (err)
      return console.error(err);

    if (result.length !== N_COMMENTS)
      console.error("Error: There should be " + N_COMMENTS + " Comments instances, but there are " + result.length + ".");

    testCollection(result, "Comments");
  }).limit(N_COMMENTS);

  Postlinks.find(function(err, result)
  {
    if (err)
      return console.error(err);

    if (result.length !== N_POSTLINKS)
      console.error("Error: There should be " + N_POSTLINKS + " Postlinks instances, but there are " + result.length + ".");

    testCollection(result, "Postlinks");
  }).limit(N_POSTLINKS);

  Posts.find(function(err, result)
  {
    if (err)
      return console.error(err);

    if (result.length !== N_POSTS)
      console.error("Error: There should be " + N_POSTS + " Posts instances, but there are " + result.length + ".");

    testCollection(result, "Posts");
  }).limit(N_POSTS);

  Users.find(function(err, result)
  {
    if (err)
      return console.error(err);

    if (result.length !== N_USERS)
      console.error("Error: There should be " + N_USERS + " Users instances, but there are " + result.length + ".");

    testCollection(result, "Users");
  }).limit(N_USERS);

  Tags.find(function(err, result)
  {
    if (err)
      return console.error(err);

    if (result.length !== N_TAGS)
      console.error("Error: There should be " + N_TAGS + " Tags instances, but there are " + result.length + ".");

    testCollection(result, "Tags");
  }).limit(N_TAGS);

  Votes.find(function(err, result)
  {
    if (err)
      return console.error(err);

    if (result.length !== N_VOTES)
      console.error("Error: There should be " + N_VOTES + " Votes instances, but there are " + result.length + ".");

    testCollection(result, "Votes");
  }).limit(N_VOTES);
}

function testDuplicateDb()
{
  Badges.find(function(err, result)
  {
    if (err)
      return console.error(err);

    result.forEach(function(item)
    {
      var newB = new Badges(); newB._id = item._id + "_TEST"; newB.Class = item.Class; newB.Date = item.Date; newB.Name = item.Name;
      newB.TagBased = item.TagBased; newB.UserId = item.UserId;
      newS.save();
    });
  }).limit(N_BADGES);

  Comments.find(function(err, result)
  {
    if (err)
      return console.error(err);

    result.forEach(function(item)
    {
      var newC = new Comments(); newC._id = item._id + "_TEST"; newC.CreationDate = item.CreationDate; newC.PostId = item.PostId; newC.Score = item.Score;
      newC.Text = item.Text; newC.UserDisplayName = item.UserDisplayName; newC.UserId = item.UserId;
      newS.save();
    });
  }).limit(N_COMMENTS);

  Postlinks.find(function(err, result)
  {
    if (err)
      return console.error(err);

    result.forEach(function(item)
    {
      var newPl = new Postlinks(); newPl._id = item._id + "_TEST"; newPl.CreationDate = item.CreationDate; newPl.LinkTypeId = item.LinkTypeId; newPl.PostId = item.PostId;
      newPl.RelatedPostId = item.RelatedPostId;
      newPl.save();
    });
  }).limit(N_POSTLINKS);

  Posts.find(function(err, result)
  {
    if (err)
      return console.error(err);

    result.forEach(function(item)
    {
      var newP = new Posts(); newP._id = item._id + "_TEST"; newP.AcceptedAnswerId = item.AcceptedAnswerId; newP.AnswerCount = item.AnswerCount; newP.Body = item.Body;
      newP.ClosedDate = item.ClosedDate; newP.CommentCount = item.CommentCount; newP.CommunityOwnedDate = item.CommunityOwnedDate; newP.CreationDate = item.CreationDate;
      newP.FavoriteCount = item.FavoriteCount; newP.LastActivityDate = item.LastActivityDate; newP.LastEditDate = item.LastEditDate; newP.LastEditorDisplayName = item.LastEditorDisplayName;
      newP.LastEditorUserId = item.LastEditorUserId; newP.OwnerDisplayName = item.OwnerDisplayName; newP.OwnerUserId = item.OwnerUserId; newP.ParentId = item.ParentId;
      newP.PostTypeId = item.PostTypeId; newP.Score = item.Score; newP.Tags = item.Tags; newP.Title = item.Title; newP.ViewCount = item.ViewCount;
      newP.save();
    });
  }).limit(N_POSTS);

  Users.find(function(err, result)
  {
    if (err)
      return console.error(err);

    result.forEach(function(item)
    {
      var newU = new Users(); newU._id = item._id + "_TEST"; newU.AboutMe = item.AboutMe; newU.AccountId = item.AccountId; newU.Age = item.Age;
      newU.CreationDate = item.CreationDate; newU.DisplayName = item.DisplayName; newU.DownVotes = item.DownVotes; newU.LastAccessDate = item.LastAccessDate;
      newU.Location = item.Location; newU.ProfileImageUrl = item.ProfileImageUrl; newU.Reputation = item.Reputation; newU.UpVotes = item.UpVotes;
      newU.Views = item.Views; newU.WebsiteUrl = item.WebsiteUrl;
      newU.save();
    });
  }).limit(N_USERS);

  Tags.find(function(err, result)
  {
    if (err)
      return console.error(err);

    result.forEach(function(item)
    {
      var newT = new Tags(); newT._id = item._id + "_TEST"; newT.Count = item.Count; newT.ExcerptPostId = item.ExcerptPostId; newT.TagName = item.TagName;
      newT.WikiPostId = item.WikiPostId;
      newT.save();
    });
  }).limit(N_TAGS);

  Votes.find(function(err, result)
  {
    if (err)
      return console.error(err);

    result.forEach(function(item)
    {
      var newV = new Votes(); newV._id = item._id + "_TEST"; newV.BountyAmount = item.BountyAmount; newV.CreationDate = item.CreationDate; newV.PostId = item.PostId;
      newV.UserId = item.UserId; newV.VoteTypeId = item.VoteTypeId;
      newV.save();
    });
  }).limit(N_VOTES);
}

function testAddErrorAndCheck()
{
  console.log("Starting TestAddErrorAndCheck...");
  console.log("TestAddErrorAndCheck finished.");
}

function testCollection(collection, tableName)
{
  if (collection === null || collection.length === 0)
    return;

  console.log("Checking consistency of the \"" + tableName + "\" collection");
  var errorNumber = 0;

  collection.forEach(function(obj)
  {
    var validation = obj.validateSync();
    if (typeof validation !== "undefined")
    {
      console.error(validation);
      errorNumber++;
    }
  });

  if (errorNumber)
    console.error("-->\"" + tableName + "\" collection: " + errorNumber + " errors found");
  else
    console.log("-->\"" + tableName + "\" collection: No errors found!");
}