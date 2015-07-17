/*
 * Copyright 2015 Red Hat, Inc.
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

package io.vertx.scala.core.parsetools

import io.vertx.scala.core.buffer.Buffer
import org.junit.Test
import org.junit.Assert._
import scala.collection.mutable.ListBuffer

/**
  * @author <a href="mailto:larsdtimm@gmail.com">Lars Timm</a>
  */
class RecordParserTest {

  @Test
  def newDelimitedTest() {
    val outputs = ListBuffer.empty[Buffer]
    val str: String = "#"

    class MyHandler {
      def handler(output: Buffer): Unit = {
        outputs += output
      }
    }

    val handler = new MyHandler
    val parser = RecordParser.newDelimited(str: String, handler.handler _)

    parser.handle(Buffer.buffer("123#456"))
    parser.handle(Buffer.buffer("#"))

    assertEquals(2, outputs.length)
    assertEquals("123", outputs.head.toString("UTF-8"))
    assertEquals("456", outputs.tail.head.toString("UTF-8"))
  }

  @Test
  def newFixedTest() {
    val outputs = ListBuffer.empty[Buffer]
    val parser = RecordParser.newFixed(4, { output =>
      outputs += output
    })

    parser.handle(Buffer.buffer.appendInt(42).appendInt(100))

    assertEquals(2, outputs.length)
    assertEquals(42, outputs.head.getInt(0))
    assertEquals(100, outputs.tail.head.getInt(0))
  }

  @Test
  def handlerTest() {
    class MyHandler {
      def handler(handler: io.vertx.scala.core.buffer.Buffer => Unit): Unit = {
      }
    }

    val parser = RecordParser.newFixed(1, { _ => })

    val handler = new MyHandler
    handler.handler(parser.handle)

    // If this compiles we're good :-)
  }

}
