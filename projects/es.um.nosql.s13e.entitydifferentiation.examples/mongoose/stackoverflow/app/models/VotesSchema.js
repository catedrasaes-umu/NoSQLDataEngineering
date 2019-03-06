'use strict'

var mongoose = require('mongoose');

var Votes = new mongoose.Schema({
  BountyAmount: Number,
  CreationDate: {type: String, required: true},
  PostId: {type: Number, ref: "Posts", required: true},
  UserId: {type: Number, ref: "Users"},
  VoteTypeId: {type: Number, required: true},
  _id: {type: String, required: true}
}, { versionKey: false, collection: 'votes'});


module.exports = mongoose.model('Votes', Votes);
