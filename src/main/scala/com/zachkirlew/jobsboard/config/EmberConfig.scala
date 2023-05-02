package com.zachkirlew.jobsboard.config

import com.comcast.ip4s.{Host, Port}
import pureconfig.ConfigReader
import pureconfig.error.CannotConvert
import pureconfig.generic.derivation.default.*

final case class EmberConfig(host: Host, port: Port) derives ConfigReader

object EmberConfig {
  given hostConfigReader: ConfigReader[Host] = ConfigReader[String].emap { hostString =>
    Host
      .fromString(hostString)
      .toRight(
        CannotConvert(hostString, Host.getClass.toString, s"Invalid host string: $hostString")
      )

  }
  given portConfigReader: ConfigReader[Port] = ConfigReader[String].emap { portString =>
    Port
      .fromString(portString)
      .toRight(
        CannotConvert(portString, Port.getClass.toString, s"Invalid port string: $portString")
      )
  }
}
