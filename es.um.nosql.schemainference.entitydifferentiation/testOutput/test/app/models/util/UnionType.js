'use strict'

var mongoose = require('mongoose');

function makeUnionType(name, type1, type2)
{
  var capitalizedName = name.charAt(0).toUpperCase() + name.slice(1);
  var typeFunction = function(key, options) {mongoose.SchemaType.call(this, key, options, capitalizedName);}
  typeFunction.prototype = Object.create(mongoose.SchemaType.prototype);
  typeFunction.prototype.cast = function(val)
  {
    console.log(val);
    console.log(mongoose.Schema.Types);
    var funcCheckMongooseType = function (type) {return mongoose.Schema.Types[type].prototype.cast;};
    var funcCheckMongooseSchema = function (type) {return function(value){if (value.constructor.modelName === type) return val; else throw new Error();}};
    var castFunction1 = type1 in mongoose.Schema.Types ? funcCheckMongooseType(type1) : funcCheckMongooseSchema(type1);
    var castFunction2 = type2 in mongoose.Schema.Types ? funcCheckMongooseType(type2) : funcCheckMongooseSchema(type2);

    var returnVal = val;

    try {returnVal = castFunction1(val);} catch (firstTypeError)
    {
      try {returnVal = castFunction2(val);} catch (secondTypeError)
      {
        throw new Error(capitalizedName + ': ' + val + ' couldn\'t be cast to ' + type1 + " or " + type2);
      }
    }

    return returnVal;
  }

  Object.defineProperty(typeFunction, "name", { value: capitalizedName });
  mongoose.Schema.Types[capitalizedName] = typeFunction;

  return typeFunction;
}

module.exports = makeUnionType;
