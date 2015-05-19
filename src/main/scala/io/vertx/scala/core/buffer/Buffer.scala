/*
 * Copyright 2014 Red Hat, Inc.
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

package io.vertx.scala.core.buffer;

import io.vertx.core.shareddata.impl.ClusterSerializable

/** Most data is shuffled around inside Vert.x using buffers.
  * 
  * A buffer is a sequence of zero or more bytes that can read from or written to and which expands automatically as
  * necessary to accommodate any bytes written to it. You can perhaps think of a buffer as smart byte array.
  * 
  * Please consult the documentation for more information on buffers.
  */
class Buffer(private val _asJava: io.vertx.core.buffer.Buffer) {

  def asJava: java.lang.Object = _asJava

  /** Returns a `String` representation of the Buffer with the encoding specified by `enc`
    */
  def toString(enc: String): String = {
    _asJava.toString(enc)
  }

  /** Returns the `byte` at position `pos` in the Buffer.
    */
  def getByte(pos: Int): Byte = {
    _asJava.getByte(pos)
  }

  /** Returns the `int` at position `pos` in the Buffer.
    */
  def getInt(pos: Int): Int = {
    _asJava.getInt(pos)
  }

  /** Returns the `long` at position `pos` in the Buffer.
    */
  def getLong(pos: Int): Long = {
    _asJava.getLong(pos)
  }

  /** Returns the `double` at position `pos` in the Buffer.
    */
  def getDouble(pos: Int): Double = {
    _asJava.getDouble(pos)
  }

  /** Returns the `float` at position `pos` in the Buffer.
    */
  def getFloat(pos: Int): Float = {
    _asJava.getFloat(pos)
  }

  /** Returns the `short` at position `pos` in the Buffer.
    */
  def getShort(pos: Int): Short = {
    _asJava.getShort(pos)
  }

  /** Returns a copy of a sub-sequence the Buffer as a [[io.vertx.scala.core.buffer.Buffer]] starting at position `start`
    * and ending at position `end - 1`
    */
  def getBuffer(start: Int, end: Int): io.vertx.scala.core.buffer.Buffer = {
    Buffer.apply(_asJava.getBuffer(start, end))
  }

  /** Returns a copy of a sub-sequence the Buffer as a `String` starting at position `start`
    * and ending at position `end - 1` interpreted as a String in the specified encoding
    */
  def getString(start: Int, end: Int, enc: String): String = {
    _asJava.getString(start, end, enc)
  }

  /** Returns a copy of a sub-sequence the Buffer as a `String` starting at position `start`
    * and ending at position `end - 1` interpreted as a String in UTF-8 encoding
    */
  def getString(start: Int, end: Int): String = {
    _asJava.getString(start, end)
  }

  /** Appends the specified `Buffer` to the end of this Buffer. The buffer will expand as necessary to accommodate
    * any bytes written.
    * Returns a reference to `this` so multiple operations can be appended together.
    */
  def appendBuffer(buff: io.vertx.scala.core.buffer.Buffer): io.vertx.scala.core.buffer.Buffer = {
    _asJava.appendBuffer(buff.asJava.asInstanceOf[io.vertx.core.buffer.Buffer])
    this
  }

  /** Appends the specified `Buffer` starting at the `offset` using `len` to the end of this Buffer. The buffer will expand as necessary to accommodate
    * any bytes written.
    * Returns a reference to `this` so multiple operations can be appended together.
    */
  def appendBuffer(buff: io.vertx.scala.core.buffer.Buffer, offset: Int, len: Int): io.vertx.scala.core.buffer.Buffer = {
    _asJava.appendBuffer(buff.asJava.asInstanceOf[io.vertx.core.buffer.Buffer], offset, len)
    this
  }

  /** Appends the specified `byte` to the end of the Buffer. The buffer will expand as necessary to accommodate any bytes written.
    * Returns a reference to `this` so multiple operations can be appended together.
    */
  def appendByte(b: Byte): io.vertx.scala.core.buffer.Buffer = {
    _asJava.appendByte(b)
    this
  }

