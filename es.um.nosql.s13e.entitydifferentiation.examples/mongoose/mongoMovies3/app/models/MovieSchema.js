'use strict'

var mongoose = require('mongoose');
var PrizeSchema = require('./PrizeSchema.js');
var RatingSchema = require('./RatingSchema.js');
var CriticismSchema = require('./CriticismSchema.js');
var UnionType = require('./util/UnionType.js');

var MovieSchema = new mongoose.Schema({
  _id: {type: String, required: true},
  _type: {type: , required: true},
  criticisms: {type: [CriticismSchema.schema], default: undefined},
  director_id: {type: String, ref: "Director", required: true},
  genre: String,
  genres: {type: [Mixed], default: undefined},
  prizes: {type: [PrizeSchema.schema], default: undefined},
  rating: RatingSchema.schema,
  running_time: Number,
  title: {type: String, required: true},
  writers: {type: [Mixed], default: undefined},
  year: {type: Number, required: true}
}, { versionKey: false, collection: 'movie'});


module.exports = mongoose.model('Movie', MovieSchema);
