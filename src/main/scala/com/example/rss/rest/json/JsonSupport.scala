package com.example.rss.rest.json

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.example.rss.rest.model.NewsEntry
import spray.json.DefaultJsonProtocol
import reactivemongo.bson._

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val newsEntryFormat = jsonFormat4(NewsEntry)
  implicit val newsEntryReader: BSONDocumentReader[NewsEntry] = Macros.reader[NewsEntry]
  implicit val newsEntryWriter: BSONDocumentWriter[NewsEntry] = Macros.writer[NewsEntry]

}
