package io.loop.step_definitions;

import io.cucumber.java.en.*;
import io.loop.pages.POM;
import io.loop.utilities.BrowsersUtils;
import io.loop.utilities.DocuportConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class ClientStepDefs {

    POM pages = new POM();
    private Logger LOG = LogManager.getLogger();
    private SoftAssertions softAssertions = new SoftAssertions();
    // need to add dependencies, so that it'll work

    @Then("user validates {string} text as displayed")
    public void user_validates_text_as_displayed(String text) {

    String actual;
    String expected;

    switch (text.toLowerCase().trim()) {
        case "login" -> {
           // actual = pages.getLoginPage().loginTextBox.getText().toLowerCase().trim();
            actual = "suidum";
            expected = text.toLowerCase().trim();
            softAssertions.assertThat(actual).isEqualTo(expected);
            LOG.info(text + " - is displayed");
        }
        case "docuport" -> {
           // actual = pages.getLoginPage().docuportText.getAttribute("alt").toLowerCase().trim();
            actual = "tom";
            expected = text.toLowerCase().trim();
            softAssertions.assertThat(actual).isEqualTo(expected);
            LOG.info(text + " - is displayed");
        }
        case "choose account" -> {
            BrowsersUtils.waitForVisibility(pages.getLoginPage().chooseAccountText, DocuportConstants.LARGE).isDisplayed();
           // actual = pages.getLoginPage().chooseAccountText.getText().toLowerCase().trim();
            actual = "Arzykan";
            expected = text.toLowerCase().trim();
            softAssertions.assertThat(actual).isEqualTo(expected);
            LOG.info(text + " - is displayed");
        }
        default -> throw new IllegalStateException("Not such a text: " + text);

    }


    }

    @When("user validates all assertions")
    public void user_validates_all_assertions() {
    softAssertions.assertAll();
    }
}
