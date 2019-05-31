package es.um.nosql.s13e.evolution.analyzer.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbClient;
import es.um.nosql.s13e.evolution.types.EntitySubtype;
import es.um.nosql.s13e.util.compare.CompareProperty;

public class DbDiscriminatorSeeker
{
  private MongoDbClient client;
  private String dbName;
  private String collName;
  private List<EntitySubtype> subtypes;
  private List<Attribute> candidates;
  private CompareProperty pComparer;

  public DbDiscriminatorSeeker(String dbName, String collName, List<EntitySubtype> subtypes, List<Attribute> candidates)
  {
    this.client = MongoDbAdapter.getMongoDbClient("localhost");
    this.dbName = dbName;
    this.collName = collName;
    this.subtypes = subtypes;
    this.candidates = candidates;
    pComparer = new CompareProperty();
  }

  public Property getDiscriminator()
  {
    MongoCollection<Document> documents = client.getDatabase(dbName).getCollection(collName);
    Map<Attribute, Map<EntitySubtype, Object>> candidateMap = new HashMap<Attribute, Map<EntitySubtype, Object>>();
    List<Attribute> candidatesToBeRemoved = new ArrayList<Attribute>();

    for (Attribute candidate : candidates)
      candidateMap.put(candidate, new HashMap<EntitySubtype, Object>());

    for (Document doc : documents.find())
    {
      // Para cada propiedad candidata, determina para este objeto a qué subtipo pertenece:
      // ¿Estaba la propiedad ya inicializada?
      // Sí: ¿Coincide el valor?
      //     Sí: Todo bien.
      //     No: Elimina la propiedad de las candidatas
      // No: Inicializa el valor de la propiedad al valor leído.
      // Al acabar, de entre todas las propiedades candidatas restantes, quedarte con la que tenga TODo valores distintos.

      EntitySubtype theSubtype = detectSubtype(doc);

      for (Attribute candidate : candidates)
      {
        Map<EntitySubtype, Object> auxMap = candidateMap.get(candidate);
        if (auxMap.containsKey(theSubtype))
        {
          // If the stored value is not the same as the value being analyzed, then the property is not the discriminator.
          if (!auxMap.get(theSubtype).equals(doc.get(candidate.getName())))
            candidatesToBeRemoved.add(candidate);
        }
        else
          auxMap.put(theSubtype, doc.get(candidate.getName()));
      }

      candidates.removeAll(candidatesToBeRemoved);
      candidatesToBeRemoved.clear();
    }

    client.close();

    // TODO: Qué candidato tiene todo valores distintos? Devolver ese.
  }

  private EntitySubtype detectSubtype(Document doc)
  {
    // TODO: ¿A qué subtipo pertenece este doc? Ver EntitySubtype.getIdentifiers.
    return null;
  }
}
