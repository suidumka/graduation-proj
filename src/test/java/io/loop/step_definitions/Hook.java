package io.loop.step_definitions;

import io.cucumber.java.*;
import io.loop.utilities.BrowsersUtils;
import io.loop.utilities.Driver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class Hook {
    @Before
    public void setUp(Scenario scenario) {
        Driver.getDriver();
        BrowsersUtils.myScenario = scenario;
        BrowsersUtils.takeScreenShot();
    }

    @After
    public void tearDown(Scenario scenario) {
        //only takes a screenshot when scenario is failed
        if(scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        Driver.closeDriver();
    }

    //@AfterStep
    public void takeScreenshot(Scenario scenario) {
        final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());
    }
}
