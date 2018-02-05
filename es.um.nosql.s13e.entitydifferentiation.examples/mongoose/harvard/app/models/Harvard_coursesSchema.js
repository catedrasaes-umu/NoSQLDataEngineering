'use strict'

var mongoose = require('mongoose');

var Harvard_coursesSchema = new mongoose.Schema({
  LoE_DI: String,
  YoB: String,
  _id: {type: mongoose.Schema.Types.ObjectId, required: true},
  _type: {type: , required: true},
  certified: {type: Number, required: true},
  course_id: {type: String, required: true},
  explored: {type: Number, required: true},
  final_cc_cname_DI: {type: String, required: true},
  gender: String,
  grade: String,
  incomplete_flag: Number,
  last_event_DI: String,
  nchapters: Number,
  ndays_act: Number,
  nevents: Number,
  nforum_posts: {type: Number, required: true},
  nplay_video: Number,
  registered: {type: Number, required: true},
  start_time_DI: {type: String, required: true},
  userid_DI: {type: String, required: true},
  viewed: {type: Number, required: true}
}, { versionKey: false, collection: 'harvard_courses'});


module.exports = mongoose.model('Harvard_courses', Harvard_coursesSchema);
