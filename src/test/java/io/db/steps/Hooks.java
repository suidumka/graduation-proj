package io.db.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.db.util.ConfigurationReader;
import io.db.util.DB_Utility;
import io.db.util.Driver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @Before("@ui")
    public void setupUi() {
        Driver.getDriver().get(ConfigurationReader.getProperty("docuportUiUrl"));
    }

    @After("@ui")
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) (Driver.getDriver())).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        Driver.getDriver().quit();
    }

    // The following are for DB
    @Before("@docuportDb")
    public void setupDB() {
        DB_Utility.docuportCreateConnection();
    }

    @Before("@hrDB")
    public void setupDBforHR() {
        DB_Utility.createConnection();
    }

    @After ("@db")
    public void  closeConnection () {
        DB_Utility.destroy();
        System.out.println("CONNECTION CLOSED");
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
}
