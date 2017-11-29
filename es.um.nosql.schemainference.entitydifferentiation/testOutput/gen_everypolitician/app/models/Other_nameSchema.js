'use strict'

var mongoose = require('mongoose');

var Other_nameSchema = new mongoose.Schema({
  lang: String,
  name: {type: String, required: true},
  note: {type: String, required: true}
}, { versionKey: false, _id : false});

module.exports = mongoose.model('Other_name', Other_nameSchema);
