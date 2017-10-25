var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/everypolitician',
{
  useMongoClient: true
}, function(err)
{
  if (err)
    console.log(err);
  else
    console.log('Connected to 127.0.0.1/everypolitician');
});
mongoose.set('debug', true);
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error: '));

var Memberships = require('./app/models/MembershipsSchema');
var Identifier = require('./app/models/IdentifierSchema');
var Events = require('./app/models/EventsSchema');
var Persons = require('./app/models/PersonsSchema');
var Organizations = require('./app/models/OrganizationsSchema');
var Other_name = require('./app/models/Other_nameSchema');
var Image = require('./app/models/ImageSchema');
var Contact_detail = require('./app/models/Contact_detailSchema');
var Link = require('./app/models/LinkSchema');
var Source = require('./app/models/SourceSchema');
var Areas = require('./app/models/AreasSchema');

var err;
var memberships = new Memberships({});
err = memberships.validateSync();
if (err !== undefined)
  console.log(err);

var identifier = new Identifier({});
err = identifier.validateSync();
if (err !== undefined)
  console.log(err);

var events = new Events({});
err = events.validateSync();
if (err !== undefined)
  console.log(err);

var persons = new Persons({});
err = persons.validateSync();
if (err !== undefined)
  console.log(err);

var organizations = new Organizations({});
err = organizations.validateSync();
if (err !== undefined)
  console.log(err);

var other_name = new Other_name({});
err = other_name.validateSync();
if (err !== undefined)
  console.log(err);

var image = new Image({});
err = image.validateSync();
if (err !== undefined)
  console.log(err);

var contact_detail = new Contact_detail({});
err = contact_detail.validateSync();
if (err !== undefined)
  console.log(err);

var link = new Link({});
err = link.validateSync();
if (err !== undefined)
  console.log(err);

var source = new Source({});
err = source.validateSync();
if (err !== undefined)
  console.log(err);

var areas = new Areas({});
err = areas.validateSync();
if (err !== undefined)
  console.log(err);
