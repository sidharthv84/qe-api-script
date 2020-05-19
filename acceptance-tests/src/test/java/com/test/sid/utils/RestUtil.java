package com.test.sid.utils;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Assert;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestUtil {

    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }


    public static String getApiEndpoint(String resources , List<NameValuePair> queryParams){
        URIBuilder builder = null;
        try{
            builder = new URIBuilder()
                    .setScheme("http")
                    .setHost("localhost:8086")
                    .setPath(resources);
                  //  .setParameter("sid","rifs");

            System.out.println(InetAddress.getLocalHost());
            System.out.println(InetAddress.getLocalHost().getHostAddress());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return builder.toString();
    }

    public void configure() {
        RestAssured.baseURI = "https://www.googleapis.com/";
        RestAssured.basePath="/books/v1/volumes";
    }

    public RequestSpecification getRequestSpecification() {
        return RestAssured.given().contentType(ContentType.JSON);
    }

    public Response getResponse(RequestSpecification requestSpecification,
                                String endpoint,int status){
        Response response = requestSpecification.get(endpoint);
        Assert.assertEquals(response.getStatusCode(),status);
        response.then().log().all();
        return response;
    }
}
