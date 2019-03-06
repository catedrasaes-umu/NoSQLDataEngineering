'use strict'

var mongoose = require('mongoose');

var Areas = new mongoose.Schema({
  _id: {type: String, required: true},
  name: {type: String, required: true},
  type: {type: String, required: true}
}, { versionKey: false, collection: 'areas'});


module.exports = mongoose.model('Areas', Areas);
