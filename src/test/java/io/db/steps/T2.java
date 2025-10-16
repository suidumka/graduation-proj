package io.db.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.db.pages.LoginPage;
import io.db.pages.UserPage;
import io.db.util.BrowserUtil;


public class T2 {
    UserPage userPage;
    static String uiTotalCount;

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String role) {
       // Driver.getDriver().get(ConfigurationReader.getProperty("docuportUiUrl"));
        LoginPage loginPage = new LoginPage();
        loginPage.loginPage(role);
    }

    @Given("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleName) {
        userPage = new UserPage();
        userPage.clickModule(moduleName);
        BrowserUtil.waitFor(10);
    }

    @When("the user gets total user count")
    public void the_user_gets_total_user_count() {
    userPage.searchButton.click();
    BrowserUtil.waitFor(2);
    userPage.clickRadioButton("All");
    userPage.searchFilter.click();
    BrowserUtil.waitFor(3);
    String pagination = userPage.pagination.getText();
    int indexOfLastSpace =  pagination.lastIndexOf(" ");
    uiTotalCount = pagination.substring(indexOfLastSpace + 1);
        //System.out.println(uiTotalCount);

    }




}
