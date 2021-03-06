'use strict'

var mongoose = require('mongoose');
var Rating = require('./RatingSchema');

var Track = new mongoose.Schema({
  _id: {type: String, required: true},
  artist_id: {type: [String], ref: "Artist", required: true},
  genres: {type: [String], required: true},
  length: {type: Number, required: true},
  name: {type: String, required: true},
  ratings: {type: [Rating], default: undefined}
}, { versionKey: false, collection: 'track'});


module.exports = Track;
