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


/** @author <a href="http://tfox.org">Tim Fox</a>
  */
trait NetworkOptions{

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

  /** Set the value of traffic class
    */
  def trafficClass_=(x: Int): Unit

  def trafficClass: Int

}

