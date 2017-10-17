'use strict'

var mongoose = require('mongoose');

function makeUnionType(name, type1, type2)
{
  var capitalizedName = name.charAt(0).toUpperCase() + name.slice(1);
  var typeFunction = new Function("key", "options", "mongoose.SchemaType.call(this, key, options, '" + capitalizedName + "');");
  typeFunction.prototype = Object.create(mongoose.SchemaType.prototype);
  typeFunction.prototype.cast = function(val)
  {
    try {mongoose.Schema.Types[type1].prototype.cast(val)} catch (firstTypeError)
    {
      try {mongoose.Schema.Types[type2].prototype.cast(val)} catch (secondTypeError)
      {
        throw new Error(capitalizedName + ': ' + val + ' couldn\'t be cast');
      }
    }

    return val;
  }
///////////
  // Since anonymous functions don't seem to work, this line will name it.
  Object.defineProperty(typeFunction, "name", { value: capitalizedName });
  // This next line is equivalent to the previous one, only quite more messy.
/*  typeFunction = (new Function("return function (call) { return function " + capitalizedName +
      " () { return call(this, arguments) }; };")())(Function.apply.bind(typeFunction));
*/
///////////
  mongoose.Schema.Types[capitalizedName] = typeFunction;
}

module.exports = makeUnionType;

// If we hardcode a union NumberOrString, this will actually work.
// It is actually usable and there is no problem with its type. Validation is also performed with the cast method.
function NumberOrString(key, options) {mongoose.SchemaType.call(this, key, options, 'NumberOrString');}
NumberOrString.prototype = Object.create(mongoose.SchemaType.prototype);
NumberOrString.prototype.cast = function(val)
{
  try {mongoose.Schema.Types.Number.prototype.cast(val);} catch (firstTypeError)
  {
    try {mongoose.Schema.Types.String.prototype.cast(val);} catch (secondTypeError)
    {
      throw new Error('NumberOrString: ' + val + ' couldn\'t be cast');
    }
  }

  return val;
};

mongoose.Schema.Types.NumberOrString = NumberOrString;