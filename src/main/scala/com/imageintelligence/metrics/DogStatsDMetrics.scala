package com.imageintelligence.metrics

import scalaz.Monad
import scalaz.syntax.monad._

trait DogStatsDMetrics {

  def time(name: String, value: Long, tags: String*): Unit
  def increment(name: String, tags: String*): Unit
  def incrementBy(name: String, count: Int, tags: String*): Unit
  def decrement(name: String, tags: String*): Unit
  def decrementBy(name: String, count: Int, tags: String*): Unit
  def gauge(name: String, value: Long, tags: String*): Unit
  def count(name: String, value: Long, tags: String*): Unit
  def recordExecutionTime(name: String, value: Long, tags: String*): Unit
  def recordHistogramValue(name: String, value: Long, tags: String*): Unit

  /**
   * Times a block.
   */
  def timeBlock[A](name: String, tags: String*)(f: => A): A = {
    val (duration, result) = Timing.timeBlock(f)
    recordExecutionTime(name, duration.toMillis, tags: _*)
    result
  }

  /**
   * * Times a Monadic function. Useful for timing Tasks or Futures.
   */
  def timeMonad[M[_]: Monad, A](name: String, tags: String*)(f: => M[A]): M[A] = {
    Timing.timeMonad[M, A](f).map { case (duration, result) =>
      recordExecutionTime(name, duration.toMillis, tags: _*)
      result
    }
  }
}
