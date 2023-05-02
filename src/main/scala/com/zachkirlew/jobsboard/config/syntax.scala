package com.zachkirlew.jobsboard.config

import cats.MonadThrow
import cats.effect.IO
import pureconfig.error.ConfigReaderException
import pureconfig.{ConfigReader, ConfigSource}

import scala.reflect.ClassTag

object syntax {
  extension (source: ConfigSource)
    def loadF[F[_], A](using reader: ConfigReader[A], F: MonadThrow[F], tag: ClassTag[A]): F[A] =
      source.load[A] match {
        case Left(errors)  => F.raiseError[A](ConfigReaderException(errors))
        case Right(config) => F.pure(config)
      }
}
