'use strict'

var mongoose = require('mongoose');
var ImageSchema = require('./ImageSchema.js');
var IdentifierSchema = require('./IdentifierSchema.js');
var Other_nameSchema = require('./Other_nameSchema.js');
var LinkSchema = require('./LinkSchema.js');
var Contact_detailSchema = require('./Contact_detailSchema.js');
var UnionType = require('./util/UnionType.js');

var PersonsSchema = new mongoose.Schema({
  birth_date: {type: String, required: true},
  identifiers: IdentifierSchema.schema,
  image: {type: String, required: true},
  given_name: {type: String, required: true},
  type: String,
  sort_name: {type: String, required: true},
  id: {type: String, required: true},
  name: {type: String, required: true},
  family_name: {type: String, required: true},
  gender: {type: String, required: true},
  images: ImageSchema.schema,
  death_date: String,
  links: LinkSchema.schema,
  other_names: Other_nameSchema.schema,
  email: String,
  contact_details: Contact_detailSchema.schema
}, {collection: 'Persons'});

module.exports = mongoose.model('Persons', PersonsSchema);
