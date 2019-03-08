'use strict'

var mongoose = require('mongoose');

var Source = new mongoose.Schema({
  url: {type: String, required: true}
}, { versionKey: false, _id : false});


module.exports = Source;
