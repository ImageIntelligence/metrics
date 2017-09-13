package com.imageintelligence.metrics.examples

import cats._, cats.implicits._

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
    def pure[A](x: A) = Return(() => x)

    def flatMap[A, B](fa: IO[A])(f: (A) => IO[B]) = Suspend(() => f(fa.run))

    def tailRecM[A, B](a: A)(f: (A) => IO[Either[A, B]]) = {
      f(a).flatMap {
        case Left(a) => tailRecM(a)(f)
        case Right(b) => pure(b)
      }
    }
  }
}
