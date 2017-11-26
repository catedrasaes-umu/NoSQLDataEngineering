'use strict'

var mongoose = require('mongoose');

var Other_nameSchema = new mongoose.Schema({
  name: {type: String, required: true},
  note: {type: String, required: true},
  lang: String
});

module.exports = mongoose.model('Other_name', Other_nameSchema);
