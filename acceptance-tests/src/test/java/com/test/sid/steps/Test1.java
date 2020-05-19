package com.test.sid.steps;


import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.Response;
import com.test.sid.base.Example;
import com.test.sid.utils.RestUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import static com.jayway.restassured.RestAssured.*;

public class Test1 extends StepDefinitionBase{
    @When("^I submit a request to the API$")
    public void iSubmitARequestToTheAPI() {
        System.out.println("Test1");
    }

    @Given("^API is up and running$")
    public void apiIsUpAndRunning() {
        System.out.println("Test2");
        Assert.assertEquals("200", "200");
    }

    @Then("^I validate the \"([^\"]*)\"$")
    public void iValidateThe(String status) throws Throwable {
        System.out.println(status);
    }

    @When("^I submit a request to \"([^\"]*)\"$")
    public void iSubmitARequestTo(String resourcePath) throws Throwable {
        RestAssured.defaultParser = Parser.JSON;
        String baseUrl = RestUtil.getApiEndpoint(resourcePath,null);
        System.out.println(baseUrl);
         Response response = RestAssured.given()
                .headers("Accept","application/json")
                .headers("User-Id","123")
                .get("http://localhost:8086/test/sid");
        System.out.println(response.getStatusCode());
        Example example = response.as(Example.class);
        System.out.println(example.getClientId());
    }


    @Then("^On Successful completion of the request, Validate the \"([^\"]*)\"$")
    public void onSuccessfulCompletionOfTheRequestValidateThe(String arg0) throws Throwable {
        RestAssured.defaultParser = Parser.JSON;
        Example user = given()
                .headers("Accept","application/json")
                .headers("User-Id","123")
                .get("http://localhost:8086/test/sid").as(Example.class);

        System.out.println(user.toString());
    }

    @And("^Validate the response body$")
    public void validateTheResponseBody() {
    }
}
