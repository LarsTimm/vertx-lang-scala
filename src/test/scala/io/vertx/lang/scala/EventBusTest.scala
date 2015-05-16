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
import io.vertx.scala.core.buffer.Buffer
import io.vertx.test.core.VertxTestBase
import org.junit.Test

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 * @author <a href="mailto:larsdtimm@gmail.com">Lars Timm</a>
 */
class EventBusTest extends VertxTestBase {
  
  var _vertx: Vertx = null;

  override def setUp() = {
    super.setUp()
    _vertx = new Vertx(vertx);
  }

  /* TODO: Needs conversion between objects
  @Test
  def BufferTest() = {
    val eventBus = _vertx.eventBus()
    eventBus.consumer[Buffer]("the_address").handler { message =>
      val body = message.body()
      if (body.toString("UTF-8").equals("the_message")) {
        testComplete()
      } else {
        fail()
      }
    }
    eventBus.send("the_address", Buffer.buffer("the_message").asJava)
    await()
  }
*/
  
  @Test
  def StringTest() = {
    val eventBus = _vertx.eventBus()
    eventBus.consumer[String]("the_address").handler { message =>
      val body = message.body()
      if (body.equals("the_message")) {
        testComplete()
      } else {
        fail()
      }
    }
    val value = "the_message"
    eventBus.send("the_address", value)
    await()
  }
}
