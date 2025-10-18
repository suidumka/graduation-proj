package io.loop.step_definitions;

import io.cucumber.java.en.*;
import io.loop.pages.POM;
import io.loop.utilities.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.awt.*;

public class SampleDocuportStepDefs {

    private static final Logger LOG = LogManager.getLogger();
    POM pages = new POM();

    @When("user inserts {string} to {string} field on {string} page")
    public void user_inserts_to_field_on_page(String input, String field, String page) {
        switch (page.trim().toLowerCase()) {
            case "login" -> {
                pages.getLoginPage().insertField(field, input);
                LOG.info(input + " was successfully inserted");
            }
            case "received doc" -> {
                pages.getReceivedDocsPage().insertField(field, input);
                LOG.info(input + " - was successfully sent to - " + field);
            }
            default -> throw new IllegalArgumentException("No such field " + page);
        }
    }


    @When("user clicks {string} button on {string} page")
    public void user_clicks_button_on_page(String button, String page) throws InterruptedException {
        switch (page.trim().toLowerCase()) {

            case "login" , "choose account" -> { pages.getLoginPage().clickButton(button);
                LOG.info(button + " was successfully clicked");
                // Thread.sleep(1000);
            }

            case "leads" ->{
                pages.getLeftNavigatePage().clickButton(button);
                LOG.info(button + " was successfully clicked");
            }

            case "users"->{
                pages.getLeftNavigatePage().clickButton(button);
                LOG.info(button + " was successfully clicked");
            }

            case "left navigate" -> {
                pages.getLeftNavigatePage().clickButton(button);
                LOG.info(button + " was successfully clicked");
            }
            case "received doc" -> {
                pages.getReceivedDocsPage().clickButton(button);
                LOG.info(button + " was successfully clicked");
            }
            case "my uploads" -> {
                pages.getMyUploadsPage().clickButton(button);
                LOG.info(button + " was successfully clicked");
            }

            case "upload documents" -> {
                pages.getLeftNavigatePage().clickButton(button);
                LOG.info(button + " was successfully clicked");
            }
            case "home" ->{
                pages.getLeftNavigatePage().clickButton(button);
                LOG.info(button + " was successfully clicked");
            }
            default -> throw new IllegalArgumentException("No such button " + page);
        }
    }

    @Then("user uploads a document")
    public void user_uploads_a_document() throws Exception {

        //upload file into web page - if in HTML  I see @type='file'
   /*     WebElement element = Driver.getDriver().findElement(By.xpath("//input[@type='file']"));
        element.sendKeys("/Users/suidum/Desktop/test.txt");*/

       // MacFileUploadHelper.uploadFileForMac("/Users/suidum/Desktop/test.txt"); // did NOT work for me

       // MacFileUploadHelper.uploadFileUsingAppleScript("/Users/suidum/Desktop/test.txt");

        //MacFileUploadHelper.uploadFileUsingAppleScript2("/Users/suidum/Desktop/test.txt");
        // worked after putting check mark in my mac to IntelliJ


    }

}



