'use strict'

var mongoose = require('mongoose');
var Nationality = require('./NationalitySchema');
var Identifier = require('./IdentifierSchema');
var Birth_place = require('./Birth_placeSchema');
var Birth_date = require('./Birth_dateSchema');
var Alias = require('./AliasSchema');
var Address = require('./AddressSchema');
var UnionType = require('./util/UnionType.js');

var Sanctions = new mongoose.Schema({
  _id: {type: String, required: true},
  addresses: {type: [Address], default: undefined},
  aliases: {type: [Alias], default: undefined},
  birth_dates: {type: [Birth_date], default: undefined},
  birth_places: {type: [Birth_place], default: undefined},
  father_name: String,
  first_name: String,
  function: String,
  gender: String,
  identifiers: {type: [Identifier], default: undefined},
  last_name: String,
  listed_at: String,
  name: String,
  nationalities: {type: [Nationality], default: undefined},
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


module.exports = Sanctions;
