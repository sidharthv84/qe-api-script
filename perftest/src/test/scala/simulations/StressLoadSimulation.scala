package simulations

import io.gatling.core.Predef.{constantUsersPerSec, nothingFor, _}
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.PopulationBuilder
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder
import scenarios._
import utils.{Environment, Headers}
import utils.Environment
import utils.Headers
import utils.test


import scala.concurrent.duration._

/**
 * Tests the system for average load expected by the business
 * This is currently assumed to be 2 user per second
 */
class StressLoadSimulation extends Simulation {

  private val duration = 2.minutes
  private val rampUp_duration = 10.seconds
  private val users: Int = 3
  private val max_users: Int = 6
  private val startUp_delay = 8.seconds

  println("URL++++"+ test.baseURL)

  val httpConfiguration: HttpProtocolBuilder = http
    .baseURL(test.baseURL)
    .headers(Headers.commonHeader)
    .disableWarmUp
    .extraInfoExtractor(ExtraInfo => {
      println("Request "+ ExtraInfo.request + "\n httpCode: " + ExtraInfo.response.statusCode.getOrElse(0)
        + "\nResponse: "+ ExtraInfo.response.body.string);
      List(ExtraInfo.response.statusCode, ExtraInfo.response.body)
    })

//  val requestAuthenticate: PopulationBuilder = Request_Authenticate.requestAuthenticate_newCard.inject(
//    constantUsersPerSec(users) during(10 minutes),
//    constantUsersPerSec(users*3) during(10 minutes),
//    constantUsersPerSec(users*6) during(10 minutes),
//    constantUsersPerSec(users*9) during(10 minutes)
//  )

  val postPayment: PopulationBuilder = Post_Payment.postPayment.inject(
    nothingFor(startUp_delay),
    constantUsersPerSec(users) during(1 minutes),
    constantUsersPerSec(users*3) during(1 minutes),
    constantUsersPerSec(users*6) during(1 minutes),
    constantUsersPerSec(users*9) during(1 minutes)
  )

  setUp(postPayment)
    .protocols(httpConfiguration)
    .assertions(global.failedRequests.count.is(0))
//    .assertions(global.responseTime.max.lessThan(Environment.maxResponseTime.toInt))
}