'use strict'

var mongoose = require('mongoose');

var Image = new mongoose.Schema({
  url: {type: String, required: true}
}, { versionKey: false, _id : false});


module.exports = mongoose.model('Image', Image);
