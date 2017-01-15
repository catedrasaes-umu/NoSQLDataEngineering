function (key, values)
{
    var v = values.reduce(function (v1, v2)
                          {
                              return {schema: key,
                                      count: v1.count + v2.count};
                          },
                          {schema: key, count: 0});
    return v;
}
