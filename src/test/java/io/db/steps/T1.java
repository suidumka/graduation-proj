package io.db.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loopcamp.util.DB_Utility;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;


public class T1 {
    @Given("Establish the database connection")
    public void establish_the_database_connection() {
       // DB_Utility.docuportCreateConnection();
        System.out.println("Establish the database connection");
    }

    @When("Execute query to get all columns for {string}")
    public void execute_query_to_get_all_columns_for(String departments) {
        String sql = "SELECT *from identity." + departments;
        DB_Utility.runQuery(sql);
    }


    @Then("verify the below columns are listed in result for")
    public void verify_the_below_columns_are_listed_in_result_for(List<String> expectedDepNames) {
       List<String> actualDepNames = DB_Utility.getColumnDataAsList("name");
        System.out.println("actualDepNames = " + actualDepNames);
        System.out.println("expectedDepNames = " + expectedDepNames);

        assertEquals("NO match", expectedDepNames, actualDepNames);
    }

    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
        String sql = "select * from document.users";
        DB_Utility.runQuery(sql);
    }

    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        List<String> allExistingIDs = DB_Utility.getColumnDataAsList("id");
        System.out.println("allExistingIDs.size() = " + allExistingIDs.size());
        Set<String> uniqueIDs = new HashSet<String>(allExistingIDs);
        System.out.println("uniqueIDs = " + uniqueIDs.size());
        assertEquals(allExistingIDs.size(), uniqueIDs.size());
    }

    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {
        String sql = "SELECT * FROM document.users";
        DB_Utility.runQuery(sql);
    }

    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> expColumnNames) {
        List <String> actColumnNames =  DB_Utility.getAllColumnNamesAsList();
        assertEquals(expColumnNames, actColumnNames);
        System.out.println("actColumnNames.size() = " + actColumnNames.size());
        System.out.println("expColumnNames.size() = " + expColumnNames.size());
    }

}
