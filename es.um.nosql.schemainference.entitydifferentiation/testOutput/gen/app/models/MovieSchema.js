'use strict'

var mongoose = require('mongoose');
var PrizeSchema = require('./PrizeSchema.js');
var RatingSchema = require('./RatingSchema.js');
var CriticismSchema = require('./CriticismSchema.js');
var UnionType = require('./util/UnionType.js');

var MovieSchema = new mongoose.Schema({
  director_id: {type: String, required: true},
  title: {type: String, required: true},
  year: {type: Number, required: true},
  type: String,
  rating: RatingSchema.schema,
  criticisms: [CriticismSchema.schema],
  running_time: Number,
  genres: [String],
  genre: String,
  writers: [String],
  prizes: [PrizeSchema.schema]
}, {collection: 'Movie'});

module.exports = mongoose.model('Movie', MovieSchema);
