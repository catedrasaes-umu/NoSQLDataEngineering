'use strict'

var mongoose = require('mongoose');

var BadgesSchema = new mongoose.Schema({
  Class: {type: Number, required: true},
  Date: {type: String, required: true},
  Name: {type: String, required: true},
  TagBased: {type: Boolean, required: true},
  UserId: {type: Number, ref: "Users", required: true},
  _id: {type: String, required: true},
  _type: {type: , required: true}
}, { versionKey: false, collection: 'badges'});


module.exports = mongoose.model('Badges', BadgesSchema);
