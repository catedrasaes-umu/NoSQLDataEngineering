'use strict'

var mongoose = require('mongoose');
var CustomDate2Schema = require('./CustomDate2Schema.js');
var PersonalDataSchema = require('./PersonalDataSchema.js');
var CustomDate1Schema = require('./CustomDate1Schema.js');
var UnionType = require('./util/UnionType.js');

var PersonsSchema = new mongoose.Schema({
  dates: UnionType("U_CustomDate1_CustomDate2_String", "CustomDate1", UnionType("U_CustomDate2_String", "CustomDate2", "String").name),
  id: UnionType("U_Number_String", "Number", "String"),
  data: UnionType("U_PersonalData_String", "PersonalData", "String")
}, {collection: 'Persons'});

module.exports = mongoose.model('Persons', PersonsSchema);
