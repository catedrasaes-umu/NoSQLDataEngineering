'use strict'

var mongoose = require('mongoose');
var Prize = require('./PrizeSchema.js');
var Review = require('./ReviewSchema.js');
var UnionType = require('./util/UnionType.js');

var Album = new mongoose.Schema({
  _id: {type: String, required: true},
  availability: UnionType("U_String_[String]", "String", "[String]"),
  formats: {type: [String], required: true},
  genre: String,
  genres: {type: [String], default: undefined},
  name: {type: String, required: true},
  prizes: {type: [Prize.schema], default: undefined},
  releaseYear: {type: Number, required: true},
  reviews: {type: [Review.schema], default: undefined},
  tracks: {type: [String], ref: "Track", required: true}
}, { versionKey: false, collection: 'album'});


module.exports = mongoose.model('Album', Album);
