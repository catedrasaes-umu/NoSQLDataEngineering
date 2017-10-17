'use strict'

var mongoose = require('mongoose');

function makeUnionType(name, type1, type2)
{
  function renameFunction(name, fn)
  {
    return (new Function("return function (call) { return function " + name +
      " () { return call(this, arguments) }; };")())(Function.apply.bind(fn));
  }

  var capitalizedName = name.charAt(0).toUpperCase() + name.slice(1);
  var typeFunction = new Function("key", "options", "mongoose.SchemaType.call(this, key, options, '" + capitalizedName + "');");
  typeFunction.prototype = Object.create(mongoose.SchemaType.prototype);
////////////////////////////
  typeFunction.prototype.cast = function(val)
  {
    // Comparisons w type1 and type2, that's ok.
    if (!(!isNaN(val) || typeof val === 'string' || val instanceof String))
      throw new Error('NumberOrString: ' + val + ' couldn\'t be cast');
    else
      return val;
  }
  typeFunction = renameFunction(capitalizedName, typeFunction);
/////////////////////////
  mongoose.Schema.Types[capitalizedName] = typeFunction;
}

module.exports = makeUnionType;