package io.loop.step_definitions_db;

import io.cucumber.java.en.Then;
import io.loop.utilities.DB_Utility;

import static org.junit.Assert.assertEquals;

public class T2_UserSteps {
    @Then("verify user count information match in DB")
    public void verify_user_count_information_match_in_db() {

       // DB_Utility.docuportCreateConnection();
        DB_Utility.runQuery("select count(*) from identity.users");
        String dbTotalCount = DB_Utility.getCellValue(1,1);
        System.out.println("DB total count: " + dbTotalCount);
        System.out.println("UI total count: " + T2.uiTotalCount);
        assertEquals("Not same count of columns", dbTotalCount, T2.uiTotalCount);
        DB_Utility.getRowCount();

    }
}
