'use strict'

var mongoose = require('mongoose');

var PostlinksSchema = new mongoose.Schema({
  CreationDate: {type: String, required: true},
  LinkTypeId: {type: Number, required: true},
  PostId: {type: Number, ref: "Posts", required: true},
  RelatedPostId: {type: Number, ref: "Posts", required: true},
  _id: {type: String, required: true}
}, { versionKey: false, collection: 'postlinks'});


module.exports = mongoose.model('Postlinks', PostlinksSchema);
