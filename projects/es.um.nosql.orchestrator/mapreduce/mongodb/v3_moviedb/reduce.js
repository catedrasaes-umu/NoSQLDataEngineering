function reduce(key, values)
{
  var v = values.reduce(function (v1, v2)
    {
      return {schema: v2.schema,
              count: v1.count + v2.count,
              firstTimestamp: Math.min(v1.firstTimestamp, v2.firstTimestamp),
              lastTimestamp: Math.max(v1.lastTimestamp, v2.lastTimestamp)
            };
    }, {schema: null, count: 0, firstTimestamp: new Date().getTime(), lastTimestamp: 0});

  return v;
}
