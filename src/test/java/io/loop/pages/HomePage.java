

package io.loop.pages;

import io.loop.utilities.BrowsersUtils;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {
    public HomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (xpath="//img[@alt='Docuport']")
    public WebElement logo;

    @FindBy (xpath="//button[@type='submit']")
    public WebElement continueButton;

    @FindBy(xpath = "//img[@src='/img/logo.d7557277.svg']")
    public WebElement logoPic;

    @FindBy(xpath = "//span[. ='Batch1 Group1']")
    public WebElement batchInfoButton;

    @FindBy(xpath = "//span[. ='Log out']")
    public WebElement logOutButton;


    //CLIENT PAGE

    @FindBy(xpath = "//span[.='Home']")
    public WebElement homeElement;

    @FindBy(xpath = "//span[.='Received docs']")
    public WebElement receivedElement;

    @FindBy(xpath = "//span[.='My uploads']")
    public WebElement myUploadsElement;

    @FindBy(xpath = "//span[.='Invitations']")
    public WebElement invitationsElement;

    @FindBy(xpath = "//h2[.='My  uploads']")
    public WebElement myUploadsFolder;

    @FindBy(xpath = "//h2[.='Received  documents']")
    public WebElement receivedDocumentsFolder;

    //EMPLOYEE PAGE

    @FindBy(xpath = "//span[.='Clients']")
    public WebElement clientsElement;

    @FindBy(xpath = "//span[.='Users']")
    public WebElement usersElement;

    @FindBy(xpath = "//span[.='Bookkeeping']")
    public WebElement bookkeepingElement;

    @FindBy(xpath = "//span[.='1099 Form']")
    public WebElement formElement;

    @FindBy(xpath = "//span[.='Reconciliations']")
    public WebElement reconciliationsElement;

    //ADVISOR PAGE

    @FindBy(xpath = "//span[.='Leads']")
    public WebElement leadsElement;

    @FindBy(xpath = "//span//span[.='Upload documents']")
    public WebElement uploadDocumentsButton;

    @FindBy(xpath = "//span//span[.='Upload file']")
    public WebElement uploadFileButton;

    @FindBy(xpath = "//*[contains(@class, 'v-list-item__content')]")
    public List<WebElement> leadsContent;

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

    @FindBy(xpath = "//span[.='Delete']//..//..//button[@class='mr-3 mb-1 mb-sm-0 v-btn v-btn--has-bg theme--light v-size--large error']")
    public WebElement deleteButton;

    @FindBy(xpath = "//button[@class='text-none body-2 font-weight-medium v-btn v-btn--has-bg theme--light v-size--default error']")
    public WebElement removeButton;

    @FindBy(xpath = "//span[.='Selected document(s) have been removed successfully']")
    public WebElement removeSuccessfully;



    @FindBy(xpath = "//label[.='Description']")
    public WebElement descriptionLabel;





    public void clickButton(String button) throws InterruptedException {
        switch (button.toLowerCase().trim()) {
            case "my uploads" -> {
                BrowsersUtils.waitForClickable(myUploadsElement, DocuportConstants.LARGE).click();
            }

            case "upload documents" -> {
                BrowsersUtils.waitForClickable(uploadDocumentsButton, DocuportConstants.LARGE).click();
            }
            case "upload file" -> {
                BrowsersUtils.waitForClickable(uploadFileButton, DocuportConstants.LARGE).click();
            }
            case "delete" -> {
                BrowsersUtils.waitForClickable(deleteButton, DocuportConstants.LARGE).click();
            }
            case "remove" -> {
                BrowsersUtils.waitForClickable(removeButton, DocuportConstants.LARGE).click();
            }

        }

    }
}