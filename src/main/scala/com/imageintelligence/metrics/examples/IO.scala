package com.imageintelligence.metrics.examples

import scalaz.Monad

/**
 * Lazy IO monad implemented here so we don't require a dependency of scalaz.effect or scalaz.task
 */
sealed trait IO[A] {
  def run: A = this match {
    case Return(a) => a()
    case Suspend(s) => s().run
  }
}
final case class Return[A](a: () => A) extends IO[A]
final case class Suspend[A](s: () => IO[A]) extends IO[A]

object IO {
  def point[A](a: => A): IO[A] = Return(() => a)

  implicit def MonadPure: Monad[IO] = new Monad[IO] {
    def point[A](a: => A): IO[A] = Return(() => a)
    def bind[A, B](fa: IO[A])(f: A => IO[B]): IO[B] = Suspend(() => f(fa.run))
  }
}
