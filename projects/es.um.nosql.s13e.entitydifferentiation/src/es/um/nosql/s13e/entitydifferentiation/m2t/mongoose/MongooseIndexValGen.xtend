package es.um.nosql.s13e.entitydifferentiation.m2t.mongoose

import es.um.nosql.s13e.entitydifferentiation.m2t.config.ConfigMongoose
import es.um.nosql.s13e.NoSQLSchema.EntityType
import es.um.nosql.s13e.entitydifferentiation.m2t.config.pojo.ConfigIndex
import java.util.ArrayList
import java.util.List
import java.util.HashMap
import es.um.nosql.s13e.entitydifferentiation.m2t.config.pojo.ConfigValidator

class MongooseIndexValGen
{
  ConfigMongoose config;

  new(ConfigMongoose config)
  {
    this.config = config;
  }

  def genIndexesForEntity(EntityType e)
  {
    if (config === null)
    ''''''
    else
    {
      val cEntity = config.entities.findFirst[ce | ce.name.equals(e.name)];
      if (cEntity === null || cEntity.indexes === null)
      ''''''
      else
      '''«FOR ConfigIndex i : cEntity.indexes SEPARATOR '\n'»«e.name».index(«genIndex(i)»);«ENDFOR»'''      
    }
  }

  def List<Pair<String, String>> genValidatorsForField(EntityType e, String field)
  {
    val result = new ArrayList<Pair<String, String>>();
    if (config === null)
      return result;

    val cEntity = config.entities.findFirst[ce | ce.name.equals(e.name)];
    if (cEntity !== null)
    {
      for (ConfigValidator v : cEntity.getValidatorsFor(field))
      {
        if (v.min !== null) result.add(new Pair("min", genValidatorValue(v.min.toString, v.message)));
        if (v.max !== null) result.add(new Pair("max", genValidatorValue(v.max.toString, v.message)));
        if (v.enumValues !== null) result.add(new Pair("enum", "[" + String.join(", ", v.enumValues.map[value | "'" + value + "'"]) + "]"));
        if (v.match !== null) result.add(new Pair("match", genValidatorValue(v.match, v.message)));
        if (v.minLength !== null) result.add(new Pair("minlength", genValidatorValue(v.minLength.toString, v.message)));
        if (v.maxLength !== null) result.add(new Pair("maxlength", genValidatorValue(v.maxLength.toString, v.message)));
        if (v.custom !== null) result.add(new Pair("validate", genValidatorValue(v.custom, null)))
      }
    }

    return result;
  }

  private def genValidatorValue(String value, String message)
  {
    if (message === null)
      value
    else
      "[" + value + ", \"" + message + "\"]"
  }

  private def genIndex(ConfigIndex index)
  '''«var count = 0»{«FOR String attr : index.attr SEPARATOR ', '»«attr»: «translateIndexType(index.type.get(count++))»«ENDFOR»}«genIndexOptions(index)»'''

  private def translateIndexType(String type)
  {
    switch type.toLowerCase
    {
      case "asc": "1"
      case "desc": "-1"
      case "hashed": "\"hashed\""
      case "text": "\"text\""
      case "geo2d": "\"2d\""
      case "geo2dsphere": "\"2dsphere\""
      default: ""
    }
  }

  private def genIndexOptions(ConfigIndex i)
  {
    val indexOptions = new HashMap<String, String>();
    if (i.unique !== null) indexOptions.put("unique", i.unique.toString);
    if (i.weight !== null)
    { // Yeah yeah I know, Diego probably knows how to zip two collections. I don't :(
      var concWeight = "{ ";
      for (var j = 0; j < i.attr.length; j++)
      {
        concWeight += i.attr.get(j) + ": " + i.weight.get(j);
        if (j !== i.attr.length -1)
          concWeight += ", ";
      }
      concWeight += " }";
      indexOptions.put("weights", concWeight);
    }
    if (i.background !== null) indexOptions.put("background", i.background.toString);
    if (i.sparse !== null) indexOptions.put("sparse", i.sparse.toString);
    if (i.name !== null) indexOptions.put("name", "\"" + i.name + "\"");
    if (i.partialFilter !== null) indexOptions.put("partialFilterExpression", i.partialFilter);
    if (i.default_language !== null) indexOptions.put("default_language", "\"" + i.default_language + "\"");
    if (i.language_override !== null) indexOptions.put("language_override", "\"" + i.language_override + "\"");
    if (i.expireAfterSeconds !== null) indexOptions.put("expireAfterSeconds", i.expireAfterSeconds.toString);

    if (indexOptions.isEmpty)
    ''''''
    else
    ''', {«FOR String option : indexOptions.keySet SEPARATOR ", "»«option»: «indexOptions.get(option)»«ENDFOR»}'''
  }
}
