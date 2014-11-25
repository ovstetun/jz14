package no.ovstetun.fivetwo

import scala.util.Properties

object Environment {

  lazy val elastic = service("ELASTIC", 9300)

  lazy val mysql = service("MYSQL", 3306)

  case class Service(host: String, port: Int)

  def service(name: String, defaultPort: Int) = {
    val h = host(name, defaultPort, "localdocker")
    val p = servicePort(name, defaultPort)

    Service(h, p)
  }

  def prop(prop: String, default: String) = {
    Properties.envOrElse(prop, default)
  }

  def host(name: String, defaultPort: Int, default: String) = {
    val prop = name + "_PORT_" + defaultPort + "_TCP_ADDR"
    Properties.envOrElse(prop, default)
  }

  def servicePort(serviceName: String, default: Int) = {
    val realServiceProp = serviceName + "_PORT_" + default + "_TCP_PORT"

    Properties.envOrElse(realServiceProp, default.toString).toInt
  }
}
