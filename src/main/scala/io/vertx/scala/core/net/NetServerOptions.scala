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

package io.vertx.scala.core.net;

import io.vertx.scala.core.buffer.Buffer

/** Options for configuring a [[io.vertx.scala.core.net.NetServer]].
  */
class NetServerOptions(val asJava: io.vertx.core.net.NetServerOptions){

  /** Set the accept back log
    */
  def acceptBacklog_=(x: Int): Unit = {
    asJava.setAcceptBacklog(x)
  }

  def acceptBacklog: Int = {
    asJava.getAcceptBacklog()
  }

  /** Set whether client auth is required
    */
  def clientAuthRequired_=(x: Boolean): Unit = {
    asJava.setClientAuthRequired(x)
  }

  def clientAuthRequired: Boolean = {
    asJava.isClientAuthRequired()
  }

  /** Add a CRL path
    */
  def addCrlPath(x: String): Unit = {
    import scala.collection.JavaConverters._
    asJava.addCrlPath(x)
  }

  /** Add a CRL value
    */
  def addCrlValue(x: io.vertx.scala.core.buffer.Buffer): Unit = {
    import scala.collection.JavaConverters._
    asJava.addCrlValue(x.asJava.asInstanceOf[io.vertx.core.buffer.Buffer])
  }

  /** Add an enabled cipher suite
    */
  def addEnabledCipherSuite(x: String): Unit = {
    import scala.collection.JavaConverters._
    asJava.addEnabledCipherSuite(x)
  }

  /** Set the host
    */
  def host_=(x: String): Unit = {
    asJava.setHost(x)
  }

  def host: String = {
    asJava.getHost()
  }

  /** Set the idle timeout, in seconds. zero means don't timeout.
    * This determines if a connection will timeout and be closed if no data is received within the timeout.
    */
  def idleTimeout_=(x: Int): Unit = {
    asJava.setIdleTimeout(x)
  }

  def idleTimeout: Int = {
    asJava.getIdleTimeout()
  }

  /** Set the key/cert options in jks format, aka Java keystore.
    */
  def setKeyStoreOptions(x: JksOptions): Unit = {
    asJava.setKeyStoreOptions(x.asJava)
  }

  /** Set the key/cert store options in pem format.
    */
  def setPemKeyCertOptions(x: PemKeyCertOptions): Unit = {
    asJava.setPemKeyCertOptions(x.asJava)
  }

  /** Set the trust options in pem format
    */
  def setPemTrustOptions(x: PemTrustOptions): Unit = {
    asJava.setPemTrustOptions(x.asJava)
  }

  /** Set the key/cert options in pfx format.
    */
  def setPfxKeyCertOptions(x: PfxOptions): Unit = {
    asJava.setPfxKeyCertOptions(x.asJava)
  }

  /** Set the trust options in pfx format
    */
  def setPfxTrustOptions(x: PfxOptions): Unit = {
    asJava.setPfxTrustOptions(x.asJava)
  }

  /** Set the port
    */
  def port_=(x: Int): Unit = {
    asJava.setPort(x)
  }

  def port: Int = {
    asJava.getPort()
  }

  /** Set the TCP receive buffer size
    */
  def receiveBufferSize_=(x: Int): Unit = {
    asJava.setReceiveBufferSize(x)
  }

  def receiveBufferSize: Int = {
    asJava.getReceiveBufferSize()
  }

  /** Set the value of reuse address
    */
  def reuseAddress_=(x: Boolean): Unit = {
    asJava.setReuseAddress(x)
  }

  def reuseAddress: Boolean = {
    asJava.isReuseAddress()
  }

  /** Set the TCP send buffer size
    */
  def sendBufferSize_=(x: Int): Unit = {
    asJava.setSendBufferSize(x)
  }

  def sendBufferSize: Int = {
    asJava.getSendBufferSize()
  }

  /** Set whether SO_linger keep alive is enabled
    */
  def soLinger_=(x: Int): Unit = {
    asJava.setSoLinger(x)
  }

  def soLinger: Int = {
    asJava.getSoLinger()
  }

  /** Set whether SSL/TLS is enabled
    */
  def ssl_=(x: Boolean): Unit = {
    asJava.setSsl(x)
  }

  def ssl: Boolean = {
    asJava.isSsl()
  }

  /** Set whether TCP keep alive is enabled
    */
  def tcpKeepAlive_=(x: Boolean): Unit = {
    asJava.setTcpKeepAlive(x)
  }

  def tcpKeepAlive: Boolean = {
    asJava.isTcpKeepAlive()
  }

  /** Set whether TCP no delay is enabled
    */
  def tcpNoDelay_=(x: Boolean): Unit = {
    asJava.setTcpNoDelay(x)
  }

  def tcpNoDelay: Boolean = {
    asJava.isTcpNoDelay()
  }

  /** Set the value of traffic class
    */
  def trafficClass_=(x: Int): Unit = {
    asJava.setTrafficClass(x)
  }

  def trafficClass: Int = {
    asJava.getTrafficClass()
  }

  /** Set the trust options in jks format, aka Java trustore
    */
  def setTrustStoreOptions(x: JksOptions): Unit = {
    asJava.setTrustStoreOptions(x.asJava)
  }

  /** Set whether Netty pooled buffers are enabled
    */
  def usePooledBuffers_=(x: Boolean): Unit = {
    asJava.setUsePooledBuffers(x)
  }

  def usePooledBuffers: Boolean = {
    asJava.isUsePooledBuffers()
  }

}

object NetServerOptions {

  def apply(asJava: io.vertx.core.net.NetServerOptions): NetServerOptions =
    new NetServerOptions(asJava)

  /** Default constructor
    */
  def apply() =
    new NetServerOptions(new io.vertx.core.net.NetServerOptions)

  /** Copy constructor
    *
    * @param other the instance to copy
    */
  def apply(other: NetServerOptions) =
    new NetServerOptions(new io.vertx.core.net.NetServerOptions(other.asJava))

  /** Constructor for creating a instance from JSON
    *
    * @param json the JSON
    */
  def apply(json: io.vertx.core.json.JsonObject) =
    new NetServerOptions(new io.vertx.core.net.NetServerOptions(json))

}
