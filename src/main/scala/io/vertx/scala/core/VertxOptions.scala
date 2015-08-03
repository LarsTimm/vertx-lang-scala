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

package io.vertx.scala.core;

import io.vertx.scala.core.metrics.MetricsOptions

/** Instances of this class are used to configure [[io.vertx.scala.core.Vertx]] instances.
  */
class VertxOptions(val asJava: io.vertx.core.VertxOptions){

  /** Sets the value of blocked thread check period, in ms.
    */
  def blockedThreadCheckInterval_=(x: Long): Unit = {
    asJava.setBlockedThreadCheckInterval(x)
  }

  def blockedThreadCheckInterval: Long = {
    asJava.getBlockedThreadCheckInterval()
  }

  /** Set the hostname to be used for clustering.
    */
  def clusterHost_=(x: String): Unit = {
    asJava.setClusterHost(x)
  }

  def clusterHost: String = {
    asJava.getClusterHost()
  }

  /** Set the value of cluster ping interval, in ms.
    */
  def clusterPingInterval_=(x: Long): Unit = {
    asJava.setClusterPingInterval(x)
  }

  def clusterPingInterval: Long = {
    asJava.getClusterPingInterval()
  }

  /** Set the value of cluster ping reply interval, in ms.
    */
  def clusterPingReplyInterval_=(x: Long): Unit = {
    asJava.setClusterPingReplyInterval(x)
  }

  def clusterPingReplyInterval: Long = {
    asJava.getClusterPingReplyInterval()
  }

  /** Set the port to be used for clustering.
    */
  def clusterPort_=(x: Int): Unit = {
    asJava.setClusterPort(x)
  }

  def clusterPort: Int = {
    asJava.getClusterPort()
  }

  /** Set whether or not the Vert.x instance will be clustered.
    */
  def clustered_=(x: Boolean): Unit = {
    asJava.setClustered(x)
  }

  def clustered: Boolean = {
    asJava.isClustered()
  }

  /** Set the number of event loop threads to be used by the Vert.x instance.
    */
  def eventLoopPoolSize_=(x: Int): Unit = {
    asJava.setEventLoopPoolSize(x)
  }

  def eventLoopPoolSize: Int = {
    asJava.getEventLoopPoolSize()
  }

  /** Set whether HA will be enabled on the Vert.x instance.
    */
  def haEnabled_=(x: Boolean): Unit = {
    asJava.setHAEnabled(x)
  }

  def haEnabled: Boolean = {
    asJava.isHAEnabled()
  }

  /** Set the HA group to be used when HA is enabled.
    */
  def haGroup_=(x: String): Unit = {
    asJava.setHAGroup(x)
  }

  def haGroup: String = {
    asJava.getHAGroup()
  }

  /** Set the value of internal blocking pool size
    */
  def internalBlockingPoolSize_=(x: Int): Unit = {
    asJava.setInternalBlockingPoolSize(x)
  }

  def internalBlockingPoolSize: Int = {
    asJava.getInternalBlockingPoolSize()
  }

  /** Sets the value of max event loop execute time, in ns.
    */
  def maxEventLoopExecuteTime_=(x: Long): Unit = {
    asJava.setMaxEventLoopExecuteTime(x)
  }

  def maxEventLoopExecuteTime: Long = {
    asJava.getMaxEventLoopExecuteTime()
  }

  /** Sets the value of max worker execute time, in ns.
    */
  def maxWorkerExecuteTime_=(x: Long): Unit = {
    asJava.setMaxWorkerExecuteTime(x)
  }

  def maxWorkerExecuteTime: Long = {
    asJava.getMaxWorkerExecuteTime()
  }

  /** Set the metrics options
    */
  def metricsOptions_=(x: MetricsOptions): Unit = {
    asJava.setMetricsOptions(x.asJava)
  }

  def metricsOptions: MetricsOptions = {
    io.vertx.scala.core.metrics.MetricsOptions.apply(asJava.getMetricsOptions())
  }

  /** Set the quorum size to be used when HA is enabled.
    */
  def quorumSize_=(x: Int): Unit = {
    asJava.setQuorumSize(x)
  }

  def quorumSize: Int = {
    asJava.getQuorumSize()
  }

  /** Set the threshold value above this, the blocked warning contains a stack trace.
    */
  def warningExceptionTime_=(x: Long): Unit = {
    asJava.setWarningExceptionTime(x)
  }

  def warningExceptionTime: Long = {
    asJava.getWarningExceptionTime()
  }

  /** Set the maximum number of worker threads to be used by the Vert.x instance.
    */
  def workerPoolSize_=(x: Int): Unit = {
    asJava.setWorkerPoolSize(x)
  }

  def workerPoolSize: Int = {
    asJava.getWorkerPoolSize()
  }

}

object VertxOptions {

  def apply(asJava: io.vertx.core.VertxOptions): VertxOptions =
    new VertxOptions(asJava)

  /** Default constructor
    */
  def apply() =
    new VertxOptions(new io.vertx.core.VertxOptions)

  /** Copy constructor
    *
    * @param other the instance to copy
    */
  def apply(other: VertxOptions) =
    new VertxOptions(new io.vertx.core.VertxOptions(other.asJava))

  /** Constructor for creating a instance from JSON
    *
    * @param json the JSON
    */
  def apply(json: io.vertx.core.json.JsonObject) =
    new VertxOptions(new io.vertx.core.VertxOptions(json))

}
