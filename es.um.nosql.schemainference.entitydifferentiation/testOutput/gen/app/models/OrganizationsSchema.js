'use strict'

var mongoose = require('mongoose');
var IdentifierSchema = require('./IdentifierSchema.js');
var LinkSchema = require('./LinkSchema.js');
var Other_nameSchema = require('./Other_nameSchema.js');
var UnionType = require('./util/UnionType.js');

var OrganizationsSchema = new mongoose.Schema({
  id: {type: String, required: true},
  classification: {type: String, required: true},
  name: {type: String, required: true},
  type: String,
  seats: Number,
  identifiers: [IdentifierSchema.schema],
  other_names: [Other_nameSchema.schema],
  srgb: String,
  image: String,
  links: [LinkSchema.schema]
}, {collection: 'Organizations'}
);

module.exports = mongoose.model('Organizations', OrganizationsSchema);
