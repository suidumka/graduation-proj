package io.loop.step_definitions_ui;

import io.cucumber.java.*;
import io.loop.utilities.BrowsersUtils;
import io.loop.utilities.DB_Utility;
import io.loop.utilities.Driver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class Hook {
    @Before("@ui")
    public void setUp(Scenario scenario) {
        Driver.getDriver();
        BrowsersUtils.myScenario = scenario;
        BrowsersUtils.takeScreenShot();
    }

    @After("@ui")
    public void tearDown(Scenario scenario) {
        //only takes a screenshot when scenario is failed
        if(scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        Driver.closeDriver();
    }

    //@AfterStep ("@ui") // takes screenshot after each step
    public void takeScreenshot(Scenario scenario) {
        final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());
    }


    // The following are for DB

    @Before("@uiDB")
    public void setUpDB(Scenario scenario) {
        Driver.getDriver();
        BrowsersUtils.myScenario = scenario;
        BrowsersUtils.takeScreenShot();
        DB_Utility.docuportCreateConnection();
    }

    @After("@uiDB")
    public void tearDownDB(Scenario scenario) {
        //only takes a screenshot when scenario is failed
        if(scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        DB_Utility.destroy();
        System.out.println("CONNECTION CLOSED");
        Driver.closeDriver();
    }

    //@AfterStep ("@uiDB") // takes screenshot after each step
    public void takeScreenshotDB(Scenario scenario) {
        final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());
    }

}
