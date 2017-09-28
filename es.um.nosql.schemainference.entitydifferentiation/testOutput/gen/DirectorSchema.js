'use strict'

var mongoose = require('mongoose');

var DirectorSchema = new mongoose.Schema({
	_id: {type: String, required: true},
	directed_movies: {type: String, required: true},
	name: {type: String, required: true},
	type: {type: String, required: true},
	actor_movies: String
});

module.exports = DirectorSchema;
