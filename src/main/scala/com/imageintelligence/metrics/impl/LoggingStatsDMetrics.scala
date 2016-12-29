package com.imageintelligence.metrics.impl

import com.imageintelligence.metrics.StatsDMetrics

/**
 * A simple no op logging based metrics recorder.
 * It just logs the metrics out via the logging function passed in.
 * Useful for debugging or local development
 */
case class LoggingStatsDMetrics(logger: String => Unit) extends StatsDMetrics {

  def time(name: String, value: Long): Unit = {
    logger(s"#time: $name took ${value}ms")
  }

  def increment(name: String): Unit = {
    logger(s"#increment: $name incremented")
  }

  def incrementBy(name: String, count: Int): Unit = {
    logger(s"#incrementBy: $name incremented by ${count}")
  }

  def decrement(name: String): Unit = {
    logger(s"#decrement: $name decremented")
  }

  def decrementBy(name: String, count: Int): Unit = {
    logger(s"#decrementBy: $name decremented by ${count}")
  }

  def gauge(name: String, value: Long): Unit = {
    logger(s"#gauge: $name gauged at ${value}")
  }
}

object LoggingStatsDMetrics {

  /**
   * A simple println based metrics impl.
   */
  def printlnMetrics = LoggingStatsDMetrics(println)
}
