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

/** Key or trust store options configuring private key and/or certificates based on PKCS#12 files.
  * 
  * When used as a key store, it should point to a store containing a private key and its certificate.
  * When used as a trust store, it should point to a store containing a list of accepted certificates.
  * 
  *
  * The store can either be loaded by Vert.x from the filesystem:
  * 
  * <pre>
  * HttpServerOptions options = new HttpServerOptions();
  * options.setPfxKeyCertOptions(new PfxOptions().setPath("/mykeystore.p12").setPassword("foo"));
  * </pre>
  *
  * Or directly provided as a buffer:
  *
  * <pre>
  * Buffer store = vertx.fileSystem().readFileSync("/mykeystore.p12");
  * options.setPfxKeyCertOptions(new PfxOptions().setValue(store).setPassword("foo"));
  * </pre>
  */
class PfxOptions(val asJava: io.vertx.core.net.PfxOptions)
    extends KeyCertOptions
    with TrustOptions{

  /** Set the password
    */
  def password_=(x: String): Unit = {
    asJava.setPassword(x)
  }

  def password: String = {
    asJava.getPassword()
  }

  /** Set the path
    */
  def path_=(x: String): Unit = {
    asJava.setPath(x)
  }

  def path: String = {
    asJava.getPath()
  }

  /** Set the store as a buffer
    */
  def value_=(x: io.vertx.scala.core.buffer.Buffer): Unit = {
    asJava.setValue(x.asJava.asInstanceOf[io.vertx.core.buffer.Buffer])
  }

  def value: io.vertx.scala.core.buffer.Buffer = {
    io.vertx.scala.core.buffer.Buffer.apply(asJava.getValue())
  }

}

object PfxOptions {

  def apply(asJava: io.vertx.core.net.PfxOptions): PfxOptions =
    new PfxOptions(asJava)

  /** Default constructor
    */
  def apply() =
    new PfxOptions(new io.vertx.core.net.PfxOptions)

  /** Copy constructor
    *
    * @param other the instance to copy
    */
  def apply(other: PfxOptions) =
    new PfxOptions(new io.vertx.core.net.PfxOptions(other.asJava))

  /** Constructor for creating a instance from JSON
    *
    * @param json the JSON
    */
  def apply(json: io.vertx.core.json.JsonObject) =
    new PfxOptions(new io.vertx.core.net.PfxOptions(json))

}
