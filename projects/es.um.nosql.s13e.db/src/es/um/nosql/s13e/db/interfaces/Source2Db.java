package es.um.nosql.s13e.db.interfaces;

import es.um.nosql.s13e.db.adapters.DbClient;
import es.um.nosql.s13e.db.adapters.couchdb.CouchDbAdapter;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.s13e.db.util.DbType;

public abstract class Source2Db
{
  private DbClient client;

  private DbType dbType;

  private String ip;

  public Source2Db(DbType db, String ip)
  {
    this.dbType = db;
    this.ip = ip;

    switch (this.dbType)
    {
      case COUCHDB: { client = CouchDbAdapter.getCouchDbClient(this.ip); break;}
      case MONGODB: { client = MongoDbAdapter.getMongoDbClient(this.ip); break;}
      default: throw new IllegalArgumentException("Database type not implemented yet.");
    }
  }

  public DbType getDbType()
  {
    return this.dbType;
  }

  public String getIp()
  {
    return this.ip;
  }

  public DbClient getClient()
  {
    return this.client;
  }

  public abstract void run(String jsonRoute, String dbName);

  public boolean shutdown()
  {
    return this.client.shutdown();
  }
}
