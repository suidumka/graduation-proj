package io.loop.step_definitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loop.pages.DocuportLoginPage;
import io.loop.pages.POM;
import io.loop.utilities.BrowsersUtils;
import io.loop.utilities.ConfigurationReader;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class LoginStepDefNew {
    POM pages = new POM();


    @Given("user is on Docuport login page")
    public void user_is_on_docuport_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperties("docuportBETA"));
    }
    @When("user enters username {string}")
    public void user_enters_username(String username) {
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
    public void user_should_be_able_to_see_home_page_for(String role) {
        String header = Driver.getDriver().getTitle();
        assertEquals("No match", "Docuport", header);

        assertTrue(pages.getDocuportLoginPage().homePageImg.isDisplayed());
    }




}
