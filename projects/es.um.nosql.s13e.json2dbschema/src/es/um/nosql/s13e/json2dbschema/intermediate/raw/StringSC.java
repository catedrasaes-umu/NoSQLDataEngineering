package es.um.nosql.s13e.json2dbschema.intermediate.raw;

/**
 * @author dsevilla
 *
 */
public class StringSC extends SchemaComponent
{
  // The type stores the string value. It might be just "String", but it could be the _type value.
  private String value;

  public StringSC(String value)
  {
    this.value = value;
  }

  public String getValue()
  {
    return this.value;
  }
}
