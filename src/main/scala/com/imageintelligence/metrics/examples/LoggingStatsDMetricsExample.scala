package com.imageintelligence.metrics.examples

import com.imageintelligence.metrics.LoggingDogStatsDMetrics

object LoggingStatsDMetricsExample {
  def main(args: Array[String]): Unit = {
    val metrics = LoggingDogStatsDMetrics.printlnMetrics
    StatsDMetricsExample.run(metrics)
  }
}
