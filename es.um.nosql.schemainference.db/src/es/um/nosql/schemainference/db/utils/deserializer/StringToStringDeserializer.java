package es.um.nosql.schemainference.db.utils.deserializer;

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
