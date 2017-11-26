'use strict'

var mongoose = require('mongoose');
var SourceSchema = require('./SourceSchema.js');

var MembershipsSchema = new mongoose.Schema({
  person_id: {type: String, required: true},
  legislative_period_id: {type: String, required: true},
  organization_id: {type: String, required: true},
  role: {type: String, required: true},
  on_behalf_of_id: {type: String, required: true},
  type: String,
  sources: [SourceSchema.schema],
  end_date: String,
  area_id: String,
  start_date: String
}, {collection: 'Memberships'}
);

module.exports = mongoose.model('Memberships', MembershipsSchema);
