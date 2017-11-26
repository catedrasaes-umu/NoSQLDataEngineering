'use strict'

var mongoose = require('mongoose');

var Contact_detailSchema = new mongoose.Schema({
  value: {type: String, required: true},
  type: String
});

module.exports = mongoose.model('Contact_detail', Contact_detailSchema);
