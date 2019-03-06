'use strict'

var mongoose = require('mongoose');
var Nationality = require('./NationalitySchema.js');
var Identifier = require('./IdentifierSchema.js');
var Birth_place = require('./Birth_placeSchema.js');
var Birth_date = require('./Birth_dateSchema.js');
var Alias = require('./AliasSchema.js');
var Address = require('./AddressSchema.js');
var UnionType = require('./util/UnionType.js');

var Sanctions = new mongoose.Schema({
  _id: {type: String, required: true},
  addresses: {type: [Address.schema], default: undefined},
  aliases: {type: [Alias.schema], default: undefined},
  birth_dates: {type: [Birth_date.schema], default: undefined},
  birth_places: {type: [Birth_place.schema], default: undefined},
  father_name: String,
  first_name: String,
  function: String,
  gender: String,
  identifiers: {type: [Identifier.schema], default: undefined},
  last_name: String,
  listed_at: String,
  name: String,
  nationalities: {type: [Nationality.schema], default: undefined},
  program: String,
  second_name: String,
  source: {type: String, required: true},
  summary: String,
  third_name: String,
  timestamp: {type: String, required: true},
  title: String,
  type: String,
  updated_at: String,
  url: String
}, { versionKey: false, collection: 'sanctions'});


module.exports = mongoose.model('Sanctions', Sanctions);
