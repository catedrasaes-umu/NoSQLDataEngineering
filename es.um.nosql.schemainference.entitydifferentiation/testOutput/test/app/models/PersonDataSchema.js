'use strict'

var mongoose = require('mongoose');
var UnionType = require('./util/UnionType.js');

var PersonDataSchema = new mongoose.Schema({
  val1: {type: Number, required: true},
  val2: {type: Boolean, required: true}
});

module.exports = mongoose.model('PersonData', PersonDataSchema);
