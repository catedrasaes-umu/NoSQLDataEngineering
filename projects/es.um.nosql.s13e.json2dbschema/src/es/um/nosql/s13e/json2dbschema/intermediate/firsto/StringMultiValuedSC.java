package es.um.nosql.s13e.json2dbschema.intermediate.firsto;

import java.util.HashSet;
import java.util.Set;

import es.um.nosql.s13e.json2dbschema.intermediate.raw.StringSC;

/**
 * @author dsevilla
 *
 */
public class StringMultiValuedSC extends StringSC implements MultiValued
{
  Set<String> values;

  public StringMultiValuedSC(String string)
  {
    super();
    values = new HashSet<String>();
  }

  @Override
  public Set<String> getValues()
  {
    return values;
  }
}
