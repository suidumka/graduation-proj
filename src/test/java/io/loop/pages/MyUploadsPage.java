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



    @FindBy(xpath = "//span[.=' Upload ']//..")
    public WebElement confirmUploadButton;

    @FindBy(xpath = "//label[text()='Client']//following-sibling::input[1]")
    public WebElement clientsLabel;
    @FindBy(xpath = "//div[@class='v-list-item__content']//div[.='3tseT']")
    public WebElement client3T;

    @FindBy(xpath = "//label[text()='Service']//following-sibling::input[1]")
    public WebElement serviceLabel;
    @FindBy(xpath = "//div[@tabindex]//div[@class='v-list-item__content']//div[.='Bookkeeping']")
    public WebElement bookkeeping;

    @FindBy(xpath = "//span[@class='ma-1 v-chip v-chip--clickable v-chip--label v-chip--outlined theme--dark v-size--small']")
    public WebElement irsLetter;

    @FindBy(xpath = "//span[@class='ma-1 v-chip v-chip--clickable v-chip--label v-chip--outlined theme--dark v-size--small']//following-sibling::span//span[.=' Q1 ']")
    public WebElement q1Label;

    @FindBy(xpath = "//span[.='Document(s) have been uploaded successfully']")
    public WebElement uploadSuccessfully;

    @FindBy(xpath = "//span[.='TEXT']//..//..//div[@class='v-data-table__checkbox v-simple-checkbox']")
    public WebElement checkbox;

    @FindBy(xpath = "//span[@class='subtitle-2 text-none' and text()='Upload file']")
    public WebElement uploadFileButton;

    @FindBy(xpath = "//span[@class='v-btn__content' and text()=' Upload ']")
    public WebElement uploadButton;



    @FindBy(xpath = "//span[.='Selected document(s) have been removed successfully']")
    public WebElement removeSuccessfully;



    public void clickButton(String button) {
        switch (button.toLowerCase().trim()) {

            case "upload button" -> BrowsersUtils.waitForClickable(uploadButton, DocuportConstants.SMALL).click();

            case "upload file" -> BrowsersUtils.waitForClickable(uploadFileButton, DocuportConstants.LARGE).click();



            default -> throw new IllegalArgumentException("No such button " + button);
        }
    }

}
