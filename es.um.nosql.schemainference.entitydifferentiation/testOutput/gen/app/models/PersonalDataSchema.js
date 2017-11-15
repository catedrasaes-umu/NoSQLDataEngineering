'use strict'

var mongoose = require('mongoose');

var PersonalDataSchema = new mongoose.Schema({
  Name: {type: String, required: true},
  Age: {type: Number, required: true}
});

module.exports = mongoose.model('PersonalData', PersonalDataSchema);
