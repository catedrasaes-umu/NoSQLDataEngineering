var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/webclicks',
{
  useMongoClient: true
}, function(err)
{
  if (err)
    console.log(err);
  else
    console.log('Connected to 127.0.0.1/webclicks');
});
mongoose.set('debug', true);
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error: '));

var Webclicks = require('./app/models/WebclicksSchema');

var err;
var webclicks = new Webclicks({});
err = webclicks.validateSync();
if (err !== undefined)
  console.log(err);
