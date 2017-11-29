'use strict'

var mongoose = require('mongoose');

var IdentifierSchema = new mongoose.Schema({
  identifier: {type: String, required: true},
  scheme: {type: String, required: true}
}, { versionKey: false, _id : false});

module.exports = mongoose.model('Identifier', IdentifierSchema);
