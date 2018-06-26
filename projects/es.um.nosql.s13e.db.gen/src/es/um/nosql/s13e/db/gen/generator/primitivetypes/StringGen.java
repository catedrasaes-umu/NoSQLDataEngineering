package es.um.nosql.s13e.db.gen.generator.primitivetypes;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import es.um.nosql.s13e.db.gen.utils.constants.ConfigConstants;
import es.um.nosql.s13e.db.gen.utils.constants.FixedConstants;

public class StringGen
{
  private static StringGen THE_INSTANCE;

  private NumberGen numGen;
  private List<String> names;
  private List<String> surnames;
  private List<String> words;
  private char[] nonsense;

  private StringGen()
  {
    numGen = NumberGen.GET_INSTANCE();
    nonsense = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
        ',','.','-',';',':','_','!','"','·','$','%','&','/','(',')','=','?','¡','¿','º','ª','\\','|','@','#','~','½','¬','{','[',']','}',
        '`','^','+','*','´','ç'};
  }

  public static StringGen GET_INSTANCE()
  {
    if (THE_INSTANCE == null)
      THE_INSTANCE = new StringGen();

    return THE_INSTANCE;
  }

  public String getRandomString()
  {
    switch (numGen.getExclusiveRandom(0, FixedConstants.GET_RANDOM_FUNCTIONS_COUNT()))
    {
      case 0: return this.getRandomName();
      case 1: return this.getRandomSurname();
      case 2: return this.getRandomWord();
      case 3: return this.getRandomPhrase();
      case 4: return this.getRandomWordNumber();
      case 5: return this.getRandomNonsense();
      case 6: return this.getRandomFullname();
      case 7: return this.getRandomLargeString();
      default: return this.getRandomString();
    }
  }

  public String getRandomName()
  {
    if (names == null)
    {
      try
      {
        names = Files.readAllLines(new File(ConfigConstants.GET_PRIMITIVE_TYPES_NAMES_FILE()).toPath(), StandardCharsets.UTF_8);
      } catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    return names.get(numGen.getExclusiveRandom(0, names.size()));
  }

  public String getRandomSurname()
  {
    if (surnames == null)
    {
      try
      {
        surnames = Files.readAllLines(new File(ConfigConstants.GET_PRIMITIVE_TYPES_SURNAMES_FILE()).toPath(), StandardCharsets.UTF_8);
      } catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    return surnames.get(numGen.getExclusiveRandom(0, surnames.size()));
  }

  public String getRandomFullname()
  {
    return this.getRandomName() + " " + this.getRandomSurname();
  }

  public String getRandomWord()
  {
    if (words == null)
    {
      try
      {
        words = Files.readAllLines(new File(ConfigConstants.GET_PRIMITIVE_TYPES_WORDS_FILE()).toPath(), StandardCharsets.UTF_8);
      } catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    return words.get(numGen.getExclusiveRandom(0, words.size()));
  }

  public String getRandomPhrase()
  {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < numGen.getInclusiveRandom(FixedConstants.GET_MIN_WORDS_IN_PHRASE(), FixedConstants.GET_MAX_WORDS_IN_PHRASE()); i++)
      result.append(this.getRandomWord() + " ");

    return result.toString();
  }

  public String getRandomWordNumber()
  {
    return this.getRandomWord() + "_" + numGen.getExclusiveRandom(0, ConfigConstants.GET_PRIMITIVE_TYPES_MAX_INT_ALLOWED());
  }

  public String getRandomNonsense()
  {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < numGen.getInclusiveRandom(FixedConstants.GET_MIN_NONSENSE_CHARS(), FixedConstants.GET_MAX_NONSENSE_CHARS()); i++)
      result.append(this.nonsense[numGen.getExclusiveRandom(0, nonsense.length)]);

    return result.toString();
  }

  public String getRandomLargeString()
  {
    StringBuilder result = new StringBuilder();

    while (result.length() < FixedConstants.GET_LARGE_STRING_LENGTH())
      result.append(this.getRandomPhrase());

    return result.toString();
  }
}