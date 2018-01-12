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
    '''
      «FOR ConfigIndex i : cEntity.indexes SEPARATOR '\n'»
        «e.name»Schema.index(«genIndex(i)»);
      «ENDFOR»
    '''
  }

  private def genIndex(ConfigIndex index)//TODO: We need also to translate the index type to mongoose. Ej: ASC is 1, DESC is -1.
  '''«var count = 0»{«FOR String attr : index.attr SEPARATOR ', '»«attr»: «index.type.get(count++).toUpperCase»«ENDFOR»}«genIndexOptions(index)»'''

  private def genIndexOptions(ConfigIndex i)
  {//TODO: Need to check if these options do actually work...
    val indexOptions = new HashMap<String, String>();
    if (i.unique !== null) indexOptions.put("unique", i.unique.toString);
    if (i.background !== null) indexOptions.put("background", i.background.toString);
    if (i.disableValidation !== null) indexOptions.put("disableValidation", i.disableValidation.toString);
    if (i.sparse !== null) indexOptions.put("sparse", i.sparse.toString);
    if (i.name !== null) indexOptions.put("name", "\"" + i.name + "\"");
    if (i.partialFilter !== null) indexOptions.put("partialFilter", "\"" + i.partialFilter + "\"");
    if (i.default_language !== null) indexOptions.put("language", "\"" + i.default_language + "\"");
    if (i.language_override !== null) indexOptions.put("languageOverride", "\"" + i.language_override + "\"");
    if (i.expireAfterSeconds !== null) indexOptions.put("expireAfterSeconds", i.expireAfterSeconds.toString);

    if (indexOptions.isEmpty)
    ''''''
    else
    ''', {«FOR String option : indexOptions.keySet SEPARATOR ", "»«option»: «indexOptions.get(option)»«ENDFOR»}'''
  }
}