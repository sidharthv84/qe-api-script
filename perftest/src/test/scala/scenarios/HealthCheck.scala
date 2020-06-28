package scenarios

import io.gatling.core.structure.ScenarioBuilder
import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
 * Used a Health check to ensure that the system is currently functional
 */

object HealthCheck {

  /**
   * Checks that the system returns a code of:
   * 200(OK) : This indicates that the command line has been correctly executed
   */

  val checkTheSystemIsFunctional: ScenarioBuilder = scenario("health check")
    .exec(http("Health check task")
        .get("health")
        .check(status.is(expected = 200)))
}
