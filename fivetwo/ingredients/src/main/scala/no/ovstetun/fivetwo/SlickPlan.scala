package no.ovstetun.fivetwo

import unfiltered.response.Pass
import scala.slick.driver.JdbcProfile

trait SlickPlan {
  val driver: JdbcProfile

  import driver.simple._

  def db: Database

  def SlickCycle[A, B](intent: Session => unfiltered.Cycle.Intent[A, B]): unfiltered.Cycle.Intent[A, B] = {
    case req =>
      db.withSession { implicit session =>
        intent(session).lift(req).getOrElse(Pass)
      }
  }
}
