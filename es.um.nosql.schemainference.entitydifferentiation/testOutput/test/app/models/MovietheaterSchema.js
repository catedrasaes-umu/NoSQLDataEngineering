'use strict'

var mongoose = require('mongoose');

var UnionType = require('../../util/UnionType');

// We create an union type for Number and String
var NewUnion = UnionType("NewUnion", "Number", "String");
// If we want to check all the schemaTypes just uncomment the next line
//console.log(mongoose.Schema.Types)

var MovietheaterSchema = new mongoose.Schema({
  name: {type: String, required: true},
  type: String,
  city: {type: String, required: true},
//  country: {type: mongoose.Schema.Types.NumberOrString, required: true},
  country: {type: NewUnion, required: true},

  noOfRooms: Number
}, {collection: 'Movietheater'});

module.exports = MovietheaterSchema;
