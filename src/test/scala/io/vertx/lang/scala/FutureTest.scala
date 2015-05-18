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

import io.vertx.lang.scala.VertxExecutionContext._
import io.vertx.scala.core.Vertx
import io.vertx.test.core.VertxTestBase
import org.junit.Test
import scala.concurrent.ExecutionContext
import scala.util.{Success, Failure}
/**
 * @author <a href="mailto:larsdtimm@gmail.com">Lars Timm</a>
 */
class FutureTest extends VertxTestBase {

  var _vertx: Vertx = null;

  // We need to manually create the execution context for the futures
  private implicit val executionContext = VertxExecutionContext()

  override def setUp() = {
    super.setUp()
    _vertx = new Vertx(vertx);
  }

  @Test
  def HttpServerListenSuccessTest() {
    // Using HttpServer.listen as test bed for the future handling
    _vertx.createHttpServer()
      .requestHandler { req => fail }
      .listen(5000).onComplete { status =>
        status match {
          case Success(_) => testComplete
          case Failure(_) => fail
        }
      }
    await
  }

  @Test
  def HttpServerListenFailureTest() {
    // Using HttpServer.listen as test bed for the future handling
    // with invalid port number to force a failure
    _vertx.createHttpServer()
      .requestHandler { req => fail }
      .listen(100000).onComplete { status =>
        status match {
          case Success(_) => fail
          case Failure(e) => testComplete
        }
      }
    await
  }

}
