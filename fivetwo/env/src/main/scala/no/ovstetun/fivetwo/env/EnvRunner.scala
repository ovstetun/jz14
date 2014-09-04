package no.ovstetun.fivetwo.env

import unfiltered.request.GET
import unfiltered.response.{JsonContent, ResponseString}
import unfiltered.directives._, Directives._

object EnvRunner extends App {
  unfiltered.jetty.Server.http(8081).plan(plan).run

  lazy val plan = unfiltered.filter.Planify {
    Directive.Intent.Path {
      case "/" => for {
        _ <- GET
      } yield {
        import org.json4s.DefaultFormats
        import org.json4s.native.Serialization.write
        implicit val formats = DefaultFormats

        val env = sys.env
        JsonContent ~> ResponseString(write(env))
      }
    }
  }
}
