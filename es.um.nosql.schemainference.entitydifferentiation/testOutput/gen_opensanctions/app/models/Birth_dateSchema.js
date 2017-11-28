'use strict'

var mongoose = require('mongoose');

var Birth_dateSchema = new mongoose.Schema({
  date: String,
  quality: String
}, { versionKey: false, _id : false});

module.exports = mongoose.model('Birth_date', Birth_dateSchema);
