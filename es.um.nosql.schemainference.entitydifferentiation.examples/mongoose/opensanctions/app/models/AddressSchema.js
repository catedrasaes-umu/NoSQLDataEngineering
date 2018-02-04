'use strict'

var mongoose = require('mongoose');

var AddressSchema = new mongoose.Schema({
  city: String,
  country: String,
  country_code: String,
  note: String,
  postal_code: String,
  region: String,
  street: String,
  street_2: String,
  text: String
}, { versionKey: false, _id : false});


module.exports = mongoose.model('Address', AddressSchema);
