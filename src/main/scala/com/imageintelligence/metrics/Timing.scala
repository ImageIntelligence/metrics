package com.imageintelligence.metrics

import java.util.concurrent.TimeUnit

import scala.concurrent.duration.Duration
import scalaz.EitherT
import scalaz.Monad
import scalaz.syntax.monad._

object Timing {

  /**
   * Times a block.
   */
  def timeBlock[A](f: => A): (Duration, A) = {
    val start = System.nanoTime()
    val result = f
    val end = System.nanoTime()
    (Duration(end - start, TimeUnit.NANOSECONDS), result)
  }

  /**
    * * Times a monadic function. Useful for timing Tasks
    */
  def timeMonad[M[_]: Monad, A](f: => M[A]): M[(Duration, A)] = {
    System.nanoTime().pure[M].flatMap { start =>
      for {
        result <- f
        end <- System.nanoTime().pure[M]
      } yield (Duration(end - start, TimeUnit.NANOSECONDS), result)
    }
  }

  /**
   * * Times a monadic EitherT function. Useful for timing transformer stacks
   */
  def timeEitherT[M[_]: Monad, E, A](f: => EitherT[M, E, A]): EitherT[M, E, (Duration, A)] = {
    type ET[Z] = EitherT[M, E, Z]
    System.nanoTime().pure[ET].flatMap { start =>
      for {
        result <- f
        end <- System.nanoTime().pure[ET]
      } yield (Duration(end - start, TimeUnit.NANOSECONDS), result)
    }
  }
}
