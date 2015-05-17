package io.vertx.lang.scala

/*import scala.language.implicitConversions
import scala.concurrent.Promise
import scala.collection.JavaConverters._
import io.vertx.core.Handler
import io.vertx.core.AsyncResult*/

object RuntimeObjectConversion {

  def asJava(obj: Any): java.lang.Object = {
    obj match {
      case o: io.vertx.scala.core.buffer.Buffer => o.asJava
      case o: Byte => byte2Byte(o)
      case o: Short => short2Short(o)
      case o: Char => char2Character(o)
      case o: Int => int2Integer(o)
      case o: Long => long2Long(o)
      case o: Float => float2Float(o)
      case o: Double => double2Double(o)
      case o: Boolean => boolean2Boolean(o)
      case o: AnyRef => o
    }
  }
  
  def asScala[S](obj: Any): S = {
    obj match {
        case o: io.vertx.core.buffer.Buffer => io.vertx.scala.core.buffer.Buffer.apply(o).asInstanceOf[S]
        case _ => obj.asInstanceOf[S]
    }
  }
}
