'use strict'

var mongoose = require('mongoose');
var Contact_detailSchema = require('./Contact_detailSchema.js');
var ImageSchema = require('./ImageSchema.js');
var IdentifierSchema = require('./IdentifierSchema.js');
var LinkSchema = require('./LinkSchema.js');
var Other_nameSchema = require('./Other_nameSchema.js');
var UnionType = require('./util/UnionType.js');

var PersonsSchema = new mongoose.Schema({
  id: {type: String, required: true},
  birth_date: {type: String, required: true},
  identifiers: [IdentifierSchema.schema],
  name: {type: String, required: true},
  family_name: {type: String, required: true},
  gender: {type: String, required: true},
  image: {type: String, required: true},
  images: [ImageSchema.schema],
  given_name: {type: String, required: true},
  type: String,
  sort_name: {type: String, required: true},
  contact_details: [Contact_detailSchema.schema],
  death_date: String,
  links: [LinkSchema.schema],
  other_names: [Other_nameSchema.schema],
  email: String
}, {collection: 'Persons'}
);

module.exports = mongoose.model('Persons', PersonsSchema);
