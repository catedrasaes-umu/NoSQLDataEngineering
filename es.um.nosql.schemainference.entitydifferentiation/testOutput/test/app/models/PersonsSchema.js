'use strict'

var mongoose = require('mongoose');
var PersonDataSchema = require('./PersonDataSchema.js');
var UnionType = require('./util/UnionType.js');

var PersonsSchema = new mongoose.Schema({
  id: {type: String, required: true},
  strange: [Number],
  dates: UnionType("U_PersonData_Number", "PersonData", "Number")
}, {collection: 'Persons'});

module.exports = mongoose.model('Persons', PersonsSchema);
