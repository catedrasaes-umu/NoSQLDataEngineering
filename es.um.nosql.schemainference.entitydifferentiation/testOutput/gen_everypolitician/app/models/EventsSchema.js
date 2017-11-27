'use strict'

var mongoose = require('mongoose');
var IdentifierSchema = require('./IdentifierSchema.js');

var EventsSchema = new mongoose.Schema({
  _id: mongoose.Schema.Types.ObjectId,
  id: {type: String, required: true},
  end_date: {type: String, required: true},
  classification: {type: String, required: true},
  name: {type: String, required: true},
  type: String,
  start_date: {type: String, required: true},
  identifiers: {type: [IdentifierSchema.schema], default: undefined},
  organization_id: String
}, { versionKey: false, collection: 'events'});

module.exports = mongoose.model('Events', EventsSchema);
