package com.imageintelligence.metrics.examples

import com.imageintelligence.metrics.LoggingStatsDMetrics

object LoggingStatsDMetricsExample {
  def main(args: Array[String]): Unit = {
    val metrics = LoggingStatsDMetrics.printlnMetrics
    StatsDMetricsExample.run(metrics)
  }
}
