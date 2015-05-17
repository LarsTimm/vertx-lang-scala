package io.vertx.lang.scala

import io.vertx.core.logging.Logger

import scala.concurrent.ExecutionContext

/**
 * @author <a href="http://www.campudus.com">Joern Bernhardt</a>.
 * @author <a href="mailto:larsdtimm@gmail.com">Lars Timm</a>
 */
object VertxExecutionContext {
  def apply(logger: Logger): ExecutionContext = new ExecutionContext {
    override def execute(runnable: Runnable): Unit = runnable.run()

    override def reportFailure(cause: Throwable): Unit = logger.error("failure in execution context", cause)
  }
}
