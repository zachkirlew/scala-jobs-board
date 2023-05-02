package com.zachkirlew.jobsboard.http

import com.zachkirlew.jobsboard.http.routes.{HealthRoutes, JobRoutes}
import org.http4s.server.Router
import cats.implicits._
import cats.Monad

class HttpApi[F[_]: Monad] private {

  private val healthRoutes = HealthRoutes[F].routes
  private val jobRoutes    = JobRoutes[F].routes

  val routes = Router("/api" -> (healthRoutes <+> jobRoutes))
}

object HttpApi {
  def apply[F[_]: Monad]: HttpApi[F] = new HttpApi[F]
}
