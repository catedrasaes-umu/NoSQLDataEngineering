'use strict'

var mongoose = require('mongoose');

var LinksSchema = new mongoose.Schema({
  _id: {type: mongoose.Schema.Types.ObjectId, required: true},
  tags: {type: [String], required: true},
  timestamp: {type: Number, required: true},
  url: {type: String, required: true}
}, { versionKey: false, collection: 'links'});


module.exports = mongoose.model('Links', LinksSchema);
