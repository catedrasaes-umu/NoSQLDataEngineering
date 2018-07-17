package es.um.nosql.s13e.decisiontree.util;

import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVariation;
import es.um.nosql.s13e.NoSQLSchema.Property;

public class ModelTree
{
  private Entity entity;
  private EntityVariation tag;
  private Property property;
  private ModelTree nodePresent;
  private ModelTree nodeAbsent;

  public ModelTree(Entity e, EntityVariation tag)
  {
    this.tag = tag;
    this.entity = e;
  }

  public ModelTree(Property property)
  {
    this.property = property;
  }

  public void setEntity(Entity entity)
  {
    this.entity = entity;
  }

  public Entity getEntity()
  {
    return entity;
  }

  public void setTag(EntityVariation tag)
  {
    this.tag = tag;
  }

  public EntityVariation getTag()
  {
    return tag;
  }

  public void setProperty(Property property)
  {
    this.property = property;
  }

  public Property getProperty()
  {
    return property;
  }

  public void setNodePresent(ModelTree nodePresent)
  {
    this.nodePresent = nodePresent;
  }

  public ModelTree getNodePresent()
  {
    return nodePresent;
  }

  public void setNodeAbsent(ModelTree nodeAbsent)
  {
    this.nodeAbsent = nodeAbsent;
  }

  public ModelTree getNodeAbsent()
  {
    return nodeAbsent;
  }

  public boolean isLeaf()
  {
    return nodePresent == null && nodeAbsent == null;
  }
}
