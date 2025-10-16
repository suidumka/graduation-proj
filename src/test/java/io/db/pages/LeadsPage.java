package io.db.pages;

import io.db.util.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class LeadsPage extends BasePage{


//    @FindBy (xpath = "//table//tbody//tr/td[count(//table//th[normalize-space()='Email address']/preceding-sibling::th)+1]")
//    public WebElement email;

    public List<String> takeColumnData(String columnName){

        List<String> allColumnData = new ArrayList<>();

        List<WebElement> elements = Driver.getDriver().findElements(By.xpath("//table//tbody//tr/td[count(//table//th[normalize-space()='" + columnName + "']/preceding-sibling::th)+1]"));

       for (WebElement each : elements) {
           allColumnData.add(each.getText());
       }
       return allColumnData;
    }
}
