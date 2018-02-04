'use strict'

var mongoose = require('mongoose');

var Birth_placeSchema = new mongoose.Schema({
  country: String,
  country_code: String,
  place: String,
  quality: String
}, { versionKey: false, _id : false});


module.exports = mongoose.model('Birth_place', Birth_placeSchema);
