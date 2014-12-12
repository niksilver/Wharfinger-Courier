package org.pigsaw.wharfinger

import java.util.logging.{Logger => JavaLogger}
import javax.servlet.http.HttpServletResponse
import java.util.logging.StreamHandler
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.util.logging.ConsoleHandler
import java.util.logging.SimpleFormatter
import java.util.logging.LogRecord
import scala.collection.mutable.MutableList

trait Logging {
  val log = JavaLogger.getLogger(super.getClass.getName)
}

trait NoLogging {
  this: Logging =>
  log.setUseParentHandlers(false)
}

/**
 * Sends the logs to a `Seq` of `String`s which is accessed
 * via the `logs` method.
 */
trait StringLogging extends NoLogging {
  this: Logging =>

  private val logStrings = MutableList[String]()
  def logs = logStrings.toSeq
  
  log.addHandler(new StreamHandler() {
    override def publish(rec: LogRecord) {
      logStrings += rec.getMessage
    }
  })
}

/**
 * Try to do something and return `Option[T]`, else return `None`.
 */
trait WarningTrier[T] {
  val log: JavaLogger

  def tryOrLogWarning(block: =>Option[T]): Option[T] = {
    try { block }
    catch {
      case e: Exception => {
        val str = new java.io.StringWriter
        e.printStackTrace(new java.io.PrintWriter(str))
        log.warning(str.toString)
        None
      }
    }
  }

}

/**
 * A combination Java and servlet logger
 */

trait CombiLogger {
  outer: CombiLogger =>

  val oLogger: Option[JavaLogger]
  val oResp: Option[HttpServletResponse]

  /** Log a warning to the Java logger.
   */
  def warn(msg: String) {
    oLogger map { g => g.warning(msg)}
  }

  /** Print to the servlet.
   */
  def println(msg: String) {
    oResp map { r => r.getWriter.println(msg) }
  }

  def withResponse(resp: HttpServletResponse): CombiLogger =
    new CombiLogger {
      val oLogger = outer.oLogger
      val oResp = Some(resp)
    }
}
