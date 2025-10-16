package io.db.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.db.pages.LeadsPage;
import io.db.util.DB_Utility;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class T3 {

    Map<String, List<String>> allDataFromUi;

    @When("the user gets all the leads information based on {string} and {string} and {string}")
    public void the_user_gets_all_the_leads_information_based_on_and_and(String fullName, String email, String phoneNumber) {
    LeadsPage leadsPage = new LeadsPage();

    allDataFromUi = new LinkedHashMap<>();
    allDataFromUi.put(fullName, leadsPage.takeColumnData(fullName));
    allDataFromUi.put(email, leadsPage.takeColumnData(email));
    allDataFromUi.put(phoneNumber, leadsPage.takeColumnData(phoneNumber));

    for(Map.Entry<String, List<String>> each : allDataFromUi.entrySet()) {
        Collections.sort(each.getValue());
    }

        System.out.println(allDataFromUi);

    }


    @Then("verify leads information match in DB")
    public void verify_leads_information_match_in_db() {
        DB_Utility.docuportCreateConnection();

        String sql = "SELECT CONCAT(owner_first_name, ' ',owner_last_name) AS \"Full name\",\n" +
                "contact_email_address AS \"Email address\",\n" +
                "contact_phone_number AS \"Phone number\"\nFROM document.leads\n" +
                "WHERE is_deleted = 'false'";

        DB_Utility.runQuery(sql);

        // [Full name, Emaill address, Phone number]
        List<String> allColumnName = DB_Utility.getAllColumnNamesAsList();

        Map<String, List<String>> allDataFromDB = new LinkedHashMap<>();

        for (String eachColumnName : allColumnName) { // Full name -- Email address -- Phone number
            List<String> trimData = new ArrayList<>();

            List<String> sorted = DB_Utility.getColumnDataAsList(eachColumnName);
            Collections.sort(sorted);

            for (String each : sorted) {
                trimData.add(each.trim());
            }
            allDataFromDB.put(eachColumnName, trimData);
        }

        /*
            KEY             VALUE
            {Full name ---- }
            {Emaill address ---- }
            {Phone number ---- }
*/
        System.out.println("All data from DB: " + allDataFromDB);
        System.out.println("All data from UI: " + allDataFromUi);

        assertEquals(allDataFromUi, allDataFromDB);

    }

}
