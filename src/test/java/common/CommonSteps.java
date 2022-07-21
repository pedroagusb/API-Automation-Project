package common;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java8.En;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CommonSteps implements En {


    JSONObject requestBody;
    JSONParser parser = new JSONParser();

    Response response = null;

    @When("^user wants to send a post using the request from '(.*)'$")
    public void userSendPost(String requestName) {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/pet";

        try{
            requestBody = (JSONObject) parser.parse(new FileReader("src/test/resources/jsonRequest/"+requestName));
            response = RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .log()
                    .all()
                    .body(requestBody)
                    .post(baseURI)
                    .then()
                    .extract().response();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @When("^User wants to get the pet with id: (.*)$")
    public void userSendGet(String petId) {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/pet/";

        try{
            response = RestAssured
                    .given()
                    .log()
                    .all()
                    .contentType(ContentType.JSON)
                    .get(baseURI+petId)
                    .then()
                    .statusCode(200)
                    .log()
                    .all()
                    .extract().response();
        }catch(Exception e){
            e.printStackTrace();
        }


    }

    @When("^User wants to change the pet using the request from '(.*)'$")
    public void userSendPut(String requestName) {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/pet";

        try{
            requestBody = (JSONObject) parser.parse(new FileReader("src/test/resources/jsonRequest/"+requestName));
            response = RestAssured
                    .given()
                    .log()
                    .all()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .put(baseURI)
                    .then().extract().response();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Then("^Status code is (.*)$")
    public void userCheckStatus(Integer statusCode){
        response.then().statusCode(statusCode);
    }

    @Then("^The response matches with the JSON Schema '(.*)'$")
    public void userVerifySchema(String schemaName){
        try {
            response.then().assertThat().body(matchesJsonSchemaInClasspath("jsonSchema/"+ schemaName));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

