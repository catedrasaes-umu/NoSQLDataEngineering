'use strict'

var mongoose = require('mongoose');

var IdentifierSchema = new mongoose.Schema({
  number: String,
  country_code: String,
  description: String,
  type: String,
  issued_at: String,
  country: String
}, { versionKey: false, _id : false});

module.exports = mongoose.model('Identifier', IdentifierSchema);
