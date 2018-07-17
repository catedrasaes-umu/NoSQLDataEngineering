package es.um.nosql.s13e.m2t.morphia

import es.um.nosql.s13e.m2t.config.ConfigMorphia
import es.um.nosql.s13e.NoSQLSchema.Entity
import es.um.nosql.s13e.m2t.config.pojo.ConfigIndex
import java.util.HashMap
import es.um.nosql.s13e.m2t.config.pojo.ConfigValidator

class MorphiaIndexValGen
{
  private ConfigMorphia config;

  public new(ConfigMorphia config)
  {
    this.config = config;
  }

  public def genIncludesForEntity(Entity e)
  {
    if (config === null)
    ''''''
    else
    {
      val cEntity = config.entities.findFirst[ce | ce.name.equals(e.name)];
      if (cEntity === null)
      ''''''
      else
      '''
      «IF cEntity.indexes !== null»
      import org.mongodb.morphia.annotations.Indexes;
      import org.mongodb.morphia.annotations.Index;
      import org.mongodb.morphia.annotations.Field;
      «IF cEntity.indexes.exists[i | i.type !== null]»import org.mongodb.morphia.utils.IndexType;«ENDIF»
      «IF cEntity.indexes.exists[i | hasOptionsDefined(i)]»import org.mongodb.morphia.annotations.IndexOptions;«ENDIF»
      «ENDIF»
      «IF cEntity.validators !== null»
        «IF cEntity.validators.exists[v | v.max !== null]»import javax.validation.constraints.Max;«ENDIF»
        «IF cEntity.validators.exists[v | v.min !== null]»import javax.validation.constraints.Min;«ENDIF»
        «IF cEntity.validators.exists[v | v.minLength !== null || v.maxLength !== null]»import javax.validation.constraints.Size;«ENDIF»
        «IF cEntity.validators.exists[v | v.enumValues !== null || v.match !== null || v.custom !== null]»import javax.validation.constraints.Pattern;«ENDIF»
      «ENDIF»'''
    }
  }

  public def genValidatorsForField(Entity e, String field)
  {
    if (config === null)
    ''''''
    else
    {
      val cEntity = config.entities.findFirst[ce | ce.name.equals(e.name)];
      if (cEntity === null)
      ''''''
      else
      '''
      «FOR ConfigValidator v : cEntity.getValidatorsFor(field)»
        «IF v.max !== null»@Max(value = «v.max»«genMsg(v)»)«ENDIF»
        «IF v.min !== null»@Min(value = «v.min»«genMsg(v)»)«ENDIF»
        «IF v.enumValues !== null»@Pattern(regexp = "«v.enumValues.join("|")»", flags = Pattern.Flag.CASE_INSENSITIVE«genMsg(v)»)«ENDIF»
        «IF v.match !== null»@Pattern(regexp = "«v.match»"«genMsg(v)»)«ENDIF»
        «IF v.custom !== null»@Pattern(regexp = "«v.custom»"«genMsg(v)»)«ENDIF»
        «IF v.minLength !== null && v.maxLength !== null»@Size(min = «v.minLength», max = «v.maxLength»«genMsg(v)»)
        «ELSEIF v.minLength !== null || v.maxLength !== null»@Size(«IF v.minLength !== null»min = «v.minLength»«ELSE»max = «v.maxLength»«ENDIF»«genMsg(v)»)
        «ENDIF»
      «ENDFOR»'''      
    }
  }

  public def genPopulateReferences(Entity e, String field)
  {
    if (config === null)
    ''', lazy = true'''
    else
    {
      val cEntity = config.entities.findFirst[ce | ce.name.equals(e.name)];
      if (cEntity === null || !cEntity.getPopulateReferencesFor(field))
      ''', lazy = true'''
    }
  }

  private def genMsg(ConfigValidator v)
  '''«IF v.message !== null», message = "«v.message»"«ENDIF»'''

  public def genIndexesForEntity(Entity e)
  {
    if (config === null)
    ''''''
    else
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
  }

  private def genIndex(ConfigIndex index)
  '''
  «var count = 0»
  @Index(fields = {«FOR String attr : index.attr SEPARATOR ', '»@Field(value = "«attr»", type = IndexType.«index.type.get(count).toUpperCase»«IF index.weight !== null», weight = «index.weight.get(count++)»«ENDIF»)«ENDFOR»}«genIndexOptions(index)»)
  '''

  private def genIndexOptions(ConfigIndex i)
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

  private def boolean hasOptionsDefined(ConfigIndex i)
  {
    return i.unique !== null || i.background !== null || i.sparse !== null || i.name !== null || i.disableValidation !== null
      || i.partialFilter !== null || i.expireAfterSeconds !== null || i.default_language !== null || i.language_override !== null;
  }
}