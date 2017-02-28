function (key, values)
{
    var v = values.reduce(function (v1, v2)
                          {
                              return {schema: v2.schema,
                                      count: v1.count + v2.count};
                          },
                          {schema: null, count: 0});
    return v;
}
