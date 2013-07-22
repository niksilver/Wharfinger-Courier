package org.pigsaw.wharfinger

abstract class BookmarkServiceNetworkHandler[T] {
  /** Get the bookmarks from the network service. */
  def bookmarks: Seq[T]
  def bookmarksPendingFetch: Seq[BookmarkPendingFetch]
}
