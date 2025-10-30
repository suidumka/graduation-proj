package io.loop.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DocuportRowsPerPage  extends Base {



    @FindBy (xpath = "//input[@aria-label='Rows per page:']")
    public WebElement pageSize;

    @FindBy (xpath = "//div[@class='v-select__selection v-select__selection--comma']")
    public WebElement rowsTextofPageCount;

    @FindBy (xpath = "//*[text()='Rows per page:']")
    public WebElement rowsPerPageText;

    @FindBy (xpath = "//div[@class='v-list-item__title' and text()='5']")
    public WebElement clickNumber5;


}
