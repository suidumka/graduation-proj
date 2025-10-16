package io.db.pages;

import io.db.util.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BasePage {
    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//div[@role=\"listbox\"]/a")
    public List<WebElement> allModules;


    public void clickModule (String module) {

        for (WebElement eachModuleElement : allModules) {
            if (eachModuleElement.getText().equals(module)) {
                eachModuleElement.click();
                break;
            }
        }
    }


}
