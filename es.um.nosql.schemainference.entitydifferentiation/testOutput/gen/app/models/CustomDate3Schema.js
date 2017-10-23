'use strict'

var mongoose = require('mongoose');
var UnionType = require('./util/UnionType.js');

var CustomDate3Schema = new mongoose.Schema({
  date3: {type: String, required: true}
});

module.exports = mongoose.model('CustomDate3', CustomDate3Schema);
