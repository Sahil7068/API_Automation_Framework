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
import resources.ApiResource;
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
    static String place_id;



    @Given("Add Place Payload with {string} and {string} and {string}")
    public void add_place_payload_with_and_and(String name, String phone_number, String address) throws IOException {





         res = given().log().all().spec(requestSpecification()).
                body(build.addPlacePayload(name, phone_number, address));
    }



    @When("User Call {string} with {string} http request")
    public void user_call_with_http_request(String resource, String method) {

        ApiResource resourceAPI = ApiResource.valueOf(resource);
        resSpec = new ResponseSpecBuilder().expectStatusCode(200).build();

        if(method.equalsIgnoreCase("POST")) {
            response = res.when().post(resourceAPI.getResource());
        }
        else if (method.equalsIgnoreCase("GET")) {
            response = res.when().get(resourceAPI.getResource());
        }
        else if (method.equalsIgnoreCase("DELETE")) {
            response = res.when().delete(resourceAPI.getResource());
        }

    }
    @Then("Api Call is successful with status code {int}")
    public void api_call_is_successful_with_status_code(int int1) {
        Assert.assertEquals(response.getStatusCode(), int1);
    }
    @Then("{string} in Response Body is {string}")
    public void in_response_body_is(String key, String expectedValue) {

        Assert.assertEquals(getJsonPath(response, key), expectedValue);
    }

    @Then("Verify Place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expected, String resource) throws IOException {
        place_id = getJsonPath(response, "place_id");
        res = given().spec(requestSpecification()).queryParam("place_id", place_id);
        user_call_with_http_request(resource, "GET");
        String name = getJsonPath(response, "name");
        Assert.assertEquals(name, expected);

    }

    @Given("Delete Place Payload")
    public void delete_place_payload() throws IOException {
        res = given().spec(requestSpecification()).body(build.deletePayload(place_id));
    }
}
