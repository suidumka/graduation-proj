package io.loop.pages;

import io.loop.utilities.BrowsersUtils;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyUploadsPage {

    public MyUploadsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//span[@class='subtitle-2 text-none' and normalize-space(.)='Upload documents']")
    public WebElement uploadDocumentsButton;

    @FindBy(xpath = "//span[@class='subtitle-2 text-none' and text()='Upload file']")
    public WebElement uploadFileButton;

    @FindBy(xpath = "//span[@class='v-btn__content' and text()=' Upload ']")
    public WebElement uploadButton;


    public void clickButton(String button) {
        switch (button.toLowerCase().trim()) {
            case "upload documents" ->
                    BrowsersUtils.waitForClickable(uploadDocumentsButton, DocuportConstants.LARGE).click();
            case "upload file" ->
                BrowsersUtils.waitForClickable(uploadFileButton, DocuportConstants.MEDIUM).click();
            case "upload button" ->
                BrowsersUtils.waitForClickable(uploadButton, DocuportConstants.SMALL).click();

            default -> throw new IllegalArgumentException("No such button " + button);
        }
    }
}
