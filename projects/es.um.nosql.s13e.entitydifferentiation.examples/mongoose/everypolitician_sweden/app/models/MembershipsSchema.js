'use strict'

var mongoose = require('mongoose');
var Source = require('./SourceSchema.js');

var Memberships = new mongoose.Schema({
  _id: {type: mongoose.Schema.Types.ObjectId, required: true},
  area_id: {type: String, ref: "Areas"},
  end_date: String,
  legislative_period_id: {type: String, required: true},
  on_behalf_of_id: {type: String, required: true},
  organization_id: {type: String, ref: "Organizations", required: true},
  person_id: {type: String, ref: "Persons", required: true},
  role: {type: String, required: true},
  sources: {type: [Source.schema], default: undefined},
  start_date: String
}, { versionKey: false, collection: 'memberships'});


module.exports = mongoose.model('Memberships', Memberships);
