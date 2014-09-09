package no.ovstetun.fivetwo


import unfiltered.request._
import unfiltered.response._
import unfiltered.directives._, Directives._

abstract class IngredientPlan(val ingredientModule: IngredientSlickModule) extends SlickPlan {
  import driver.simple._
  import PlanUtils._

  val plan = unfiltered.filter.Planify {
    SlickCycle { implicit sess: Session =>
      Directive.Intent.Path {
        case "/" =>
          json(GET) { () =>
            val all = ingredientModule.ingredients.list
            Right(all)
          } |
          jsonInput(POST) { ingredient: Ingredient =>
            val res = ingredientModule.create(ingredient)

            ElasticIntegration.save(res)

            Right(res)
          }

        case Seg(ID(id) :: Nil) =>
          json(GET) { () =>
            val i = ingredientModule.ingredients.byId(id).firstOption

            (i map (Right(_))) getOrElse Left(NotFound ~> ResponseString(s"no such ingredient, id = [$id]"))
          } |
          jsonInput(PUT) { ingredient: Ingredient =>
            val u = ingredientModule.ingredients.byId(id)

            u update ingredient.copy(id = Some(id))

            Right(ingredient copy (id = Some(id)))
          }
      }
    }
  }
}
