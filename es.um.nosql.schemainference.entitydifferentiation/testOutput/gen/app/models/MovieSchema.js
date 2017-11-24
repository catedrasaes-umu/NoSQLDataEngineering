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
  writers: {type: [String], default: () => undefined},
  genres: {type: [String], default: () => undefined},
  running_time: Number,
  genre: String,
  rating: RatingSchema.schema,
  criticisms: {type: [CriticismSchema.schema], default: () => undefined},
  prizes: {type: [PrizeSchema.schema], default: () => undefined}
}, {collection: 'Movie'}
);

module.exports = mongoose.model('Movie', MovieSchema);
