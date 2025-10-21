package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import pojo.AddPlace;
import pojo.LongLat;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StepDefinition extends Utils {

    RequestSpecification req;
    ResponseSpecification resSpec;
    RequestSpecification res;
    Response response;

    TestDataBuild build = new TestDataBuild();



    @Given("Add Place Payload with {string} and {string} and {string}")
    public void add_place_payload_with_and_and(String name, String phone_number, String address) throws IOException {





         res = given().log().all().spec(requestSpecification()).
                body(build.addPlacePayload(name, phone_number, address));
    }



    @When("User Call {string}")
    public void user_call(String string) {
        resSpec = new ResponseSpecBuilder().expectStatusCode(200).build();
        response =res.when().post("/maps/api/place/add/json")
                .then().spec(resSpec).extract().response();
    }
    @Then("Api Call is successful with status code {int}")
    public void api_call_is_successful_with_status_code(Integer int1) {
        Assert.assertEquals(response.getStatusCode(), int1);
    }
    @Then("{string} in Response Body is {string}")
    public void in_response_body_is(String key, String expectedValue) {
        String placeResponse = response.asString();
        JsonPath js = new JsonPath(placeResponse);
        Assert.assertEquals(js.get(key).toString(), expectedValue);
    }
}
