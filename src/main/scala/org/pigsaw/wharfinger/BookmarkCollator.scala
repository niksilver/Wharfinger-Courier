package org.pigsaw.wharfinger

abstract class BookmarkCollator[T] {
  /** Get the bookmarks from the network service. */
  def bookmarks: Seq[T]
  def bookmarksPendingFetch: Seq[BookmarkPendingFetch]
}
