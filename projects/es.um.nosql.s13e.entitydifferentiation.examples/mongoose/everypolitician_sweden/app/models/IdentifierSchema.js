'use strict'

var mongoose = require('mongoose');

var Identifier = new mongoose.Schema({
  identifier: {type: String, required: true},
  scheme: {type: String, required: true}
}, { versionKey: false, _id : false});


module.exports = mongoose.model('Identifier', Identifier);
