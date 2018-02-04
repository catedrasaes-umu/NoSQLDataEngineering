'use strict'

var mongoose = require('mongoose');
var UnionType = require('./util/UnionType.js');

var PostsSchema = new mongoose.Schema({
  AcceptedAnswerId: {type: String, required: true},
  AnswerCount: {type: Number, required: true},
  Body: {type: String, required: true},
  ClosedDate: {type: String, required: true},
  CommentCount: {type: String, required: true},
  CommunityOwnedDate: {type: String, required: true},
  CreationDate: {type: String, required: true},
  FavoriteCount: String,
  LastActivityDate: {type: String, required: true},
  LastEditDate: String,
  LastEditorDisplayName: String,
  LastEditorUserId: {type: Number, ref: "Users"},
  OwnerDisplayName: String,
  OwnerUserId: {type: Number, ref: "Users"},
  PostTypeId: {type: Number, ref: "Posts"},
  Score: {type: Number, required: true},
  Tags: {type: [String], ref: "Tags", required: true},
  Title: {type: String, required: true},
  ViewCount: {type: String, required: true},
  _id: {type: String, required: true},
  _type: {type: , required: true}
}, { versionKey: false, collection: 'posts'});


module.exports = mongoose.model('Posts', PostsSchema);
