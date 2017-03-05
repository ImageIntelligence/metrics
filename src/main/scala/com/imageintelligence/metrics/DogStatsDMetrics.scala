package com.imageintelligence.metrics

import scalaz.EitherT
import scalaz.Monad
import scalaz.syntax.monad._
import scalaz.Id._

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
    val (duration, result) = Timing.timeMonad[Id, A](f)
    recordExecutionTime(name, duration.toMillis, tags: _*)
    result
  }

  /**
   * * Times a monadic function. Useful for timing Tasks or Futures.
   */
  def timeMonad[M[_]: Monad, A](name: String, tags: String*)(f: => M[A]): M[A] = {
    Timing.timeMonad[M, A](f).map { case (duration, result) =>
      recordExecutionTime(name, duration.toMillis, tags: _*)
      result
    }
  }

  /**
   * * Times a monadic EitherT function. Useful for timing transformer stacks
   */
  def timeEitherT[M[_]: Monad, E, A](name: String, tags: String*)(f: => EitherT[M, E, A]): EitherT[M, E, A] = {
    type ET[Z] = EitherT[M, E, Z]
    Timing.timeMonad[ET, A](f).map { case (duration, result) =>
      recordExecutionTime(name, duration.toMillis, tags: _*)
      result
    }
  }
}
