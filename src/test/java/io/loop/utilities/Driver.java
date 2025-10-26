package io.loop.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Driver {

    static String browserType;
    private Driver() {}

    private static WebDriver driver;
    private static ChromeOptions chromeOptions;



    private static InheritableThreadLocal <WebDriver> driverPool = new InheritableThreadLocal<>();

    static {
        Logger root = Logger.getLogger("");
        root.setLevel(Level.SEVERE);
        Arrays.stream(root.getHandlers()).forEach(h -> h.setLevel(Level.SEVERE));

        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.devtools").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.devtools.CdpVersionFinder").setLevel(Level.SEVERE);
    }


    public static WebDriver getDriver() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("autofill.profile_enabled", false);
        prefs.put("autofill.credit_card_enabled", false);

        ChromeOptions options = new ChromeOptions();

        options.setExperimentalOption("prefs", prefs);
        options.addArguments(
                "--disable-features=PasswordLeakDetection,PasswordManagerOnboarding"
        );
        options.addArguments("--disable-features=HttpsFirstMode,HttpsFirstModeV2");
        if (driverPool.get() == null) {

            browserType = System.getProperty("browser");
            if (browserType == null || browserType.isBlank()) {
                browserType = System.getenv("BROWSER");
            }
            if (browserType == null || browserType.isBlank()) {
                browserType =  ConfigurationReader.getProperties("browser");
            }
            browserType = browserType.trim().toLowerCase();
            System.out.println("Browser: " + browserType);

            switch (browserType) {
                case "chrome" -> {
                    options.addArguments("--disable-blink-features=AutomationControlled");
                    driverPool.set(new ChromeDriver(options));
                }
                case "firefox" -> driverPool.set(new FirefoxDriver());
                case "safari" -> driverPool.set(new SafariDriver());
                case "headless" -> {
                    options.addArguments("--disable-blink-features=AutomationControlled");
                    options.addArguments("--headless"); // kept exactly as you had it
                    driverPool.set(new ChromeDriver(options));
                }
                case "remote-chrome-linux" -> {
                    try {
                        // assign your grid server address
                        String gridAddress = "18.234.251.252";
                        URL url = new URL("http://" + gridAddress + ":4444/wd/hub");
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        chromeOptions = new ChromeOptions();
                        chromeOptions.addArguments("--headless");
                        chromeOptions.addArguments("--no-sandbox");
                        chromeOptions.addArguments("--disable-dev-shm-usage");
                        desiredCapabilities.merge(chromeOptions);
                        driver = new RemoteWebDriver(url, desiredCapabilities);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                default -> { // unknown value -> default to chrome
                    options.addArguments("--disable-blink-features=AutomationControlled");
                    driverPool.set(new ChromeDriver(options));
                }
            }
            //assert driverPool != null;
            driverPool.get().manage().window().maximize();
            // driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(DocuportConstants.LARGE));
            driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.valueOf(io.loop.utilities.ConfigurationReader.getProperties("timeouts"))));
        }
        return driverPool.get();
    }

    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove(); // we assign it back to null so that next time we call getDriver(), a new instance will be created
        }
    }
}