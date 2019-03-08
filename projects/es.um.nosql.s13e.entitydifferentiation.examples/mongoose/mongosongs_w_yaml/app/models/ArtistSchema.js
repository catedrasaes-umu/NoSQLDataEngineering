'use strict'

var mongoose = require('mongoose');

var Artist = new mongoose.Schema({
  _id: {type: String, required: true},
  albums: {type: [String], ref: "Album", default: undefined},
  composedTracks: {type: [String], ref: "Track", default: undefined},
  lyricsTracks: {type: [String], ref: "Track", default: undefined},
  name: {type: String, required: true},
  startingYear: {min: [1900, "Starting year of a group must be > 1900 and < 2018"], type: Number, max: [2018, "Starting year of a group must be > 1900 and < 2018"], required: true}
}, { versionKey: false, collection: 'artist'});

Artist.index({name: -1}, {sparse: true, background: false, unique: true});

module.exports = Artist;
