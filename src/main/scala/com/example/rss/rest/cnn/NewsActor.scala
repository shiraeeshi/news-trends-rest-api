package com.example.rss.rest.cnn

import akka.actor.{ Actor, Props }
import akka.pattern.pipe
import com.example.rss.rest.json.JsonSupport
import com.example.rss.rest.persistence.SimpleMongoWrapper
import com.example.rss.rest.model.NewsEntry
import reactivemongo.bson._

object NewsActor {
  case object GetNews

  def props: Props = Props(new NewsActor)
}

class NewsActor extends Actor with JsonSupport {

  val mongoUri = "mongodb://root:example@db:27017/test?authSource=admin"
  val mongo = new SimpleMongoWrapper(mongoUri, "news-db")

  import context.dispatcher
  import NewsActor._

  override def receive: Receive = {
    case GetNews =>
      val newsCollectionFuture = mongo.collection("news")
      val newsFuture = newsCollectionFuture
        .flatMap(
          _.find(BSONDocument())
            .sort(BSONDocument("publishedAt" -> -1))
            .cursor[NewsEntry]()
            .collect[List](25)
        )
      newsFuture pipeTo sender

  }
}
