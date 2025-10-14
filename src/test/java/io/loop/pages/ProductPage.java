package io.loop.pages;

import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    public ProductPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public void clickCategory(String category) {
        WebElement cat = Driver.getDriver().findElement(By.xpath("//a[contains(.,'" + category + "')]"));
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(DocuportConstants.LARGE));
        wait.until(ExpectedConditions.elementToBeClickable(cat)).click();

    }

    public String getProductPrice(String product){
       // WebElement productPrice = Driver.getDriver().findElement(By.xpath("//a[normalize-space(.)='"+product+"']/../../h5"));
        String actualPrice = Driver.getDriver().findElement(By.xpath("//a[normalize-space(.)='" + product + "']/../../h5")).getText();
        return actualPrice.substring(1);
    }

}
