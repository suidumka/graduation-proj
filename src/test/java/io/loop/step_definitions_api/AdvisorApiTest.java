package io.loop.step_definitions_api;

import io.loop.utilities.DocuportApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AdvisorApiTest extends DocuportApiTestBase {

    /**
     * Given accept type is Json
     * And header Authorization is access token for advisor
     * And query param -searchTerm -- > is Scottie Heaney
     * When I send GET request to /api/v1/document/clients/all
     * Then status code is 200
     * And content type is application/json; charset=utf-8
     * body index 1 matches data:
     * {
     *         "id": 1406,
     *         "name": "Scottie Heaney",
     *         "clientType": 2,
     *         "isActive": true,
     *         "advisor": null
     * }
     * Extra: Practice using Map
     */

    @DisplayName("GET /api/v1/document/clients/all")
    @Test
    public void getAllClientsTest() {

        Map <String, Object> queryParam = new HashMap<>();
        queryParam.put("searchTerm", "Scottie Heaney");

        Map <String, Object> body = new HashMap<>();
        body.put("id", 1406);
        body.put("name", "Scottie Heaney");
        body.put("clientType", 2);
        body.put("isActive", true);
        body.put("advisor", null);

        Response response = given().contentType(ContentType.JSON)
                .and().header("Authorization", getAccessToken("advisor"))
                .and().queryParams(queryParam)
                .when().get("/api/v1/document/clients/all");

        JsonPath js = response.jsonPath();

        assertThat(response.contentType(),is("application/json; charset=utf-8") );
        assertThat(response.statusCode(), is(HttpStatus.SC_OK));
        assertThat(js.getInt("[0].id"), is((Integer) body.get("id")));
        assertThat(js.getString("[0].name"), is(body.get("name")));
        assertThat(js.getInt("[0].clientType"), is((Integer) body.get("clientType")));
        assertThat(js.getBoolean("[0].isActive"), is(body.get("isActive")));
        assertThat(js.getString("[0].advisor"), is(nullValue()) );
    }


    @DisplayName("POST add a client")
    @Test
    public void addClientTest() {
        given().contentType(ContentType.JSON)
                .and().header("Authorization", getAccessToken("client"))
                .and().post("/api/v1/document/users/me/clients");



    }



}
