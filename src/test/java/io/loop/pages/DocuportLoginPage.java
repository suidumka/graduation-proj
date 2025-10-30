package io.loop.pages;

import io.loop.utilities.BrowsersUtils;
import io.loop.utilities.ConfigurationReader;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DocuportLoginPage extends Base {



    @FindBy(xpath = "//input[@type='text']")
    public WebElement usernameInput;

    @FindBy (xpath = "//input[@type='password']")
    public WebElement passwordInput;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement loginButton;

    @FindBy (xpath = "//img[@src='/img/logo.d7557277.svg']")
    public WebElement homePageImg;

    @FindBy (xpath="//button[@type='submit']")
    public WebElement continueButton;

    @FindBy (css = "span[class='subtitle-2 text-none pl-2 pr-3 gray--text text--darken-3']")
    public WebElement batchInfoButton;

    @FindBy (xpath = "//img[@src='/img/logo.d7557277.svg']")
    public WebElement logo;


    public void clickButton(String button) throws InterruptedException {
        switch (button.toLowerCase().trim()) {
            case "login" -> BrowsersUtils.waitForClickable(loginButton, DocuportConstants.LARGE).click();
            case "continue" -> {
                try {
                    BrowsersUtils.waitForClickable(continueButton, DocuportConstants.LARGE).click();
                } catch (StaleElementReferenceException e) {
                    //  Thread.sleep(5000);
                    WebElement element = Driver.getDriver().findElement(By.xpath("//span[.=' Continue ']"));
                    BrowsersUtils.waitForClickable(element, DocuportConstants.EXTRA_LARGE).click();
                }
            }
            default -> throw new IllegalArgumentException("Not such a button: " + button);
        }
    }

    public void insertField(String field, String input) {
        switch(field.toLowerCase().trim()) {
            case "username" -> BrowsersUtils.waitForVisibility(usernameInput, DocuportConstants.LARGE).sendKeys(input);
            case "password" -> BrowsersUtils.waitForVisibility(passwordInput, DocuportConstants.LARGE).sendKeys(input);
            default -> throw new IllegalArgumentException("Not such a button");
        }
    }


//WebElement logo = driver.findElement(By.cssSelector("img[src='/img/logo.d7557277.svg']"));

    public void loginDocuport(String role) throws InterruptedException {
        Driver.getDriver().get(ConfigurationReader.getProperties("docuportBETA"));
        WebElement username = Driver.getDriver().findElement(By.xpath("//label[.='Username or email']/following-sibling::input"));
        WebElement password = Driver.getDriver().findElement(By.xpath("//input[@type='password']"));
        WebElement loginButton = Driver.getDriver().findElement(By.xpath("//button[@type='submit']"));

        switch (role.toLowerCase()){
            case "client"->{
                Thread.sleep(1000);
                username.sendKeys(DocuportConstants.USERNAME_CLIENT);
                password.sendKeys(DocuportConstants.PASSWORD_FOR_LOGIN);
            }
            case "employee"->{
                Thread.sleep(1000);
                username.sendKeys(DocuportConstants.USERNAME_EMPLOYEE);
                password.sendKeys(DocuportConstants.PASSWORD_FOR_LOGIN);
            }
            case "supervisor"->{
                Thread.sleep(1000);
                username.sendKeys(DocuportConstants.USERNAME_SUPERVISOR);
                password.sendKeys(DocuportConstants.PASSWORD_FOR_LOGIN);
            }
            case "advisor"->{
                Thread.sleep(1000);
                username.sendKeys(DocuportConstants.USERNAME_ADVISOR);
                password.sendKeys(DocuportConstants.PASSWORD_FOR_LOGIN);
            }

            default -> throw new InterruptedException("There is not such a role " + role);
        }

        loginButton.click();

        if(role.toLowerCase().equals("client")){
            Thread.sleep(2000);
            WebElement cont =Driver.getDriver().findElement(By.xpath("//button[@type='submit']"));
            cont.click();
            Thread.sleep(2000);
        }
    }

}
