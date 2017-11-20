'use strict'

var mongoose = require('mongoose');

var DirectorSchema = new mongoose.Schema({
  directed_movies: {type: [String], default: () => undefined},
  name: {type: String, required: true},
  type: String,
  actor_movies: {type: [String], default: () => undefined}
}, {collection: 'Director'});

module.exports = mongoose.model('Director', DirectorSchema);
