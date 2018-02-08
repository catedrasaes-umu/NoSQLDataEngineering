'use strict'

var mongoose = require('mongoose');
var IdentifierSchema = require('./IdentifierSchema.js');
var Other_nameSchema = require('./Other_nameSchema.js');
var ImageSchema = require('./ImageSchema.js');
var Contact_detailSchema = require('./Contact_detailSchema.js');
var LinkSchema = require('./LinkSchema.js');
var UnionType = require('./util/UnionType.js');

var PersonsSchema = new mongoose.Schema({
  _id: {type: String, required: true},
  birth_date: {type: String, required: true},
  contact_details: {type: [Contact_detailSchema.schema], default: undefined},
  death_date: String,
  email: String,
  family_name: {type: String, required: true},
  gender: {type: String, required: true},
  given_name: {type: String, required: true},
  identifiers: {type: [IdentifierSchema.schema], default: undefined},
  image: {type: String, required: true},
  images: {type: [ImageSchema.schema], default: undefined},
  links: {type: [LinkSchema.schema], default: undefined},
  name: {type: String, required: true},
  other_names: {type: [Other_nameSchema.schema], default: undefined},
  sort_name: {type: String, required: true}
}, { versionKey: false, collection: 'persons'});


module.exports = mongoose.model('Persons', PersonsSchema);
