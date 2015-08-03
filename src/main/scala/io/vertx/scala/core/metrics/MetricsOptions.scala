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

package io.vertx.scala.core.metrics;


/** Vert.x metrics base configuration, this class can be extended by provider implementations to configure
  * those specific implementations.
  */
class MetricsOptions(val asJava: io.vertx.core.metrics.MetricsOptions){

  /** Set whether metrics will be enabled on the Vert.x instance.
    */
  def enabled_=(x: Boolean): Unit = {
    asJava.setEnabled(x)
  }

  def enabled: Boolean = {
    asJava.isEnabled()
  }

}

object MetricsOptions {

  def apply(asJava: io.vertx.core.metrics.MetricsOptions): MetricsOptions =
    new MetricsOptions(asJava)

  /** Default constructor
    */
  def apply() =
    new MetricsOptions(new io.vertx.core.metrics.MetricsOptions)

  /** Copy constructor
    *
    * @param other the instance to copy
    */
  def apply(other: MetricsOptions) =
    new MetricsOptions(new io.vertx.core.metrics.MetricsOptions(other.asJava))

  /** Constructor for creating a instance from JSON
    *
    * @param json the JSON
    */
  def apply(json: io.vertx.core.json.JsonObject) =
    new MetricsOptions(new io.vertx.core.metrics.MetricsOptions(json))

}
