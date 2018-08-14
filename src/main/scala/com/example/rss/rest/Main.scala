package com.example.rss.rest

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.example.rss.rest.json.JsonSupport

object Main extends App with CnnRoutes with JsonSupport {

  implicit val system: ActorSystem = ActorSystem("news-rest-api")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  import system.dispatcher

  Http().bindAndHandle(route, "0.0.0.0", 9090)

  println("Server online at http://localhost:9090/")

  Await.result(system.whenTerminated, Duration.Inf)

}
