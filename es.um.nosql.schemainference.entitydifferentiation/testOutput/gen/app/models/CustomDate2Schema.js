'use strict'

var mongoose = require('mongoose');
var UnionType = require('./util/UnionType.js');

var CustomDate2Schema = new mongoose.Schema({
  date2: {type: Boolean, required: true}
});

module.exports = mongoose.model('CustomDate2', CustomDate2Schema);
