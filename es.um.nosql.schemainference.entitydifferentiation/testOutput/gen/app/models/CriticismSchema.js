'use strict'

var mongoose = require('mongoose');
var MediumSchema = require('./MediumSchema.js');
var UnionType = require('./util/UnionType.js');

var CriticismSchema = new mongoose.Schema({
  color: {type: String, required: true},
  journalist: {type: String, required: true},
  media: UnionType("U_Medium_String", "Medium", "String")
});

module.exports = mongoose.model('Criticism', CriticismSchema);
