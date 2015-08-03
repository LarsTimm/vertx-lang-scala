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

/** Base class for Client options
  */
trait ClientOptionsBase{

  /** Set the connect timeout
    */
  def connectTimeout_=(x: Int): Unit

  def connectTimeout: Int

  /** Add a CRL path
    */
  def addCrlPath(x: String): Unit

  /** Add a CRL value
    */
  def addCrlValue(x: io.vertx.scala.core.buffer.Buffer): Unit

  /** Add an enabled cipher suite
    */
  def addEnabledCipherSuite(x: String): Unit

  /** Set the idle timeout, in seconds. zero means don't timeout.
    * This determines if a connection will timeout and be closed if no data is received within the timeout.
    */
  def idleTimeout_=(x: Int): Unit

  def idleTimeout: Int

  /** Set the key/cert options in jks format, aka Java keystore.
    */
  def setKeyStoreOptions(x: JksOptions): Unit

  /** Set the key/cert store options in pem format.
    */
  def setPemKeyCertOptions(x: PemKeyCertOptions): Unit

  /** Set the trust options in pem format
    */
  def setPemTrustOptions(x: PemTrustOptions): Unit

  /** Set the key/cert options in pfx format.
    */
  def setPfxKeyCertOptions(x: PfxOptions): Unit

  /** Set the trust options in pfx format
    */
  def setPfxTrustOptions(x: PfxOptions): Unit

  /** Set the TCP receive buffer size
    */
  def receiveBufferSize_=(x: Int): Unit

  def receiveBufferSize: Int

  /** Set the value of reuse address
    */
  def reuseAddress_=(x: Boolean): Unit

  def reuseAddress: Boolean

  /** Set the TCP send buffer size
    */
  def sendBufferSize_=(x: Int): Unit

  def sendBufferSize: Int

  /** Set whether SO_linger keep alive is enabled
    */
  def soLinger_=(x: Int): Unit

  def soLinger: Int

  /** Set whether SSL/TLS is enabled
    */
  def ssl_=(x: Boolean): Unit

  def ssl: Boolean

  /** Set whether TCP keep alive is enabled
    */
  def tcpKeepAlive_=(x: Boolean): Unit

  def tcpKeepAlive: Boolean

  /** Set whether TCP no delay is enabled
    */
  def tcpNoDelay_=(x: Boolean): Unit

  def tcpNoDelay: Boolean

  /** Set the value of traffic class
    */
  def trafficClass_=(x: Int): Unit

  def trafficClass: Int

  /** Set whether all server certificates should be trusted
    */
  def trustAll_=(x: Boolean): Unit

  def trustAll: Boolean

  /** Set the trust options in jks format, aka Java trustore
    */
  def setTrustStoreOptions(x: JksOptions): Unit

  /** Set whether Netty pooled buffers are enabled
    */
  def usePooledBuffers_=(x: Boolean): Unit

  def usePooledBuffers: Boolean

}

