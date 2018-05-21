package es.um.nosql.streaminginference.spark.listeners

import org.apache.spark.streaming.scheduler.StreamingListener
import org.apache.spark.streaming.scheduler.StreamingListenerBatchCompleted
import org.apache.spark.streaming.scheduler.StreamingListenerBatchStarted
import scala.collection.immutable.HashMap
import es.um.nosql.streaminginference.spark.utils.HDFSHelper

class BenchmarkListener(options: HashMap[String, String]) extends StreamingListener 
{ 
    private var listening:Boolean = false
    private var totalDelay:Long = 0
    private var totalTime:Long = 0
    private var totalRecords:Long = 0
    private var totalBatches:Int = 0
    private var maxDelay:Long = 0
    private var maxProcessing:Long = 0
    private var start:Long = 0
    
    private val statsHeader = 
      "BATCH_INTERVAL,BLOCK_INTERVAL,PROCESSING_INTERVAL," +
      "TOTAL_BATCHES,TOTAL_DELAY,TOTAL_PROCESSING,TOTAL_RECORDS,"+
      "AVERAGE_DELAY,AVERAGE_PROCESSING,AVERAGE_RECORDS,"+
      "MAX_PROCESSING,MAX_DELAY\n"
     
    
    override def onBatchCompleted(batchCompleted: StreamingListenerBatchCompleted) 
    {      
      if (listening) 
      {
        totalBatches += 1;
        totalDelay += batchCompleted.batchInfo.totalDelay.get
        totalTime += batchCompleted.batchInfo.processingDelay.get
        totalRecords += batchCompleted.batchInfo.numRecords
        
        if (batchCompleted.batchInfo.totalDelay.get > maxDelay)
          maxDelay = batchCompleted.batchInfo.totalDelay.get
          
        if (batchCompleted.batchInfo.processingDelay.get > maxProcessing)
          maxProcessing = batchCompleted.batchInfo.processingDelay.get
      }
    }
    
    override def onBatchStarted(batchStarted: StreamingListenerBatchStarted) 
    {
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
        val processingInterval = System.currentTimeMillis() - start
        println("Processing interval: " + processingInterval)
        println("Total batches: " + totalBatches)
        println("Total delay: " + totalDelay)
        println("Total processing time: " + totalTime)
        println("Number of records: " + totalRecords)
        println("Average delay: " + (totalDelay/totalBatches))
        println("Average processing time: " + (totalTime/totalBatches))
        println("Average batch records: " + (totalRecords/totalBatches))
        println("Max processing: " + maxProcessing)
        println("Max delay: " + maxDelay)
        
        HDFSHelper.append(options("output")+"/stats.csv",
                          options("interval")+ "," +
                          options("block-interval")+ "," +
                          processingInterval + "," +
                          totalBatches + "," +
                          totalDelay + "," +
                          totalTime + "," +
                          totalRecords + "," +
                          (totalDelay/totalBatches) + "," +
                          (totalTime/totalBatches) + "," +
                          (totalRecords/totalBatches) + "," +
                          maxProcessing + "," +
                          maxDelay + "\n",
                          statsHeader)
        // Force streaming shutdown
        HDFSHelper.touch(options("output")+"/_DONE")
      }
    }
}
