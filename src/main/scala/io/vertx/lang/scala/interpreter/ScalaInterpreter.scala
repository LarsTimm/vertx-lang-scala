package io.vertx.lang.scala.interpreter

import java.io.{ File, PrintWriter }
import scala.annotation.tailrec
import scala.io.Source
import scala.reflect.internal.util.BatchSourceFile
import scala.reflect.io.PlainFile
import scala.tools.nsc.Settings
import scala.tools.nsc.interpreter.{NamedParamClass, IMain}
import scala.tools.nsc.interpreter.Results.Result
import scala.tools.nsc.interpreter.Results.{ Error => InterpreterError }
import scala.tools.nsc.interpreter.Results.{ Incomplete => InterpreterIncomplete}
import scala.tools.nsc.interpreter.Results.{ Success => InterpreterSuccess }
import io.vertx.scala.core.Vertx
import scala.tools.nsc.NewLinePrintWriter
import scala.tools.nsc.ConsoleWriter
import java.net.URL
//import org.vertx.scala.platform.Container
import scala.util.{Success, Failure, Try}

/**
 * Scala interpreter
 *
 * @author Galder Zamarreño
 * @author <a href="mailto:larsdtimm@gmail.com">Lars Timm</a>
 */
class ScalaInterpreter(
    settings: Settings,
    vertx: Vertx,
    out: PrintWriter = new NewLinePrintWriter(new ConsoleWriter, true)) {

  private val interpreter = new IMain(settings, out)
  interpreter.setContextClassLoader()

  def addBootClasspathJar(path: String): Unit =
    settings.bootclasspath.append(path)

  def close(): Unit = interpreter.close()

  def runScript(script: URL): Try[Unit] = {
    val content = Source.fromURL(script).mkString
    val ops = List(
      () => addImport("io.vertx.core.json._"),
      () => addImport("io.vertx.lang.scala._"),
      () => addImport("io.vertx.lang.scala.json._"),
      () => addImport("io.vertx.scala.core._"),
      () => addImport("io.vertx.scala.core.buffer._"),
      () => addImport("io.vertx.scala.core.datagram._"),
      () => addImport("io.vertx.scala.core.dns._"),
      () => addImport("io.vertx.scala.core.eventbus._"),
      () => addImport("io.vertx.scala.core.file._"),
      () => addImport("io.vertx.scala.core.http._"),
      () => addImport("io.vertx.scala.core.net._"),
      () => addImport("io.vertx.scala.core.shareddata._"),
      () => addImport("io.vertx.scala.core.streams._"),
      () => bind("vertx", "io.vertx.scala.core.Vertx", vertx),
      () => interpret(content)
    )
    val result = interpret(ops, InterpreterIncomplete)
    result match {
      case InterpreterError => Failure(new ScalaCompilerError(s"Interpreter error running $script"))
      case InterpreterIncomplete => Failure(new ScalaCompilerError(s"Incomplete script $script"))
      case InterpreterSuccess => Success(())
    }
  }

  private def addImport(id: String): Result =
    interpret("import " + id)

  private def bind(name: String, boundType: String, value: Any): Result =
    verboseOrQuiet(
      interpreter.bind(name, boundType, value),
      interpreter.quietBind(NamedParamClass(name, boundType, value)))

  private def interpret(content: String): Result =
    verboseOrQuiet(
      interpreter.interpret(content),
      interpreter.beQuietDuring(interpreter.interpret(content)))

  private def verboseOrQuiet(verbose: => Result, quiet: => Result): Result = {
    if (ScalaInterpreter.isVerbose) verbose else quiet
  }

  def compileClass(classFile: File): Try[ClassLoader] = {
    val source = new BatchSourceFile(new PlainFile(classFile))
    val result = interpreter.compileSources(source)
    if (result)
      Success(interpreter.classLoader)
    else
      Failure(new IllegalArgumentException(s"Unable to compile $classFile"))
  }

  @tailrec
  private def interpret(ops: List[() => Result], accumulate: Result): Result = {
    ops match {
      case List() => accumulate
      case x :: xs =>
        val result = x()
        result match {
          case InterpreterError | InterpreterIncomplete => result
          case InterpreterSuccess => interpret(xs, result)
        }
    }
  }

  private class ScalaCompilerError(message: String) extends Exception(message)
}

object ScalaInterpreter {

  def isVerbose: Boolean =
    System.getProperty("vertx.scala.interpreter.verbose", "false").toBoolean

}