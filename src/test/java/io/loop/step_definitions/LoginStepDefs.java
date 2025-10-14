package io.loop.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loop.pages.LoginPage;
import io.loop.utilities.BrowsersUtils;
import io.loop.utilities.ConfigurationReader;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;

import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class LoginStepDefs {
    LoginPage loginPage = new LoginPage();

    @Given("user is on Docuport login page")
    public void user_is_on_docuport_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperties("docuportBETA"));

    }

    @When("user enters username for client")
    public void user_enters_username_for_client() {
        BrowsersUtils.waitForClickable(loginPage.loginButton, DocuportConstants.LARGE);
        assertTrue("Not displayed", loginPage.loginButton.isDisplayed());
        loginPage.usernameInput.sendKeys(DocuportConstants.USERNAME_CLIENT);
    }

    @When("user enters password for client")
    public void user_enters_password_for_client() {
        loginPage.passwordInput.sendKeys(DocuportConstants.PASSWORD_FOR_LOGIN);
    }

    @When("user clicks login button")
    public void user_clicks_login_button() {
        loginPage.loginButton.click();
    }

    @Then("user should be able to see home page for client")
    public void user_should_be_able_to_see_home_page_for_client() throws InterruptedException {
        Thread.sleep(1000);

        String header = Driver.getDriver().getTitle();
        assertEquals("No match", "Docuport", header);

        assertTrue(loginPage.homePageImg.isDisplayed());

    }


    @When("user enters username for employee")
    public void user_enters_username_for_employee() {
        BrowsersUtils.waitForClickable(loginPage.loginButton, DocuportConstants.LARGE);
        assertTrue("Not displayed", loginPage.loginButton.isDisplayed());
        loginPage.usernameInput.sendKeys(DocuportConstants.USERNAME_EMPLOYEE);
    }

    @When("user enters password for employee")
    public void user_enters_password_for_employee() {
        loginPage.passwordInput.sendKeys(DocuportConstants.PASSWORD_FOR_LOGIN);
        loginPage.loginButton.click();
    }

    @Then("user should be able to see home page for employee")
    public void user_should_be_able_to_see_home_page_for_employee() {
        String header = Driver.getDriver().getTitle();
        assertEquals("No match", "Docuport", header);

        assertTrue(loginPage.homePageImg.isDisplayed());

    }


    @When("user enters username for advisor")
    public void user_enters_username_for_advisor() {
        BrowsersUtils.waitForClickable(loginPage.loginButton, DocuportConstants.LARGE);
        assertTrue("Not displayed", loginPage.loginButton.isDisplayed());
        loginPage.usernameInput.sendKeys(DocuportConstants.USERNAME_ADVISOR);
    }

    @When("user enters password for advisor")
    public void user_enters_password_for_advisor() {
        loginPage.passwordInput.sendKeys(DocuportConstants.PASSWORD_FOR_LOGIN);
        loginPage.loginButton.click();
    }

    @Then("user should be able to see home page for advisor")
    public void user_should_be_able_to_see_home_page_for_advisor() {
        String header = Driver.getDriver().getTitle();
        assertEquals("No match", "Docuport", header);

        assertTrue(loginPage.homePageImg.isDisplayed());
    }


    @When("user enters username for supervisor")
    public void user_enters_username_for_supervisor() {
        BrowsersUtils.waitForClickable(loginPage.loginButton, DocuportConstants.LARGE);
        assertTrue("Not displayed", loginPage.loginButton.isDisplayed());
        loginPage.usernameInput.sendKeys(DocuportConstants.USERNAME_SUPERVISOR);
    }

    @When("user enters password for supervisor")
    public void user_enters_password_for_supervisor() {
        loginPage.passwordInput.sendKeys(DocuportConstants.PASSWORD_FOR_LOGIN);
        loginPage.loginButton.click();
    }

    @Then("user should be able to see home page for supervisor")
    public void user_should_be_able_to_see_home_page_for_supervisor() {
        String header = Driver.getDriver().getTitle();
        assertEquals("No match", "Docuport", header);

        assertTrue(loginPage.homePageImg.isDisplayed());
    }

    @When("user enters credentials")
    public void user_enters_credentials(Map<String, String> credentials) throws InterruptedException {
    /*   for (Map.Entry<String, String> each :credentials.entrySet()){
           String key = each.getKey();
           String value = each.getValue();

           System.out.println("key: " + key);
           System.out.println("value: " + value);
           System.out.println("...................................");*/

        loginPage.loginDocuport(credentials.get("username"), credentials.get("password"));
    }
}


