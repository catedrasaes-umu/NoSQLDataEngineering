'use strict'

var mongoose = require('mongoose');

var DirectorSchema = new mongoose.Schema({
  _id: {type: mongoose.Schema.Types.ObjectId, required: true},
  actor_movies: {type: [String], default: undefined},
  directed_movies: {type: [String], default: undefined},
  name: {type: String, required: true},
  type: String
}, { versionKey: false, collection: 'director'});

module.exports = mongoose.model('Director', DirectorSchema);
