package io.loop.pages;

import io.loop.utilities.BrowsersUtils;
import io.loop.utilities.DocuportConstants;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LeftNavigatePage extends Base {



    @FindBy(xpath = "//span[contains(text(),'Upload')]")
    public WebElement uploadButton;

    @FindBy(xpath = "//span[contains(text(),'Home')]")
    public WebElement homeButton;

    @FindBy(xpath = "//span[contains(text(),'Received')]")
    public WebElement receivedDocsButton;

    @FindBy(xpath = "//span[contains(text(),'My uploads')]")
    public WebElement myUploads;

    @FindBy(xpath = "//a[@href='/invitations']")
    public WebElement invitationsButton;

    @FindBy(xpath = "//a[contains(text(),'Terms')]")
    public WebElement termsAndConditionsButton;


    @FindBy (xpath = "//span[contains(text(),'Leads')]")
    public WebElement leadsModule;

    @FindBy (xpath = "//span[contains(text(),'Users')]")
    public WebElement usersModule;



    public void clickButton(String button) {
        switch (button.toLowerCase().trim()) {

            case "leads" -> {
                try{
                BrowsersUtils.waitForClickable(leadsModule, DocuportConstants.LARGE).click();
            }catch (StaleElementReferenceException se){
                    BrowsersUtils.waitForVisibility(leadsModule, DocuportConstants.LARGE);
                    BrowsersUtils.clickWithJS(leadsModule);
                }
            }

            case "users" ->{
                BrowsersUtils.waitForClickable(usersModule, DocuportConstants.LARGE);
                BrowsersUtils.clickWithJS(usersModule);
            }

            case "upload" -> BrowsersUtils.waitForClickable(uploadButton, DocuportConstants.LARGE).click();
         /*   case "home" -> {
                try {
                    BrowsersUtils.waitForClickable(homeButton, DocuportConstants.LARGE).click();
                }catch (StaleElementReferenceException se){
                    BrowsersUtils.waitForClickable(homeButton, DocuportConstants.LARGE).click();
                } // better to use try{}catch // instead of Thread.sleep
            }*/

           //case "home" -> BrowsersUtils.waitForClickable(homeButton, DocuportConstants.LARGE).click();
            case "home" ->{
                BrowsersUtils.waitForClickable(homeButton, DocuportConstants.LARGE);
                BrowsersUtils.clickWithJS(homeButton);
            }
            case "received doc" -> BrowsersUtils.waitForClickable(receivedDocsButton, DocuportConstants.LARGE).click();

            case "my uploads" -> BrowsersUtils.waitForClickable(myUploads, DocuportConstants.EXTRA_LARGE).click();

            case "invitations" ->{
                try {
                   // BrowsersUtils.waitForClickable(invitationsButton, DocuportConstants.EXTRA_LARGE).click();
                    BrowsersUtils.clickWithJS(invitationsButton);

                }
                catch (StaleElementReferenceException se){
                    BrowsersUtils.waitForClickable(invitationsButton, DocuportConstants.LARGE).click();
                    BrowsersUtils.clickWithJS(invitationsButton);
                }
            }


            case "terms and conditions" -> BrowsersUtils.waitForClickable(termsAndConditionsButton, DocuportConstants.LARGE).click();

            default -> throw new IllegalArgumentException("No such button " + button);
        }
    }
}
