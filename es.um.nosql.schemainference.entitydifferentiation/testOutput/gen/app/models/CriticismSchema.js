'use strict'

var mongoose = require('mongoose');
var MediumSchema = require('./MediumSchema.js');
var UnionType = require('./util/UnionType.js');

var CriticismSchema = new mongoose.Schema({
  color: {type: String, required: true},
  journalist: {type: String, required: true},
  media: {type: UnionType("U_[MediumSchema.schema]_String", "[MediumSchema.schema]", "String"), default: undefined}
}, { versionKey: false, _id : false});

module.exports = mongoose.model('Criticism', CriticismSchema);
