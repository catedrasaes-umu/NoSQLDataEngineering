'use strict'

var mongoose = require('mongoose');

var UnionType = require('../../util/UnionType');

function NumberOrString(key, options)
{
  mongoose.SchemaType.call(this, key, options, 'NumberOrString');
}
NumberOrString.prototype = Object.create(mongoose.SchemaType.prototype);
NumberOrString.prototype.cast = function(val)
{
  if (!(!isNaN(val) || typeof val === 'string' || val instanceof String))
    throw new Error('NumberOrString: ' + val + ' couldn\'t be cast');
  else
    return val;
};
mongoose.Schema.Types.NumberOrString = NumberOrString;
//Actually this works. Thing is, Mongoose doesnt really like anonymous functions, it seems...

UnionType("NewUnion", "Number", "String");
//console.log(UnionType("newUnion2", "Number", "String"));
//console.log(mongoose.Schema.Types.NewUnion2)
//UnionType("NewUnion", "type1", "type2");

var MovietheaterSchema = new mongoose.Schema({
  name: {type: String, required: true},
  type: String,
  city: {type: String, required: true},
  country: {type: NewUnion, required: true},
  noOfRooms: Number
}, {collection: 'Movietheater'});

module.exports = MovietheaterSchema;
