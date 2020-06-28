package scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

object Request_Authenticate {

  //private val testData = csv("data/testData.csv").circular

  private val requestBody_newCard = ElFileBody("request-bodies/authenticate-new-card.json")

  val requestAuthenticate_newCard: ScenarioBuilder = scenario("Authenticate - new card")
   // .feed(testData)
        .exec(http("Authenticate new card")
        .get("/test/sid").header("Accept","application/json")
        .check(status.is(200))
    )

  val request_Authenticate: ScenarioBuilder = scenario("Authenticate the request")
    .exec(requestAuthenticate_newCard)



}
