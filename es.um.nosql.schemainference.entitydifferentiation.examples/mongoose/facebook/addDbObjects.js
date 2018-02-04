var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/facebook',
{
  useMongoClient: true
}, function(err)
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

var err;
var pages = new Pages({});
err = pages.validateSync();
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
