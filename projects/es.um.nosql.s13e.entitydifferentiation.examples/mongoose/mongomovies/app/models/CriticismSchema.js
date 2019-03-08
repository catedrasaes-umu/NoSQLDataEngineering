'use strict'

var mongoose = require('mongoose');
var Media = require('./MediaSchema');
var UnionType = require('./util/UnionType.js');

var Criticism = new mongoose.Schema({
  color: {type: String, required: true},
  journalist: {type: String, required: true},
  media: {type: UnionType("U_[Media]_String", "[Media]", "String"), default: undefined}
}, { versionKey: false, _id : false});


module.exports = Criticism;
