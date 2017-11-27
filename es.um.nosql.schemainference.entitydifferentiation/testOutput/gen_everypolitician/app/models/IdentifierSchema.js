'use strict'

var mongoose = require('mongoose');

var IdentifierSchema = new mongoose.Schema({
  scheme: {type: String, required: true},
  identifier: {type: String, required: true}
}, { versionKey: false, _id : false});

module.exports = mongoose.model('Identifier', IdentifierSchema);
