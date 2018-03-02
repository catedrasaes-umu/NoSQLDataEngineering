package es.um.nosql.streaminginference.spark.implicits

import org.mongodb.scala.bson.collection.immutable.Document
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit
import org.bson.conversions.Bson
import org.mongodb.scala.Observable
import org.mongodb.scala.SingleObservable


// Based on: https://github.com/mongodb/mongo-scala-driver/blob/master/examples/src/test/scala/tour/Helpers.scala
object MongoHelpers 
{
  val timeout = Duration(10, TimeUnit.SECONDS)
  
  implicit class DocumentSingleObservable[C](val observable: SingleObservable[Document]) extends ImplicitSingleObservable[Document] 
  {
    override val converter: (Document) => String = (doc) => doc.toJson
  }

  implicit class GenericSingleObservable[C](val observable: SingleObservable[C]) extends ImplicitSingleObservable[C] 
  {
    override val converter: (C) => String = (doc) => doc.toString
  }
  
  implicit class DocumentObservable[C](val observable: Observable[Document]) extends ImplicitObservable[Document] 
  {
    override val converter: (Document) => String = (doc) => doc.toJson
  }

  implicit class GenericObservable[C](val observable: Observable[C]) extends ImplicitObservable[C] 
  {
    override val converter: (C) => String = (doc) => doc.toString
  }

  trait ImplicitObservable[C] 
  {
    val observable: Observable[C]
    val converter: (C) => String

    def results(): Seq[C] = Await.result(observable.toFuture(), timeout)
    def headResult() = Await.result(observable.head(), timeout)
    def printResults(initial: String = ""): Unit = 
    {
      if (initial.length > 0) print(initial)
      results().foreach(res => println(converter(res)))
    }
    def printHeadResult(initial: String = ""): Unit = println(s"${initial}${converter(headResult())}")
  }
  
  trait ImplicitSingleObservable[C] extends ImplicitObservable[C]
  {

    def result(): C = Await.result(observable.asInstanceOf[SingleObservable[C]].toFuture(), timeout)
    def printResult(initial: String = ""): Unit = {
      if (initial.length > 0) print(initial)
        println(converter(result()))
    }
  }

}