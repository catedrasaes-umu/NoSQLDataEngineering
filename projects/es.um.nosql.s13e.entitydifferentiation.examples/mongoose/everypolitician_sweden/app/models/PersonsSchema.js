'use strict'

var mongoose = require('mongoose');
var Identifier = require('./IdentifierSchema');
var Other_name = require('./Other_nameSchema');
var Image = require('./ImageSchema');
var Contact_detail = require('./Contact_detailSchema');
var Link = require('./LinkSchema');
var UnionType = require('./util/UnionType.js');

var Persons = new mongoose.Schema({
  _id: {type: String, required: true},
  birth_date: {type: String, required: true},
  contact_details: {type: [Contact_detail], default: undefined},
  death_date: String,
  email: String,
  family_name: {type: String, required: true},
  gender: {type: String, required: true},
  given_name: {type: String, required: true},
  identifiers: {type: [Identifier], default: undefined},
  image: {type: String, required: true},
  images: {type: [Image], default: undefined},
  links: {type: [Link], default: undefined},
  name: {type: String, required: true},
  other_names: {type: [Other_name], default: undefined},
  sort_name: {type: String, required: true}
}, { versionKey: false, collection: 'persons'});


module.exports = Persons;
