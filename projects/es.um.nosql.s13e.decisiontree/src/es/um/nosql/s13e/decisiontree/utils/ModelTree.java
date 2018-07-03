package es.um.nosql.s13e.decisiontree.utils;

import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVariation;
import es.um.nosql.s13e.NoSQLSchema.Property;

public class ModelTree
{
  private Property property;
  private EntityVariation tag;
  private ModelTree nodePresent;
  private ModelTree nodeAbsent;
  private Entity entity;

  public ModelTree(Entity e, EntityVariation tag)
  {
    super();
    this.tag = tag;
    this.entity = e;
  }

  public Entity getEntity()
  {
    return entity;
  }

  public void setEntity(Entity entity)
  {
    this.entity = entity;
  }

  public ModelTree(Property property)
  {
    super();
    this.property = property;
  }

  public Property getProperty()
  {
    return property;
  }

  public void setProperty(Property property)
  {
    this.property = property;
  }

  public EntityVariation getTag()
  {
    return tag;
  }

  public void setTag(EntityVariation tag)
  {
    this.tag = tag;
  }

  public ModelTree getNodePresent()
  {
    return nodePresent;
  }

  public void setNodePresent(ModelTree nodePresent)
  {
    this.nodePresent = nodePresent;
  }

  public ModelTree getNodeAbsent()
  {
    return nodeAbsent;
  }

  public void setNodeAbsent(ModelTree nodeAbsent)
  {
    this.nodeAbsent = nodeAbsent;
  }

  public boolean is_leaf()
  {
    return nodePresent == null && nodeAbsent == null;
  }
}
