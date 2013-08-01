package org.pigsaw.wharfinger

import java.util.logging.{Logger => JavaLogger}
import javax.servlet.http.HttpServletResponse

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
    oResp map { r => r.getWriter.println(msg) }
  }

  /** Print to the servlet.
   */
  def println(msg: String) {
    oLogger map { g => g.warning(msg)}
  }

  def withResponse(resp: HttpServletResponse): CombiLogger =
    new CombiLogger {
      val oLogger = outer.oLogger
      val oResp = Some(resp)
    }
}
