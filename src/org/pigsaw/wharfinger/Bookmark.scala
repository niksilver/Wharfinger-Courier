package org.pigsaw.wharfinger

import javax.jdo.annotations.{PrimaryKey, Persistent, PersistenceCapable}
import com.google.appengine.api.datastore.Text
import javax.jdo.PersistenceManager

/**
 * Bookmark trait and all its concrete subclasses.
 */

trait Bookmark {

  val url: String
  val popularity: Int
  val username: String
  val citation: Text
  val tryCount: Int

  /** Process a single bookmark.
  def process() {
    val task = new QueueableTask("/fetchurl")
    task += ("url" -> url)
    task.enqueue
  }*/
}

@PersistenceCapable
class BookmarkPendingFetch(@Persistent @PrimaryKey override val url:String,
                           @Persistent override val popularity: Int,
                           @Persistent override val username: String,
                           @Persistent override val citation: Text
        ) extends Bookmark {
  val tryCount = 0

  def saveForLaterFetching() {
    val pm = PMF.get.getPersistenceManager
    try {
      pm.makePersistent(this)
    }
    finally {
      pm.close
    }
  }
}
