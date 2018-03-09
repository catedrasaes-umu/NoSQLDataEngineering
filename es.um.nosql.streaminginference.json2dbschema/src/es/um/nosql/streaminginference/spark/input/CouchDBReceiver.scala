package es.um.nosql.streaminginference.spark.input

import scala.collection.JavaConversions.asScalaBuffer

import org.apache.spark.internal.Logging
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver
import org.ektorp.CouchDbConnector
import org.ektorp.CouchDbInstance
import org.ektorp.changes.ChangesCommand
import org.ektorp.changes.ChangesFeed
import org.ektorp.changes.DocumentChange
import org.ektorp.http.HttpClient
import org.ektorp.http.StdHttpClient
import org.ektorp.impl.StdCouchDbInstance

import com.fasterxml.jackson.databind.node.ObjectNode

import es.um.nosql.streaminginference.json2dbschema.util.inflector.Inflector

class CouchDBReceiver (host: String, port: Int, username: Option[String] = None, password: Option[String] = None)
  extends Receiver[(String, String)](StorageLevel.MEMORY_AND_DISK_2) with Logging 
{
  def onStart() 
  {
    new Thread("CouchDB Receiver") 
    {
      override def run() 
      { 
        receive() 
      }
    }
    .start()
    
  }
  
  private def receive() 
  {
    var currentDbs:List[String] = List()
    try 
    {
      // Connect with CouchDB Server
      val request:StdHttpClient.Builder = new StdHttpClient
                                              .Builder()
                                              .url("http://" + host + ":" + port)                                
      if (username.isDefined)
        request.username(username.get)
      if (password.isDefined)
        request.password(password.get)
                                      
      val httpClient:HttpClient = request.build
      val dbInstance:CouchDbInstance = new StdCouchDbInstance(httpClient)
    
      while (!isStopped()) 
      {
        val dbs:List[String] = dbInstance
                                .getAllDatabases
                                .toList
                                // Filter out internal databases
                                .filter(db => !db.startsWith("_"))
        val newDbs = dbs.diff(currentDbs)
        newDbs.foreach(newDb => 
        {
          currentDbs = newDb :: currentDbs
          val th = new Thread("Couch DB Watcher")
          {
            override def run()
            {
              val db:CouchDbConnector = dbInstance.createConnector(newDb, true)
              val cmd:ChangesCommand = new ChangesCommand
                                           .Builder()
                                           .since(0)
                                           .continuous(true)
                                           .includeDocs(true)
                                           .build()
                                           
              val feed:ChangesFeed = db.changesFeed(cmd)
              while (feed.isAlive() && !isStopped) 
              {
                  val change:DocumentChange = feed.next();
                  val node = change.getDocAsNode.asInstanceOf[ObjectNode]
                  node
                    .put("_type", Inflector.getInstance.singularize(newDb))
                    .remove("_rev")
                  store(("couch", node.toString))            
              }
            }
          }.start()
        })
        // Thread waiting
        Thread.sleep(10000)
      }
    }
    catch
    {
      case e: java.net.ConnectException =>
        // restart if could not connect to server
        restart("Error connecting to " + host + ":" + port, e)
      case t: Throwable =>
        // restart if there is any other error
        restart("Error receiving data", t)
    } 

  }

  def onStop() 
  {
    // There is nothing much to do as the thread calling receive()
    // is designed to stop by itself if isStopped() returns false
  }

}