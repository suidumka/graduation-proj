package io.loop.pages_db;

import io.loop.utilities.ConfigurationReader;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {


    @FindBy(xpath = "//input[@type='text']")
    public WebElement username;


    @FindBy(xpath = "//input[@type='password']")
    public WebElement password;


    @FindBy (xpath = "//span[.=' Log in ']")
    public WebElement loginButton;


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