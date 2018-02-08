'use strict'

var mongoose = require('mongoose');
var UnionType = require('./util/UnionType.js');

var PostsSchema = new mongoose.Schema({
  AcceptedAnswerId: String,
  AnswerCount: Number,
  Body: {type: String, required: true},
  ClosedDate: String,
  CommentCount: {type: String, required: true},
  CommunityOwnedDate: String,
  CreationDate: {type: String, required: true},
  FavoriteCount: String,
  LastActivityDate: {type: String, required: true},
  LastEditDate: String,
  LastEditorDisplayName: String,
  LastEditorUserId: {type: Number, ref: "Users"},
  OwnerDisplayName: String,
  OwnerUserId: {type: Number, ref: "Users"},
  ParentId: String,
  PostTypeId: {type: Number, ref: "Posts"},
  Score: {type: Number, required: true},
  Tags: {type: String, ref: "Tags"},
  Title: String,
  ViewCount: String,
  _id: {type: String, required: true}
}, { versionKey: false, collection: 'posts'});


module.exports = mongoose.model('Posts', PostsSchema);
