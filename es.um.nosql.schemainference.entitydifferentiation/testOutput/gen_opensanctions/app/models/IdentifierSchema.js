'use strict'

var mongoose = require('mongoose');

var IdentifierSchema = new mongoose.Schema({
  country: String,
  country_code: String,
  description: String,
  issued_at: String,
  number: String,
  type: String
}, { versionKey: false, _id : false});

module.exports = mongoose.model('Identifier', IdentifierSchema);
