'use strict'

var mongoose = require('mongoose');
var UnionType = require('./util/UnionType.js');

var Identifier = new mongoose.Schema({
  country: String,
  country_code: String,
  description: String,
  issued_at: String,
  number: UnionType("U_String_Number", "String", "Number"),
  type: String
}, { versionKey: false, _id : false});


module.exports = mongoose.model('Identifier', Identifier);
