package es.um.nosql.schemainference.m2t.mongoose

import es.um.nosql.schemainference.m2t.config.ConfigMongoose
import es.um.nosql.schemainference.NoSQLSchema.Entity
import es.um.nosql.schemainference.m2t.config.pojo.ConfigIndex
import java.util.HashMap

class MongooseIndexGen
{
  ConfigMongoose config;

  new(ConfigMongoose config)
  {
    this.config = config;
  }

  def genIndexesForEntity(Entity e)
  {
    val cEntity = config.entities.findFirst[ce | ce.name.equals(e.name)];
    if (cEntity === null || cEntity.indexes === null)
    ''''''
    else
    '''«FOR ConfigIndex i : cEntity.indexes SEPARATOR '\n'»«e.name»Schema.index(«genIndex(i)»);«ENDFOR»'''
  }

  private def genIndex(ConfigIndex index)
  '''«var count = 0»{«FOR String attr : index.attr SEPARATOR ', '»«attr»: «translateIndexType(index.type.get(count))»«ENDFOR»}«genIndexOptions(index)»'''

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
    //if (i.disableValidation !== null) indexOptions.put("disableValidation", i.disableValidation.toString); // Seems like Mongoose does not have this option available...
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