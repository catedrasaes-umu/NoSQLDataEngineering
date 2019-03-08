'use strict'

var mongoose = require('mongoose');

var Badges = new mongoose.Schema({
  Class: {type: Number, required: true},
  Date: {type: String, required: true},
  Name: {type: String, required: true},
  TagBased: {type: String, required: true},
  UserId: {type: Number, ref: "Users", required: true},
  _id: {type: String, required: true}
}, { versionKey: false, collection: 'badges'});


module.exports = Badges;
