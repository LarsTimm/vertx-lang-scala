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

import io.vertx.core.Future
import io.vertx.scala.core.Vertx
import io.vertx.test.core.VertxTestBase
import org.junit.Test

/**
 * Scala verticle factory tests.
 *
 * @author Galder Zamarreño
 * @author <a href="mailto:larsdtimm@gmail.com">Lars Timm</a>
 */
class ScalaVerticleFactoryTest extends VertxTestBase {

  var _vertx: Vertx = null

  override def setUp() = {
    super.setUp()
    _vertx = new Vertx(vertx)
  }
  
  @Test
  def createVerticleWithSystemPath(): Unit = {
    val factory = createScalaVerticleFactory()
    val path = "src/test/scala/io/vertx/lang/scala/VerticleClass.scala"
    val verticle = factory.createVerticle(path, this.getClass.getClassLoader)
    val future: Future[Void] = Future.future()
    verticle.init(vertx, vertx.getOrCreateContext)
    verticle.start(future)
    assertHttpClientGetNow("Hello verticle class!")
    await
  }

  @Test
  def createVerticleWithUnmatchingPathAndClassName(): Unit = {
    val factory = createScalaVerticleFactory()
    val path = "src/test/scala/io/vertx/lang/scala/UnsupportedVerticleClass.scala"
    try {
      factory.createVerticle(path, this.getClass.getClassLoader)
      fail("Should have failed with an exception")
    } catch {
      case e: ClassNotFoundException => testComplete() // expected
    }
  }

  private def createScalaVerticleFactory(): ScalaVerticleFactory = {
    val factory = new ScalaVerticleFactory
    factory.init(vertx)
    factory
  }

  private def assertHttpClientGetNow(expected: String) {
    val client = _vertx.createHttpClient()
    client.getNow(8080, "localhost", "/", { h =>
      h.bodyHandler { data => {
          assertEquals(expected, data.toString("UTF-8"))
          println("testcomplete")
          testComplete()
        }
      }
    })
  }

}
