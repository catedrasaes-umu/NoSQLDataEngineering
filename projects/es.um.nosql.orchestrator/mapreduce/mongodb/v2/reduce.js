function reduce(key, values)
{
  var v = values.reduce(function (v1, v2)
    {
      return {schema: v2.schema,
              count: v1.count + v2.count,
              timestamp: Math.min(v1.timestamp, v2.timestamp)
            };
    }, {schema: null, count: 0, timestamp: new Date().getTime()});

  return v;
}
