'use strict'

var mongoose = require('mongoose');
var PrizeSchema = require('./PrizeSchema.js');
var ReviewSchema = require('./ReviewSchema.js');
var UnionType = require('./util/UnionType.js');

var AlbumSchema = new mongoose.Schema({
  _id: {type: String, required: true},
  availability: UnionType("U_String_[String]_[String]", "String", "[String]", "[String]"),
  formats: {type: [String], required: true},
  genre: String,
  genres: {type: [String], default: undefined},
  name: {type: String, maxlength: [300, "This album's name is too long"], required: true},
  prizes: {type: [PrizeSchema.schema], default: undefined},
  releaseYear: {type: Number, required: true, min: 1900},
  reviews: {type: [ReviewSchema.schema], default: undefined},
  tracks: {type: [String], ref: "Track", required: true}
}, { versionKey: false, collection: 'album'});

AlbumSchema.index({name: 1, releaseYear: 1}, {unique: true});

module.exports = mongoose.model('Album', AlbumSchema);