  /** Appends the specified `int` to the end of the Buffer. The buffer will expand as necessary to accommodate any bytes written.
    * Returns a reference to `this` so multiple operations can be appended together.
    */
  def appendInt(i: Int): io.vertx.scala.core.buffer.Buffer = {
    _asJava.appendInt(i)
    this
  }

  /** Appends the specified `long` to the end of the Buffer. The buffer will expand as necessary to accommodate any bytes written.
    * Returns a reference to `this` so multiple operations can be appended together.
    */
  def appendLong(l: Long): io.vertx.scala.core.buffer.Buffer = {
    _asJava.appendLong(l)
    this
  }

  /** Appends the specified `short` to the end of the Buffer.The buffer will expand as necessary to accommodate any bytes written.
    * Returns a reference to `this` so multiple operations can be appended together.
    */
  def appendShort(s: Short): io.vertx.scala.core.buffer.Buffer = {
    _asJava.appendShort(s)
    this
  }

  /** Appends the specified `float` to the end of the Buffer. The buffer will expand as necessary to accommodate any bytes written.
    * Returns a reference to `this` so multiple operations can be appended together.
    */
  def appendFloat(f: Float): io.vertx.scala.core.buffer.Buffer = {
    _asJava.appendFloat(f)
    this
  }

  /** Appends the specified `double` to the end of the Buffer. The buffer will expand as necessary to accommodate any bytes written.
    * Returns a reference to `this` so multiple operations can be appended together.
    */
  def appendDouble(d: Double): io.vertx.scala.core.buffer.Buffer = {
    _asJava.appendDouble(d)
    this
  }

  /** Appends the specified `String` to the end of the Buffer with the encoding as specified by `enc`.
    * The buffer will expand as necessary to accommodate any bytes written.
    * Returns a reference to `this` so multiple operations can be appended together.
    */
  def appendString(str: String, enc: String): io.vertx.scala.core.buffer.Buffer = {
    _asJava.appendString(str, enc)
    this
  }

  /** Appends the specified `String str` to the end of the Buffer with UTF-8 encoding.
    * The buffer will expand as necessary to accommodate any bytes written.
    * Returns a reference to `this` so multiple operations can be appended together
    */
  def appendString(str: String): io.vertx.scala.core.buffer.Buffer = {
    _asJava.appendString(str)
    this
  }

  /** Sets the `byte` at position `pos` in the Buffer to the value `b`.
    * The buffer will expand as necessary to accommodate any value written.
    */
  def setByte(pos: Int, b: Byte): io.vertx.scala.core.buffer.Buffer = {
    _asJava.setByte(pos, b)
    this
  }

  /** Sets the `int` at position `pos` in the Buffer to the value `i`.
    * The buffer will expand as necessary to accommodate any value written.
    */
  def setInt(pos: Int, i: Int): io.vertx.scala.core.buffer.Buffer = {
    _asJava.setInt(pos, i)
    this
  }

  /** Sets the `long` at position `pos` in the Buffer to the value `l`.
    * The buffer will expand as necessary to accommodate any value written.
    */
  def setLong(pos: Int, l: Long): io.vertx.scala.core.buffer.Buffer = {
    _asJava.setLong(pos, l)
    this
  }

  /** Sets the `double` at position `pos` in the Buffer to the value `d`.
    * The buffer will expand as necessary to accommodate any value written.
    */
  def setDouble(pos: Int, d: Double): io.vertx.scala.core.buffer.Buffer = {
    _asJava.setDouble(pos, d)
    this
  }

  /** Sets the `float` at position `pos` in the Buffer to the value `f`.
    * The buffer will expand as necessary to accommodate any value written.
    */
  def setFloat(pos: Int, f: Float): io.vertx.scala.core.buffer.Buffer = {
    _asJava.setFloat(pos, f)
    this
  }

  /** Sets the `short` at position `pos` in the Buffer to the value `s`.
    * The buffer will expand as necessary to accommodate any value written.
    */
  def setShort(pos: Int, s: Short): io.vertx.scala.core.buffer.Buffer = {
    _asJava.setShort(pos, s)
    this
  }

