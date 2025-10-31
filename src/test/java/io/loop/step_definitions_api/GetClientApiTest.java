package io.loop.step_definitions_api;

import io.loop.utilities.*;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class GetClientApiTest {

    String token;
    Response response;
    String username;
    String password;
    Map<String, Object> queryParams = new HashMap<>();
    Logger LOG = LogManager.getLogger();


    @Given("the user has a valid access token for role {string}")
    public void the_user_has_a_valid_access_token_for_role(String role) {
        username = DocuportApiUtil.getUserInfo(role).get("userEmail");
        password = DocuportApiUtil.getUserInfo(role).get("userPassword");

        token = DocuportApiUtil.getAccessToken(username, password);

        LOG.info("username, password, token are authorized");
    }


    @Given("the advisor adds query parameter {string} with value {string}")
    public void the_advisor_adds_query_parameter_with_value(String queryParam, String paramValue) {
        queryParams.put(queryParam, paramValue);

    }

    @When("the advisor sends a GET request to {string}")
    public void the_advisor_sends_a_get_request_to(String endpoint) {



        response = given()
                .contentType(ContentType.JSON)
                .and().header("Authorization", token)
                .and().queryParams(queryParams)
                //.and().log().all()
                .when()
                .get(ConfigurationReader.getProperties("docuportBETA") + endpoint);
        //response.prettyPrint();

       LOG.info(response.asString());
    }

    @Then("the status code should be {int}")
    public void the_status_code_should_be(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.statusCode());
        LOG.info("Response status code is " + response.statusCode());
    }


    @Then("the content type should be {string}")
    public void the_content_type_should_be(String expectedContentType) {
        assertThat(response.contentType(), is(expectedContentType));
        LOG.info("Response content type is " + response.contentType());

    }

    @Then("the first client object should match:")
    public void the_first_client_object_should_match(Map<String, String> reqBody) {

        JsonPath js= response.jsonPath();
        List<String> advisor = js.getList("advisor");

        assertEquals(Integer.parseInt(reqBody.get("id")), js.getInt("[0].id"));
        assertEquals(reqBody.get("name"), js.getString("[0].name"));
        assertEquals(reqBody.get("clientType"), js.getString("[0].clientType"));
        assertEquals(reqBody.get("isActive"), js.getString("[0].isActive"));
        assertThat(reqBody.get("advisor"), is(reqBody.get("advisor")));
        LOG.info("Actual body {} is matching expected {}", response.asString(), reqBody);

    }

}
