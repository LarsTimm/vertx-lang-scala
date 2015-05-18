/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package io.vertx.lang.scala

import io.vertx.scala.core.Vertx
import io.vertx.lang.scala.json._
import org.junit.Test

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 * @author <a href="mailto:larsdtimm@gmail.com">Lars Timm</a>
 */
class OptionsTest {

  @Test
  def HttpServerTest() {
    val vertx = Vertx.vertx();
    try {
      try {
        vertx.createHttpServer(null);
      } catch {
        case ignore: Exception => { /* Expected */ };
      }
      vertx.createHttpServer();
      
      // Hmm... Not particular fond of this syntax. We should either consider:
      // 1. Generating scala specific option classes with implicit conversion from JsonObject
      // 2. Change methods to take JsonObject instead of option class
      vertx.createHttpServer(new io.vertx.core.http.HttpServerOptions(Json.obj("port" -> 8080)));
      vertx.createHttpServer(new io.vertx.core.http.HttpServerOptions(Json.obj()));
    } finally {
      vertx.close();
    }
  }
}
