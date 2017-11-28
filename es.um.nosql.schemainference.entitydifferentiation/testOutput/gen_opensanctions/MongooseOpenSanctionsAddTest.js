var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/opensanctions',
{
  useMongoClient: true
}, function(err)
{
  if (err)
    console.log(err);
  else
    console.log('Connected to 127.0.0.1/opensanctions');
});
mongoose.set('debug', true);
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error: '));

var Identifier = require('./app/models/IdentifierSchema');
var Address = require('./app/models/AddressSchema');
var Birth_date = require('./app/models/Birth_dateSchema');
var Alias = require('./app/models/AliasSchema');
var Nationality = require('./app/models/NationalitySchema');
var Birth_place = require('./app/models/Birth_placeSchema');
var Sanctions = require('./app/models/SanctionsSchema');

var bp1 = new Birth_place(); bp1.country = "country1"; bp1.country_code = "code1"; bp1.place = "place1"; bp1.quality = "quality1";
var bp2 = new Birth_place(); bp1.country = "country2";
var bp3 = new Birth_place(); bp3.place = "place3"; bp3.quality = "quality3";

var bd1 = new Birth_date(); bd1.date = "date1"; bd1.quality = "quality1";
var bd2 = new Birth_date(); bd2.date = "date2";
var bd3 = new Birth_date(); bd3.quality = "quality3";

var alias1 = new Alias(); alias1.first_name = "first_name1"; alias1.last_name = "last_name1"; alias1.name = "name1"; alias1.quality = "quality1"; alias1.second_name = "second_name1"; alias1.title = "title1"; alias1.type = "type1";
var alias2 = new Alias(); alias2.first_name = "first_name2"; alias2.last_name = "last_name2";
var alias3 = new Alias(); alias3.quality = "quality3"; alias3.title = "title3"; alias3.type = "type3";

var id1 = new Identifier(); id1.country = "country1"; id1.country_code = "code1"; id1.description = "descr1"; id1.issued_at = "issue1"; id1.number = "number1"; id1.type = "type1";
var id2 = new Identifier(); id2.description = "descr2"; id2.issued_at = "issue2"; id2.number = "number2"; id2.type = "type2";
var id3 = new Identifier(); id3.country = "country3"; id3.country_code = "code3";

var add1 = new Address(); add1.city = "city1"; add1.country = "country1"; add1.country_code = "code1"; add1.note = "note1"; add1.region = "region1"; add1.street = "street1";
var add2 = new Address(); add2.city = "city2"; add2.country = "country2";
var add3 = new Address(); add3.city = "city3"; add3.street = "street3";

var nat1 = new Nationality(); nat1.country = "country1"; nat1.country_code = "code1";
var nat2 = new Nationality(); nat2.country = "country2";
var nat3 = new Nationality(); nat3.country = "country3";

var s1 = new Sanctions(); s1._id = mongoose.Types.ObjectId(); s1.first_name = "first_name1"; s1.function = "function1"; s1.id = "id1"; s1.listed_at = "listed_at1"; s1.name = "name1";
s1.program = "program1"; s1.second_name = "second_name1"; s1.source = "source1"; s1.summary = "summary1"; s1.third_name = "third_name1"; s1.timestamp = "timestamp1"; s1.title = "title1";
s1.type = "type1"; s1.updated_at = "updated_at1"; s1.addresses = [add1]; s1.aliases = [alias1]; s1.birth_dates = [bd1]; s1.birth_places = [bp1]; s1.identifiers = [id1]; s1.nationalities = [nat1];
s1.save();
var s2 = new Sanctions(); s2._id = mongoose.Types.ObjectId(); s2.id = "id2"; s2.source = "source2"; s2.timestamp = "timestamp2"; s2.save();
var s3 = new Sanctions(); s3._id = mongoose.Types.ObjectId(); s3.id = "id3"; s3.source = "source3"; s3.timestamp = "timestamp3"; s3.addresses = [add2, add3];
s3.aliases = [alias2, alias3]; s3.birth_dates = [bd2, bd3]; s3.birth_places = [bp2, bp3]; s3.identifiers = [id2, id3]; s3.nationalities = [nat2, nat3]; s3.save();