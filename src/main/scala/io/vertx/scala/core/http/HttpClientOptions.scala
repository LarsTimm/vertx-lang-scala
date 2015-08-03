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

package io.vertx.scala.core.http;

import io.vertx.scala.core.buffer.Buffer
import io.vertx.scala.core.net.JksOptions
import io.vertx.scala.core.net.PemKeyCertOptions
import io.vertx.scala.core.net.PemTrustOptions
import io.vertx.scala.core.net.PfxOptions
import io.vertx.scala.core.net.ClientOptionsBase

/** Options describing how an [[io.vertx.scala.core.http.HttpClient]] will make connections.
  */
class HttpClientOptions(val asJava: io.vertx.core.http.HttpClientOptions){

  /** Set the connect timeout
    */
  def connectTimeout_=(x: Int): Unit = {
    asJava.setConnectTimeout(x)
  }

  def connectTimeout: Int = {
    asJava.getConnectTimeout()
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

  /** Set the default host name to be used by this client in requests if none is provided when making the request.
    */
  def defaultHost_=(x: String): Unit = {
    asJava.setDefaultHost(x)
  }

  def defaultHost: String = {
    asJava.getDefaultHost()
  }

  /** Set the default port to be used by this client in requests if none is provided when making the request.
    */
  def defaultPort_=(x: Int): Unit = {
    asJava.setDefaultPort(x)
  }

  def defaultPort: Int = {
    asJava.getDefaultPort()
  }

  /** Add an enabled cipher suite
    */
  def addEnabledCipherSuite(x: String): Unit = {
    import scala.collection.JavaConverters._
    asJava.addEnabledCipherSuite(x)
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

  /** Set whether keep alive is enabled on the client
    */
  def keepAlive_=(x: Boolean): Unit = {
    asJava.setKeepAlive(x)
  }

  def keepAlive: Boolean = {
    asJava.isKeepAlive()
  }

  /** Set the key/cert options in jks format, aka Java keystore.
    */
  def setKeyStoreOptions(x: JksOptions): Unit = {
    asJava.setKeyStoreOptions(x.asJava)
  }

  /** Set the maximum pool size for connections
    */
  def maxPoolSize_=(x: Int): Unit = {
    asJava.setMaxPoolSize(x)
  }

  def maxPoolSize: Int = {
    asJava.getMaxPoolSize()
  }

  /** Set the max websocket frame size
    */
  def maxWebsocketFrameSize_=(x: Int): Unit = {
    asJava.setMaxWebsocketFrameSize(x)
  }

  def maxWebsocketFrameSize: Int = {
    asJava.getMaxWebsocketFrameSize()
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

  /** Set whether pipe-lining is enabled on the client
    */
  def pipelining_=(x: Boolean): Unit = {
    asJava.setPipelining(x)
  }

  def pipelining: Boolean = {
    asJava.isPipelining()
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

  /** Set whether all server certificates should be trusted
    */
  def trustAll_=(x: Boolean): Unit = {
    asJava.setTrustAll(x)
  }

  def trustAll: Boolean = {
    asJava.isTrustAll()
  }

  /** Set the trust options in jks format, aka Java trustore
    */
  def setTrustStoreOptions(x: JksOptions): Unit = {
    asJava.setTrustStoreOptions(x.asJava)
  }

  /** Set whether compression is enabled
    */
  def tryUseCompression_=(x: Boolean): Unit = {
    asJava.setTryUseCompression(x)
  }

  def tryUseCompression: Boolean = {
    asJava.isTryUseCompression()
  }

  /** Set whether Netty pooled buffers are enabled
    */
  def usePooledBuffers_=(x: Boolean): Unit = {
    asJava.setUsePooledBuffers(x)
  }

  def usePooledBuffers: Boolean = {
    asJava.isUsePooledBuffers()
  }

  /** Set whether hostname verification is enabled
    */
  def verifyHost_=(x: Boolean): Unit = {
    asJava.setVerifyHost(x)
  }

  def verifyHost: Boolean = {
    asJava.isVerifyHost()
  }

}

object HttpClientOptions {

  def apply(asJava: io.vertx.core.http.HttpClientOptions): HttpClientOptions =
    new HttpClientOptions(asJava)

  /** Default constructor
    */
  def apply() =
    new HttpClientOptions(new io.vertx.core.http.HttpClientOptions)

  /** Copy constructor
    *
    * @param other the instance to copy
    */
  def apply(other: HttpClientOptions) =
    new HttpClientOptions(new io.vertx.core.http.HttpClientOptions(other.asJava))

  /** Constructor for creating a instance from JSON
    *
    * @param json the JSON
    */
  def apply(json: io.vertx.core.json.JsonObject) =
    new HttpClientOptions(new io.vertx.core.http.HttpClientOptions(json))

}
