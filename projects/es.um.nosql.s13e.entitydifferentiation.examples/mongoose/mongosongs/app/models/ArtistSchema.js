'use strict'

var mongoose = require('mongoose');

var Artist = new mongoose.Schema({
  _id: {type: String, required: true},
  albums: {type: [String], ref: "Album", default: undefined},
  composedTracks: {type: [String], ref: "Track", default: undefined},
  lyricsTracks: {type: [String], ref: "Track", default: undefined},
  name: {type: String, required: true},
  startingYear: {type: Number, required: true}
}, { versionKey: false, collection: 'artist'});


module.exports = Artist;
