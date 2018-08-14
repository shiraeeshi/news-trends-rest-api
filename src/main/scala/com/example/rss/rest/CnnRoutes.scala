package com.example.rss.rest

import scala.concurrent.duration._

import akka.actor.{ ActorSystem, Props }
import akka.pattern.ask
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import akka.util.Timeout

import com.example.rss.rest.cnn.NewsActor
import com.example.rss.rest.json.JsonSupport
import com.example.rss.rest.model.NewsEntry

trait CnnRoutes { this: JsonSupport =>

  implicit val system: ActorSystem
  implicit val materializer: ActorMaterializer

  import NewsActor.GetNews

  private def newsActorProps: Props = NewsActor.props

  lazy val route = pathPrefix("news") {
    get {
      pathEnd {
        complete {
          implicit val timeout = Timeout(5.seconds)

          val newsActor = system.actorOf(newsActorProps)
          val fNews = (newsActor ? GetNews).mapTo[List[NewsEntry]]
          fNews
        }
      }
    }
  }

}
