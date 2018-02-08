'use strict'

var mongoose = require('mongoose');

var PublicationsSchema = new mongoose.Schema({
  _id: {type: mongoose.Schema.Types.ObjectId, required: true},
  article_title: {type: String, required: true},
  authors: String,
  pmid: {type: Number, required: true},
  pub_year: Number
}, { versionKey: false, collection: 'publications'});


module.exports = mongoose.model('Publications', PublicationsSchema);
