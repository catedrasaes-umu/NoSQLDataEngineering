'use strict'

var mongoose = require('mongoose');
var IdentifierSchema = require('./IdentifierSchema.js');
var Other_nameSchema = require('./Other_nameSchema.js');
var LinkSchema = require('./LinkSchema.js');
var UnionType = require('./util/UnionType.js');

var OrganizationsSchema = new mongoose.Schema({
  id: {type: String, required: true},
  classification: {type: String, required: true},
  name: {type: String, required: true},
  type: String,
  other_names: Other_nameSchema.schema,
  seats: Number,
  image: String,
  identifiers: IdentifierSchema.schema,
  links: LinkSchema.schema,
  srgb: String
}, {collection: 'Organizations'});

module.exports = mongoose.model('Organizations', OrganizationsSchema);
