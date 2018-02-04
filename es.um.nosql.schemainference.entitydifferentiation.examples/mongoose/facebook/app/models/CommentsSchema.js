'use strict'

var mongoose = require('mongoose');

var CommentsSchema = new mongoose.Schema({
  _id: {type: mongoose.Schema.Types.ObjectId, required: true},
  _type: {type: , required: true},
  created_time: String,
  from_id: String,
  from_name: String,
  message: String,
  post_id: {type: String, ref: "Posts"}
}, { versionKey: false, collection: 'comments'});


module.exports = mongoose.model('Comments', CommentsSchema);
