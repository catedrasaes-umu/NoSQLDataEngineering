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

var err;
var badges = new Badges({});
err = badges.validateSync();
if (err !== undefined)
  console.log(err);

var comments = new Comments({});
err = comments.validateSync();
if (err !== undefined)
  console.log(err);

var posts = new Posts({});
err = posts.validateSync();
if (err !== undefined)
  console.log(err);

var votes = new Votes({});
err = votes.validateSync();
if (err !== undefined)
  console.log(err);

var users = new Users({});
err = users.validateSync();
if (err !== undefined)
  console.log(err);

var postlinks = new Postlinks({});
err = postlinks.validateSync();
if (err !== undefined)
  console.log(err);

var tags = new Tags({});
err = tags.validateSync();
if (err !== undefined)
  console.log(err);
