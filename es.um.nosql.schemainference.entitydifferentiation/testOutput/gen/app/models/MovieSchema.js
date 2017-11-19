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
  prizes: {type: [PrizeSchema.schema], default: () => undefined},
  writers: {type: [String], default: () => undefined},
  criticisms: {type: [CriticismSchema.schema], default: () => undefined},
  genres: {type: [String], default: () => undefined},
  rating: {type: RatingSchema.schema, default: () => undefined},
  genre: String
}, {collection: 'Movie'});

module.exports = mongoose.model('Movie', MovieSchema);
