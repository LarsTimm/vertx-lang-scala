package io.vertx.lang.scala


import io.vertx.core.{ Vertx => JVertx }
import io.vertx.core.Verticle
import io.vertx.core.Future;
import io.vertx.core.spi.VerticleFactory

import io.vertx.scala.core.Vertx

import java.io.{PrintWriter, Writer, File, FilenameFilter}

import scala.tools.nsc.Settings
import scala.util.matching.Regex

import io.vertx.core.logging.Logger
import io.vertx.core.logging.impl.LoggerFactory
import io.vertx.lang.scala.interpreter.{ClassLoaders, ScalaInterpreter}
import java.net.URL
import java.security.{PrivilegedAction, AccessController}
import scala.util.{Success, Failure, Try}
import scala.annotation.tailrec 

/**
 * @author <a href="http://www.campudus.com/">Joern Bernhardt</a>
 * @author <a href="mailto:larsdtimm@gmail.com">Lars Timm</a>
 */
class ScalaVerticleFactory extends VerticleFactory {
  import ScalaVerticleFactory._

  private var vertx: Vertx = null

  override def init(vertx: JVertx): Unit = this.vertx = new Vertx(vertx)

  override def prefix(): String = "scala"

  override def close(): Unit = this.vertx = null

  override def createVerticle(verticleName: String, classLoader: ClassLoader): Verticle = {
    val name = VerticleFactory.removePrefix(verticleName);
    if (name.endsWith(".scala")) {
      verticleFromSource(name, classLoader).get.asJava()
    } else {
      verticleFromClass(name, classLoader).get.asJava()
    }
  }

  private def verticleFromClass(verticleName: String, classLoader: ClassLoader): Try[ScalaVerticle] = {
    ClassLoaders.newInstance(verticleName, classLoader)
  }
  
  private def verticleFromSource(verticleName: String, classLoader: ClassLoader): Try[ScalaVerticle] = {
    val settings = interpreterSettings(classLoader)
    val interpreter = new ScalaInterpreter(
      settings.get, vertx, new LogPrintWriter(logger))

    try {
      runAsScript(verticleName, interpreter, classLoader).recoverWith { case _ =>
        // Recover by trying to compile it as a Scala class
        logger.info(s"Script contains compilation errors, or $verticleName is a Scala class (pass -Dvertx.scala.interpreter.verbose=true to find out more)")
        logger.info(s"Compiling as a Scala class")

        val className = extractClassName(verticleName)
        val classFile = getClassFile(verticleName, classLoader)

        for {
          classLoader <- interpreter.compileClass(classFile)
          verticle <- newVerticleInstance(className, classLoader)
        } yield {
          logger.info(s"Starting $className")
          verticle
        }
      }
    } finally {
      interpreter.close()
    }
  }

  private def resolveVerticlePath(main: String, classLoader: ClassLoader): Try[URL] = {
    // Check if path exists
    val file = new File(main)
    if (file.exists())
      Success(file.toURI.toURL)
    else {
      Option(classLoader.getResource(main)) match {
        case None => Failure(new IllegalArgumentException(
            s"Cannot find main script: '$main' on classpath"))
        case Some(res) => Success(res)
      }
    }
  }      

  private def runAsScript(verticleName: String, interpreter: ScalaInterpreter, classLoader: ClassLoader): Try[ScalaVerticle] = {
    logger.info(s"Compiling $verticleName as Scala script")
    // Try running it as a script
    for {
      url <- resolveVerticlePath(verticleName, classLoader)
      result <- interpreter.runScript(url)
    } yield {
      logger.info(s"Starting $verticleName")
      DummyVerticle
    }
  }

  private def interpreterSettings(classLoader: ClassLoader): Try[Settings] = {
    val settings = new Settings()

    for {
      jar <- findAll(classLoader, "lib", JarFileRegex)
    } yield {
      logger.debug(s"Add $jar to compiler boot classpath")
      settings.bootclasspath.append(jar.getAbsolutePath)
    }

    val moduleLocation = getRootModuleLocation
    moduleLocation match {
      case None =>
        Failure(new IllegalStateException("Unable to resolve mod-lang-scala root location"))
      case Some(loc) =>
        settings.bootclasspath.append(loc.getAbsolutePath)
        settings.usejavacp.value = true
        settings.verbose.value = ScalaInterpreter.isVerbose
        Success(settings)
    }
  }

