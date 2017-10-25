'use strict'

var mongoose = require('mongoose');

var MovietheaterSchema = new mongoose.Schema({
  name: {type: String, required: true},
  type: String,
  city: {type: String, required: true},
  country: {type: String, required: true},
  noOfRooms: Number
}, {collection: 'Movietheater'});

module.exports = mongoose.model('Movietheater', MovietheaterSchema);
