package org.pigsaw.wharfinger

import javax.jdo.PersistenceManager
import javax.servlet.http.HttpServletResponse

/**
 * Close a query at the end.
 */

trait Transaction {

  def transaction(query: javax.jdo.Query)(block: =>Unit): Unit = {
    try { block }
    finally { query.closeAll }
  }

  def transactionWithReporting(query: javax.jdo.Query, resp: HttpServletResponse)(block: Unit): Unit = {
    try { block }
    catch { case e => resp.getWriter.println("Exception: " + e.getMessage) }
    finally { query.closeAll }
  }

  def persistAndClose(pm: PersistenceManager)(block: =>Unit) {
    try { block }
    finally { if (!pm.isClosed) pm.close }
  }

  def println(s: String)(implicit resp: HttpServletResponse) = resp.getWriter.println(s)
  def print(s: String)(implicit resp: HttpServletResponse) = resp.getWriter.print(s)

}