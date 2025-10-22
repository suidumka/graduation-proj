package io.loop.pages_db;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(xpath = "(//a[@class='doc-card style-1'])[1]")
    public WebElement recDocCard;


    // add all other locators for Home page ....
}