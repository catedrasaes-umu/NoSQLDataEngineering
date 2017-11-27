'use strict'

var mongoose = require('mongoose');
var Other_nameSchema = require('./Other_nameSchema.js');
var IdentifierSchema = require('./IdentifierSchema.js');
var Contact_detailSchema = require('./Contact_detailSchema.js');
var ImageSchema = require('./ImageSchema.js');
var LinkSchema = require('./LinkSchema.js');
var UnionType = require('./util/UnionType.js');

var PersonsSchema = new mongoose.Schema({
  _id: mongoose.Schema.Types.ObjectId,
  id: {type: String, required: true},
  birth_date: {type: String, required: true},
  identifiers: {type: [IdentifierSchema.schema], default: undefined},
  name: {type: String, required: true},
  family_name: {type: String, required: true},
  gender: {type: String, required: true},
  image: {type: String, required: true},
  images: {type: [ImageSchema.schema], default: undefined},
  given_name: {type: String, required: true},
  type: String,
  sort_name: {type: String, required: true},
  death_date: String,
  contact_details: {type: [Contact_detailSchema.schema], default: undefined},
  links: {type: [LinkSchema.schema], default: undefined},
  other_names: {type: [Other_nameSchema.schema], default: undefined},
  email: String
}, { versionKey: false, collection: 'persons'});

module.exports = mongoose.model('Persons', PersonsSchema);
