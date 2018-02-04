'use strict'

var mongoose = require('mongoose');

var AliasSchema = new mongoose.Schema({
  description: String,
  father_name: String,
  first_name: String,
  last_name: String,
  name: String,
  quality: String,
  second_name: String,
  third_name: String,
  title: String,
  type: String
}, { versionKey: false, _id : false});


module.exports = mongoose.model('Alias', AliasSchema);
