package es.um.nosql.streaminginference.spark.listeners

import org.apache.spark.streaming.scheduler.StreamingListener
import org.apache.spark.streaming.scheduler.StreamingListenerBatchCompleted
import org.apache.spark.streaming.scheduler.StreamingListenerBatchStarted
import scala.collection.immutable.HashMap
import es.um.nosql.streaminginference.spark.utils.HDFSHelper

abstract class BenchmarkListener(options: HashMap[String, String]) extends StreamingListener 
{ 
    protected var listening:Boolean = false
    protected var totalDelay:Long = 0
    protected var totalTime:Long = 0
    protected var totalIdle:Long = 0
    protected var totalRecords:Long = 0
    protected var totalBatches:Int = 0
    protected var maxDelay:Long = 0
    protected var maxProcessing:Long = 0
    protected var maxIdle:Long = 0
    protected var start:Long = 0
    
    protected val statsHeader = 
      "BATCH_INTERVAL,BLOCK_INTERVAL,PROCESSING_INTERVAL," +
      "TOTAL_BATCHES,TOTAL_DELAY,TOTAL_PROCESSING,TOTAL_IDLE,TOTAL_RECORDS,"+
      "AVERAGE_DELAY,AVERAGE_PROCESSING,AVERAGE_IDLE,AVERAGE_RECORDS,"+
      "MAX_PROCESSING,MAX_IDLE,MAX_DELAY\n"
      
    protected def outputStats(end:Long = System.currentTimeMillis())
    {
      val processingInterval = end - start
      println("Processing interval: " + processingInterval)
      println("Total batches: " + totalBatches)
      println("Total delay: " + totalDelay)
      println("Total processing time: " + totalTime)
      println("Total idle time: " + totalIdle)
      println("Number of records: " + totalRecords)
      println("Average delay: " + (totalDelay/totalBatches))
      println("Average processing time: " + (totalTime/totalBatches))
      println("Average idle time: " + (totalIdle/totalBatches))
      println("Average batch records: " + (totalRecords/totalBatches))
      println("Max processing: " + maxProcessing)
      println("Max delay: " + maxDelay)
      println("Max idle: " + maxIdle)
      
      HDFSHelper.append(options("output")+"/stats.csv",
                        options("interval")+ "," +
                        options("block-interval")+ "," +
                        processingInterval + "," +
                        totalBatches + "," +
                        totalDelay + "," +
                        totalTime + "," +
                        totalIdle + "," +
                        totalRecords + "," +
                        (totalDelay/totalBatches) + "," +
                        (totalTime/totalBatches) + "," +
                        (totalIdle/totalBatches) + "," +
                        (totalRecords/totalBatches) + "," +
                        maxProcessing + "," +
                        maxIdle + "," +
                        maxDelay + "\n",
                        statsHeader)
    }
    
    protected def updateStats(batchCompleted: StreamingListenerBatchCompleted) 
    {
      val interval = options("interval").toInt
      val processing = batchCompleted.batchInfo.processingDelay.get
      val delay = batchCompleted.batchInfo.totalDelay.get
      val idle = if (interval - processing > 0) interval - processing else 0 
      totalBatches += 1;
      totalDelay += delay
      totalTime += processing
      totalIdle += idle
      totalRecords += batchCompleted.batchInfo.numRecords
      
      if (delay > maxDelay)
        maxDelay = delay
        
      if (processing > maxProcessing)
        maxProcessing = processing
        
      if (idle > maxIdle)
        maxProcessing = idle
    }

}
