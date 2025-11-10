package io.loop.step_definitions_ui;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loop.pages.POM;
import io.loop.utilities.ConfigurationReader;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LefNavModulesListValidationStepDefs {

    POM pages = new POM();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(DocuportConstants.LARGE));
    private static final Logger LOG = LogManager.getLogger();


    @Given("the Docuport site is open")
    public void the_docuport_site_is_open() {
        Driver.getDriver().get(ConfigurationReader.getProperties("docuportBETA"));
    }

    @When("the user logs in as {string}")
    public void the_user_logs_in_as_with_and(String role) throws InterruptedException {
        pages.getDocuportLoginPage().loginDocuport(role);
    }

    @And("the user is on the Docuport home page")
    public void theUserIsOnTheDocuportHomePage() {
    //  wait.until(ExpectedConditions.visibilityOf(pages.getDocuportLoginPage().logo));
    //    assertTrue(pages.getDocuportLoginPage().logo.isDisplayed());
    }

    @Then("the left navigation for {string} should be:")
    public void the_left_navigation_for_should_be(String role, Map<String, List<String>> item) throws InterruptedException {
        for (Map.Entry<String, List<String>> eachLink : item.entrySet()) {
            String role1 = eachLink.getKey();
            List<String> expectedListOfItems = eachLink.getValue();

            if (role1.equalsIgnoreCase(role)) {
                try {
                    List<String> expectedClean = new ArrayList<>();
                    for (String s : expectedListOfItems) {
                        if (s != null && !s.trim().isEmpty()) {
                            expectedClean.add(s.trim());
                        }
                    }

                    List<String> actualItemsList = pages.getBaseDocuportPage().getVisibleItemTexts();

                    assertEquals("No match: " + role1, expectedClean, actualItemsList);
                    LOG.info("Role: \n\t {}, \nexpected list of items: \n\t {}, \nactual list: \n\t{}", role1, expectedClean, actualItemsList);

                } catch (Exception e) {
                    // System.out.println(e.getMessage());
                }
            }
        }

    }


    @When("the user verifies module pages, header, and items:")
    public void the_user_verifies_module_pages_header_and_items(List<Map<String, String>> items) throws InterruptedException {

        for (Map<String, String> eachItem : items) {
            String module = eachItem.get("module");
            String header = eachItem.get("header");
            String item = eachItem.get("items");


            pages.getBaseDocuportPage().clickModule(module);
            Thread.sleep(1000);
            assertTrue(pages.getBaseDocuportPage().getModulesHeaderText(header));
            assertTrue(pages.getBaseDocuportPage().getModulesItems(item));
        }
    }

}

