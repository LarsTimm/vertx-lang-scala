package io.vertx.lang.scala

import io.vertx.core.{AbstractVerticle, Future, Verticle}
import io.vertx.lang.scala.VertxExecutionContext._
import io.vertx.scala.core.{Context, Vertx}
import scala.concurrent.Promise
import scala.util.{Success, Failure}

/**
  * A verticle is a piece of code that can be deployed by Vert.x.
  *
  * Use of verticles with Vert.x is entirely optional, but if you use them they provide an <i>actor-like</i>
  * deployment and concurrency model, out of the box.
  *
  * Vert.x does not provide a strict actor implementation, but there are significant similarities.
  *
  * You can think of verticle instances as a bit like actors in the Actor Model. A typical verticle-based Vert.x application
  * will be composed of many verticle instances in each Vert.x instance.
  *
  * The verticles communicate with each other by sending messages over the {@link io.vertx.scala.core.eventbus.EventBus}.
  *
  * @author <a href="mailto:larsdtimm@gmail.com">Lars Timm</a>
  */
class ScalaVerticle {

  protected var vertx: Vertx = null
  protected var context: Context = null

  implicit val executionContext = VertxExecutionContext()

  /** Get a reference to the Vert.x instance that deployed this verticle
    *
    * @return A reference to the Vert.x instance
    */
  def getVertx: io.vertx.scala.core.Vertx = vertx

  /** Initialise the verticle.
    *  
    * This is called by Vert.x when the verticle instance is deployed. Don't call it yourself.
    * 
    * @param vertx  the deploying Vert.x instance
    * @param context  the context of the verticle
    */
  def init(vertx: Vertx, context: Context): Unit = {
    this.vertx = vertx
    this.context = context
  }

  /** Start the verticle instance.
    *
    * Vert.x calls this method when deploying the instance. You do not call it yourself.
    *
    * A promise is passed into the method, and when deployment is complete the verticle should either call
    * {@link scala.concurrent.Promise#success} or {@link scala.concurrent.Promise#failure}.
    *
    * @param promise  Promise that should be completed when verticle is started
    */
  def start(promise: Promise[Unit]): Unit = promise.success(Unit)

  /** Stop the verticle.
    *  
    * This is called by Vert.x when the verticle instance is un-deployed. Don't call it yourself.
    * 
    * If your verticle does things in it's shut-down which take some time then you can override this method
    * and call the promise some time later when clean-up is complete.
    * 
    * @param promise  Promise that should be completed when verticle clean-up is done.
    * @throws Exception
    */
  def stop(promise: Promise[Unit]): Unit = promise.success(Unit)

  def asJava(): Verticle = new AbstractVerticle {
    override def init(vertx: io.vertx.core.Vertx, context: io.vertx.core.Context): Unit = {
      super.init(vertx, context)
      ScalaVerticle.this.init(Vertx(vertx), Context(context))
    }

    override def start(startFuture: Future[Void]): Unit = {
      val p = Promise[Unit]()
      ScalaVerticle.this.start(p)
      p.future.onComplete {
        case Success(_) =>
          startFuture.complete()
        case Failure(x) =>
          startFuture.fail(x)
      }
    } 

    override def stop(stopFuture: Future[Void]): Unit = {
      val p = Promise[Unit]()
      ScalaVerticle.this.stop(p) 
      p.future.onComplete {
        case Success(_) =>
          stopFuture.complete()
        case Failure(x) =>
          stopFuture.fail(x)
      }
    }
  }
}
