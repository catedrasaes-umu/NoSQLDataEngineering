'use strict'

var mongoose = require('mongoose');

var AliasSchema = new mongoose.Schema({
  type: String,
  third_name: String,
  title: String,
  second_name: String,
  last_name: String,
  quality: String,
  father_name: String,
  name: String,
  first_name: String,
  description: String
}, { versionKey: false, _id : false});

module.exports = mongoose.model('Alias', AliasSchema);
