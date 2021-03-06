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

package io.vertx.scala.core.datagram;

import io.vertx.scala.core.buffer.Buffer
import io.vertx.scala.core.metrics.Measured
import io.vertx.scala.core.streams.ReadStream
import io.vertx.core.Handler
import io.vertx.scala.core.net.SocketAddress

/** A datagram socket can be used to send [[io.vertx.scala.core.datagram.DatagramPacket]]'s to remote datagram servers
  * and receive [[io.vertx.scala.core.datagram.DatagramPacket]]s .
  * 
  * Usually you use a datagram socket to send UDP over the wire. UDP is connection-less which means you are not connected
  * to the remote peer in a persistent way. Because of this you have to supply the address and port of the remote peer
  * when sending data.
  * 
  * You can send data to ipv4 or ipv6 addresses, which also include multicast addresses.
  * 
  * Please consult the documentation for more information on datagram sockets.
  */
class DatagramSocket(private val _asJava: io.vertx.core.datagram.DatagramSocket) 
    extends io.vertx.scala.core.streams.ReadStream[io.vertx.scala.core.datagram.DatagramPacket] 
    with io.vertx.scala.core.metrics.Measured {

  def asJava: java.lang.Object = _asJava

  /** Whether the metrics are enabled for this measured object
    *
    * @return true if the metrics are enabled
    */
  def isMetricsEnabled(): Boolean = {
    _asJava.isMetricsEnabled()
  }

  /** Write the given [[io.vertx.scala.core.buffer.Buffer]] to the [[io.vertx.scala.core.net.SocketAddress]].
    * The function will be notified once the write completes.
    *
    * @param packet the [[io.vertx.scala.core.buffer.Buffer]] to write
    * @param port the host port of the remote peer
    * @param host the host address of the remote peer
    * @return the function to notify once the write completes.
    */
  def send(packet: io.vertx.scala.core.buffer.Buffer, port: Int, host: String): scala.concurrent.Future[io.vertx.scala.core.datagram.DatagramSocket] = {
    import io.vertx.lang.scala.HandlerOps._
    val promise = scala.concurrent.Promise[io.vertx.scala.core.datagram.DatagramSocket]()
    _asJava.send(packet.asJava.asInstanceOf[io.vertx.core.buffer.Buffer], port, host, promiseToMappedAsyncResultHandler(DatagramSocket.apply)(promise))
    promise.future
  }

  /** Returns a [[io.vertx.scala.core.datagram.PacketWritestream]] able to send [[Buffer]] to the
    * [[io.vertx.scala.core.net.SocketAddress]].
    *
    * @param port the port of the remote peer
    * @param host the host address of the remote peer
    * @return the write stream for sending packets
    */
  def sender(port: Int, host: String): io.vertx.scala.core.datagram.PacketWritestream = {
    PacketWritestream.apply(_asJava.sender(port, host))
  }

  /** Write the given [[String]] to the [[io.vertx.scala.core.net.SocketAddress]] using UTF8 encoding.
    * The function will be notified once the write completes.
    *
    * @param str the [[String]] to write
    * @param port the host port of the remote peer
    * @param host the host address of the remote peer
    * @return the function to notify once the write completes.
    */
  def send(str: String, port: Int, host: String): scala.concurrent.Future[io.vertx.scala.core.datagram.DatagramSocket] = {
    import io.vertx.lang.scala.HandlerOps._
    val promise = scala.concurrent.Promise[io.vertx.scala.core.datagram.DatagramSocket]()
    _asJava.send(str, port, host, promiseToMappedAsyncResultHandler(DatagramSocket.apply)(promise))
    promise.future
  }

  /** Write the given [[String]] to the [[io.vertx.scala.core.net.SocketAddress]] using the given encoding.
    * The function will be notified once the write completes.
    *
    * @param str the [[String]] to write
    * @param enc the charset used for encoding
    * @param port the host port of the remote peer
    * @param host the host address of the remote peer
    * @return the function to notify once the write completes.
    */
  def send(str: String, enc: String, port: Int, host: String): scala.concurrent.Future[io.vertx.scala.core.datagram.DatagramSocket] = {
    import io.vertx.lang.scala.HandlerOps._
    val promise = scala.concurrent.Promise[io.vertx.scala.core.datagram.DatagramSocket]()
    _asJava.send(str, enc, port, host, promiseToMappedAsyncResultHandler(DatagramSocket.apply)(promise))
    promise.future
  }

  /** Closes the [[io.vertx.scala.core.datagram.DatagramSocket]] implementation asynchronous
    * and notifies the handler once done.
    *
    * @return the handler to notify once complete
    */
  def close(): scala.concurrent.Future[Unit] = {
    import io.vertx.lang.scala.HandlerOps._
    val promise = scala.concurrent.Promise[Unit]()
    _asJava.close(promiseToMappedAsyncResultHandler[java.lang.Void, Unit](x => x.asInstanceOf[Unit])(promise))
    promise.future
  }

  /** Return the [[io.vertx.scala.core.net.SocketAddress]] to which
    * this [[io.vertx.scala.core.datagram.DatagramSocket]] is bound.
    *
    * @return the socket address
    */
  def localAddress(): io.vertx.scala.core.net.SocketAddress = {
    SocketAddress.apply(_asJava.localAddress())
  }

  /** Joins a multicast group and listens for packets send to it.
    * The function is notified once the operation completes.
    *
    * @param multicastAddress the address of the multicast group to join
    * @return then handler to notify once the operation completes
    */
  def listenMulticastGroup(multicastAddress: String): scala.concurrent.Future[io.vertx.scala.core.datagram.DatagramSocket] = {
    import io.vertx.lang.scala.HandlerOps._
    val promise = scala.concurrent.Promise[io.vertx.scala.core.datagram.DatagramSocket]()
    _asJava.listenMulticastGroup(multicastAddress, promiseToMappedAsyncResultHandler(DatagramSocket.apply)(promise))
    promise.future
  }

  /** Joins a multicast group and listens for packets send to it on the given network interface.
    * The function is notified once the operation completes.
    *
    * @param multicastAddress the address of the multicast group to join
    * @param networkInterface the network interface on which to listen for packets.
    * @param source the address of the source for which we will listen for multicast packets
    * @return then handler to notify once the operation completes
    */
  def listenMulticastGroup(multicastAddress: String, networkInterface: String, source: String): scala.concurrent.Future[io.vertx.scala.core.datagram.DatagramSocket] = {
    import io.vertx.lang.scala.HandlerOps._
    val promise = scala.concurrent.Promise[io.vertx.scala.core.datagram.DatagramSocket]()
    _asJava.listenMulticastGroup(multicastAddress, networkInterface, source, promiseToMappedAsyncResultHandler(DatagramSocket.apply)(promise))
    promise.future
  }

  /** Leaves a multicast group and stops listening for packets send to it.
    * The function is notified once the operation completes.
    *
    * @param multicastAddress the address of the multicast group to leave
    * @return then handler to notify once the operation completes
    */
  def unlistenMulticastGroup(multicastAddress: String): scala.concurrent.Future[io.vertx.scala.core.datagram.DatagramSocket] = {
    import io.vertx.lang.scala.HandlerOps._
    val promise = scala.concurrent.Promise[io.vertx.scala.core.datagram.DatagramSocket]()
    _asJava.unlistenMulticastGroup(multicastAddress, promiseToMappedAsyncResultHandler(DatagramSocket.apply)(promise))
    promise.future
  }

  /** Leaves a multicast group and stops listening for packets send to it on the given network interface.
    * The function is notified once the operation completes.
    *
    * @param multicastAddress the address of the multicast group to join
    * @param networkInterface the network interface on which to listen for packets.
    * @param source the address of the source for which we will listen for multicast packets
    * @return the handler to notify once the operation completes
    */
  def unlistenMulticastGroup(multicastAddress: String, networkInterface: String, source: String): scala.concurrent.Future[io.vertx.scala.core.datagram.DatagramSocket] = {
    import io.vertx.lang.scala.HandlerOps._
    val promise = scala.concurrent.Promise[io.vertx.scala.core.datagram.DatagramSocket]()
    _asJava.unlistenMulticastGroup(multicastAddress, networkInterface, source, promiseToMappedAsyncResultHandler(DatagramSocket.apply)(promise))
    promise.future
  }

  /** Block the given address for the given multicast address and notifies the function once
    * the operation completes.
    *
    * @param multicastAddress the address for which you want to block the source address
    * @param sourceToBlock the source address which should be blocked. You will not receive an multicast packets for it anymore.
    * @return the handler to notify once the operation completes
    */
  def blockMulticastGroup(multicastAddress: String, sourceToBlock: String): scala.concurrent.Future[io.vertx.scala.core.datagram.DatagramSocket] = {
    import io.vertx.lang.scala.HandlerOps._
    val promise = scala.concurrent.Promise[io.vertx.scala.core.datagram.DatagramSocket]()
    _asJava.blockMulticastGroup(multicastAddress, sourceToBlock, promiseToMappedAsyncResultHandler(DatagramSocket.apply)(promise))
    promise.future
  }

  /** Block the given address for the given multicast address on the given network interface and notifies
    * the function once the operation completes.
    *
    * @param multicastAddress the address for which you want to block the source address
    * @param networkInterface the network interface on which the blocking should occur.
    * @param sourceToBlock the source address which should be blocked. You will not receive an multicast packets for it anymore.
    * @return the handler to notify once the operation completes
    */
  def blockMulticastGroup(multicastAddress: String, networkInterface: String, sourceToBlock: String): scala.concurrent.Future[io.vertx.scala.core.datagram.DatagramSocket] = {
    import io.vertx.lang.scala.HandlerOps._
    val promise = scala.concurrent.Promise[io.vertx.scala.core.datagram.DatagramSocket]()
    _asJava.blockMulticastGroup(multicastAddress, networkInterface, sourceToBlock, promiseToMappedAsyncResultHandler(DatagramSocket.apply)(promise))
    promise.future
  }

  /** Start listening on the given port and host. The handler will be called when the socket is listening.
    *
    * @param port the port to listen on
    * @param host the host to listen on
    * @return the handler will be called when listening
    */
  def listen(port: Int, host: String): scala.concurrent.Future[io.vertx.scala.core.datagram.DatagramSocket] = {
    import io.vertx.lang.scala.HandlerOps._
    val promise = scala.concurrent.Promise[io.vertx.scala.core.datagram.DatagramSocket]()
    _asJava.listen(port, host, promiseToMappedAsyncResultHandler(DatagramSocket.apply)(promise))
    promise.future
  }

  def pause(): io.vertx.scala.core.datagram.DatagramSocket = {
    _asJava.pause()
    this
  }

  def resume(): io.vertx.scala.core.datagram.DatagramSocket = {
    _asJava.resume()
    this
  }

  def endHandler(endHandler: => Unit): io.vertx.scala.core.datagram.DatagramSocket = {
    import io.vertx.lang.scala.HandlerOps._
    _asJava.endHandler(funcToMappedHandler[java.lang.Void, Unit](x => x.asInstanceOf[Unit])(_ =>endHandler))
    this
  }

  def handler(handler: io.vertx.scala.core.datagram.DatagramPacket => Unit): io.vertx.scala.core.datagram.DatagramSocket = {
    import io.vertx.lang.scala.HandlerOps._
    _asJava.handler(funcToMappedHandler(DatagramPacket.apply)(handler))
    this
  }

  def exceptionHandler(handler: Throwable => Unit): io.vertx.scala.core.datagram.DatagramSocket = {
    import io.vertx.lang.scala.HandlerOps._
    _asJava.exceptionHandler(funcToMappedHandler[java.lang.Throwable, Throwable](x => x)(handler))
    this
  }

}

object DatagramSocket {

  def apply(_asJava: io.vertx.core.datagram.DatagramSocket): io.vertx.scala.core.datagram.DatagramSocket =
    new io.vertx.scala.core.datagram.DatagramSocket(_asJava)
}
