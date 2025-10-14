package io.db.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class UserPage  extends BasePage{


    @FindBy(xpath = "(//span[.='Search'])[1]")
    public WebElement searchButton;

    @FindBy(xpath = "//span[.=' Search ']")
    public WebElement searchFilter;

    @FindBy(xpath = "//div[@class='v-data-footer__pagination']")
    public WebElement pagination;

    @FindBy(xpath = "//label[.='First name']")
    public WebElement firstNameFilter;

    @FindBy(xpath = "//label[.='Last name']")
    public WebElement lastNameFilter;

    @FindBy(xpath = "//div[@role='radiogroup']/div")
    public List<WebElement> radioButtons;


    public void clickRadioButton(String radioButtonName) {
        for (WebElement eachRadioButton : radioButtons) {
            if (eachRadioButton.getText().equals(radioButtonName)) {
                eachRadioButton.click();
                break;
            }
        }
    }

}
