'use strict'

var mongoose = require('mongoose');
var Prize = require('./PrizeSchema');
var Review = require('./ReviewSchema');
var UnionType = require('./util/UnionType.js');

var Album = new mongoose.Schema({
  _id: {type: String, required: true},
  availability: UnionType("U_String_[String]", "String", "[String]"),
  formats: {type: [String], required: true},
  genre: String,
  genres: {type: [String], default: undefined},
  name: {type: String, maxlength: [300, "This album's name is too long"], required: true},
  prizes: {type: [Prize], default: undefined},
  releaseYear: {type: Number, required: true, min: 1900},
  reviews: {type: [Review], default: undefined},
  tracks: {type: [String], ref: "Track", required: true}
}, { versionKey: false, collection: 'album'});

Album.index({name: 1, releaseYear: -1}, {unique: true});

module.exports = Album;
