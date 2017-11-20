'use strict'

var mongoose = require('mongoose');

var PrizeSchema = new mongoose.Schema({
  event: {type: String, required: true},
  year: {type: Number, required: true},
  name: String,
  names: {type: [String], default: () => undefined}
});

module.exports = mongoose.model('Prize', PrizeSchema);
