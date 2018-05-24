'use strict'

var mongoose = require('mongoose');
var MediaSchema = require('./MediaSchema.js');
var UnionType = require('./util/UnionType.js');

var ReviewSchema = new mongoose.Schema({
  journalist: {type: String, required: true},
  media: {type: UnionType("U_[MediaSchema.schema]_String", "[MediaSchema.schema]", "String"), default: undefined},
  rank: {type: String, required: true},
  stars: UnionType("U_Number_String", "Number", "String")
}, { versionKey: false, _id : false});


module.exports = mongoose.model('Review', ReviewSchema);
