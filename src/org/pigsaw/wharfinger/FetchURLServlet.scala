package org.pigsaw.wharfinger

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}

/**
 * Created by IntelliJ IDEA.
 * User: Nik
 * Date: 13-Aug-2010
 * Time: 22:34:45
 * To change this template use File | Settings | File Templates.
 */

class FetchURLServlet extends HttpServlet {

  override def doPost(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    val url = req.getParameter("url")
    val pm = PMF.get.getPersistenceManager
    try {
      pm.makePersistent(new Article(url))
    }
    finally {
      pm.close
    }
  }

}
