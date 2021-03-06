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

/** Certificate Authority options configuring certificates based on
  * <i>Privacy-enhanced Electronic Email</i> (PEM) files. The options is configured with a list of
  * validating certificates.
  * 
  * Validating certificates must contain X.509 certificates wrapped in a PEM block:
  *
  * <pre>
  * -----BEGIN CERTIFICATE-----
  * MIIDezCCAmOgAwIBAgIEVmLkwTANBgkqhkiG9w0BAQsFADBuMRAwDgYDVQQGEwdV
  * ...
  * z5+DuODBJUQst141Jmgq8bS543IU/5apcKQeGNxEyQ==
  * -----END CERTIFICATE-----
  * </pre>
  *
  * The certificates can either be loaded by Vert.x from the filesystem:
  * 
  * <pre>
  * HttpServerOptions options = new HttpServerOptions();
  * options.setPemTrustOptions(new PemTrustOptions().addCertPath("/cert.pem"));
  * </pre>
  *
  * Or directly provided as a buffer:
  * 
  *
  * <pre>
  * Buffer cert = vertx.fileSystem().readFileSync("/cert.pem");
  * HttpServerOptions options = new HttpServerOptions();
  * options.setPemTrustOptions(new PemTrustOptions().addCertValue(cert));
  * </pre>
  */
class PemTrustOptions(val asJava: io.vertx.core.net.PemTrustOptions)
    extends TrustOptions{

  /** Add a certificate path
    */
  def addCertPath(x: String): Unit = {
    import scala.collection.JavaConverters._
    asJava.addCertPath(x)
  }

  /** Add a certificate value
    */
  def addCertValue(x: io.vertx.scala.core.buffer.Buffer): Unit = {
    import scala.collection.JavaConverters._
    asJava.addCertValue(x.asJava.asInstanceOf[io.vertx.core.buffer.Buffer])
  }

}

object PemTrustOptions {

  def apply(asJava: io.vertx.core.net.PemTrustOptions): PemTrustOptions =
    new PemTrustOptions(asJava)

  /** Default constructor
    */
  def apply() =
    new PemTrustOptions(new io.vertx.core.net.PemTrustOptions)

  /** Copy constructor
    *
    * @param other the instance to copy
    */
  def apply(other: PemTrustOptions) =
    new PemTrustOptions(new io.vertx.core.net.PemTrustOptions(other.asJava))

  /** Constructor for creating a instance from JSON
    *
    * @param json the JSON
    */
  def apply(json: io.vertx.core.json.JsonObject) =
    new PemTrustOptions(new io.vertx.core.net.PemTrustOptions(json))

}
