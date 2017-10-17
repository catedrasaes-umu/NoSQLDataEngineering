'use strict'

var mongoose = require('mongoose');

var MediumSchema = new mongoose.Schema({
  name: {type: String, required: true},
  url: {type: String, required: true}
});

module.exports = MediumSchema;
