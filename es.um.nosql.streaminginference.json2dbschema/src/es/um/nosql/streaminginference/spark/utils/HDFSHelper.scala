package es.um.nosql.streaminginference.spark.utils

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path
import org.apache.hadoop.fs.FSDataOutputStream
import java.io.IOException


object HDFSHelper 
{  
  private val conf: Configuration = new Configuration
  private val fs: FileSystem = FileSystem.get(conf)
  
  def exists(path: String): Boolean = 
  {
      val hPath:Path = new Path(path);
  		fs.exists(hPath)
  }
  
  def delete(path: String): Boolean = 
  {
    val hPath:Path = new Path(path);
		// http://apache-spark-user-list.1001560.n3.nabble.com/How-can-I-make-Spark-1-0-saveAsTextFile-to-overwrite-existing-file-td6696.html
		// https://community.hortonworks.com/questions/59140/apache-spark-overwrite-data-file.html
		try 
		{ 
		  fs.delete(hPath, true)
		  true
		} 
		catch 
		{ 
		  case _ : Throwable => 
		    false 
		}
		
  }
  
  def getOutputStream(path: String): FSDataOutputStream = {
    val hPath:Path = new Path(path);
    fs.create(hPath)
  }
  
  def getPatternFiles(path: String): Array[String] = {
    val hPath:Path = new Path(path);
    fs.globStatus(hPath).map(fileStatus => fileStatus.getPath.toString)
  }
  
  def append(path: String, content:String, createString:String = "") = {
    val hPath:Path = new Path(path)
    if (!exists(path)) 
    {
      val handler = getOutputStream(path)
      handler.writeBytes(createString+content)
      handler.close()
    } 
    else
    {
      try
      {
        val handler = fs.append(hPath)  
        handler.writeChars(content)
        handler.close()
      } 
      catch 
      {
        // Append is not supported in local mode
        case _ : IOException | _ : UnsupportedOperationException =>
          scala.tools.nsc.io.File(path).appendAll(content)
      }
    }
    
  }
  
  def touch(path: String) = {
    val stream = getOutputStream(path)
    stream.writeChars("")
    stream.close()
  }
  
}