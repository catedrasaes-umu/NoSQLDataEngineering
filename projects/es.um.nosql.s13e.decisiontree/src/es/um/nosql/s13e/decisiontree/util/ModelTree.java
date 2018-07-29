package es.um.nosql.s13e.decisiontree.util;

import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.NoSQLSchema.Property;

public class ModelTree
{
  private EntityClass entity;
  private StructuralVariation tag;
  private Property property;
  private ModelTree nodePresent;
  private ModelTree nodeAbsent;

  public ModelTree(EntityClass e, StructuralVariation tag)
  {
    this.tag = tag;
    this.entity = e;
  }

  public ModelTree(Property property)
  {
    this.property = property;
  }

  public void setEntityClass(EntityClass entity)
  {
    this.entity = entity;
  }

  public EntityClass getEntityClass()
  {
    return entity;
  }

  public void setTag(StructuralVariation tag)
  {
    this.tag = tag;
  }

  public StructuralVariation getTag()
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
