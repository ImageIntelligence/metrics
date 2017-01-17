package com.imageintelligence.metrics

import com.timgroup.statsd.NonBlockingStatsDClient


case class NonBlockingDogStatsDMetrics(prefix: String, hostname: String, port: Int, constantTags: String*) extends DogStatsDMetrics {

  private val client = new NonBlockingStatsDClient(prefix, hostname, port, constantTags: _*)

  def time(name: String, value: Long, tags: String*): Unit = {
    client.time(name, value, tags: _*)
  }

  def count(name: String, value: Long, tags: String*): Unit = {
    client.count(name, value, tags: _*)
  }

  def increment(name: String, tags: String*): Unit = {
    client.count(name, 1, tags: _*)
  }

  def incrementBy(name: String, count: Int, tags: String*): Unit = {
    client.count(name, count, tags: _*)
  }

  def decrement(name: String, tags: String*): Unit = {
    client.count(name, -1, tags: _*)
  }

  def decrementBy(name: String, count: Int, tags: String*): Unit = {
    client.count(name, -count, tags: _*)
  }

  def gauge(name: String, value: Long, tags: String*): Unit = {
    client.gauge(name, value, tags: _*)
  }

  def recordExecutionTime(name: String, value: Long, tags: String*): Unit = {
    client.recordExecutionTime(name, value, tags: _*)
  }

  def recordHistogramValue(name: String, value: Long, tags: String*): Unit = {
    client.recordHistogramValue(name, value, tags: _*)
  }
}
