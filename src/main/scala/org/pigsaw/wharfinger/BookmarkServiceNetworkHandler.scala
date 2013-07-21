package org.pigsaw.wharfinger

import Preamble._
import java.io.Reader
import collection.mutable.ListBuffer
import xml.{Node, NodeSeq}

abstract class BookmarkServiceNetworkHandler[T] {
  /** Get the bookmarks from the network service. */
  def bookmarks: Seq[T]
  def bookmarksPendingFetch: Seq[BookmarkPendingFetch]
}
