function (documents)
{
    function processElement(value, interesting_keys)
    {
    	if (value instanceof Array)
            return flattenArray(value);
        else if ((typeof value) === 'object')
            return flattenObject(value, interesting_keys);
        else if ((typeof interesting_keys !== 'undefined')
                && (-1 !== _search_string_in_array(key, interesting_keys)))
            return escape(value);
        else
            return flattenValue(value);
    }

    function flattenObject(object, interesting_keys)
    {
        var result = {};
        for (key in object)
        {
            result[key] = processElement(object[key], interesting_keys);
        }
        
        return result;
    }

    function flattenArray(value)
    {
    	var result = [];
    
    	if (value.length > 0)
    	{
    		var index = 0;
    		
    		var prototype = flattenElement(value[0]);
    		
    		// Take the first object as prototype
    		value.every(function(e) { return e === prototype;})
    	}
    	
    	return result;
    }
    
    function flattenValue(value)
    {
        if (value === null)
            return '0';
        else if (value instanceof Date)
            return 'd';
        else
            return (typeof value)[0];
    }

    var schema = JSON.stringify(flattenObject(documents));

    emit(schema, {schema: schema, count: 1});
}
