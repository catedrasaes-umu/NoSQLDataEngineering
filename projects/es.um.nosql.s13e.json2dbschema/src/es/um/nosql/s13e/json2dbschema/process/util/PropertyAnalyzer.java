package es.um.nosql.s13e.json2dbschema.process.util;

import java.util.ArrayList;
import java.util.List;

import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.util.compare.CompareProperty;

public class PropertyAnalyzer
{
  public void setOptionalProperties(List<StructuralVariation> variations)
  {
    // This comparer will tell us if two properties are the same based on the formal Property comparator
    CompareProperty comparer = new CompareProperty();

    // At most each property of the first variation will be a common property.
    List<Property> commonProps = new ArrayList<Property>();
    commonProps.addAll(variations.get(0).getProperties());
    List<Property> optionalProps = new ArrayList<Property>();

    // This loop will reduce the list of common properties checking if each property is actually common to all variations
    for (Property prop : commonProps)
      for (StructuralVariation var : variations)
        if (var.getProperties().stream().noneMatch(varProp -> { return comparer.compare(prop, varProp); }))
        {
          optionalProps.add(prop);
          break;
        }

    // Now for sure we have all common properties on this list.
    commonProps.removeAll(optionalProps);

    // For each property, if it is not on the common properties list, flag it as optional.
    for (StructuralVariation var: variations)
      var.getProperties().forEach(prop ->
      {
        if (!commonProps.stream().anyMatch(cProp -> { return comparer.compare(prop, cProp); }))
          prop.setOptional(true);
        else
          prop.setOptional(false);
      });
  }
}
