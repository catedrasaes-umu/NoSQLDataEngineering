package es.um.nosql.s13e.db.gen.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import es.um.nosql.s13e.db.gen.constants.Constants;

public class StringGenerator
{
  private static StringGenerator THE_INSTANCE;

  private static int RANDOM_FUNCTIONS_COUNT = 8;

  private List<String> names;
  private List<String> surnames;
  private List<String> words;

  private StringGenerator()
  {
  }

  public static StringGenerator GET_INSTANCE()
  {
    if (THE_INSTANCE == null)
      THE_INSTANCE = new StringGenerator();

    return THE_INSTANCE;
  }

  public String getRandomString()
  {
    switch (ThreadLocalRandom.current().nextInt(0, RANDOM_FUNCTIONS_COUNT))
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
        names = Files.readAllLines(new File(Constants.GET_STRING_NAMES_FILE()).toPath(), StandardCharsets.UTF_8);
      } catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    return names.get(ThreadLocalRandom.current().nextInt(names.size()));
  }

  public String getRandomSurname()
  {
    if (surnames == null)
    {
      try
      {
        surnames = Files.readAllLines(new File(Constants.GET_STRING_SURNAMES_FILE()).toPath(), StandardCharsets.UTF_8);
      } catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    return surnames.get(ThreadLocalRandom.current().nextInt(surnames.size()));
  }

  public String getRandomWord()
  {
    if (words == null)
    {
      try
      {
        words = Files.readAllLines(new File(Constants.GET_STRING_WORDS_FILE()).toPath(), StandardCharsets.UTF_8);
      } catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    return words.get(ThreadLocalRandom.current().nextInt(words.size()));
  }

  public String getRandomPhrase()
  {//TODO:
    return "random phrase";
  }

  public String getRandomWordNumber()
  {
    return this.getRandomWord() + "_" + ThreadLocalRandom.current().nextInt(Constants.GET_CONFIGURE_MAX_INT_ALLOWED());
  }

  public String getRandomNonsense()
  {//TODO:
    return "!\"·$%&/";
  }

  public String getRandomFullname()
  {
    return this.getRandomName() + " " + this.getRandomSurname();
  }

  public String getRandomLargeString()
  {//TODO:
    return "random large string";
  }
}