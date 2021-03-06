/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.vertx.lang.scala

import io.vertx.scala.core.http.HttpServerRequest

// Class name different to file name for testing purposes
class VerticleClassWithDifferentName extends ScalaVerticle {

  override def start(promise: scala.concurrent.Promise[Unit]) {
    vertx.createHttpServer().requestHandler { req: HttpServerRequest =>
      req.response().end("Hello verticle class!")
    }.listen(8080)
    promise.success(Unit)
  }

}