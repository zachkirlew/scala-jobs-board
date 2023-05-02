package com.zachkirlew.jobsboard.http.routes

import cats.Monad
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.dsl.impl.{->, /}
import org.http4s.server.Router

class HealthRoutes[F[_]: Monad] private extends Http4sDsl[F] {

  private val healthEndpoint: HttpRoutes[F] = {
    HttpRoutes.of[F] { case GET -> Root =>
      Ok("All good!")
    }
  }

  val routes = Router("/health" -> healthEndpoint)

}

object HealthRoutes {
  def apply[F[_]: Monad]: HealthRoutes[F] = new HealthRoutes()
}
