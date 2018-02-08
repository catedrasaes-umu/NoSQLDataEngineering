'use strict'

var mongoose = require('mongoose');

var WebclicksSchema = new mongoose.Schema({
  _id: {type: mongoose.Schema.Types.ObjectId, required: true},
  count: {type: Number, required: true},
  from: String,
  timestamp: {type: Number, required: true},
  to: {type: String, required: true}
}, { versionKey: false, collection: 'webclicks'});


module.exports = mongoose.model('Webclicks', WebclicksSchema);
