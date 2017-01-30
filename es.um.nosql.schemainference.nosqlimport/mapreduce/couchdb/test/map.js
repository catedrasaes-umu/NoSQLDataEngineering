var mapProcess = function(doc)
{
    function flatten_schema(obj, interesting_keys)
    {
        var retschema = '';

        function _search_string_in_array(str, strArray)
        {
            for (var j=0; j<strArray.length; j++)
                if (strArray[j].match(str))
                    return j;

            return -1;
        }

        function _complex_obj(obj, output_keys)
        {
            var _retschema = '';
            Object.keys(obj).sort().forEach(function (key)
            {
                if (output_keys)
                {
                    _retschema += key + ':';

                    if ((typeof interesting_keys !== 'undefined')
                        && (-1 !== _search_string_in_array(key, interesting_keys)))
                        _retschema += '"' + escape(obj[key]) + '"';
                }

                _retschema += flatten_schema(obj[key]);
            });

            return _retschema;
        };

        // Array
        if (obj instanceof Array)
        {
            retschema += '[' + _complex_obj(obj, false) + ']';
        } // null
        else if (obj === null)
        {
            retschema += '0';
        } // Date
        else if (obj instanceof Date)
        {
            retschema += 'd';
        }// Object
        else if ((typeof obj) === 'object')
        {
            retschema += '{' + _complex_obj(obj, true) + '}';
        }
        // Other
        else
        {
            // number, string, boolean
            retschema += (typeof obj)[0];
        }

        return retschema;
    }

    var schema = flatten_schema(doc,['type']);

//    emit(schema, {schema: schema, count: 1});
    return (schema, {schema: schema, count: 1});
}

var mapProcess2 = function(doc)
{
    function flatten_schema(obj, interesting_keys)
    {
        var retschema = '';

        function _search_string_in_array(str, strArray)
        {
            for (var j=0; j<strArray.length; j++)
                if (strArray[j].match(str))
                    return j;

            return -1;
        }

        function _decompose_array(array)
        {
            var _retschema = [];
            Object.keys(array).sort().forEach(function (key)
            {
                _retschema.push(flatten_schema(array[key]));
            });

            return _retschema;
        }

        function _decompose_object(obj)
        {
            var _retschema = '';
            Object.keys(obj).sort().forEach(function (key)
            {

                    _retschema += key + ':';

                    if ((typeof interesting_keys !== 'undefined')
                        && (-1 !== _search_string_in_array(key, interesting_keys)))
                        _retschema += '"' + escape(obj[key]) + '"';

                _retschema += flatten_schema(obj[key]);
            });

            return _retschema;
        };

        // Array
        if (obj instanceof Array)
        {
            retschema += '[' + _decompose_array(obj) + ']';
        } // null
        else if (obj === null)
        {
            retschema += '0';
        } // Date
        else if (obj instanceof Date)
        {
            retschema += 'd';
        }// Object
        else if ((typeof obj) === 'object')
        {
            retschema += '{' + _decompose_object(obj) + '}';
        }
        // Other
        else
        {
            // number, string, boolean
            retschema += (typeof obj)[0];
        }

        return retschema;
    }

    var schema = flatten_schema(doc,['type']);

//    emit(schema, {schema: schema, count: 1});
    return (schema, {schema: schema, count: 1});
}