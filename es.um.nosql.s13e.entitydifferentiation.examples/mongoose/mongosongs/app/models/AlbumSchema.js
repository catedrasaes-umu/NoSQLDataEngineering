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
  name: {type: String, required: true},
  prizes: {type: [PrizeSchema.schema], default: undefined},
  releaseYear: {type: Number, required: true},
  reviews: {type: [ReviewSchema.schema], default: undefined},
  tracks: {type: [String], ref: "Track", required: true}
}, { versionKey: false, collection: 'album'});


module.exports = mongoose.model('Album', AlbumSchema);
