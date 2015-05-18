def requestHandler(request: HttpServerRequest): Unit = {
  println("Got request")
  val response = request.response()
  response.putHeader("Content-Type", "text/html")
  response.setChunked(true)
  response.write("<html><body>Hello World</body></html>")
  response.end()
}

vertx
  .createHttpServer()
  .requestHandler(requestHandler)
  .listen(8080)
