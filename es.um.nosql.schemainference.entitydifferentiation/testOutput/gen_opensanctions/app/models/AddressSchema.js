'use strict'

var mongoose = require('mongoose');

var AddressSchema = new mongoose.Schema({
  note: String,
  country: String,
  street_2: String,
  text: String,
  region: String,
  postal_code: String,
  country_code: String,
  city: String,
  street: String
}, { versionKey: false, _id : false});

module.exports = mongoose.model('Address', AddressSchema);
