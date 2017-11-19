'use strict'

var mongoose = require('mongoose');

var PrizeSchema = new mongoose.Schema({
  event: {type: String, required: true},
  year: {type: Number, required: true},
  names: {type: [String], default: () => undefined},
  name: String
});

module.exports = mongoose.model('Prize', PrizeSchema);