  private def getRootModuleLocation: Option[File] = {
    AccessController.doPrivileged(new PrivilegedAction[Option[File]]() {
      def run(): Option[File] = {
        for {
          pd <- Option(classOf[ScalaVerticleFactory].getProtectionDomain)
          cs <- Option(pd.getCodeSource)
          loc <- Option(cs.getLocation)
        } yield new File(loc.toURI)
      }
    })
  }

  private def extractClassName(verticleName: String): String =
    verticleName.replaceFirst(".scala$", "").replaceAll("/", ".")

  private def getClassFile(verticleName: String, classLoader: ClassLoader): File = {
    Option(classLoader.getResource(verticleName)) match {
      case None => new File(verticleName)
      case Some(resource) =>
        new File(resource.toExternalForm.replaceFirst("file:", ""))
    }
  }

  private def newVerticleInstance(className: String, classLoader: ClassLoader): Try[ScalaVerticle] = {
    // If class not found, try to deduce a shorter name in case a system path was passed
    // Could have used recoverWith but you lose tail recursion, so using pattern matching
    @tailrec
    def searchVerticleInstance(className: String, classLoader: ClassLoader): Try[ScalaVerticle] = {
      val instanceTry = ClassLoaders.newInstance(className, classLoader)
      instanceTry match {
        case Success(verticle) => instanceTry
        case Failure(e: ClassNotFoundException) =>
          val dot = className.indexOf('.')
          if (dot < 0) instanceTry
          else {
            val shorterClassName = className.substring(dot + 1)
            logger.debug(s"Class not found, try with $shorterClassName")
            searchVerticleInstance(shorterClassName, classLoader)
          }
        case Failure(t) => instanceTry
      }
    }

    searchVerticleInstance(className, classLoader).recoverWith { case _ =>
      Failure(new ClassNotFoundException(
        s"Class $className not found, nor any shortened versions"))
    }
  }

  private case object DummyVerticle extends ScalaVerticle
}

object ScalaVerticleFactory {

  val logger = LoggerFactory.getLogger(classOf[ScalaVerticleFactory])
  
  val JarFileRegex = "^(.*\\.jar)$".r

  /**
   * Find all files matching the given regular expression in the directory.
   * Note that the search is not recursive.
   *
   * @param directory File denoting directory to search files in
   * @param regex regular expression to match
   * @return an [[scala.Array[File]] representing the collection of files matching
   *         the regular expression
   */
  def findAll(directory: File, regex: Regex): Array[File] = {
    // Protect against null return from listing files
    val files: Array[File] = directory.listFiles(new FilenameFilter {
      def accept(dir: File, name: String): Boolean = {
        regex.findFirstIn(name).isDefined
      }
    })
    Option(files).getOrElse(Array[File]())
  }

  /**
   * Find all files matching the given regular expression in a path within a
   * classloader. Note that the search is not recursive.
   *
   * @param classLoader class repository where to look for files
   * @param path String representing the path within the classloader where to look for files
   * @param regex regular expression to match
   * @return an [[scala.Array[File]] representing the collection of files matching
   *         the regular expression
   */
  def findAll(classLoader: ClassLoader, path: String, regex: Regex): Array[File] = {
    if (classLoader.getResources(path).hasMoreElements) {
      findAll(new File(classLoader.getResources(path).nextElement().toURI), regex)
    } else {
      Array[File]()
    }
  }

}

private class LogPrintWriter(logger: Logger)
  extends PrintWriter(new LogWriter(logger), true)

private class LogWriter(logger: Logger) extends Writer {
  override def close(): Unit = {}
  override def flush(): Unit = {}
  override def write(str: String): Unit = logger.info(str)
  override def write(cbuf: Array[Char], off: Int, len: Int): Unit = {
    if (len > 0)
      write(new String(cbuf.slice(off, off+len)))
  }
} 