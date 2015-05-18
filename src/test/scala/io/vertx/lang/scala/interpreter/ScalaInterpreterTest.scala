package io.vertx.lang.scala.interpreter

import java.io.{File, PrintWriter, StringWriter, Writer}
import io.vertx.core.Future
import io.vertx.core.Verticle
import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.core.Vertx
import io.vertx.test.core.VertxTestBase
import scala.tools.nsc.Settings
import scala.util.{Failure, Success}
import org.junit.Test


class ScalaInterpreterTest extends VertxTestBase {

  var _vertx: Vertx = null

  override def setUp() = {
    super.setUp()
    _vertx = new Vertx(vertx)
  }

  @Test
  def runScriptTest(): Unit = {
    val path = new File("src/test/scripts/VerticleScript.scala").toURI.toURL
    val out = new StringWriter()
    val interpreter = createInterpreter(out)
    interpreter.runScript(path) match {
      case Success(s) =>
        assertHttpClientGetNow("Hello verticle script!")
      case Failure(ex) =>
        println(out.toString)
        fail(ex.toString)
    }
    await
  }

  @Test
  def runClassTest(): Unit = {
    val filePath = "src/test/scala/io/vertx/lang/scala/VerticleClass.scala"
    val out = new StringWriter()
    val interpreter = createInterpreter(out)
    val classLoader = interpreter.compileClass(new File(filePath))
    val className = "io.vertx.lang.scala.VerticleClass"
    val verticle = ClassLoaders.newInstance[ScalaVerticle](className, classLoader.get).get.asJava
    val future: Future[Void] = Future.future()
    verticle.init(vertx, vertx.getOrCreateContext)
    verticle.start(future)
    assertHttpClientGetNow("Hello verticle class!")
    await
  }

  private def createInterpreter(out: Writer): ScalaInterpreter = {
    val settings = new Settings()
    settings.usejavacp.value = true
    settings.verbose.value = ScalaInterpreter.isVerbose
    new ScalaInterpreter(settings, _vertx, new PrintWriter(out))
  }

  private def assertHttpClientGetNow(expected: String) {
    val client = _vertx.createHttpClient()
    client.getNow(8080, "localhost", "/", { h =>
      h.bodyHandler { data => {
          assertEquals(expected, data.toString("UTF-8"))
          testComplete()
        }
      }
    })
  }

}
