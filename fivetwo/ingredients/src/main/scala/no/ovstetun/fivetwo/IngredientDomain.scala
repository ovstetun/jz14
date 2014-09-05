package no.ovstetun.fivetwo

import scala.slick.driver.JdbcProfile

case class Ingredient(name: String, calories: Int, id: Option[Int] = None)

class IngredientSlickModule(val driver: JdbcProfile) {
  import driver.simple._

  class Ingredients(tag: Tag) extends Table[Ingredient](tag, "INGREDIENTS") {
    def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def name = column[String]("NAME")
    def calories = column[Int]("CALORIES")

    def * = (name, calories, id.?) <>(Ingredient.tupled, Ingredient.unapply)
  }

  object ingredients extends TableQuery(new Ingredients(_)) {
    def byId = this.findBy(_.id)
  }

  def create(ing: Ingredient)(implicit ss: Session): Ingredient = ingredients returning ingredients.map(_.id) into {
    case (i, genId) => i copy (id = Some(genId))
  } insert ing
}
