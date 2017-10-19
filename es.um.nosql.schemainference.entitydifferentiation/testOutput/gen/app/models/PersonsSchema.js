'use strict'

var mongoose = require('mongoose');
var PersonalDataSchema = require('./PersonalDataSchema.js');
var CustomDate3Schema = require('./CustomDate3Schema.js');
var CustomDate2Schema = require('./CustomDate2Schema.js');
var CustomDate1Schema = require('./CustomDate1Schema.js');
var UnionType = require('./util/UnionType.js');

var PersonsSchema = new mongoose.Schema({
  data: {type: PersonalDataSchema.schema, required: true},
  dates: UnionType("U_CustomDate1_CustomDate2_CustomDate3", "CustomDate1", UnionType("U_CustomDate2_CustomDate3", "CustomDate2", "CustomDate3").name),
  id: UnionType("U_Number_String", "Number", "String")
}, {collection: 'Persons'});

module.exports = mongoose.model('Persons', PersonsSchema);
