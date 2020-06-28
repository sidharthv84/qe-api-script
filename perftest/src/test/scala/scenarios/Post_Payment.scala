package scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import utils.Environment

import scala.collection.mutable
import scala.util.Random

object Post_Payment {

  private val testData = csv("data/testData.csv").circular
  private val requestBody_newCard = ElFileBody("request-bodies/authenticate-new-card.json")
  println(requestBody_newCard)
  val postPayment_newCard: ScenarioBuilder = scenario("post payment - new card")
    .feed(testData)
    .exec(session => {
      val user = session("userData").as[mutable.HashMap[String, String]]
      session.setAll(user)
    })
    .exec(http("post payment new card")
      .post("/test/sid3")
      .header("User-Id", "${User-Id}")
      .body(requestBody_newCard)
      .check(status.is(200))
    //  .check(jsonPath("$.paymentReferenceId").exists)
    )
 // println(requestBody_newCard)

  val postPayment: ScenarioBuilder = scenario("post the payment")
    .exec(postPayment_newCard)
}
