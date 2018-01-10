package es.um.nosql.schemainference.m2t.morphia

import es.um.nosql.schemainference.m2t.config.ConfigMorphia
import es.um.nosql.schemainference.NoSQLSchema.Entity
import es.um.nosql.schemainference.m2t.config.pojo.ConfigIndex
import es.um.nosql.schemainference.m2t.config.pojo.ConfigEntity
import java.util.HashMap

class MorphiaIndexGen
{
  ConfigMorphia config;

  new(ConfigMorphia config)
  {
    this.config = config;
  }

  def genIncludesForEntity(Entity e)
  {
    val cEntity = config.entities.findFirst[ce | ce.name.equals(e.name)];
    if (cEntity === null || cEntity.indexes === null)
    ''''''
    else
    '''
    import org.mongodb.morphia.annotations.Indexes;
    import org.mongodb.morphia.annotations.Index;
    import org.mongodb.morphia.annotations.Field;
    «IF hasIndexTypesDefined(cEntity)»import org.mongodb.morphia.utils.IndexType;«ENDIF»
    «IF hasIndexOptionsDefined(cEntity)»import org.mongodb.morphia.annotations.IndexOptions;«ENDIF»
  '''
  }

  def genIndexesForEntity(Entity e)
  {
    val cEntity = config.entities.findFirst[ce | ce.name.equals(e.name)];
    if (cEntity === null || cEntity.indexes === null)
    ''''''
    else
    '''
    @Indexes({
      «FOR ConfigIndex i : cEntity.indexes SEPARATOR ','»
        «genIndex(i)»
      «ENDFOR»
    })
    '''
  }

  def genIndex(ConfigIndex index)
  '''
  «var count = 0»
  @Index(fields = {«FOR String attr : index.attr SEPARATOR ', '»@Field(value = "«attr»"«IF index.type !== null», type = IndexType.«index.type.get(count++).toUpperCase»«ENDIF»«IF index.weight !== null», weight = «index.weight»«ENDIF»)«ENDFOR»}«genIndexOptions(index)»)
  '''

  def genIndexOptions(ConfigIndex i)
  {
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
    ''', options = @IndexOptions(«FOR String option : indexOptions.keySet SEPARATOR ", "»«option» = «indexOptions.get(option)»«ENDFOR»)'''
  }

  private def boolean hasIndexTypesDefined(ConfigEntity e)
  {
    return e.indexes !== null && e.indexes.exists[i | i.type !== null];
  }

  private def boolean hasIndexOptionsDefined(ConfigEntity e)
  {
    return e.indexes !== null && e.indexes.exists[i | hasOptionsDefined(i)];
  }

  private def boolean hasOptionsDefined(ConfigIndex i)
  {
    return i.unique !== null || i.background !== null || i.sparse !== null || i.name !== null || i.disableValidation !== null
      || i.partialFilter !== null || i.expireAfterSeconds !== null || i.weight !== null || i.default_language !== null || i.language_override !== null
      /*|| i.textIndexVersion !== null || i.geo2dsphereIndexVersion !== null || i.bits !== null || i.min !== null || i.max !== null*/;
      //TODO We have to remove geo2d index options until we learn where to put them on generation
  }
}