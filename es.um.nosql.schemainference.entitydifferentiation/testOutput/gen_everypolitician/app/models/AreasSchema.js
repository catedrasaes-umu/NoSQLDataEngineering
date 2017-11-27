'use strict'

var mongoose = require('mongoose');

var AreasSchema = new mongoose.Schema({
  _id: mongoose.Schema.Types.ObjectId,
  id: {type: String, required: true},
  name: {type: String, required: true},
  type: String
}, { versionKey: false, collection: 'areas'});

module.exports = mongoose.model('Areas', AreasSchema);
