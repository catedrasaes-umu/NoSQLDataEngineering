'use strict'

var mongoose = require('mongoose');
var Other_nameSchema = require('./Other_nameSchema.js');
var IdentifierSchema = require('./IdentifierSchema.js');
var LinkSchema = require('./LinkSchema.js');
var UnionType = require('./util/UnionType.js');

var OrganizationsSchema = new mongoose.Schema({
  _id: mongoose.Schema.Types.ObjectId,
  id: {type: String, required: true},
  classification: {type: String, required: true},
  name: {type: String, required: true},
  type: String,
  image: String,
  seats: Number,
  srgb: String,
  identifiers: {type: [IdentifierSchema.schema], default: undefined},
  links: {type: [LinkSchema.schema], default: undefined},
  other_names: {type: [Other_nameSchema.schema], default: undefined}
}, { versionKey: false, collection: 'organizations'});

module.exports = mongoose.model('Organizations', OrganizationsSchema);
