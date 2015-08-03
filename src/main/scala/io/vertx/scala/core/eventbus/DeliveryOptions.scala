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

package io.vertx.scala.core.eventbus;

import io.vertx.scala.core.MultiMap

/** Delivery options are used to configure message delivery.
  * 
  * Delivery options allow to configure delivery timeout and message codec name, and to provide any headers
  * that you wish to send with the message.
  */
class DeliveryOptions(val asJava: io.vertx.core.eventbus.DeliveryOptions){

  /** Set the codec name.
    */
  def codecName_=(x: String): Unit = {
    asJava.setCodecName(x)
  }

  def codecName: String = {
    asJava.getCodecName()
  }

  /** Set message headers from a multi-map.
    */
  def headers_=(x: io.vertx.scala.core.MultiMap): Unit = {
    asJava.setHeaders(x.asJava.asInstanceOf[io.vertx.core.MultiMap])
  }

  def headers: io.vertx.scala.core.MultiMap = {
    io.vertx.scala.core.MultiMap.apply(asJava.getHeaders())
  }

  /** Set the send timeout.
    */
  def sendTimeout_=(x: Long): Unit = {
    asJava.setSendTimeout(x)
  }

  def sendTimeout: Long = {
    asJava.getSendTimeout()
  }

}

object DeliveryOptions {

  def apply(asJava: io.vertx.core.eventbus.DeliveryOptions): DeliveryOptions =
    new DeliveryOptions(asJava)

  /** Default constructor
    */
  def apply() =
    new DeliveryOptions(new io.vertx.core.eventbus.DeliveryOptions)

  /** Copy constructor
    *
    * @param other the instance to copy
    */
  def apply(other: DeliveryOptions) =
    new DeliveryOptions(new io.vertx.core.eventbus.DeliveryOptions(other.asJava))

  /** Constructor for creating a instance from JSON
    *
    * @param json the JSON
    */
  def apply(json: io.vertx.core.json.JsonObject) =
    new DeliveryOptions(new io.vertx.core.eventbus.DeliveryOptions(json))

}
