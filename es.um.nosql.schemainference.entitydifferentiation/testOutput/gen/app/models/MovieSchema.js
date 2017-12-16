'use strict'

var mongoose = require('mongoose');
var PrizeSchema = require('./PrizeSchema.js');
var RatingSchema = require('./RatingSchema.js');
var CriticismSchema = require('./CriticismSchema.js');
var UnionType = require('./util/UnionType.js');

var MovieSchema = new mongoose.Schema({
  _id: {type: mongoose.Schema.Types.ObjectId, required: true},
  criticisms: {type: [CriticismSchema.schema], default: undefined},
  director_id: {type: String, required: true},
  genre: String,
  genres: {type: [String], default: undefined},
  prizes: {type: [PrizeSchema.schema], default: undefined},
  rating: {type: [RatingSchema.schema], default: undefined},
  running_time: Number,
  title: {type: String, required: true},
  type: String,
  writers: {type: [String], default: undefined},
  year: {type: Number, required: true}
}, { versionKey: false, collection: 'movie'});

module.exports = mongoose.model('Movie', MovieSchema);
