package es.um.nosql.s13e.design.services.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import es.um.nosql.s13e.NoSQLSchema.Classifier;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.util.compare.CompareProperty;

public class PropertyCollector
{
  private CompareProperty propComparer;

  public PropertyCollector()
  {
    propComparer = new CompareProperty();
  }

  public <T extends Property> List<T> getUnionProperties(Classifier classifier, Class<T> theClass)
  {
    List<T> result = new ArrayList<T>();

    classifier.getVariations().forEach(var ->
    {
      var.getProperties().stream().filter(prop -> theClass.isInstance(prop)).forEach(prop ->
      {
        if (result.stream().noneMatch(prop2 -> propComparer.compare(prop, prop2)))
          result.add(theClass.cast(prop));
      });
    });

    result.sort((prop1, prop2) -> prop1.getName().compareTo(prop2.getName()));

    return result;
  }

  public <T extends Property> List<T> getCommonProperties(Classifier classifier, Class<T> theClass)
  {
    List<T> result = new ArrayList<T>();

    if (!classifier.getVariations().isEmpty())
    {
      result.addAll(classifier.getVariations().get(0).getProperties().stream()
          .filter(prop -> theClass.isInstance(prop))
          .map(prop -> theClass.cast(prop))
          .collect(Collectors.toList()));

      if (classifier.getVariations().size() > 1)
      {
        result = result.stream().filter(prop ->
        {
          return classifier.getVariations().stream().skip(1).allMatch(var ->
          {
            return var.getProperties().stream().anyMatch(prop2 -> propComparer.compare(prop, prop2));
          });
        }).collect(Collectors.toList());
      }
    }

    result.sort((prop1, prop2) -> prop1.getName().compareTo(prop2.getName()));

    return result;
  }

  public <T extends Property> List<T> getParticularProperties(StructuralVariation var, Class<T> theClass)
  {
    List<T> result = new ArrayList<T>();
    List<T> commonProps = getCommonProperties((Classifier)var.eContainer(), theClass);

    for (Property prop : var.getProperties())
      if (theClass.isInstance(prop))
        result.add(theClass.cast(prop));

    result = result.stream().filter(prop1 ->
    {
      return commonProps.stream().noneMatch(prop2 ->
      {
        return propComparer.compare(prop1, prop2);
      });
    }).collect(Collectors.toList());

    result.sort((prop1, prop2) -> prop1.getName().compareTo(prop2.getName()));

    return result;
  }
}
