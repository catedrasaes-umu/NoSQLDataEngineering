'use strict'

var mongoose = require('mongoose');

var CustomDate3Schema = new mongoose.Schema({
  date3: {type: String, required: true}
});

module.exports = mongoose.model('CustomDate3', CustomDate3Schema);
