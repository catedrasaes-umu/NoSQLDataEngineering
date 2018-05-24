package es.um.nosql.streaminginference.spark.listeners

import org.apache.spark.streaming.scheduler.StreamingListener
import org.apache.spark.streaming.scheduler.StreamingListenerBatchCompleted
import org.apache.spark.streaming.scheduler.StreamingListenerBatchStarted
import scala.collection.immutable.HashMap
import es.um.nosql.streaminginference.spark.utils.HDFSHelper

class ReceiverBenchmarkListener(options: HashMap[String, String]) extends BenchmarkListener(options) 
{ 

    override def onBatchCompleted(batchCompleted: StreamingListenerBatchCompleted) 
    {      
      if (listening) 
        updateStats(batchCompleted)
    }
    
    override def onBatchStarted(batchStarted: StreamingListenerBatchStarted) 
    {
      //println("KO!");
      if (!listening && batchStarted.batchInfo.numRecords > 0) 
      {
        listening = true
        start = System.currentTimeMillis()
      } 
      else if (listening &&
              totalRecords > 0 &&
              batchStarted.batchInfo.numRecords == 0 && 
              batchStarted.batchInfo.schedulingDelay.get < 5) 
      {
        // Streaming application has processed all records
        listening = false
        outputStats()
        // Force streaming shutdown
        HDFSHelper.touch(options("output")+"/_DONE")
      }
    }
}
