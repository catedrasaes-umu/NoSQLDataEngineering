'use strict'

var mongoose = require('mongoose');
var dateSchema = require('./dateSchema.js');

var PersonsSchema = new mongoose.Schema({
  dates: UnionType("U_[dateSchema]_Number_Boolean", "[dateSchema]", UnionType("U_Number_Boolean", "Number", "Boolean").name),
  id: UnionType("U_Number_String", "Number", "String")
}, {collection: 'Persons'});

module.exports = PersonsSchema;
