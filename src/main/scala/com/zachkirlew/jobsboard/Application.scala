package com.zachkirlew.jobsboard

import org.http4s.*
import cats.effect.*
import org.http4s.dsl.*
import org.http4s.dsl.impl.*
import org.http4s.server.*
import cats.*
import com.zachkirlew.jobsboard.config.EmberConfig
import com.zachkirlew.jobsboard.http.routes.HealthRoutes
import org.http4s.ember.server
import org.http4s.ember.server.EmberServerBuilder
import pureconfig.ConfigSource
import pureconfig.ConfigSource.*
import pureconfig.error.ConfigReaderException
import com.zachkirlew.jobsboard.config.syntax.*
import com.zachkirlew.jobsboard.http.HttpApi

object Application extends IOApp.Simple {

  override def run: IO[Unit] = ConfigSource.default.loadF[IO, EmberConfig].flatMap { config =>
    EmberServerBuilder
      .default[IO]
      .withHost(config.host)
      .withPort(config.port)
      .withHttpApp(HttpApi[IO].routes.orNotFound)
      .build
      .use(_ => IO.println("Server ready!") *> IO.never)
  }

}
