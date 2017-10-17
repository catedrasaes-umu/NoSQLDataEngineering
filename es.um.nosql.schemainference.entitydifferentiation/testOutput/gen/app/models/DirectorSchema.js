'use strict'

var mongoose = require('mongoose');

var DirectorSchema = new mongoose.Schema({
  directed_movies: [String],
  name: {type: String, required: true},
  type: String,
  actor_movies: [String]
}, {collection: 'Director'});

module.exports = DirectorSchema;
