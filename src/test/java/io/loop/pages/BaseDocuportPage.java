package io.loop.pages;

import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BaseDocuportPage {

    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(DocuportConstants.LARGE));

    public BaseDocuportPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public List<String> getVisibleItemTexts() {
        List<WebElement> items = Driver.getDriver()
                .findElements(By.xpath("//*[contains(@class,'v-list-item__title')]"));

        List<String> texts = new ArrayList<>();
        for (WebElement each : items) {
            texts.add(each.getText().trim());
        }
        return texts;
    }


    public void clickModule(String moduleName) {
        WebElement module = Driver.getDriver().findElement(By.xpath("//span[.='" + moduleName + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(module)).click();
    }

    public boolean getModulesHeaderText(String moduleName) {

        return wait.until(ExpectedConditions.visibilityOf(
                        Driver.getDriver().findElement(
                                By.xpath("//h1[text()='" + moduleName + "']"))))
                                .isDisplayed();
    }

    public boolean getModulesItems(String itemName) {
        return wait.until(ExpectedConditions.visibilityOf(
                        Driver.getDriver().findElement(
                                By.xpath("//span[@class='subtitle-2 text-none' and normalize-space(.)='" + itemName + "']"))))
                                .isDisplayed();
    }


}
