'use strict'

var mongoose = require('mongoose');

var Director = new mongoose.Schema({
  _id: {type: String, required: true},
  actor_movies: {type: [String], ref: "Movie", default: undefined},
  directed_movies: {type: [String], ref: "Movie", default: undefined},
  name: {type: String, required: true}
}, { versionKey: false, collection: 'director'});


module.exports = Director;
