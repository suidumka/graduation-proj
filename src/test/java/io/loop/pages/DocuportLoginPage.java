package io.loop.pages;

import io.loop.utilities.ConfigurationReader;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocuportLoginPage {

    public DocuportLoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

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



//WebElement logo = driver.findElement(By.cssSelector("img[src='/img/logo.d7557277.svg']"));

    public void loginDocuport(String role) throws InterruptedException {
        Driver.getDriver().get(ConfigurationReader.getProperties("docuportBETA"));
        WebElement username = Driver.getDriver().findElement(By.xpath("//label[.='Username or email']/following-sibling::input"));
        WebElement password = Driver.getDriver().findElement(By.xpath("//input[@type='password']"));
        WebElement loginButton = Driver.getDriver().findElement(By.xpath("//button[@type='submit']"));

        switch (role.toLowerCase()){
            case "client"->{
                username.sendKeys(DocuportConstants.USERNAME_CLIENT);
                password.sendKeys(DocuportConstants.PASSWORD_FOR_LOGIN);
            }
            case "employee"->{
                username.sendKeys(DocuportConstants.USERNAME_EMPLOYEE);
                password.sendKeys(DocuportConstants.PASSWORD_FOR_LOGIN);
            }
            case "supervisor"->{
                username.sendKeys(DocuportConstants.USERNAME_SUPERVISOR);
                password.sendKeys(DocuportConstants.PASSWORD_FOR_LOGIN);
            }
            case "advisor"->{
                username.sendKeys(DocuportConstants.USERNAME_ADVISOR);
                password.sendKeys(DocuportConstants.PASSWORD_FOR_LOGIN);
            }

            default -> throw new InterruptedException("There is not such a role " + role);
        }

        loginButton.click();

        if(role.toLowerCase().equals("client")){
            Thread.sleep(3000);
            WebElement cont =Driver.getDriver().findElement(By.xpath("//button[@type='submit']"));
            cont.click();
            Thread.sleep(3000);
        }
    }





}
