'use strict'

var mongoose = require('mongoose');
var MediaSchema = require('./MediaSchema.js');
var UnionType = require('./util/UnionType.js');

var CriticismSchema = new mongoose.Schema({
  color: {type: String, required: true},
  journalist: {type: String, required: true},
  media: UnionType("U_Media_String", "Media", "String")
}, { versionKey: false, _id : false});


module.exports = mongoose.model('Criticism', CriticismSchema);
