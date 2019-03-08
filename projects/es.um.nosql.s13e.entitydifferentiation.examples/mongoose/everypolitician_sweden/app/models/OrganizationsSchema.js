'use strict'

var mongoose = require('mongoose');
var Identifier = require('./IdentifierSchema');
var Other_name = require('./Other_nameSchema');
var Link = require('./LinkSchema');
var UnionType = require('./util/UnionType.js');

var Organizations = new mongoose.Schema({
  _id: {type: String, required: true},
  classification: {type: String, required: true},
  identifiers: {type: [Identifier], default: undefined},
  image: String,
  links: {type: [Link], default: undefined},
  name: {type: String, required: true},
  other_names: {type: [Other_name], default: undefined},
  seats: Number,
  srgb: String,
  type: String
}, { versionKey: false, collection: 'organizations'});


module.exports = Organizations;
