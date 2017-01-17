package com.imageintelligence.metrics.examples

import com.imageintelligence.metrics.DogStatsDMetrics
import scalaz.Monad

object StatsDMetricsExample {

  def doSomeWork(): String = {
    Thread.sleep(1000)
    println("Performing work")
    "Complete"
  }

  def doSomeWorkMonad(): IO[String] = {
    Monad[IO].point(doSomeWork)
  }

  def run(metrics: DogStatsDMetrics): Unit =  {
    metrics.timeBlock("blocking-work")(doSomeWork())
    metrics.timeMonad("monad-work")(doSomeWorkMonad()).run

    metrics.decrement("example-decrement")
    metrics.increment("example-increment")

    metrics.incrementBy("example-incrementBy", 10)
    metrics.decrementBy("example-decrementBy", 10)

    metrics.gauge("example-gauge", 22)
    metrics.time("example-time", 23)
  }

}
