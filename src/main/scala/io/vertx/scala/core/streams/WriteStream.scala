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

package io.vertx.scala.core.streams;

import io.vertx.core.Handler

/**
  * Represents a stream of data that can be written to.
  * 
  * Any class that implements this interface can be used by a [[io.vertx.scala.core.streams.Pump]] to pump data from a `ReadStream`
  * to it.
  */
trait WriteStream[T] 
    extends io.vertx.scala.core.streams.StreamBase {

  def asJava: java.lang.Object

  /** Set an exception handler on the write stream.
  *
  * @param handler the exception handler
  * @return a reference to this, so the API can be used fluently
  */
  def exceptionHandler(handler: Throwable => Unit): io.vertx.scala.core.streams.WriteStream[T]

  /** Write some data to the stream. The data is put on an internal write queue, and the write actually happens
  * asynchronously. To avoid running out of memory by putting too much on the write queue,
  * check the [[io.vertx.scala.core.streams.WriteStream#writeQueueFull]] method before writing. This is done automatically if using a [[io.vertx.scala.core.streams.Pump]].
  *
  * @param data the data to write
  * @return a reference to this, so the API can be used fluently
  */
  def write(data: T): io.vertx.scala.core.streams.WriteStream[T]

  /** Set the maximum size of the write queue to `maxSize`. You will still be able to write to the stream even
  * if there is more than `maxSize` bytes in the write queue. This is used as an indicator by classes such as
  * `Pump` to provide flow control.
  *
  * @param maxSize the max size of the write stream
  * @return a reference to this, so the API can be used fluently
  */
  def setWriteQueueMaxSize(maxSize: Int): io.vertx.scala.core.streams.WriteStream[T]

  /** This will return `true` if there are more bytes in the write queue than the value set using [[io.vertx.scala.core.streams.WriteStream#setWriteQueueMaxSize]]
  *
  * @return true if write queue is full
  */
  def writeQueueFull(): Boolean

  /** Set a drain handler on the stream. If the write queue is full, then the handler will be called when the write
  * queue has been reduced to maxSize / 2. See [[io.vertx.scala.core.streams.Pump]] for an example of this being used.
  *
  * @param handler the handler
  * @return a reference to this, so the API can be used fluently
  */
  def drainHandler(handler: => Unit): io.vertx.scala.core.streams.WriteStream[T]

}

object WriteStream {

  def apply[T](_asJava: io.vertx.core.streams.WriteStream[T]): io.vertx.scala.core.streams.WriteStream[T] =
    new WriteStreamImpl[T](_asJava)

  private class WriteStreamImpl[T](private val _asJava: io.vertx.core.streams.WriteStream[T]) extends WriteStream[T] {

    def asJava: java.lang.Object = _asJava

    /** Set an exception handler on the write stream.
      *
      * @param handler the exception handler
      * @return a reference to this, so the API can be used fluently
      */
    def exceptionHandler(handler: Throwable => Unit): io.vertx.scala.core.streams.WriteStream[T] = {
      import io.vertx.lang.scala.HandlerOps._
      _asJava.exceptionHandler(funcToMappedHandler[java.lang.Throwable, Throwable](x => x)(handler))
      this
    }

    /** Write some data to the stream. The data is put on an internal write queue, and the write actually happens
      * asynchronously. To avoid running out of memory by putting too much on the write queue,
      * check the [[io.vertx.scala.core.streams.WriteStream#writeQueueFull]] method before writing. This is done automatically if using a [[io.vertx.scala.core.streams.Pump]].
      *
      * @param data the data to write
      * @return a reference to this, so the API can be used fluently
      */
    def write(data: T): io.vertx.scala.core.streams.WriteStream[T] = {
      _asJava.write(data)
      this
    }

    /** Set the maximum size of the write queue to `maxSize`. You will still be able to write to the stream even
      * if there is more than `maxSize` bytes in the write queue. This is used as an indicator by classes such as
      * `Pump` to provide flow control.
      *
      * @param maxSize the max size of the write stream
      * @return a reference to this, so the API can be used fluently
      */
    def setWriteQueueMaxSize(maxSize: Int): io.vertx.scala.core.streams.WriteStream[T] = {
      _asJava.setWriteQueueMaxSize(maxSize)
      this
    }

    /** This will return `true` if there are more bytes in the write queue than the value set using [[io.vertx.scala.core.streams.WriteStream#setWriteQueueMaxSize]]
      *
      * @return true if write queue is full
      */
    def writeQueueFull(): Boolean = {
      _asJava.writeQueueFull()
    }

    /** Set a drain handler on the stream. If the write queue is full, then the handler will be called when the write
      * queue has been reduced to maxSize / 2. See [[io.vertx.scala.core.streams.Pump]] for an example of this being used.
      *
      * @param handler the handler
      * @return a reference to this, so the API can be used fluently
      */
    def drainHandler(handler: => Unit): io.vertx.scala.core.streams.WriteStream[T] = {
      import io.vertx.lang.scala.HandlerOps._
      _asJava.drainHandler(funcToMappedHandler[java.lang.Void, Unit](x => x.asInstanceOf[Unit])(_ =>handler))
      this
    }

  }

}
