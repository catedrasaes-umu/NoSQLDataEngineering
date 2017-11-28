'use strict'

var mongoose = require('mongoose');
var AddressSchema = require('./AddressSchema.js');
var Birth_dateSchema = require('./Birth_dateSchema.js');
var AliasSchema = require('./AliasSchema.js');
var NationalitySchema = require('./NationalitySchema.js');
var IdentifierSchema = require('./IdentifierSchema.js');
var Birth_placeSchema = require('./Birth_placeSchema.js');
var UnionType = require('./util/UnionType.js');

var SanctionsSchema = new mongoose.Schema({
  _id: mongoose.Schema.Types.ObjectId,
  type: String,
  id: {type: String, required: true},
  timestamp: {type: String, required: true},
  source: {type: String, required: true},
  summary: String,
  last_name: String,
  third_name: String,
  birth_places: {type: [Birth_placeSchema.schema], default: undefined},
  father_name: String,
  second_name: String,
  function: String,
  title: String,
  url: String,
  identifiers: {type: [IdentifierSchema.schema], default: undefined},
  updated_at: String,
  birth_dates: {type: [Birth_dateSchema.schema], default: undefined},
  gender: String,
  addresses: {type: [AddressSchema.schema], default: undefined},
  nationalities: {type: [NationalitySchema.schema], default: undefined},
  program: String,
  listed_at: String,
  name: String,
  aliases: {type: [AliasSchema.schema], default: undefined},
  first_name: String
}, { versionKey: false, collection: 'sanctions'});

module.exports = mongoose.model('Sanctions', SanctionsSchema);
