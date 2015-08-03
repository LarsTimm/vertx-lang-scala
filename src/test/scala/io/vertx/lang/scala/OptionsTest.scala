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
import io.vertx.test.core.VertxTestBase
import org.junit.Test

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 * @author <a href="mailto:larsdtimm@gmail.com">Lars Timm</a>
 */
class OptionsTest extends VertxTestBase {

  var _vertx: Vertx = null;

  override def setUp() = {
    super.setUp()
    _vertx = new Vertx(vertx);
  }

  @Test
  def HttpServerNullArgumentTest() {
    try {
      _vertx.createHttpServer(null);
    } catch {
      case ignore: Exception => { /* Expected */ };
    }
  }

  @Test
  def HttpServerNoArgumentTest() {
    _vertx.createHttpServer();
  }

  @Test
  def HttpServerJsonTest() {
    _vertx.createHttpServer(io.vertx.scala.core.http.HttpServerOptions(Json.obj("port" -> 8080)));
    _vertx.createHttpServer(io.vertx.scala.core.http.HttpServerOptions(Json.obj()));
  }

  @Test
  def HttpServerTest() {
    val option = io.vertx.scala.core.http.HttpServerOptions()
    option.port = 8080

    _vertx.createHttpServer(option);
  }

}
