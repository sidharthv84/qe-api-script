package com.test.sid.steps;

import com.jayway.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpHeaders;
import org.junit.Ignore;
import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.response.Cookie.PATH;
import static java.security.KeyRep.Type.SECRET;

@Ignore

public class StepDefinitionBase {

    static final Map<String,String> DEFAULT_HEADERS;

    static {
        DEFAULT_HEADERS = new HashMap<>();
        DEFAULT_HEADERS.put(HttpHeaders.CONTENT_TYPE, "application/json");
    }

  protected transient ValidatableResponse response;

    public StepDefinitionBase() {
        requestSpecification = given()
             //   .keyStore(PATH, SECRET)
             //   .trustStore(PATH, SECRET)
                .urlEncodingEnabled(false).log().all();
    }


    protected void makeGetCall(String url, Map<String,String> headers) {
        response = requestSpecification
                .headers(ObjectUtils.defaultIfNull(headers, new HashMap<>()))
                .when()
                .port(port)
                .get(url)
                .then();
                //.statusCode(200);
    }

    protected void makePostCallWithJson(String url, Map<String,String> headers, Object body ) {
        response = requestSpecification
                .headers(ObjectUtils.defaultIfNull(headers, new HashMap<>()))
                .body(body)
                .when()
                .port(port)
                .post(url)
                .then();
    }

    protected void makePutCallWithJson(String url, Map<String,String> headers, Object body ) {
        response = requestSpecification
                .headers(ObjectUtils.defaultIfNull(headers, new HashMap<>()))
                .body(body)
                .when()
                .port(port)
                .put(url)
                .then();
    }

    protected void makeDeleteCallWithJson(String url, Map<String,String> headers, Object body ) {
        response = requestSpecification
                .headers(ObjectUtils.defaultIfNull(headers, new HashMap<>()))
                .body(body)
                .when()
                .port(port)
                .delete(url)
                .then();
    }

    protected void makePatchCallWithJson(String url, Map<String,String> headers, Object body ) {
        response = requestSpecification
                .headers(ObjectUtils.defaultIfNull(headers, new HashMap<>()))
                .body(body)
                .when()
                .port(port)
                .patch(url)
                .then();
    }

    protected void makePostCallWithJson1( String body ) {
        response = given()
                .contentType("application/json")
                .body(body)
                .when()
                .get("https://showoff-rails-react-production.herokuapp.com/api/v1/users")
                .then();
    }
}