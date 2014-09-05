package no.ovstetun.fivetwo

import scala.util.Properties

object Environment {

  lazy val elastic = service("ELASTIC", 9300)

  lazy val mysql = service("MYSQL", 3306)

  case class Service(host: String, port: Int)

  def service(name: String, defaultPort: Int) = {
    val h = host(name + "_PORT_" + defaultPort + "_TCP_ADDR", "localdocker")
    val p = servicePort(name, defaultPort)

    Service(h, p)
  }

  def prop(prop: String, default: String) = {
    Properties.envOrElse(prop, default)
  }
  def host(prop: String, default: String) = {
    if (isKubernetes) "localhost"
    else Properties.envOrElse(prop, default)
  }
  def servicePort(serviceName: String, default: Int) = {
    val realServiceProp =
      if (isKubernetes) serviceName+"_SERVICE_PORT"
      else serviceName + "_PORT_" + default + "_PORT"

    Properties.envOrElse(realServiceProp, default.toString).toInt
  }

  private def isKubernetes = {
    Properties.envOrNone("SERVICE_HOST").exists(_ => true)
  }
}
