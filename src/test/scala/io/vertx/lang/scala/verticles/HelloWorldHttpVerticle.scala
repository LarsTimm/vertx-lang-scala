package io.vertx.lang.scala.verticles

import io.vertx.core.Future
import io.vertx.lang.scala.ScalaVerticle

class HelloWorldHttpVerticle extends ScalaVerticle {

  override def start(promise: scala.concurrent.Promise[Unit]): Unit = {
    val server = vertx.createHttpServer()
    server.requestHandler { request =>
      println("Got request")
      val response = request.response()
      response.putHeader("Content-Type", "text/html")
      response.setChunked(true)
      response.write("<html><body>Hello World</body></html>")
      response.end()
    }
    server.listen(8080)
    promise.success(Unit)
  }
}
