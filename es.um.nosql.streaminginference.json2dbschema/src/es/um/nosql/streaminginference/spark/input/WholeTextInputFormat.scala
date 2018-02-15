package es.um.nosql.streaminginference.spark.input
// https://github.com/apache/spark/blob/master/core/src/main/scala/org/apache/spark/input/WholeTextFileInputFormat.scala
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.{InputSplit, JobContext, RecordReader, TaskAttemptContext}
import org.apache.hadoop.mapreduce.lib.input.CombineFileInputFormat

class WholeTextInputFormat 
  extends CombineFileInputFormat[Text, Text] with Configurable {
   
  override protected def isSplitable(context: JobContext, file: Path): Boolean = false

  override def createRecordReader(
      split: InputSplit,
      context: TaskAttemptContext): RecordReader[Text, Text] = {
    val reader =
      new ConfigurableCombineFileRecordReader(split, context, classOf[WholeTextFileRecordReader])
    reader.setConf(getConf)
    reader
  }

}