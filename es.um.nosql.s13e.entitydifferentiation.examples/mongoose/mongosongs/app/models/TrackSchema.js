'use strict'

var mongoose = require('mongoose');
var RatingSchema = require('./RatingSchema.js');

var TrackSchema = new mongoose.Schema({
  _id: {type: String, required: true},
  artist_id: {type: [String], ref: "Artist", required: true},
  genres: {type: [String], required: true},
  length: {type: Number, required: true},
  name: {type: String, required: true},
  popularity: {type: Number, required: true},
  ratings: {type: [RatingSchema.schema], default: undefined}
}, { versionKey: false, collection: 'track'});


module.exports = mongoose.model('Track', TrackSchema);
