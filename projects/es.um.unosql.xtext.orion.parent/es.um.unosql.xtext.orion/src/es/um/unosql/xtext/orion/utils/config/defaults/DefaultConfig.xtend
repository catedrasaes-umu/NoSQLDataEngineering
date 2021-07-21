package es.um.unosql.xtext.orion.utils.config.defaults

import java.util.Properties
import java.io.IOException

class DefaultConfig
{
  static final String PROPERTIES_FILE_NAME = "config.properties";
  static val properties =
  {
    val result = new Properties()
    val inputStream = DefaultConfig.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);

    try
    {
      result.load(inputStream);
    } catch (IOException e)
    {
      e.printStackTrace();
    }

    result
  }

  def static Properties getDefaultConfig()
  {
    return properties;
  }
}
