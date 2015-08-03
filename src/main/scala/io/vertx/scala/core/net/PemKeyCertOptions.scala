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

/** Key store options configuring a private key and its certificate based on
  * <i>Privacy-enhanced Electronic Email</i> (PEM) files.
  * 
  *
  * The key file must contain a <b>non encrypted</b> private key in <b>PKCS8</b> format wrapped in a PEM
  * block, for example:
  * 
  *
  * <pre>
  * -----BEGIN PRIVATE KEY-----
  * MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDV6zPk5WqLwS0a
  * ...
  * K5xBhtm1AhdnZjx5KfW3BecE
  * -----END PRIVATE KEY-----
  * </pre>
  *
  * The certificate file must contain an X.509 certificate wrapped in a PEM block, for example:
  * 
  *
  * <pre>
  * -----BEGIN CERTIFICATE-----
  * MIIDezCCAmOgAwIBAgIEZOI/3TANBgkqhkiG9w0BAQsFADBuMRAwDgYDVQQGEwdV
  * ...
  * +tmLSvYS39O2nqIzzAUfztkYnUlZmB0l/mKkVqbGJA==
  * -----END CERTIFICATE-----
  * </pre>
  *
  * The key and certificate can either be loaded by Vert.x from the filesystem:
  * 
  * <pre>
  * HttpServerOptions options = new HttpServerOptions();
  * options.setPemKeyCertOptions(new PemKeyCertOptions().setKeyPath("/mykey.pem").setCertPath("/mycert.pem"));
  * </pre>
  *
  * Or directly provided as a buffer:
  *
  * <pre>
  * Buffer key = vertx.fileSystem().readFileSync("/mykey.pem");
  * Buffer cert = vertx.fileSystem().readFileSync("/mycert.pem");
  * options.setPemKeyCertOptions(new PemKeyCertOptions().setKeyValue(key).setCertValue(cert));
  * </pre>
  */
class PemKeyCertOptions(val asJava: io.vertx.core.net.PemKeyCertOptions)
    extends KeyCertOptions{

  /** Set the path to the certificate
    */
  def certPath_=(x: String): Unit = {
    asJava.setCertPath(x)
  }

  def certPath: String = {
    asJava.getCertPath()
  }

  /** Set the certificate as a buffer
    */
  def certValue_=(x: io.vertx.scala.core.buffer.Buffer): Unit = {
    asJava.setCertValue(x.asJava.asInstanceOf[io.vertx.core.buffer.Buffer])
  }

  def certValue: io.vertx.scala.core.buffer.Buffer = {
    io.vertx.scala.core.buffer.Buffer.apply(asJava.getCertValue())
  }

  /** Set the path to the key file
    */
  def keyPath_=(x: String): Unit = {
    asJava.setKeyPath(x)
  }

  def keyPath: String = {
    asJava.getKeyPath()
  }

  /** Set the key a a buffer
    */
  def keyValue_=(x: io.vertx.scala.core.buffer.Buffer): Unit = {
    asJava.setKeyValue(x.asJava.asInstanceOf[io.vertx.core.buffer.Buffer])
  }

  def keyValue: io.vertx.scala.core.buffer.Buffer = {
    io.vertx.scala.core.buffer.Buffer.apply(asJava.getKeyValue())
  }

}

object PemKeyCertOptions {

  def apply(asJava: io.vertx.core.net.PemKeyCertOptions): PemKeyCertOptions =
    new PemKeyCertOptions(asJava)

  /** Default constructor
    */
  def apply() =
    new PemKeyCertOptions(new io.vertx.core.net.PemKeyCertOptions)

  /** Copy constructor
    *
    * @param other the instance to copy
    */
  def apply(other: PemKeyCertOptions) =
    new PemKeyCertOptions(new io.vertx.core.net.PemKeyCertOptions(other.asJava))

  /** Constructor for creating a instance from JSON
    *
    * @param json the JSON
    */
  def apply(json: io.vertx.core.json.JsonObject) =
    new PemKeyCertOptions(new io.vertx.core.net.PemKeyCertOptions(json))

}
