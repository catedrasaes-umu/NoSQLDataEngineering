package es.um.nosql.s13e.design.services;

import java.util.List;

import es.um.nosql.s13e.NoSQLSchema.Association;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.Null;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.design.services.util.PropertyCollector;
import es.um.nosql.s13e.design.services.util.SchemaCollector;

public class StructuralVariationServices
{
  private PropertyCollector propCollector;

  public StructuralVariationServices()
  {
    propCollector = new PropertyCollector();
  }

  public List<StructuralVariation> getEVariationsFromSchema(StructuralVariation root)
  {
    List<StructuralVariation> result = SchemaCollector.getEVariationsFromSchema(root);
    result.sort((var1, var2) ->
    {
      int compareTo = var1.getContainer().getName().compareTo(var2.getContainer().getName());

      return compareTo != 0 ? compareTo : (var1.getVariationId() > var2.getVariationId() ? 1 : -1);
    });

    return result;
  }

  public List<Attribute> getParticularAttributesFromVar(StructuralVariation var)
  {
    return propCollector.getParticularProperties(var, Attribute.class);
  }

  public List<Association> getParticularAssociationsFromVar(StructuralVariation var)
  {
    return propCollector.getParticularProperties(var, Association.class);
  }

  public List<Null> getParticularNullsFromVar(StructuralVariation var)
  {
    return propCollector.getParticularProperties(var, Null.class);
  }
}
