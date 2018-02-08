'use strict'

var mongoose = require('mongoose');

var PagesSchema = new mongoose.Schema({
  _id: {type: String, required: true},
  page_name: {type: String, required: true}
}, { versionKey: false, collection: 'pages'});


module.exports = mongoose.model('Pages', PagesSchema);
