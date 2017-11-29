'use strict'

var mongoose = require('mongoose');

var Contact_detailSchema = new mongoose.Schema({
  type: String,
  value: {type: String, required: true}
}, { versionKey: false, _id : false});

module.exports = mongoose.model('Contact_detail', Contact_detailSchema);
