var mongoose   = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/mongoMovies3',
{
  useMongoClient: true
}, function(err)
{
  if (err)
    console.log(err);
  else
    console.log('Connected to 127.0.0.1/mongoMovies3');
});

mongoose.set('debug', true);
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error: '));

var Movietheater = mongoose.model('Movietheater', require('./app/models/MovietheaterSchema'));

db.once('open', function()
{
  // Nothing new here, just create a Movietheater and try to save it to the database.
  var newTheater = new Movietheater({name: "thisName", city: "thisCity", country: "[false, true]"});
  newTheater.save(function(err, newTheater)
  {
    if (err)
      return console.error(err);
    else
      console.log("Inserción completada");
  })
});
