package com.zachkirlew.jobsboard.http.routes

import cats.Monad
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.dsl.impl.{->, /}
import org.http4s.server.Router
import cats.implicits._

class JobRoutes[F[_]: Monad] private extends Http4sDsl[F] {

  private val allJobsRoute: HttpRoutes[F] = {
    HttpRoutes.of[F] { case GET -> Root =>
      Ok("TODO fetch all jobs")
    }
  }

  private val findJobRoute: HttpRoutes[F] = {
    HttpRoutes.of[F] { case GET -> Root / UUIDVar(id) =>
      Ok(s"TODO find job for id $id")
    }
  }

  private val createJobRoute: HttpRoutes[F] = {
    HttpRoutes.of[F] { case POST -> Root =>
      Ok("TODO create job")
    }
  }

  private val updateJobRoute: HttpRoutes[F] = {
    HttpRoutes.of[F] { case PUT -> Root / UUIDVar(id) =>
      Ok(s"TODO update job for id $id")
    }
  }

  private val deleteJobRoute: HttpRoutes[F] = {
    HttpRoutes.of[F] { case DELETE -> Root / UUIDVar(id) =>
      Ok(s"TODO delete job for id $id")
    }
  }

  val routes = Router(
    "/jobs" -> (allJobsRoute <+> findJobRoute <+> createJobRoute <+> updateJobRoute <+> deleteJobRoute)
  )

}

object JobRoutes {
  def apply[F[_]: Monad]: JobRoutes[F] = new JobRoutes[F]
}
