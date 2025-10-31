package io.loop.utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class DocuportApiTestBase {

    protected static RequestSpecification reqSpec;


    protected static ResponseSpecification resSpec;

    @BeforeClass
    public static void setUp () {
        baseURI = ConfigurationReader.getProperties("docuportBETA");

        reqSpec = given().accept(ContentType.JSON)
                .and().header("Authorization", getAccessToken("employee"));

        resSpec = expect().statusCode(HttpStatus.SC_OK)
                .and().contentType(ContentType.JSON);
    }


    public static String getAccessToken (String userType) {

        String userTypeStr = "";
        String userTypePassword = "";

        if (userType.equalsIgnoreCase("employee")) {
            userTypeStr = ConfigurationReader.getProperties("employee_username");
            userTypePassword = ConfigurationReader.getProperties("employee_password");
        } else if (userType.equalsIgnoreCase("advisor")) {
            userTypeStr = ConfigurationReader.getProperties("advisor_username");
            userTypePassword = ConfigurationReader.getProperties("advisor_password");
        } else if (userType.equalsIgnoreCase("supervisor")) {
            userTypeStr = ConfigurationReader.getProperties("supervisor_username");
            userTypePassword = ConfigurationReader.getProperties("supervisor_password");
        } else if (userType.equalsIgnoreCase("client")) {
            userTypeStr = ConfigurationReader.getProperties("client_username");
            userTypePassword = ConfigurationReader.getProperties("client_password");
        } else {
            throw new RuntimeException("Invalid user type: " + userType);
        }


        //String reqBody = "{\n" +
        //        "  \"usernameOrEmailAddress\": \"" +  userTypeStr + "\",\n" +
        //        "  \"password\": \" " + userTypePassword + " \"\n" +
        //        "}";

        Map<String, String> reqBodyMap = new HashMap<>();
        reqBodyMap.put("usernameOrEmailAddress", userTypeStr);
        reqBodyMap.put("password", userTypePassword);

        Response response = given()
                .baseUri(baseURI)
                .accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(reqBodyMap)
                //.and().log().all()
                .when().post("/api/v1/authentication/account/authenticate");

        String accessToken = response.path("user.jwtToken.accessToken");

        return "Bearer " + accessToken;

    }

}
