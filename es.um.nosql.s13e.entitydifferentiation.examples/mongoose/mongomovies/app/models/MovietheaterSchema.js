'use strict'

var mongoose = require('mongoose');

var MovietheaterSchema = new mongoose.Schema({
  _id: {type: String, required: true},
  city: {type: String, required: true},
  country: {type: String, required: true},
  name: {type: String, required: true},
  noOfRooms: Number
}, { versionKey: false, collection: 'movietheater'});


module.exports = mongoose.model('Movietheater', MovietheaterSchema);
