// app/models/bear.js

var mongoose     = require('mongoose');
var Schema       = mongoose.Schema;

var AddressSchema = new Schema({
    street: String,
    number: Number
},{_id: false});

var BearSchema   = new Schema({
    name: String,
    address: AddressSchema
});

module.exports = mongoose.model('Bear', BearSchema);
