package org.pigsaw.wharfinger

import collection.mutable.HashMap
import com.google.appengine.api.labs.taskqueue.QueueFactory
import com.google.appengine.api.labs.taskqueue.TaskOptions.Builder._
import com.google.appengine.api.labs.taskqueue.TaskOptions.Method


/**
 * A task which can be put in a Google app engine queue
 */

class QueueableTask(val worker:String) extends HashMap[String, String] {

  def enqueue() {
    val queue = QueueFactory.getDefaultQueue();
    //queue.add((url(name) /: toSeq) ((b, pair) => b.param(pair._1, pair._2)))
    //queue.add(url(name).param("title",apply("title")).param("url",apply("url")).method(Method.GET))
    queue.add(url(worker).param("url", this("url")))
  }
}