'use strict'

var mongoose = require('mongoose');

function make_union_type(name, type1, type2)
{
    var capitalized_name = name.charAt(0).toUpperCase() + name.slice(1);
    var typefn =
        new Function(key, options,
                     "mongoose.SchemaType.call(this, key, options, '"
                     + capitalized_name
                     + "');");
    typefn.prototype = Object.create(mongoose.SchemaType.prototype);



    // Add to mongoose registry
    mongoose.Schema.Types[capitalized_name] = typefn;
}

module.exports = make_union_type;