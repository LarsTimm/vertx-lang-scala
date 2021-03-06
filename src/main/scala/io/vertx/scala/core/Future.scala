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

package io.vertx.scala.core;

import io.vertx.core.Handler

/** Represents the result of an action that may, or may not, have occurred yet.
  * 
  */
class Future[T](private val _asJava: io.vertx.core.Future[T]) {

  def asJava: java.lang.Object = _asJava

  /** Has the future completed?
    * 
    * It's completed if it's either succeeded or failed.
    *
    * @return true if completed, false if not
    */
  def isComplete(): Boolean = {
    _asJava.isComplete()
  }

  /** Set a handler for the result.
    * 
    * If the future has already been completed it will be called immediately. Otherwise it will be called when the
    * future is completed.
    *
    * @return the Handler that will be called with the result
    */
  def setHandler(): scala.concurrent.Future[T] = {
    import io.vertx.lang.scala.HandlerOps._
    val promise = scala.concurrent.Promise[T]()
    _asJava.setHandler(promiseToAsyncResultHandler(promise))
    promise.future
  }

  /** Set the result. Any handler will be called, if there is one, and the future will be marked as completed.
    *
    * @param result the result
    */
  def complete(result: T): Unit = {
    _asJava.complete(result)
  }

  /** Set a null result. Any handler will be called, if there is one, and the future will be marked as completed.
    */
  def complete(): Unit = {
    _asJava.complete()
  }

  /** Set the failure. Any handler will be called, if there is one, and the future will be marked as completed.
    *
    * @param failureMessage the failure message
    */
  def fail(failureMessage: String): Unit = {
    _asJava.fail(failureMessage)
  }

}

object Future {

  def apply[T](_asJava: io.vertx.core.Future[T]): io.vertx.scala.core.Future[T] =
    new io.vertx.scala.core.Future[T](_asJava)

  /** Create a future that hasn't completed yet
    *
    * @return the future
    */
  def future[T](): io.vertx.scala.core.Future[T] = {
    Future.apply[T](io.vertx.core.Future.future())
  }

  /** Create a succeeded future with a null result
    *
    * @return the future
    */
  def succeededFuture[T](): io.vertx.scala.core.Future[T] = {
    Future.apply[T](io.vertx.core.Future.succeededFuture())
  }

  /** Created a succeeded future with the specified result.
    *
    * @param result the result
    * @return the future
    */
  def succeededFuture[T](result: T): io.vertx.scala.core.Future[T] = {
    Future.apply[T](io.vertx.core.Future.succeededFuture(result))
  }

  /** Create a failed future with the specified failure message.
    *
    * @param failureMessage the failure message
    * @return the future
    */
  def failedFuture[T](failureMessage: String): io.vertx.scala.core.Future[T] = {
    Future.apply[T](io.vertx.core.Future.failedFuture(failureMessage))
  }
}
