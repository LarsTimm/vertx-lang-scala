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

package io.vertx.scala.core.datagram;

import io.vertx.scala.core.net.NetworkOptions

/** Options used to configure a datagram socket.
  */
class DatagramSocketOptions(val asJava: io.vertx.core.datagram.DatagramSocketOptions){

  /** Set if the socket can receive broadcast packets
    */
  def broadcast_=(x: Boolean): Unit = {
    asJava.setBroadcast(x)
  }

  def broadcast: Boolean = {
    asJava.isBroadcast()
  }

  /** Set if IP v6 should be used
    */
  def ipV6_=(x: Boolean): Unit = {
    asJava.setIpV6(x)
  }

  def ipV6: Boolean = {
    asJava.isIpV6()
  }

  /** Set if loopback mode is disabled
    */
  def loopbackModeDisabled_=(x: Boolean): Unit = {
    asJava.setLoopbackModeDisabled(x)
  }

  def loopbackModeDisabled: Boolean = {
    asJava.isLoopbackModeDisabled()
  }

  /** Set the multicast network interface address
    */
  def multicastNetworkInterface_=(x: String): Unit = {
    asJava.setMulticastNetworkInterface(x)
  }

  def multicastNetworkInterface: String = {
    asJava.getMulticastNetworkInterface()
  }

  /** Set the multicast ttl value
    */
  def multicastTimeToLive_=(x: Int): Unit = {
    asJava.setMulticastTimeToLive(x)
  }

  def multicastTimeToLive: Int = {
    asJava.getMulticastTimeToLive()
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

  /** Set the value of traffic class
    */
  def trafficClass_=(x: Int): Unit = {
    asJava.setTrafficClass(x)
  }

  def trafficClass: Int = {
    asJava.getTrafficClass()
  }

}

object DatagramSocketOptions {

  def apply(asJava: io.vertx.core.datagram.DatagramSocketOptions): DatagramSocketOptions =
    new DatagramSocketOptions(asJava)

  /** Default constructor
    */
  def apply() =
    new DatagramSocketOptions(new io.vertx.core.datagram.DatagramSocketOptions)

  /** Copy constructor
    *
    * @param other the instance to copy
    */
  def apply(other: DatagramSocketOptions) =
    new DatagramSocketOptions(new io.vertx.core.datagram.DatagramSocketOptions(other.asJava))

  /** Constructor for creating a instance from JSON
    *
    * @param json the JSON
    */
  def apply(json: io.vertx.core.json.JsonObject) =
    new DatagramSocketOptions(new io.vertx.core.datagram.DatagramSocketOptions(json))

}
