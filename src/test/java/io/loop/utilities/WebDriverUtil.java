package io.loop.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverUtil {
    /**
     * @param browserType
     * return the driver of that browser
     * @author SJ
     */
    public static WebDriver getDriver (String browserType){

        if (browserType.equalsIgnoreCase("Chrome")){
            return new ChromeDriver();
        } else if (browserType.equalsIgnoreCase("firefox")) {
            return new FirefoxDriver();
        } else if (browserType.equalsIgnoreCase("safari")) {
            return new SafariDriver();
        }else {
            System.out.println("No driver found");
            System.out.println("Driver is null");
            return null;
        }

    }

    public static WebDriver getDriver(String browserType, ChromeOptions options){
        browserType = browserType.trim();
        // in ur main method add up ChromeOptions options = new ChromeOptions();
        if(browserType.equalsIgnoreCase("chrome")){
            options.addArguments("--disable-blink-features=AutomationControlled");
            return new ChromeDriver(options);
        }else if (browserType.equalsIgnoreCase("Safari")){
            return new SafariDriver();
        }else if (browserType.equalsIgnoreCase("Firefox")){
            return new FirefoxDriver();
        }else {
            System.out.println("No driver found");
            System.out.println("Driver is null");
            return  null;
        }
    }


    public static void openUrl(WebDriver driver, String url){
        if (url.startsWith ("http://") || url.startsWith("https://")) {
            url = "http://" + url;
        }
        driver.get(url);
    }
}
