'use strict'

var mongoose = require('mongoose');

var CommentsSchema = new mongoose.Schema({
  CreationDate: {type: String, required: true},
  PostId: {type: Number, ref: "Posts", required: true},
  Score: {type: Number, required: true},
  Text: {type: String, required: true},
  UserDisplayName: String,
  UserId: {type: Number, ref: "Users"},
  _id: {type: String, required: true}
}, { versionKey: false, collection: 'comments'});


module.exports = mongoose.model('Comments', CommentsSchema);
