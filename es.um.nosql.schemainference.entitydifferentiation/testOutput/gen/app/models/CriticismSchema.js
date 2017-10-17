'use strict'

var mongoose = require('mongoose');
var MediumSchema = require('./MediumSchema.js');

var CriticismSchema = new mongoose.Schema({
  color: {type: String, required: true},
  journalist: {type: String, required: true},
  media: mongoose.Schema.Types.Mixed
});

module.exports = CriticismSchema;
