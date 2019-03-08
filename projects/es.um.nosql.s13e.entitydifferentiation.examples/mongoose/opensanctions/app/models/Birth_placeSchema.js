'use strict'

var mongoose = require('mongoose');

var Birth_place = new mongoose.Schema({
  country: String,
  country_code: String,
  place: String,
  quality: String
}, { versionKey: false, _id : false});


module.exports = Birth_place;
