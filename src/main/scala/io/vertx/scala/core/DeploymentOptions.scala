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

import io.vertx.core.json.JsonObject

/** Options for configuring a verticle deployment.
  * 
  */
class DeploymentOptions(val asJava: io.vertx.core.DeploymentOptions){

  /** Set the JSON configuration that will be passed to the verticle(s) when it's deployed
    */
  def config_=(x: io.vertx.core.json.JsonObject): Unit = {
    asJava.setConfig(x)
  }

  def config: io.vertx.core.json.JsonObject = {
    asJava.getConfig()
  }

  /** Set any extra classpath to be used when deploying the verticle.
    * 
    * Ignored if no isolation group is set.
    */
  def extraClasspath_=(x: List[String]): Unit = {
    import scala.collection.JavaConverters._
    asJava.setExtraClasspath(x.asJava)
  }

  /** Set whether the verticle(s) will be deployed as HA.
    */
  def ha_=(x: Boolean): Unit = {
    asJava.setHa(x)
  }

  def ha: Boolean = {
    asJava.isHa()
  }

  /** Set the number of instances that should be deployed.
    */
  def instances_=(x: Int): Unit = {
    asJava.setInstances(x)
  }

  def instances: Int = {
    asJava.getInstances()
  }

  /** Set the isolated class names.
    */
  def isolatedClasses_=(x: List[String]): Unit = {
    import scala.collection.JavaConverters._
    asJava.setIsolatedClasses(x.asJava)
  }

  /** Set the isolation group that will be used when deploying the verticle(s)
    */
  def isolationGroup_=(x: String): Unit = {
    asJava.setIsolationGroup(x)
  }

  def isolationGroup: String = {
    asJava.getIsolationGroup()
  }

  /** Set whether the verticle(s) should be deployed as a multi-threaded worker verticle
    */
  def multiThreaded_=(x: Boolean): Unit = {
    asJava.setMultiThreaded(x)
  }

  def multiThreaded: Boolean = {
    asJava.isMultiThreaded()
  }

  /** Set whether the verticle(s) should be deployed as a worker verticle
    */
  def worker_=(x: Boolean): Unit = {
    asJava.setWorker(x)
  }

  def worker: Boolean = {
    asJava.isWorker()
  }

}

object DeploymentOptions {

  def apply(asJava: io.vertx.core.DeploymentOptions): DeploymentOptions =
    new DeploymentOptions(asJava)

  /** Default constructor
    */
  def apply() =
    new DeploymentOptions(new io.vertx.core.DeploymentOptions)

  /** Copy constructor
    *
    * @param other the instance to copy
    */
  def apply(other: DeploymentOptions) =
    new DeploymentOptions(new io.vertx.core.DeploymentOptions(other.asJava))

  /** Constructor for creating a instance from JSON
    *
    * @param json the JSON
    */
  def apply(json: io.vertx.core.json.JsonObject) =
    new DeploymentOptions(new io.vertx.core.DeploymentOptions(json))

}
