

package io.loop.pages;

import io.loop.utilities.BrowsersUtils;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    public HomePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//img[@alt='Docuport']")
    public WebElement logo;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement continueButton;

    @FindBy(xpath = "//h2[.='Received  documents']//..//..")
    public WebElement receivedDocumentsFolder;

    @FindBy(xpath = "//h2[.='My  uploads']/../..")
    public WebElement myUploadsFolder;

    @FindBy(xpath = "//span[.='Batch1 Group1']")
    public WebElement batch1Group1Button;

    @FindBy(xpath = "//a[@href='/profile']")
    public WebElement profileButton;

    @FindBy(xpath = "//a[@href='/accounts']")
    public WebElement accountsButton;

    @FindBy(xpath = "//span[.='Change password']/../..")
    public WebElement changePasswordButton;

    @FindBy(xpath = "//span[.='Send invitation']/../..")
    public WebElement sendInvitationButton;

    @FindBy(xpath = "//span[.='Log out']/../..")
    public WebElement logoutButton;

    @FindBy(xpath = "//span[contains(text(),'My uploads')]")
    public WebElement myUploads;

    @FindBy(xpath = "//span//span[.='Upload documents']")
    public WebElement uploadDocumentsButton;

    @FindBy(xpath = "//span[.='Delete']//..//..//button[@class='mr-3 mb-1 mb-sm-0 v-btn v-btn--has-bg theme--light v-size--large error']")
    public WebElement deleteButton;

    @FindBy(xpath = "//button[@class='text-none body-2 font-weight-medium v-btn v-btn--has-bg theme--light v-size--default error']")
    public WebElement removeButton;

    public void clickButton(String button) {
        switch (button.toLowerCase().trim()) {

            case "my uploads" -> BrowsersUtils.waitForClickable(myUploads, DocuportConstants.EXTRA_LARGE).click();

            case "upload documents" ->
                    BrowsersUtils.waitForClickable(uploadDocumentsButton, DocuportConstants.LARGE).click();

            case "delete" -> BrowsersUtils.waitForClickable(deleteButton, DocuportConstants.LARGE).click();

            case "remove" -> BrowsersUtils.waitForClickable(removeButton, DocuportConstants.LARGE).click();

            default -> throw new IllegalArgumentException("No such button " + button);
        }
    }

}