package com.imageintelligence.metrics

import scalaz.Monad
import scalaz.syntax.monad._

trait StatsDMetrics {

  def time(name: String, value: Long): Unit
  def increment(name: String): Unit
  def incrementBy(name: String, count: Int): Unit
  def decrement(name: String): Unit
  def decrementBy(name: String, count: Int): Unit
  def gauge(name: String, value: Long): Unit

  /**
   * Times a block.
   */
  def timeBlock[A](name: String)(f: => A): A = {
    val (duration, result) = Timing.timeBlock(name)(f)
    time(name, duration.toMillis)
    result
  }

  /**
   * * Times a Monadic function. Useful for timing Tasks or Futures.
   */
  def timeMonad[M[_]: Monad, A](name: String)(f: => M[A]): M[A] = {
    Timing.timeMonad[M, A](name)(f).map { case (duration, result) =>
      time(name, duration.toMillis)
      result
    }
  }
}
