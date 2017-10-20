package es.um.nosql.schemainference.mongoMovies3;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;

import es.um.nosql.schemainference.db.adapters.mongodb.MongoDbAdapter;
import mongoMovies3.entities.Movietheater;

public class Main {

  public static void main(String[] args)
  {
    Morphia morphia = new Morphia();
    morphia.mapPackage("mongoMovies3");
    Datastore datastore = morphia.createDatastore(MongoDbAdapter.getMongoDbClient("localhost"), "morphia_example");
    datastore.ensureIndexes();

    Movietheater dawg = new Movietheater();
    dawg.setName("yodawg");
    dawg.setCountry("country");
    dawg.setCity("maCityNigah");
    dawg.setNoOfRooms(3);

    ValidatorFactory factory = new ValidationExtension(morphia).getValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<Movietheater>> violations = validator.validate(dawg);
    System.out.println(violations.size() + " errors");
    for (ConstraintViolation<Movietheater> violation : violations)
      System.out.println(violation.getMessage());
    if (violations.isEmpty())
      datastore.save(dawg);
    else
      for (ConstraintViolation<Movietheater> violation : violations)
        System.out.println(violation.getMessage());
  }
}
