/*
package io.loop.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loop.pages.LoginPage;
import io.loop.pages.POM;
import io.loop.utilities.BrowsersUtils;
import io.loop.utilities.ConfigurationReader;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.apache.poi.poifs.filesystem.POIFSMiniStore;

import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class LoginStepDefs {
    POM pages = new POM();



    @Given("user is on Docuport login page")
    public void user_is_on_docuport_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperties("docuportBETA"));

    }

    @When("user enters username {string}")
    public void user_enters_username_for_client(String username) {
        BrowsersUtils.waitForClickable(pages.getDocuportLoginPage().loginButton, DocuportConstants.LARGE);
        assertTrue("Not displayed", pages.getLoginPage().loginButton.isDisplayed());
        pages.getDocuportLoginPage().usernameInput.sendKeys(username);
    }

    @When("user enters password {string}")
    public void user_enters_password(String password) {
        pages.getDocuportLoginPage().passwordInput.sendKeys(password);
    }

    @When("user clicks login button")
    public void user_clicks_login_button() {
        BrowsersUtils.waitForClickable(pages.getDocuportLoginPage().loginButton, DocuportConstants.LARGE);
        pages.getDocuportLoginPage().loginButton.click();
    }

    @Then("user should be able to see home page for {string}")
    public void user_should_be_able_to_see_home_page_for_client(String role) throws InterruptedException {
        Thread.sleep(1000);

        String header = Driver.getDriver().getTitle();
        assertEquals("No match", "Docuport", header);

        assertTrue(pages.getLoginPage().homePageImg.isDisplayed());

    }


    @When("user enters username for employee")
    public void user_enters_username_for_employee() {
        BrowsersUtils.waitForClickable(pages.getLoginPage().loginButton, DocuportConstants.LARGE);
        assertTrue("Not displayed", pages.getLoginPage().loginButton.isDisplayed());
        pages.getLoginPage().usernameInput.sendKeys(DocuportConstants.USERNAME_EMPLOYEE);
    }

    @When("user enters password for employee")
    public void user_enters_password_for_employee() {
        pages.getLoginPage().passwordInput.sendKeys(DocuportConstants.PASSWORD_FOR_LOGIN);
        pages.getLoginPage().loginButton.click();
    }

    @Then("user should be able to see home page for employee")
    public void user_should_be_able_to_see_home_page_for_employee() {
        String header = Driver.getDriver().getTitle();
        assertEquals("No match", "Docuport", header);

        assertTrue(pages.getLoginPage().homePageImg.isDisplayed());

    }


    @When("user enters username for advisor")
    public void user_enters_username_for_advisor() {
        BrowsersUtils.waitForClickable(pages.getLoginPage().loginButton, DocuportConstants.LARGE);
        assertTrue("Not displayed", pages.getLoginPage().loginButton.isDisplayed());
        pages.getLoginPage().usernameInput.sendKeys(DocuportConstants.USERNAME_ADVISOR);
    }

    @When("user enters password for advisor")
    public void user_enters_password_for_advisor() {
        pages.getLoginPage().passwordInput.sendKeys(DocuportConstants.PASSWORD_FOR_LOGIN);
        pages.getLoginPage().loginButton.click();
    }

    @Then("user should be able to see home page for advisor")
    public void user_should_be_able_to_see_home_page_for_advisor() {
        String header = Driver.getDriver().getTitle();
        assertEquals("No match", "Docuport", header);

        assertTrue(pages.getLoginPage().homePageImg.isDisplayed());
    }


    @When("user enters username for supervisor")
    public void user_enters_username_for_supervisor() {
        BrowsersUtils.waitForClickable(pages.getLoginPage().loginButton, DocuportConstants.LARGE);
        assertTrue("Not displayed", pages.getLoginPage().loginButton.isDisplayed());
        pages.getLoginPage().usernameInput.sendKeys(DocuportConstants.USERNAME_SUPERVISOR);
    }

    @When("user enters password for supervisor")
    public void user_enters_password_for_supervisor() {
        pages.getLoginPage().passwordInput.sendKeys(DocuportConstants.PASSWORD_FOR_LOGIN);
        pages.getLoginPage().loginButton.click();
    }

    @Then("user should be able to see home page for supervisor")
    public void user_should_be_able_to_see_home_page_for_supervisor() {
        String header = Driver.getDriver().getTitle();
        assertEquals("No match", "Docuport", header);

        assertTrue(pages.getLoginPage().homePageImg.isDisplayed());
    }

    @When("user enters credentials")
    public void user_enters_credentials(Map<String, String> credentials) throws InterruptedException {
   for (Map.Entry<String, String> each :credentials.entrySet()){
           String key = each.getKey();
           String value = each.getValue();

           System.out.println("key: " + key);
           System.out.println("value: " + value);
           System.out.println("...................................");


        pages.getLoginPage().loginDocuport(credentials.get("username"), credentials.get("password"));
    }
}


*/
