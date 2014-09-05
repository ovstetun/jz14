package no.ovstetun.fivetwo

import unfiltered.directives.Directives._
import unfiltered.request.{Body, Accepts, Method}
import unfiltered.response._

object PlanUtils {
  def json[R <: AnyRef](t: Method)(f: () => Either[ResponseFunction[Any], R]) = for {
    _ <- t
    _ <- Accepts.Json
    r <- request[Any]
  } yield {
    import org.json4s._
    import org.json4s.native.Serialization.write
    implicit val formats = DefaultFormats

    val res = f()
    //ResponseHeader("Access-Control-Allow-Origin", Seq("*")) ~>
    res fold(
            response => response,
            obj => JsonContent ~> ResponseString(write(obj))
            )
  }

  def jsonInput[T, R <: AnyRef](method: Method)(f: T => Either[ResponseFunction[Any], R])(implicit mf: Manifest[T]) = for {
    _ <- method
    _ <- Accepts.Json
    r <- request[Any]
  } yield {
    val s = Body.string(r)

    import org.json4s.DefaultFormats
    import org.json4s.native.Serialization.write
    import org.json4s.native.JsonParser._
    implicit val formats = DefaultFormats

    val t: Option[T] = parseOpt(s) flatMap (_.extractOpt[T])

    val res = t map f

    res map (x => x.fold(
      response => response,
      obj => JsonContent ~> ResponseString(write(obj))
    )) getOrElse BadRequest
  }

  object ID {
    def unapply(s: String): Option[Int] = scala.util.control.Exception.allCatch.opt(s.toInt)
  }

}
