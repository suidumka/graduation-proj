package io.loop.pages;

import io.loop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocuportRowsPerPage {

    public DocuportRowsPerPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (xpath = "//input[@aria-label='Rows per page:']")
    public WebElement pageSize;

    @FindBy (xpath = "//div[@class='v-select__selection v-select__selection--comma']")
    public WebElement rowsTextofPageCount;

    @FindBy (xpath = "//*[text()='Rows per page:']")
    public WebElement rowsPerPageText;

    @FindBy (xpath = "//div[@class='v-list-item__title' and text()='5']")
    public WebElement clickNumber5;

    public int getDefaultRowsPerPageValue() {
        String value = pageSize.getAttribute("value");
        return Integer.parseInt(value);
    }



}
