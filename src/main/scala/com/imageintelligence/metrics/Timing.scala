package com.imageintelligence.metrics

import java.util.concurrent.TimeUnit

import scala.concurrent.duration.Duration
import cats._, cats.implicits._

object Timing {

  /**
    * Times a monadic function.
    */
  def timeMonad[M[_]: Monad, A](f: => M[A]): M[(Duration, A)] = {
    for {
      start <- System.nanoTime().pure[M]
      result <- f
      end <- System.nanoTime().pure[M]
    } yield (Duration(end - start, TimeUnit.NANOSECONDS), result)
  }
}
