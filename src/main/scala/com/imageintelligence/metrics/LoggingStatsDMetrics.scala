package com.imageintelligence.metrics

/**
 * A simple no op logging based metrics recorder.
 * It just logs the metrics out via the logging function passed in.
 * Useful for debugging or local development
 */
case class LoggingStatsDMetrics(logger: String => Unit) extends DogStatsDMetrics {

  def time(name: String, value: Long, tags: String*): Unit = {
    logger(s"#time: $name took ${value}ms. Tags: ${tags.mkString(",")}")
  }

  def increment(name: String, tags: String*): Unit = {
    logger(s"#increment: $name incremented. Tags: ${tags.mkString(",")}")
  }

  def incrementBy(name: String, count: Int, tags: String*): Unit = {
    logger(s"#incrementBy: $name incremented by ${count}. Tags: ${tags.mkString(",")}")
  }

  def decrement(name: String, tags: String*): Unit = {
    logger(s"#decrement: $name decremented. Tags: ${tags.mkString(",")}")
  }

  def decrementBy(name: String, count: Int, tags: String*): Unit = {
    logger(s"#decrementBy: $name decremented by ${count}. Tags: ${tags.mkString(",")}")
  }

  def gauge(name: String, value: Long, tags: String*): Unit = {
    logger(s"#gauge: $name gauged at ${value}. Tags: ${tags.mkString(",")}")
  }

  def count(name: String, value: Long, tags: String*): Unit = {
    logger(s"#gauge: $name gauged at ${value}. Tags: ${tags.mkString(",")}")
  }

  def recordExecutionTime(name: String, value: Long, tags: String*): Unit = {
    logger(s"#gauge: $name gauged at ${value}. Tags: ${tags.mkString(",")}")
  }

  def recordHistogramValue(name: String, value: Long, tags: String*): Unit = {
    logger(s"#gauge: $name gauged at ${value}. Tags: ${tags.mkString(",")}")
  }
}

object LoggingStatsDMetrics {

  /**
   * A simple println based metrics impl.
   */
  def printlnMetrics = LoggingStatsDMetrics(println)
}
