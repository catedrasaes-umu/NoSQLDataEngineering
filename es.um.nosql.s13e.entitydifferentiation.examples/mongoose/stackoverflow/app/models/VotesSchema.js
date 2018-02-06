'use strict'

var mongoose = require('mongoose');
var UnionType = require('./util/UnionType.js');

var VotesSchema = new mongoose.Schema({
  BountyAmount: Number,
  CreationDate: {type: String, required: true},
  PostId: {type: Number, ref: "Posts", required: true},
  UserId: {type: Number, ref: "Users"},
  VoteTypeId: {type: Number, ref: "Votes"},
  _id: {type: String, required: true},
  _type: {type: , required: true}
}, { versionKey: false, collection: 'votes'});


module.exports = mongoose.model('Votes', VotesSchema);
