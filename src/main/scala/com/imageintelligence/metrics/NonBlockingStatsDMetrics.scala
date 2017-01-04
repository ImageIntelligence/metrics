package com.imageintelligence.metrics

import com.timgroup.statsd.NonBlockingStatsDClient

case class NonBlockingStatsDMetrics(prefix: String, hostname: String, port: Int) extends StatsDMetrics {

  private val client = new NonBlockingStatsDClient(prefix, hostname, port)

  def time(name: String, value: Long): Unit = {
    client.time(name, value)
  }

  def increment(name: String): Unit = {
    client.count(name, 1)
  }

  def incrementBy(name: String, count: Int): Unit = {
    client.count(name, count)
  }

  def decrement(name: String): Unit = {
    client.count(name, -1)
  }

  def decrementBy(name: String, count: Int): Unit = {
    client.count(name, -count)
  }

  def gauge(name: String, value: Long): Unit = {
    client.gauge(name, value)
  }
}
