'use strict'

var mongoose = require('mongoose');
var UnionType = require('./util/UnionType.js');

var PersonalDataSchema = new mongoose.Schema({
  name: {type: String, required: true},
  age: {type: Number, required: true}
});

module.exports = mongoose.model('PersonalData', PersonalDataSchema);