  /** Sets the bytes at position `pos` in the Buffer to the bytes represented by the `Buffer b`.
    * The buffer will expand as necessary to accommodate any value written.
    */
  def setBuffer(pos: Int, b: io.vertx.scala.core.buffer.Buffer): io.vertx.scala.core.buffer.Buffer = {
    _asJava.setBuffer(pos, b.asJava.asInstanceOf[io.vertx.core.buffer.Buffer])
    this
  }

  /** Sets the bytes at position `pos` in the Buffer to the bytes represented by the `Buffer b` on the given `offset` and `len`.
    * The buffer will expand as necessary to accommodate any value written.
    */
  def setBuffer(pos: Int, b: io.vertx.scala.core.buffer.Buffer, offset: Int, len: Int): io.vertx.scala.core.buffer.Buffer = {
    _asJava.setBuffer(pos, b.asJava.asInstanceOf[io.vertx.core.buffer.Buffer], offset, len)
    this
  }

  /** Sets the bytes at position `pos` in the Buffer to the value of `str` encoded in UTF-8.
    * The buffer will expand as necessary to accommodate any value written.
    */
  def setString(pos: Int, str: String): io.vertx.scala.core.buffer.Buffer = {
    _asJava.setString(pos, str)
    this
  }

  /** Sets the bytes at position `pos` in the Buffer to the value of `str` encoded in encoding `enc`.
    * The buffer will expand as necessary to accommodate any value written.
    */
  def setString(pos: Int, str: String, enc: String): io.vertx.scala.core.buffer.Buffer = {
    _asJava.setString(pos, str, enc)
    this
  }

  /** Returns the length of the buffer, measured in bytes.
    * All positions are indexed from zero.
    */
  def length(): Int = {
    _asJava.length()
  }

  /** Returns a copy of the entire Buffer.
    */
  def copy(): io.vertx.scala.core.buffer.Buffer = {
    Buffer.apply(_asJava.copy())
  }

  /** Returns a slice of this buffer. Modifying the content
    * of the returned buffer or this buffer affects each other's content
    * while they maintain separate indexes and marks.
    */
  def slice(): io.vertx.scala.core.buffer.Buffer = {
    Buffer.apply(_asJava.slice())
  }

  /** Returns a slice of this buffer. Modifying the content
    * of the returned buffer or this buffer affects each other's content
    * while they maintain separate indexes and marks.
    */
  def slice(start: Int, end: Int): io.vertx.scala.core.buffer.Buffer = {
    Buffer.apply(_asJava.slice(start, end))
  }

}

object Buffer {

  def apply(_asJava: io.vertx.core.buffer.Buffer): io.vertx.scala.core.buffer.Buffer =
    new io.vertx.scala.core.buffer.Buffer(_asJava)

  /** Create a new, empty buffer.
    *
    * @return the buffer
    */
  def buffer(): io.vertx.scala.core.buffer.Buffer = {
    Buffer.apply(io.vertx.core.buffer.Buffer.buffer())
  }

  /** Create a new buffer given the initial size hint.
    * 
    * If you know the buffer will require a certain size, providing the hint can prevent unnecessary re-allocations
    * as the buffer is written to and resized.
    *
    * @param initialSizeHint the hint, in bytes
    * @return the buffer
    */
  def buffer(initialSizeHint: Int): io.vertx.scala.core.buffer.Buffer = {
    Buffer.apply(io.vertx.core.buffer.Buffer.buffer(initialSizeHint))
  }

  /** Create a new buffer from a string. The string will be UTF-8 encoded into the buffer.
    *
    * @param string the string
    * @return the buffer
    */
  def buffer(string: String): io.vertx.scala.core.buffer.Buffer = {
    Buffer.apply(io.vertx.core.buffer.Buffer.buffer(string))
  }

  /** Create a new buffer from a string and using the specified encoding.
    * The string will be encoded into the buffer using the specified encoding.
    *
    * @param string the string
    * @return the buffer
    */
  def buffer(string: String, enc: String): io.vertx.scala.core.buffer.Buffer = {
    Buffer.apply(io.vertx.core.buffer.Buffer.buffer(string, enc))
  }
}
