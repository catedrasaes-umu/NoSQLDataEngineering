'use strict'

var mongoose = require('mongoose');
var SourceSchema = require('./SourceSchema.js');

var MembershipsSchema = new mongoose.Schema({
  _id: {type: mongoose.Schema.Types.ObjectId, required: true},
  area_id: String,
  end_date: String,
  legislative_period_id: {type: String, required: true},
  on_behalf_of_id: {type: String, required: true},
  organization_id: {type: String, required: true},
  person_id: {type: String, required: true},
  role: {type: String, required: true},
  sources: {type: [SourceSchema.schema], default: undefined},
  start_date: String,
  type: String
}, { versionKey: false, collection: 'memberships'});

module.exports = mongoose.model('Memberships', MembershipsSchema);
