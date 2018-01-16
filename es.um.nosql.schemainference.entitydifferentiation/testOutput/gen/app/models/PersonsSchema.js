'use strict'

var mongoose = require('mongoose');

var PersonsSchema = new mongoose.Schema({
  _id: {type: mongoose.Schema.Types.ObjectId, required: true},
  age: {min: [18, "age must be > 18 && < 100"], type: Number, max: [100, "age must be > 18 && < 100"], required: true},
  isEmployed: {type: Boolean, required: true},
  isMarried: {type: Boolean, required: true},
  name: {minlength: [3, "name must have a length > 3 && < 10"], type: String, maxlength: [10, "name must have a length > 3 && < 10"], required: true},
  status: {type: String, match: /status[0-9]/, required: true},
  surname: {type: String, required: true, enum: ['surname1', 'surname2', 'surname3', 'surname4', 'surname5']}
}, { versionKey: false, collection: 'persons'});

PersonsSchema.index({age: 1});
PersonsSchema.index({name: 1, surname: 1}, {sparse: true, background: false, unique: true});
PersonsSchema.index({status: "text"}, {expireAfterSeconds: 330, name: "custom_index_name", weights: { status: 25 }});
PersonsSchema.index({isEmployed: "hashed"});

module.exports = mongoose.model('Persons', PersonsSchema);
