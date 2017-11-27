'use strict'

var mongoose = require('mongoose');
var SourceSchema = require('./SourceSchema.js');

var MembershipsSchema = new mongoose.Schema({
  _id: mongoose.Schema.Types.ObjectId,
  person_id: {type: String, required: true},
  legislative_period_id: {type: String, required: true},
  organization_id: {type: String, required: true},
  role: {type: String, required: true},
  on_behalf_of_id: {type: String, required: true},
  type: String,
  sources: {type: [SourceSchema.schema], default: undefined},
  start_date: String,
  end_date: String,
  area_id: String
}, { versionKey: false, collection: 'memberships'});

module.exports = mongoose.model('Memberships', MembershipsSchema);
