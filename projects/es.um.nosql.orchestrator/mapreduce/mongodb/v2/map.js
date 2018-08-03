function map()
{
  function TimestampAnalyzer()
  {
    var _format    = "";
    var _attrName  = "timestamp";
    var _value     = null;
  };

  TimestampAnalyzer.prototype.getAttrName = function()
  {
    return this._attrName;
  };

  TimestampAnalyzer.prototype.getAttrValue = function()
  {
    if (this._value === null)
      return new Date().getTime();
    else
      return this._value;
  };

  TimestampAnalyzer.prototype.setAttrValue = function(attrValue)
  {
    this._value = this.attrValue;
  };

  TimestampAnalyzer.prototype.analyzeAttribute = function(attrName, attrValue)
  {
    if (attrName === this.getAttrName())
      setAttrValue(attrValue);
  };

  var tsAnalyzer = new TimestampAnalyzer();

  function flatten_schema_str(obj, interesting_keys)
  {
    return JSON.stringify(flatten_schema(obj, interesting_keys));
  }

  function flatten_schema(obj, interesting_keys)
  {
    var retschema = null;

    function _search_string_in_array(str, strArray)
    {
      return !strArray.every(function (s)
      {
        return !s.toLowerCase().match(str);
      });
    }

    function _complex_obj(obj, asObject)
    {
      var _retschema;
      if (asObject)
        _retschema = {};
      else
        _retschema = [];

      Object.keys(obj).sort().forEach(function (key)
      {
        tsAnalyzer.analyzeAttribute(key, obj[key]);

        //if (!obj.hasOwnProperty(key))
        //    return;
        if (asObject)
        {
          if ((typeof interesting_keys !== 'undefined')
            && _search_string_in_array(key, interesting_keys))
            _retschema[key] = obj[key];
          else
            _retschema[key] = flatten_schema(obj[key]);
        }
        else
          _retschema.push(flatten_schema(obj[key]));
      });

      return _retschema;
    };

    // Array
    if (Array.isArray(obj))
    {
      if (obj.length == 0)
        retschema = [];
      else
      {
        // See if we can produce just one array object with one inside type (homogeneous)
        var schemas = obj.map(function (e) { return flatten_schema(e); });

        var str_schema_0 = JSON.stringify(schemas[0]);
        if (obj.length == 1 || schemas.every(function (e) { return JSON.stringify(e) == str_schema_0; }))
          retschema = [ schemas[0] ];
        else
          retschema = schemas;
      }
    } // null
    else if (obj === null)
    {
      retschema = null;
    } // Date
    else if (obj instanceof Date)
    {
      retschema = new Date();
    } // Object
    else if ((typeof obj) == 'object')
    {
      if (obj instanceof ObjectId)
        retschema = "oid";
      else
        retschema = _complex_obj(obj, true);
    }
    // Other
    else if ((typeof obj) == 'number')
    {
      retschema = 0;
    } else if ((typeof obj == 'boolean'))
    {
      retschema = true;
    } else if ((typeof obj == 'string'))
    {
      retschema = "string";
    }

    return retschema;
  }

  var schema = flatten_schema_str(this, []);

  emit(schema, {schema: schema, count: 1, timestamp: tsAnalyzer.getAttrValue()});
}