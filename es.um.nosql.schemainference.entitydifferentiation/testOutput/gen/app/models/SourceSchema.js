'use strict'

var mongoose = require('mongoose');

var SourceSchema = new mongoose.Schema({
  url: {type: String, required: true}
});

module.exports = mongoose.model('Source', SourceSchema);
