'use strict'

var mongoose = require('mongoose');

var MediaSchema = new mongoose.Schema({
	name: {type: String, required: true},
	url: {type: String, required: true}
});

module.exports = MediaSchema;
