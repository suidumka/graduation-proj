package io.loop.pages;

import io.loop.utilities.BrowsersUtils;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Base {


    @FindBy(xpath = "//input[@type='text']")
    public WebElement usernameInput;

    @FindBy (xpath = "//input[@type='password']")
    public WebElement passwordInput;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement loginButton;

    @FindBy (xpath = "//img[@src='/img/logo.d7557277.svg']")
    public WebElement homePageImg;

    @FindBy (xpath="//button[@class='text-none body-2 font-weight-medium v-btn v-btn--has-bg theme--light v-size--default success']")
    public WebElement continueButton;

    @FindBy (xpath = "//h1[.=' Login ']")
    public WebElement loginTextBox;

    @FindBy (xpath = "//img[@src='/img/logo.d7557277.svg']")
    public WebElement docuportText;

    @FindBy (xpath = "//h3[.='Choose account']")
    public WebElement chooseAccountText;



    /**
     * Logins to docuport
     * @param username
     * @param password
     * @author SJ
     */
    public void loginDocuport(String username, String password) throws InterruptedException {
        BrowsersUtils.waitForClickable(loginButton, DocuportConstants.LARGE);
        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginButton.click();
        Thread.sleep(3000);
     /*   if(BrowsersUtils.waitForVisibility(continueButton,10).isDisplayed());
        loginButton.click();*/
    }

    public void insertField(String field, String input){

        switch (field.trim().toLowerCase()) {
            case "username" -> BrowsersUtils.waitForVisibility( usernameInput, DocuportConstants.LARGE ).sendKeys(input);
            case "password" -> BrowsersUtils.waitForClickable(passwordInput, DocuportConstants.LARGE ).sendKeys(input);
            default -> throw new IllegalArgumentException("No such field " + field);
        }
    }

    public void clickButton(String button){
        switch (button.toLowerCase().trim()){
            case "login" -> BrowsersUtils.waitForClickable(loginButton, DocuportConstants.LARGE).click();
            case "continue" -> {
                try {
                    BrowsersUtils.waitForClickable(continueButton, DocuportConstants.LARGE).click();
                } catch (StaleElementReferenceException e){
                    WebElement element = Driver.getDriver().findElement(By.xpath("//button[@class='text-none body-2 font-weight-medium v-btn v-btn--has-bg theme--light v-size--default success']"));
                    BrowsersUtils.waitForClickable(element, DocuportConstants.LARGE).click();
                }
            }
            default -> throw new IllegalArgumentException("Not such a button: " + button);
        }
    }
}
