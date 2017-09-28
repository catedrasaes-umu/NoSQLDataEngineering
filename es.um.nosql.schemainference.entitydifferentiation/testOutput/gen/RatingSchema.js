'use strict'

var mongoose = require('mongoose');

var RatingSchema = new mongoose.Schema({
	score: {type: Number, required: true},
	voters: {type: Number, required: true}
});

module.exports = RatingSchema;
