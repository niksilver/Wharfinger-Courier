package org.pigsaw.wharfinger

import javax.jdo.PersistenceManager

/**
 * Close a query at the end.
 */

trait Transaction {

  def transaction(query: javax.jdo.Query)(block: =>Unit): Unit = {
    try { block }
    finally { query.closeAll }
  }

  def transactionWithReporting(query: javax.jdo.Query)(block: Unit): Unit = {
    try { block }
    catch { case e => println("Exception: " + e.getMessage) }
    finally { query.closeAll }
  }

  def persistAndClose(pm: PersistenceManager)(block: =>Unit) {
    try { block }
    finally { if (!pm.isClosed) pm.close }
  }

}