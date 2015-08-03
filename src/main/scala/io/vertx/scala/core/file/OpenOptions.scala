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

package io.vertx.scala.core.file;


/** Describes how an [[io.vertx.scala.core.file.AsyncFile]] should be opened.
  */
class OpenOptions(val asJava: io.vertx.core.file.OpenOptions){

  /** Set whether the file should be created if it does not already exist.
    */
  def create_=(x: Boolean): Unit = {
    asJava.setCreate(x)
  }

  def create: Boolean = {
    asJava.isCreate()
  }

  /** Set whether the file should be created and fail if it does exist already.
    */
  def createNew_=(x: Boolean): Unit = {
    asJava.setCreateNew(x)
  }

  def createNew: Boolean = {
    asJava.isCreateNew()
  }

  /** Set whether the file should be deleted when it's closed, or the JVM is shutdown.
    */
  def deleteOnClose_=(x: Boolean): Unit = {
    asJava.setDeleteOnClose(x)
  }

  def deleteOnClose: Boolean = {
    asJava.isDeleteOnClose()
  }

  /** Set whether every write to the file's content  ill be written synchronously to the underlying hardware.
    */
  def dsync_=(x: Boolean): Unit = {
    asJava.setDsync(x)
  }

  def dsync: Boolean = {
    asJava.isDsync()
  }

  /** Set the permissions string
    */
  def perms_=(x: String): Unit = {
    asJava.setPerms(x)
  }

  def perms: String = {
    asJava.getPerms()
  }

  /** Set whether the file is to be opened for reading
    */
  def read_=(x: Boolean): Unit = {
    asJava.setRead(x)
  }

  def read: Boolean = {
    asJava.isRead()
  }

  /** Set whether a hint should be provided that the file to created is sparse
    */
  def sparse_=(x: Boolean): Unit = {
    asJava.setSparse(x)
  }

  def sparse: Boolean = {
    asJava.isSparse()
  }

  /** Set whether every write to the file's content and meta-data will be written synchronously to the underlying hardware.
    */
  def sync_=(x: Boolean): Unit = {
    asJava.setSync(x)
  }

  def sync: Boolean = {
    asJava.isSync()
  }

  /** Set whether the file should be truncated to zero length on opening if it exists and is opened for write
    */
  def truncateExisting_=(x: Boolean): Unit = {
    asJava.setTruncateExisting(x)
  }

  def truncateExisting: Boolean = {
    asJava.isTruncateExisting()
  }

  /** Set whether the file is to be opened for writing
    */
  def write_=(x: Boolean): Unit = {
    asJava.setWrite(x)
  }

  def write: Boolean = {
    asJava.isWrite()
  }

}

object OpenOptions {

  def apply(asJava: io.vertx.core.file.OpenOptions): OpenOptions =
    new OpenOptions(asJava)

  /** Default constructor
    */
  def apply() =
    new OpenOptions(new io.vertx.core.file.OpenOptions)

  /** Copy constructor
    *
    * @param other the instance to copy
    */
  def apply(other: OpenOptions) =
    new OpenOptions(new io.vertx.core.file.OpenOptions(other.asJava))

  /** Constructor for creating a instance from JSON
    *
    * @param json the JSON
    */
  def apply(json: io.vertx.core.json.JsonObject) =
    new OpenOptions(new io.vertx.core.file.OpenOptions(json))

}
