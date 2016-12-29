package com.imageintelligence.metrics.examples

import com.imageintelligence.metrics.impl.NonBlockingStatsDMetrics

object NonBlockingStatsDMetricsExample {
  def main(args: Array[String]): Unit = {
    val metrics = NonBlockingStatsDMetrics("example", "localhost", 8152)
    StatsDMetricsExample.run(metrics)
  }
}
