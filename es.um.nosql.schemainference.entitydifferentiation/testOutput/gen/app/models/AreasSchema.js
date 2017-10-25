'use strict'

var mongoose = require('mongoose');

var AreasSchema = new mongoose.Schema({
  id: {type: String, required: true},
  name: {type: String, required: true},
  type: String
}, {collection: 'Areas'});

module.exports = mongoose.model('Areas', AreasSchema);
