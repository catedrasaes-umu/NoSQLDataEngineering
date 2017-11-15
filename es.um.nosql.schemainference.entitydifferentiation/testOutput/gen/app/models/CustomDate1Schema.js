'use strict'

var mongoose = require('mongoose');

var CustomDate1Schema = new mongoose.Schema({
  date1: {type: Number, required: true}
});

module.exports = mongoose.model('CustomDate1', CustomDate1Schema);
