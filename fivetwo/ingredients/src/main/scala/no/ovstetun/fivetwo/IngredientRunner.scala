package no.ovstetun.fivetwo

import com.mchange.v2.c3p0.ComboPooledDataSource
import org.flywaydb.core.Flyway
import org.slf4j.LoggerFactory
import scala.slick.driver.MySQLDriver


object IngredientRunner extends App {
  private val logger = LoggerFactory.getLogger("no.ovstetun.fivetwo.IngredientRunner")

  val profile = MySQLDriver
  import profile.simple._

  import Environment.mysql
  val mysqlPw = Environment.prop("MYSQL_INGREDIENTS_PW", "ingredients")


  logger.warn("MySql is: " + mysql.toString)
  logger.warn("Password is: " + mysqlPw)

  val source = new ComboPooledDataSource()
  source.setDriverClass("com.mysql.jdbc.Driver")
  source.setJdbcUrl("jdbc:mysql://"+ mysql.host+":"+ mysql.port+"/ingredients")
  source.setUser("ingredients")
  source.setPassword(mysqlPw)

  val flyway = new Flyway()
  flyway.setDataSource(source)
  flyway.migrate()

  lazy val database = Database.forDataSource(source)

  val module = new IngredientSlickModule(profile)
  private val ingredientPlan = new IngredientPlan(module) {
    val driver = profile
    val db = database
  }

  unfiltered.jetty.Server.http(8080).plan(ingredientPlan.plan).run
}
