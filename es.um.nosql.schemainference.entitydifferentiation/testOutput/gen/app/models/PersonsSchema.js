'use strict'

var mongoose = require('mongoose');
var CustomDate2Schema = require('./CustomDate2Schema.js');
var CustomDate1Schema = require('./CustomDate1Schema.js');
var CustomDate3Schema = require('./CustomDate3Schema.js');
var PersonalDataSchema = require('./PersonalDataSchema.js');
var UnionType = require('./util/UnionType.js');

var PersonsSchema = new mongoose.Schema({
  data: {type: PersonalDataSchema.schema, required: true},
//  dates: UnionType("U_CustomDate1_CustomDate2_CustomDate3", "[CustomDate1]", UnionType("U_CustomDate2_CustomDate3", "[CustomDate2]", "Boolean").name),
  id: {type: UnionType("U_[Number]_[Number]", "[Number]", "[Number]"), required: true}
}, {collection: 'Persons'});

module.exports = mongoose.model('Persons', PersonsSchema);
