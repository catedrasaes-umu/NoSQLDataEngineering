'use strict'

var mongoose = require('mongoose');

var Prize = new mongoose.Schema({
  certification: String,
  event: {type: String, required: true},
  name: String,
  names: {type: [String], default: undefined},
  units: {type: Number, required: true},
  year: {type: Number, required: true}
}, { versionKey: false, _id : false});


module.exports = Prize;
