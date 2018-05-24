'use strict'

var mongoose = require('mongoose');

var MediaSchema = new mongoose.Schema({
  name: {type: String, required: true},
  type: String,
  url: {type: String, required: true}
}, { versionKey: false, _id : false});


module.exports = mongoose.model('Media', MediaSchema);
