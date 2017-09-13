package com.imageintelligence.metrics.examples

import com.imageintelligence.metrics.DogStatsDMetrics
import cats._
import cats.data.EitherT
import cats.implicits._

object StatsDMetricsExample {

  def doSomeWork(): String = {
    Thread.sleep(1000)
    println("Performing work")
    "Complete"
  }

  def doSomeWorkMonad(): IO[String] = {
    Monad[IO].pure(doSomeWork)
  }

  def doSomeWorkEitherT(): EitherT[IO, Throwable, String] = {
    EitherT(Monad[IO].pure(Either.catchNonFatal(doSomeWork)))
  }

  def run(metrics: DogStatsDMetrics): Unit =  {
    metrics.timeBlock("blocking-work")(doSomeWork())
    metrics.timeMonad("monad-work")(doSomeWorkMonad()).run
    metrics.timeEitherT("eithert-work")(doSomeWorkEitherT()).value.run

    metrics.decrement("example-decrement")
    metrics.increment("example-increment")

    metrics.incrementBy("example-incrementBy", 10)
    metrics.decrementBy("example-decrementBy", 10)

    metrics.gauge("example-gauge", 22)
    metrics.time("example-time", 23)
  }

}
