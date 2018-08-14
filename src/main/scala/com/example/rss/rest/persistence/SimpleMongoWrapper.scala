package com.example.rss.rest.persistence

import scala.concurrent.{ ExecutionContext, Future }

import reactivemongo.api.{ Cursor, DefaultDB, MongoConnection, MongoDriver }
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson.{
  BSONDocumentWriter,
  BSONDocumentReader,
  Macros,
  document
}

class SimpleMongoWrapper(mongoUri: String, dbName: String) extends Serializable {
  import ExecutionContext.Implicits.global

  lazy val futureConnection = {
    val driver = MongoDriver()
    val parsedUri = MongoConnection.parseURI(mongoUri)
    val connection = parsedUri.map(driver.connection(_))
    Future.fromTry(connection)
  }

  lazy val futureDB = futureConnection.flatMap(_.database(dbName))

  def collection(name: String): Future[BSONCollection] = futureDB.map(_.collection(name))

}
