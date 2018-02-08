'use strict'

var mongoose = require('mongoose');

var PostsSchema = new mongoose.Schema({
  _id: {type: String, required: true},
  created_time: {type: String, required: true},
  description: String,
  link: String,
  message: String,
  page_id: {type: String, ref: "Pages", required: true},
  react_angry: {type: Number, required: true},
  react_haha: {type: Number, required: true},
  react_like: {type: Number, required: true},
  react_love: {type: Number, required: true},
  react_sad: {type: Number, required: true},
  react_wow: {type: Number, required: true},
  scrape_time: {type: String, required: true},
  shares: {type: Number, required: true}
}, { versionKey: false, collection: 'posts'});


module.exports = mongoose.model('Posts', PostsSchema);
