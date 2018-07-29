package es.um.nosql.s13e.decisiontree.util;

import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.EntityDifferentiation.PropertySpec;

public class ModelNode
{
  private StructuralVariation sv;
  private PropertySpec property;
  private ModelNode nodePresent;
  private ModelNode nodeAbsent;
  private boolean checkNot;

  public ModelNode(StructuralVariation tag)
  {
    setEv(tag);
    setCheckNot(false);
  }

  public ModelNode(PropertySpec property, boolean checkNot)
  {
    setProperty(property);
    setCheckNot(checkNot);
  }

  public void setProperty(PropertySpec property)
  {
    this.property = property;
  }

  public PropertySpec getProperty()
  {
    return property;
  }

  public void setEv(StructuralVariation tag)
  {
    this.sv = tag;
  }

  public StructuralVariation getSv()
  {
    return sv;
  }

  public void setNodePresent(ModelNode nodePresent)
  {
    this.nodePresent = nodePresent;
  }

  public ModelNode getNodePresent()
  {
    return nodePresent;
  }

  public void setNodeAbsent(ModelNode nodeAbsent)
  {
    this.nodeAbsent = nodeAbsent;
  }

  public ModelNode getNodeAbsent()
  {
    return nodeAbsent;
  }

  public void setCheckNot(boolean checkNot)
  {
    this.checkNot = checkNot;
  }

  public boolean isCheckNot()
  {
    return checkNot;
  }

  public boolean isLeaf()
  {
    return nodePresent == null && nodeAbsent == null;
  }
}
