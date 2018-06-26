package es.um.nosql.streaminginference.spark.listeners

import org.apache.spark.streaming.scheduler.StreamingListener
import org.apache.spark.streaming.scheduler.StreamingListenerBatchCompleted
import org.apache.spark.streaming.scheduler.StreamingListenerBatchStarted
import scala.collection.immutable.HashMap
import es.um.nosql.streaminginference.spark.utils.HDFSHelper

class FileBenchmarkListener(options: HashMap[String, String]) extends BenchmarkListener(options)
{ 
  private val LOWER_PROCESSING_THRESHOLD = 1000
  private val UPPER_PROCESSING_THRESHOLD = 1000
  
    override def onBatchStarted(batchStarted: StreamingListenerBatchStarted) 
    {
      // Only measure first batch in file benchmark mode
      if (totalBatches == 0)
        start = System.currentTimeMillis()
    }
    override def onBatchCompleted(batchCompleted: StreamingListenerBatchCompleted) 
    {    
      // Only measure first batch in file benchmark mode
      if (totalBatches == 0)
      {
        updateStats(batchCompleted)
        outputStats()
        HDFSHelper.touch(options("output")+"/_DONE")
        totalBatches+=1
      }
    }
}
