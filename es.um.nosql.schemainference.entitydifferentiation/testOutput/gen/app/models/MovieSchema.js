'use strict'

var mongoose = require('mongoose');
var RatingSchema = require('./RatingSchema.js');
var PrizeSchema = require('./PrizeSchema.js');
var CriticismSchema = require('./CriticismSchema.js');
var UnionType = require('./util/UnionType.js');

var MovieSchema = new mongoose.Schema({
  director_id: {type: String, required: true},
  title: {type: String, required: true},
  year: {type: Number, required: true},
  type: String,
  running_time: Number,
  criticisms: {type: [CriticismSchema.schema], default: () => undefined},
  prizes: {type: [PrizeSchema.schema], default: () => undefined},
  rating: RatingSchema.schema,
  genre: String,
  writers: {type: [String], default: () => undefined},
  genres: {type: [String], default: () => undefined}
}, {collection: 'Movie'});

module.exports = mongoose.model('Movie', MovieSchema);
