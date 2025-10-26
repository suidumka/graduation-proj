package io.loop.step_definitions_ui;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loop.pages.POM;
import io.loop.utilities.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import static org.junit.Assert.assertTrue;


public class DocuportAddAndRemoveDocumentStepDef {

    private static final Logger LOG = LogManager.getLogger();
    POM pages = new POM();


    @When("user enters valid {string} to {string} on {string} page")
    public void user_enters_valid_to_on_page(String input, String field, String page) {

        switch (page.toLowerCase().trim()) {
            case "login" -> {
                pages.getDocuportLoginPage().insertField(field, input);
                LOG.info(input + " - was successfully sent to - " + field);
            }
            default -> throw new IllegalArgumentException("No such a page: " + page);
        }
    }

    @When("user clicks the {string} button on {string} page")
    public void user_clicks_the_button_on_page(String button, String page) throws InterruptedException {
        switch (page.toLowerCase().trim()) {

            case "login", "Login" -> {
                pages.getDocuportLoginPage().clickButton(button);
                LOG.info(button + " - was successfully clicked");
            }
            default -> throw new IllegalArgumentException("No such a page: " + page);
        }
    }

    @When("user should be redirected to the {string} page and see My uploads folder")
    public void user_should_be_redirected_to_the_page_and_see_My_uploads_folder(String page) {

        assertTrue("My uploads is not visible", pages.getHomePage().myUploadsFolder.isDisplayed());
        assertTrue("Received documents is not visible", pages.getHomePage().receivedDocumentsFolder.isDisplayed());
    }

    @When("user clicks {string} on {string} page")
    public void user_clicks_on_page(String button, String page) throws InterruptedException {
        switch (page.toLowerCase().trim()) {

            case "my uploads", "home" -> {
                pages.getHomePage().clickButton(button);
                LOG.info(button + " - was successfully clicked");
            }
            case "upload documents" -> {
                pages.getHomePage().clickButton(button);
                LOG.info(button + " - was successfully clicked");
            }
            case "upload file" -> {
                pages.getHomePage().clickButton(button);
                LOG.info(button + " - was successfully clicked");
            }
            case "delete" -> {
                pages.getHomePage().clickButton(button);
                LOG.info(button + " - was successfully clicked");
            }
            case "remove" -> {
                pages.getHomePage().clickButton(button);
                LOG.info(button + " - was successfully clicked");
            }
            default -> throw new IllegalArgumentException("No such a page: " + page);
        }
    }

    @When("user upload an document")
    public void user_upload_an_document() throws Exception {
        WebElement element = Driver.getDriver().findElement(By.xpath("//input[@type='file']"));
        element.sendKeys("/Users/suidum/Desktop/bootcamp/test.txt");


        Thread.sleep(2000);
        pages.getHomePage().clientsLabel.click();
        pages.getHomePage().client3T.click();
        pages.getHomePage().serviceLabel.click();
        pages.getHomePage().bookkeeping.click();
        pages.getHomePage().irsLetter.click();
        pages.getHomePage().q1Label.click();


        pages.getHomePage().confirmUploadButton.click();
    }

    @When("user should be able see document first on a list")
    public void user_should_be_able_see_document_first_on_a_list() {
        assertTrue(pages.getHomePage().uploadSuccessfully.isDisplayed());
        LOG.info(pages.getHomePage().uploadSuccessfully.getText() + " - message is displayed");
    }

    @Then("user choose document")
    public void user_choose_document() throws InterruptedException {
        Thread.sleep(3000);
        pages.getHomePage().checkbox.click();
    }

    @Then("document should be successfully removed from list")
    public void document_should_be_successfully_removed_from_list() throws InterruptedException {
        assertTrue(pages.getHomePage().removeSuccessfully.isDisplayed());
        LOG.info(pages.getHomePage().removeSuccessfully.getText() + " - message is displayed");


    }


}
