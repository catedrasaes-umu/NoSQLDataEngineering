package es.um.nosql.schemainference.db.interfaces;

import es.um.nosql.schemainference.db.adapters.DbClient;
import es.um.nosql.schemainference.db.adapters.couchdb.CouchDbAdapter;
import es.um.nosql.schemainference.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.schemainference.db.utils.DbType;

public abstract class Source2Db
{
  private DbClient client;

  public Source2Db(DbType db, String ip)
  {
    switch (db)
    {
      case COUCHDB: { client = CouchDbAdapter.getCouchDbClient(ip); break;}
      case MONGODB: { client = MongoDbAdapter.getMongoDbClient(ip); break;}
      default: throw new IllegalArgumentException("Database type not implemented yet.");
    }
  }

  public DbClient getClient()
  {
    return client;
  }

  public boolean shutdown()
  {
    return client.shutdown();
  }
}
