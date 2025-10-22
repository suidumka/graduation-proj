package io.loop.step_definitions_db;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.loop.pages_db.LoginPage;
import io.loop.pages_db.UserPage;
import io.loop.utilities.BrowserUtilDB;
import io.loop.utilities.ConfigurationReader;
import io.loop.utilities.Driver;


public class T2 {
    UserPage userPage;
    static String uiTotalCount;

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String role) throws InterruptedException {
        Driver.getDriver().get(ConfigurationReader.getProperties("docuportBETA"));
        LoginPage loginPage = new LoginPage();
        loginPage.loginDocuport(role);
    }

    @Given("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleName) {
        userPage = new UserPage();
        userPage.clickModule(moduleName);
        BrowserUtilDB.waitFor(10);
    }

    @When("the user gets total user count")
    public void the_user_gets_total_user_count() {
    userPage.searchButton.click();
    BrowserUtilDB.waitFor(2);
    userPage.clickRadioButton("All");
    userPage.searchFilter.click();
    BrowserUtilDB.waitFor(3);
    String pagination = userPage.pagination.getText();
    int indexOfLastSpace =  pagination.lastIndexOf(" ");
    uiTotalCount = pagination.substring(indexOfLastSpace + 1);
        //System.out.println(uiTotalCount);

    }

}
