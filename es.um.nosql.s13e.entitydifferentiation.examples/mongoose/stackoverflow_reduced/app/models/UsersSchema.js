'use strict'

var mongoose = require('mongoose');

var UsersSchema = new mongoose.Schema({
  AboutMe: {type: String, required: true},
  AccountId: {type: Number, required: true},
  Age: {type: Number, required: true},
  CreationDate: {type: String, required: true},
  DisplayName: {type: String, required: true},
  DownVotes: {type: Number, required: true},
  LastAccessDate: {type: String, required: true},
  Location: String,
  ProfileImageUrl: String,
  Reputation: {type: Number, required: true},
  UpVotes: {type: Number, required: true},
  Views: {type: String, required: true},
  WebsiteUrl: String,
  _id: {type: String, required: true},
  _type: {type: , required: true}
}, { versionKey: false, collection: 'users'});


module.exports = mongoose.model('Users', UsersSchema);
