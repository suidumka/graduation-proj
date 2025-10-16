package io.db.pages;

import io.db.util.ConfigurationReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {


    @FindBy(xpath = "//input[@type='text']")
    public WebElement username;


    @FindBy(xpath = "//input[@type='password']")
    public WebElement password;


    @FindBy (xpath = "//span[.=' Log in ']")
    public WebElement loginButton;


    public void loginPage(String userType) {

        String username;
        String password;

        switch (userType.toLowerCase()){
            case "advisor":
                username = ConfigurationReader.getProperty("docuportAdvisor");
                password = ConfigurationReader.getProperty("docuportPassword");
                break;
            case "supervisor":
                username = ConfigurationReader.getProperty("docuportSupervisor");
                password = ConfigurationReader.getProperty("docuportPassword");
                break;
            default:
                throw new RuntimeException("Unsupported user type: " + userType);
        }

        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.loginButton.click();

    }

}