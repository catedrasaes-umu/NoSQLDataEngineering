package es.um.nosql.streaminginference.spark.listeners

import org.apache.spark.streaming.scheduler.StreamingListener
import org.apache.spark.streaming.scheduler.StreamingListenerBatchCompleted
import org.apache.spark.streaming.scheduler.StreamingListenerBatchStarted
import scala.collection.immutable.HashMap
import es.um.nosql.streaminginference.spark.utils.HDFSHelper

class FileBenchmarkListener(options: HashMap[String, String]) extends BenchmarkListener(options)
{ 
  private val LOWER_PROCESSING_THRESHOLD = 300
  private val UPPER_PROCESSING_THRESHOLD = 700
  
    override def onBatchCompleted(batchCompleted: StreamingListenerBatchCompleted) 
    {      
      if (batchCompleted.batchInfo.processingDelay.get > UPPER_PROCESSING_THRESHOLD)
      {
        val interval = options("interval").toInt
        start = System.currentTimeMillis() - interval
        listening = true
      }
      
      if (listening) 
      {
        if (batchCompleted.batchInfo.processingDelay.get < LOWER_PROCESSING_THRESHOLD)
        {
          listening = false
          val interval = options("interval").toInt
          outputStats(System.currentTimeMillis()-interval)
          // Force streaming shutdown
          HDFSHelper.touch(options("output")+"/_DONE")
        }
        else
          updateStats(batchCompleted)
      }
    }
}
