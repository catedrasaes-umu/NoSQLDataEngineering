'use strict'

var mongoose = require('mongoose');

function makeUnionType(name, type1, type2)
{
  function isArray(type) { return type.match(new RegExp("^\\[")) && type.match(new RegExp("\\]$"));}

  var capitalizedName = name.charAt(0).toUpperCase() + name.slice(1);
  var typeFunction = function(key, options) {mongoose.SchemaType.call(this, key, options, capitalizedName);}
  typeFunction.prototype = Object.create(mongoose.SchemaType.prototype);
  typeFunction.prototype.cast = function(val)
  {
    var funcCheckMongooseType = function(type)
    {
      return function (value)
      {
        if (isArray(type))
          return [mongoose.Schema.Types[type.slice(1, -1)].prototype.cast(value[0])];
        else
          return mongoose.Schema.Types[type].prototype.cast(value);
      }
    };

    var funcCheckMongooseSchema = function (type)
    {
      return function (value)
      {
        if (isArray(type))
        {
          if (value[0].constructor.modelName === type.slice(1, -1))
            return value;
          else
            throw new Error();
        }
        else
          if (value.constructor.modelName === type)
            return value;
          else
            throw new Error();
      }
    };

    var castFunction1 = (type1 in mongoose.Schema.Types || (isArray(type1) && type1.slice(1, -1) in mongoose.Schema.Types))?
      funcCheckMongooseType(type1) : funcCheckMongooseSchema(type1);
    var castFunction2 = (type2 in mongoose.Schema.Types || (isArray(type2) && type2.slice(1, -1) in mongoose.Schema.Types))?
      funcCheckMongooseType(type2) : funcCheckMongooseSchema(type2);

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
