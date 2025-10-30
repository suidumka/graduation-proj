package io.loop.step_definitions_api;

import io.loop.utilities.*;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.github.javafaker.Faker;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class AddClientApiTest {

    String token;
    Response response;
    String username;
    String password;
    Map <String, Object> rBody;
    Logger LOG = LogManager.getLogger();
    Faker faker =  new Faker();

    @Given("User logged in to Docuport api as {string} role")
    public void user_logged_in_to_docuport_api_as_role(String role) {

        username = DocuportApiUtil.getUserInfo(role).get("userEmail");
        password = DocuportApiUtil.getUserInfo(role).get("userPassword");
        token = DocuportApiUtil.getAccessToken(username, password);

    }

    @When("Users sends POST request to {string} with following info:")
    public void users_sends_POST_request_to_with_following_info(String endPoint, Map <String, String> requestBody) {

        rBody = new HashMap<>(requestBody);
        //we've copied Map from the parameters, in order to be able to modify the Body of it. Like below:

        rBody.put("name", faker.company().name());

        response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().header("Authorization", token)
                .and().body(rBody)
                .and().log().all() // to see what random name we've added
                .when().post(ConfigurationReader.getProperties("docuportBETA") + endPoint );

        LOG.info("Status code is: " + response.statusCode());

/*
{
    "name": "Barton LLC",
    "firstName": "Martin",
    "lastName": "Sheinz",
    "clientType": "1"
}
 */
    }

    @Then("the POST status code should be {int}")
    public void thePOSTStatusCodeShouldBe(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.statusCode());
    }


    @Then("Database should persist same client info")
    public void database_should_persist_same_client_info() {

        String sqlQuery = "SELECT client_type, name, first_name, last_name FROM document.clients WHERE name = '" + rBody.get("name") + "'";
        List<Map<String, Object>> dbResult = DBUtils.getQueryResultMap(sqlQuery);


        assertEquals(rBody.get("name"), dbResult.get(0).get("name"));
        //assertEquals(rBody.get("firstName"), dbResult.get(0).get("first_name"));
        //assertEquals(rBody.get("lastName"), dbResult.get(0).get("last_name"));
        assertEquals(rBody.get("clientType"), dbResult.get(0).get("client_type")+"");

        LOG.info("Client info is shown is database");
    }


}