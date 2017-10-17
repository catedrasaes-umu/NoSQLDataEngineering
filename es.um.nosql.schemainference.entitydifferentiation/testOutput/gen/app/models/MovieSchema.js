'use strict'

var mongoose = require('mongoose');
var RatingSchema = require('./RatingSchema.js');
var PrizeSchema = require('./PrizeSchema.js');
var CriticismSchema = require('./CriticismSchema.js');

var MovieSchema = new mongoose.Schema({
  director_id: {type: String, required: true},
  title: {type: String, required: true},
  year: {type: Number, required: true},
  type: String,
  rating: [RatingSchema],
  prizes: mongoose.Schema.Types.Mixed,
  writers: [String],
  genre: String,
  genres: [String],
  running_time: Number,
  criticisms: [CriticismSchema]
}, {collection: 'Movie'});

module.exports = MovieSchema;
