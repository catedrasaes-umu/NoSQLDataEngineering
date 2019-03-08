'use strict'

var mongoose = require('mongoose');

var Nationality = new mongoose.Schema({
  country: {type: String, required: true},
  country_code: String
}, { versionKey: false, _id : false});


module.exports = Nationality;
