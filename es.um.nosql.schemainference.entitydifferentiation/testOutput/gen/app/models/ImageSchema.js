'use strict'

var mongoose = require('mongoose');

var ImageSchema = new mongoose.Schema({
  url: {type: String, required: true}
});

module.exports = mongoose.model('Image', ImageSchema);
