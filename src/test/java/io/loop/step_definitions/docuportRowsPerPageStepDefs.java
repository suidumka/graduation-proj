package io.loop.step_definitions;

import io.cucumber.java.en.*;
import io.loop.pages.POM;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class docuportRowsPerPageStepDefs {
    POM pages = new POM();
    private static final Logger LOG = LogManager.getLogger();

    @Then("user should see {string} default value as {int}")
    public void user_should_see_default_value_as(String pagination, int count) {

//        String actualPaginationText = pagination + ":\n10";
//        assertEquals(actualPaginationText, pages.getDocuportRowsPerPage().rowsPerPageText.getText());
        String actualPaginationLabel = pages.getDocuportRowsPerPage().rowsPerPageText.getText().trim();
        assertTrue(actualPaginationLabel.contains(pagination));

        String actualPageSize = pages.getDocuportRowsPerPage().rowsTextofPageCount.getText().trim();
        int actual = Integer.parseInt(actualPageSize);
        int expectedPageSize = count;
        assertEquals(expectedPageSize, actual);
        LOG.info("Default page size of actual is: {}, expected is: {}, match DONE.", actual, count);


    }

    @When("user changes {string} value to {int}")
    public void user_changes_value_to(String pagination, Integer count) {

        pages.getDocuportRowsPerPage().rowsTextofPageCount.click();
        pages.getDocuportRowsPerPage().clickNumber5.click();

        LOG.info("User changed {} to: {}", pagination, count);

    }

    @Then("user should see {string} value updated to {int}")
    public void user_should_see_value_updated_to(String pagination, Integer count) {

//        String actualPaginationText = pagination + ":\n5";
//        assertEquals(actualPaginationText, pages.getDocuportRowsPerPage().rowsPerPageText.getText());
        String actualPaginationLabel = pages.getDocuportRowsPerPage().rowsPerPageText.getText().trim();
        assertTrue(actualPaginationLabel.contains(pagination));

        String actualPageSize = pages.getDocuportRowsPerPage().rowsTextofPageCount.getText().trim();
        int actual = Integer.parseInt(actualPageSize);
        int expectedPageSize = count;
        assertEquals(expectedPageSize, actual);
        LOG.info("Default page size of actual is: {}, expected is: {}, match DONE.", actual, count);


    }

}
