package es.um.nosql.s13e.db.util.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class StringToStringDeserializer extends JsonDeserializer<String>
{
  @Override
  public String deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException
  {
    if (parser.getText().isEmpty())
      return null;
    else
      return parser.getValueAsString();
  }
}
