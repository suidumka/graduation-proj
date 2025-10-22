package io.loop.pages_db;

import io.loop.utilities.Driver;
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

    /*
          LeadsPage leadsPage = new LeadsPage();
//        System.out.println(leadsPage.takeColumnData(fullName));
//        System.out.println(leadsPage.takeColumnData(email));
//        System.out.println(leadsPage.takeColumnData(phoneNumber));

        allDataFromUi = new LinkedHashMap<>();
        allDataFromUi.put(fullName, leadsPage.takeColumnData(fullName));
        allDataFromUi.put(email, leadsPage.takeColumnData(email));
        allDataFromUi.put(phoneNumber, leadsPage.takeColumnData(phoneNumber));



        for (Map.Entry<String, List<String>> each : allDataFromUi.entrySet()){
            Collections.sort(each.getValue());
        }
        System.out.println(allDataFromUi);
     */