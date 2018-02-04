'use strict'

var mongoose = require('mongoose');

var PrizeSchema = new mongoose.Schema({
  event: {type: String, required: true},
  name: String,
  names: {type: [Mixed], default: undefined},
  year: {type: Number, required: true}
}, { versionKey: false, _id : false});


module.exports = mongoose.model('Prize', PrizeSchema);
