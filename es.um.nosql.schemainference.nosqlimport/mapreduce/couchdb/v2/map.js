function (documents)
{
    function flattenObject(object)
    {
        var result = {};
        for (key in object)
        {
            var value = object[key];

            if (value instanceof Array)
                result[key] = flattenArray(value);
            else if ((typeof value) === 'object')
                result[key] = flattenObject(value);
            else if (key.match('type'))
                result[key] = escape(value);
            else
                result[key] = flattenValue(value);
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

    var schema = flattenObject(documents);

    emit(schema, {schema: schema, count: 1});
}
