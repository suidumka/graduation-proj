package io.loop.pages;

import io.loop.utilities.BrowsersUtils;
import io.loop.utilities.DocuportConstants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReceivedDocsPage extends Base {



    @FindBy(xpath = "//span[@class='subtitle-2 text-none' and .='Search']")
    public WebElement searchButton;

    @FindBy(xpath = "//label[.='Document name']/following-sibling::input")
    public WebElement documentName;

    public void clickButton(String button) {
        switch (button.toLowerCase().trim()) {
           case "search" -> BrowsersUtils.waitForClickable(searchButton, DocuportConstants.LARGE).click();
            default -> throw new IllegalArgumentException("Button " + button + " not found");

        }
    }

    public void insertField(String field, String input) {
        switch (field.toLowerCase().trim()) {
            case "document name" -> BrowsersUtils.waitForClickable(documentName, DocuportConstants.LARGE).sendKeys(input);
        }
    }
}















