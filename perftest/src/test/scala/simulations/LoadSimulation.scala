package simulations

import io.gatling.core.Predef.{constantUsersPerSec, nothingFor, _}
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.PopulationBuilder
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder
import io.gatling.http.request.ExtraInfo
import jdk.nashorn.internal.ir.RuntimeNode.Request
import scenarios._
import utils.{Environment, Headers}
import utils.Environment
import utils.Headers

import scala.concurrent.duration._

/**
 * Tests the system for average load expected by the business
 * This is currently assumed to be 2 user per second
 */
class LoadSimulation extends Simulation {

  private val duration = 60.minutes
  private val rampUp_duration = 10.seconds
  private val users: Int = 3
  private val max_users: Int = 6
  private val startUp_delay = 8.seconds

  val httpConfiguration: HttpProtocolBuilder = http
    .baseURL("http://localhost:8086")
    .headers(Headers.commonHeader)
    .disableWarmUp
    .extraInfoExtractor(ExtraInfo => {
          println("Request "+ ExtraInfo.request + "\n httpCode: " + ExtraInfo.response.statusCode.getOrElse(0)
          + "\nResponse: "+ ExtraInfo.response.body.string);
        List(ExtraInfo.response.statusCode, ExtraInfo.response.body.string)
      })

  val requestAuthenticate: PopulationBuilder = Request_Authenticate.requestAuthenticate_newCard.inject(
  rampUsersPerSec(users) to (max_users) during(rampUp_duration),
  constantUsersPerSec(users) during(duration)
  )

  setUp(requestAuthenticate)
    .protocols(httpConfiguration)
    .assertions(global.failedRequests.count.is(0))
  //val firstTest: PopulationBuilder = P

}


