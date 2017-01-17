package com.imageintelligence.metrics.examples

import com.imageintelligence.metrics.NonBlockingDogStatsDMetrics

object NonBlockingStatsDMetricsExample {
  def main(args: Array[String]): Unit = {
    val metrics = NonBlockingDogStatsDMetrics("example", "localhost", 8152)
    StatsDMetricsExample.run(metrics)
  }
}
