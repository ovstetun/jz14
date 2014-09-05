package no.ovstetun.fivetwo

object ElasticIntegration {

  import com.sksamuel.elastic4s._, ElasticDsl._
  import com.sksamuel.elastic4s.source.ObjectSource

  import Environment.elastic

  lazy val client = ElasticClient.remote(elastic.host, elastic.port)

  def save(ingredient: Ingredient) = {
    client execute {
      index into "ingredients/ingredient" id ingredient.id.get doc ObjectSource(ingredient)
    }
  }
}
