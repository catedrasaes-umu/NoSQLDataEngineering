'use strict'

var mongoose = require('mongoose');
var IdentifierSchema = require('./IdentifierSchema.js');
var Other_nameSchema = require('./Other_nameSchema.js');
var LinkSchema = require('./LinkSchema.js');
var UnionType = require('./util/UnionType.js');

var OrganizationsSchema = new mongoose.Schema({
  _id: {type: String, required: true},
  _type: {type: , required: true},
  classification: {type: String, required: true},
  identifiers: {type: [IdentifierSchema.schema], default: undefined},
  image: String,
  links: {type: [LinkSchema.schema], default: undefined},
  name: {type: String, required: true},
  other_names: {type: [Other_nameSchema.schema], default: undefined},
  seats: Number,
  srgb: String,
  type: String
}, { versionKey: false, collection: 'organizations'});


module.exports = mongoose.model('Organizations', OrganizationsSchema);
