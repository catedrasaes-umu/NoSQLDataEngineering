'use strict'

var mongoose = require('mongoose');

function makeUnionType(name, type1, type2)
{
  var capitalizedName = name.charAt(0).toUpperCase() + name.slice(1);
  var typeFunction = function(key, options) {mongoose.SchemaType.call(this, key, options, capitalizedName);}
  typeFunction.prototype = Object.create(mongoose.SchemaType.prototype);
  typeFunction.prototype.cast = function(val)
  {
    var returnVal = val;

    try {returnVal = mongoose.Schema.Types[type1].prototype.cast(val);} catch (firstTypeError)
    {
      try {returnVal = mongoose.Schema.Types[type2].prototype.cast(val);} catch (secondTypeError)
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
