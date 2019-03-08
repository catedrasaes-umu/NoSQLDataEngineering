'use strict'

var mongoose = require('mongoose');
var Rating = require('./RatingSchema.js');
var Prize = require('./PrizeSchema.js');
var Criticism = require('./CriticismSchema.js');
var UnionType = require('./util/UnionType.js');

var Movie = new mongoose.Schema({
  _id: {type: String, required: true},
  criticisms: {type: [Criticism], default: undefined},
  director_id: {type: String, ref: "Director", required: true},
  genre: String,
  genres: {type: [String], default: undefined},
  prizes: {type: [Prize], default: undefined},
  rating: Rating,
  running_time: Number,
  title: {type: String, required: true},
  writers: {type: [String], default: undefined},
  year: {type: Number, required: true}
}, { versionKey: false, collection: 'movie'});


module.exports = Movie;
