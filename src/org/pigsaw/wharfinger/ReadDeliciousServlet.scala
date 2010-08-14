package org.pigsaw.wharfinger

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}

/**
 * Created by IntelliJ IDEA.
 * User: Nik
 * Date: 14-Aug-2010
 * Time: 23:28:59
 * To change this template use File | Settings | File Templates.
 */

class ReadDeliciousServlet extends HttpServlet {

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) {
    val handler = new DeliciousNetworkHandler
    handler.parse
    handler.process
  }
}