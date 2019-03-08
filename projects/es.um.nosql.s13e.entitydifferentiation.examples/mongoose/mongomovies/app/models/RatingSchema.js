'use strict'

var mongoose = require('mongoose');

var Rating = new mongoose.Schema({
  score: {type: Number, required: true},
  voters: {type: Number, required: true}
}, { versionKey: false, _id : false});


module.exports = Rating;
