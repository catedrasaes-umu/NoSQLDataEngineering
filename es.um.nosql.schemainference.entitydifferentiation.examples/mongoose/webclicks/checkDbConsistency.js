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

db.once('open', function()
{
  Webclicks.find(function(err, result)
  {
    if (err)
      return console.error(err);

    console.log("Checking consistency of the \"Webclicks\" table");
    var errorNumber = 0;

    result.forEach(function(webclicks)
    {
      var validation = webclicks.validateSync();
      if (typeof validation !== "undefined")
      {
        console.log(validation);
        errorNumber++;
      }
    });

    if (errorNumber)
      console.log("\"Webclicks\" table: " + errorNumber + " errors found");
    else
      console.log("\"Webclicks\" table: No errors found!");
  });

});
