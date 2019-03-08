'use strict'

var mongoose = require('mongoose');

var Tags = new mongoose.Schema({
  Count: {type: Number, required: true},
  ExcerptPostId: {type: Number, ref: "Posts"},
  TagName: {type: String, required: true},
  WikiPostId: {type: Number, ref: "Posts"},
  _id: {type: String, required: true}
}, { versionKey: false, collection: 'tags'});


module.exports = Tags;
