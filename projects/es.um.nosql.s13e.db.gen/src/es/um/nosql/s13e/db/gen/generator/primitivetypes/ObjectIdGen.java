package es.um.nosql.s13e.db.gen.generator.primitivetypes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.bson.types.ObjectId;

import es.um.nosql.s13e.db.gen.util.constants.ConfigConstants;

public class ObjectIdGen
{
  private static ObjectIdGen THE_INSTANCE;

  private BooleanGen boolGen;
  private NumberGen numGen;
  private Calendar calendar;

  private ObjectIdGen()
  {
    boolGen = BooleanGen.GET_INSTANCE();
    numGen = NumberGen.GET_INSTANCE();
    calendar = Calendar.getInstance();
    try
    {
      calendar.setTime(new SimpleDateFormat(ConfigConstants.GET_DATEFORMAT()).parse(ConfigConstants.GET_INITIAL_TIMESTAMP()));
    } catch (ParseException e)
    {
      e.printStackTrace();
    }
  }

  public static ObjectIdGen GET_INSTANCE()
  {
    if (THE_INSTANCE == null)
      THE_INSTANCE = new ObjectIdGen();

    return THE_INSTANCE;
  }

  public ObjectId getRandomObjectId()
  {
    return new ObjectId();
  }

  public ObjectId getTimestampObjectId()
  {
    return getTimestampObjectId(getNextDate());
  }

  public ObjectId getTimestampObjectId(Date date)
  {
    return new ObjectId(date);
  }

  public Object getNull()
  {
    return null;
  }

  private Date getNextDate()
  {
    int number = ConfigConstants.GET_INTERVAL_NUMBER();
    if (boolGen.thisHappens(ConfigConstants.GET_RANDOM_INTERVAL_PROBABILITY()))
    {
      // 50% chance the result will be added
      if (boolGen.getRandomBoolean())
        number += numGen.getInclusiveRandom(0, new Double(ConfigConstants.GET_RANDOM_INTERVAL_MARGIN() * number).intValue());
      // 50% chance the result will be substracted
      else
        number -= numGen.getInclusiveRandom(0, new Double(ConfigConstants.GET_RANDOM_INTERVAL_MARGIN() * number).intValue());
    }

    // Yeah, because apparently calendar is NOT thread safe, and memory corruption does happen...: (
    synchronized(calendar)
    {
      switch (ConfigConstants.GET_INTERVAL_UNITS())
      {
        case "second": case "seconds": {calendar.add(Calendar.SECOND, number); break;}
        case "minute": case "minutes": {calendar.add(Calendar.MINUTE, number); break;}
        case "hour": case "hours": {calendar.add(Calendar.HOUR_OF_DAY, number); break;}
        case "day": case "days": {calendar.add(Calendar.DAY_OF_MONTH, number); break;}
        case "month": case "months": {calendar.add(Calendar.MONTH, number); break;}
        case "year": case "years": {calendar.add(Calendar.YEAR, number); break;}
      }
      return calendar.getTime();
    }
  }
}