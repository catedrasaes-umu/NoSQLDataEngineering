'use strict'

var mongoose = require('mongoose');
var Media = require('./MediaSchema.js');
var UnionType = require('./util/UnionType.js');

var Review = new mongoose.Schema({
  journalist: {type: String, required: true},
  media: {type: UnionType("U_[Media.schema]_String", "[Media.schema]", "String"), default: undefined},
  rank: {type: String, required: true},
  stars: UnionType("U_Number_String", "Number", "String")
}, { versionKey: false, _id : false});


module.exports = mongoose.model('Review', Review);
