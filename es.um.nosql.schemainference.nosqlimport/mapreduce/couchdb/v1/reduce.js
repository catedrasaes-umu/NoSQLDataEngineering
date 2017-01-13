function (key, values, rereduce)
{
    if (rereduce)
	return values;
    else
    {
        v = values.reduce(function (v1, v2)
                          {
                              return {schema: v1.schema,
                                      count: v1.count + v2.count};
                          },
                          {schema: key, count: 0});
	return v;
    }
}
