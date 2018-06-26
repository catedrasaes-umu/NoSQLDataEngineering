package es.um.nosql.streaminginference.spark.utils

import org.apache.spark.SparkConf

object KryoHelper {
  
  def enable(conf: SparkConf): Unit = {
    
    conf
      // https://stackoverflow.com/questions/42555183/mapwithstaterddrecord-with-kryo
      // .set("spark.kryo.registrationRequired", "true")
      .set("spark.serializer","org.apache.spark.serializer.KryoSerializer")
      
    conf.registerKryoClasses(Array(
        classOf[scala.collection.mutable.WrappedArray$ofRef],
        // https://stackoverflow.com/questions/42683553/how-to-register-receiver-with-kryo
        classOf[Array[org.apache.spark.streaming.receiver.Receiver[_]]],
        classOf[es.um.nosql.streaminginference.spark.input.MongoReceiver],
        classOf[es.um.nosql.streaminginference.json2dbschema.process.SchemaInference],
        classOf[java.util.HashSet[_]],
        classOf[java.util.HashMap[_,_]],
        classOf[java.util.ArrayList[_]],
        classOf[org.apache.commons.lang3.tuple.MutablePair[_,_]],
        classOf[es.um.nosql.streaminginference.json2dbschema.intermediate.raw.ArraySC], 
        classOf[es.um.nosql.streaminginference.json2dbschema.intermediate.raw.BooleanSC],
        classOf[es.um.nosql.streaminginference.json2dbschema.intermediate.raw.NullSC],
        classOf[es.um.nosql.streaminginference.json2dbschema.intermediate.raw.NumberSC],
        classOf[es.um.nosql.streaminginference.json2dbschema.intermediate.raw.ObjectSC],
        classOf[es.um.nosql.streaminginference.json2dbschema.intermediate.raw.SchemaComponent],
        classOf[es.um.nosql.streaminginference.json2dbschema.intermediate.raw.StringSC]
    ))
    
  }
}