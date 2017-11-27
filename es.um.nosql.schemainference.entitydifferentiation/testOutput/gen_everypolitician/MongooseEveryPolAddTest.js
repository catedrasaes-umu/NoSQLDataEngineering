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

var source1 = new Source(); source1.url = "url1";
var source2 = new Source(); source2.url = "url2";
var source3 = new Source(); source3.url = "url3";

var other_name1 = new Other_name(); other_name1.lang = "lang1"; other_name1.name = "name1"; other_name1.note = "note1";
var other_name2 = new Other_name(); other_name2.name = "name2"; other_name2.note = "note2";
var other_name3 = new Other_name(); other_name3.lang = "lang3"; other_name3.name = "name3"; other_name3.note = "note3";
var other_name4 = new Other_name(); other_name4.name = "name4"; other_name4.note = "note4";
var other_name5 = new Other_name(); other_name5.lang = "lang5"; other_name5.name = "name5"; other_name5.note = "note5";
var other_name6 = new Other_name(); other_name6.name = "name6"; other_name6.note = "note6";

var identifier1 = new Identifier(); identifier1.scheme = "scheme1"; identifier1.identifier = "id1";
var identifier2 = new Identifier(); identifier2.scheme = "scheme2"; identifier2.identifier = "id2";
var identifier3 = new Identifier(); identifier3.scheme = "scheme3"; identifier3.identifier = "id3";
var identifier4 = new Identifier(); identifier4.scheme = "scheme4"; identifier4.identifier = "id4";
var identifier5 = new Identifier(); identifier5.scheme = "scheme5"; identifier5.identifier = "id5";
var identifier6 = new Identifier(); identifier6.scheme = "scheme6"; identifier6.identifier = "id6";
var identifier7 = new Identifier(); identifier7.scheme = "scheme7"; identifier7.identifier = "id7";
var identifier8 = new Identifier(); identifier8.scheme = "scheme8"; identifier8.identifier = "id8";
var identifier9 = new Identifier(); identifier9.scheme = "scheme9"; identifier9.identifier = "id9";

var link1 = new Link(); link1.note = "note1"; link1.url = "url1";
var link2 = new Link(); link2.note = "note2"; link2.url = "url2";
var link3 = new Link(); link3.note = "note3"; link3.url = "url3";
var link4 = new Link(); link4.note = "note4"; link4.url = "url4";
var link5 = new Link(); link5.note = "note5"; link5.url = "url5";
var link6 = new Link(); link6.note = "note6"; link6.url = "url6";

var cdetail1 = new Contact_detail(); cdetail1.value = "value1"; cdetail1.type = "type1";
var cdetail2 = new Contact_detail(); cdetail2.value = "value2";
var cdetail3 = new Contact_detail(); cdetail3.value = "value3"; cdetail3.type = "type3";

var img1 = new Image(); img1.url = "url1";
var img2 = new Image(); img2.url = "url2";
var img3 = new Image(); img3.url = "url3";

var area1 = new Areas(); area1._id = mongoose.Types.ObjectId(); area1.id = "id1"; area1.name = "name1"; area1.type = "type1"; area1.save();
var area2 = new Areas(); area2._id = mongoose.Types.ObjectId(); area2.id = "id2"; area2.name = "name2"; area2.type = "type2"; area2.save();
var area3 = new Areas(); area3._id = mongoose.Types.ObjectId(); area3.id = "id3"; area3.name = "name3"; area3.save();

var org1 = new Organizations(); org1._id = mongoose.Types.ObjectId(); org1.id = "id1"; org1.classification = "classification1"; org1.name = "name1"; org1.type = "type1"; org1.image = "img1";
org1.seats = 33; org1.srgb = "srgb1"; org1.identifiers = [identifier1, identifier2, identifier3]; org1.links = [link1]; org1.other_names = [other_name1]; org1.save();
var org2 = new Organizations(); org2._id = mongoose.Types.ObjectId(); org2.id = "id2"; org2.classification = "classification2"; org2.name = "name2"; org2.save();
var org3 = new Organizations(); org3._id = mongoose.Types.ObjectId(); org3.id = "id3"; org3.classification = "classification3"; org3.name = "name3"; org3.type = "type3"; org3.srgb = "srgb3";
org3.links = [link2]; org3.other_names = [other_name2, other_name3]; org3.save();

var ev1 = new Events(); ev1._id = mongoose.Types.ObjectId(); ev1.id = "id1"; ev1.end_date = "end_date1"; ev1.classification = "classification1"; ev1.name = "name1"; ev1.type = "type1";
ev1.start_date = "start_date1"; ev1.identifiers = [identifier4]; ev1.organization_id = org1.id; ev1.save();
var ev2 = new Events(); ev2._id = mongoose.Types.ObjectId(); ev2.id = "id2"; ev2.end_date = "end_date2"; ev2.classification = "classification2"; ev2.name = "name2";
ev2.start_date = "start_date2"; ev2.save();
var ev3 = new Events(); ev3._id = mongoose.Types.ObjectId(); ev3.id = "id3"; ev3.end_date = "end_date3"; ev3.classification = "classification3"; ev3.name = "name3";
ev3.start_date = "start_date3"; ev3.identifiers = [identifier5, identifier6]; ev3.save();