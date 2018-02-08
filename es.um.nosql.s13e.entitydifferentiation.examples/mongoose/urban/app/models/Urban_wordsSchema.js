'use strict'

var mongoose = require('mongoose');

var Urban_wordsSchema = new mongoose.Schema({
  _id: {type: mongoose.Schema.Types.ObjectId, required: true},
  author: String,
  defid: Number,
  definition: String,
  example: String,
  lowercase_word: String,
  permalink: {type: String, required: true},
  sounds: {type: [String], default: undefined},
  tags: {type: [String], default: undefined},
  thumbs_down: {type: Number, required: true},
  thumbs_up: {type: Number, required: true},
  word: String
}, { versionKey: false, collection: 'urban_words'});


module.exports = mongoose.model('Urban_words', Urban_wordsSchema);
