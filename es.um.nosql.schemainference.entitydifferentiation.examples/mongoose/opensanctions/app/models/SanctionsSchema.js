'use strict'

var mongoose = require('mongoose');
var IdentifierSchema = require('./IdentifierSchema.js');
var AddressSchema = require('./AddressSchema.js');
var AliasSchema = require('./AliasSchema.js');
var NationalitySchema = require('./NationalitySchema.js');
var Birth_placeSchema = require('./Birth_placeSchema.js');
var Birth_dateSchema = require('./Birth_dateSchema.js');
var UnionType = require('./util/UnionType.js');

var SanctionsSchema = new mongoose.Schema({
  _id: {type: String, required: true},
  _type: {type: , required: true},
  addresses: {type: [AddressSchema.schema], default: undefined},
  aliases: {type: [AliasSchema.schema], default: undefined},
  birth_dates: {type: [Birth_dateSchema.schema], default: undefined},
  birth_places: {type: [Birth_placeSchema.schema], default: undefined},
  father_name: String,
  first_name: String,
  function: String,
  gender: String,
  identifiers: {type: [IdentifierSchema.schema], default: undefined},
  last_name: String,
  listed_at: String,
  name: String,
  nationalities: {type: [NationalitySchema.schema], default: undefined},
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


module.exports = mongoose.model('Sanctions', SanctionsSchema);
