'use strict'

var mongoose = require('mongoose');

var Contact_detail = new mongoose.Schema({
  type: {type: String, required: true},
  value: {type: String, required: true}
}, { versionKey: false, _id : false});


module.exports = mongoose.model('Contact_detail', Contact_detail);
