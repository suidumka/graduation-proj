/*
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
                case "chrome-linux" -> {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    driverPool.set(new ChromeDriver(chromeOptions));
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
}*//*


package io.loop.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;

public class Driver {
    static String browser;

    private Driver() {
    }

    private static WebDriver driver;
    private static ChromeOptions chromeOptions;
    private static FirefoxOptions firefoxOptions;

    private static DesiredCapabilities desiredCapabilities;

    public static WebDriver getDriver() {
        if (driver == null) {

            if (System.getProperty("BROWSER") == null) {
                browser = ConfigurationReader.getProperties("browser");
            } else {
                browser = System.getProperty("BROWSER");
            }
            System.out.println("Browser: " + browser);
            switch (browser) {
                case "remote-chrome":
                    try {
                        // assign your grid server address
                        String gridAddress = "18.234.251.252";
                        URL url = new URL("http://" + gridAddress + ":4444/wd/hub");
                        desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName("chrome");
                        driver = new RemoteWebDriver(url, desiredCapabilities);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case "remote-firefox":
                    try {
                        // assign your grid server address
                        String gridAddress = "18.234.251.252";
                        URL url = new URL("http://" + gridAddress + ":4444/wd/hub");
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName("firefox");
                        driver = new RemoteWebDriver(url, desiredCapabilities);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;

                case "chrome-headless":
//                    WebDriverManager.chromedriver().setup();
//                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless"); // Enable headless mode
                    //options.addArguments("start-maximized"); // maximize
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(options);
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

                case "firefox-headless":
//                    WebDriverManager.firefoxdriver().setup();
//                    driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
                    FirefoxOptions options2 = new FirefoxOptions();
                    options2.addArguments("--headless"); // Enable headless mode
                    //options.addArguments("start-maximized"); // maximize
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(options2);
                    break;

                case "ie":
                    if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                        throw new WebDriverException("Your operating system does not support the requested browser");
                    }
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;

                case "edge":
                    if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                        throw new WebDriverException("Your operating system does not support the requested browser");
                    }
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;

                case "safari":
                    if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                        throw new WebDriverException("Your operating system does not support the requested browser");
                    }
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driver = new SafariDriver();
                    break;


                */
/**
                 * These added because of EC@2 Jenkins on Linux was not running the ones above because of graphical issues.
                 *//*

                case "chrome-linux":
                    WebDriverManager.chromedriver().setup();
                    chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "remote-chrome-linux":
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
                    break;

                case "firefox-linux":
                    WebDriverManager.firefoxdriver().setup();
                    firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    firefoxOptions.addArguments("--disable-gpu");
                    firefoxOptions.addArguments("--no-sandbox");
                    driver = new FirefoxDriver(firefoxOptions);
                    break;

                case "remote-firefox-linux":
                    try {
                        // assign your grid server address
                        String gridAddress = "18.234.251.252";
                        URL url = new URL("http://" + gridAddress + ":4444/wd/hub");
                        desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName("firefox");
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        firefoxOptions.addArguments("--headless");
                        firefoxOptions.addArguments("--disable-gpu");
                        firefoxOptions.addArguments("--no-sandbox");
                        desiredCapabilities.merge(firefoxOptions);
                        driver = new RemoteWebDriver(url, desiredCapabilities);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }

        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
*/

package io.loop.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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

/**
 * Unified Driver class — supports:
 * - Local browsers (Chrome, Firefox, Safari, Edge)
 * - Headless modes
 * - Linux/CI modes
 * - Remote execution (Grid/Jenkins/EC2)
 * Thread-safe for parallel execution using InheritableThreadLocal.
 */
public class Driver {

    private Driver() {}

    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();
    static String browserType;

    static {
        // Suppress Selenium logs
        Logger root = Logger.getLogger("");
        root.setLevel(Level.SEVERE);
        Arrays.stream(root.getHandlers()).forEach(h -> h.setLevel(Level.SEVERE));
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
    }

    public static WebDriver getDriver() {

        if (driverPool.get() == null) {

            // Determine browser
            browserType = System.getProperty("BROWSER");
            if (browserType == null || browserType.isBlank()) {
                browserType = System.getenv("BROWSER");
            }
            if (browserType == null || browserType.isBlank()) {
                browserType = ConfigurationReader.getProperties("browser");
            }
            browserType = browserType.trim().toLowerCase();
            System.out.println("Browser: " + browserType);

            // Common Chrome prefs
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.password_manager_leak_detection", false);
            prefs.put("autofill.profile_enabled", false);
            prefs.put("autofill.credit_card_enabled", false);

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("prefs", prefs);
            chromeOptions.addArguments("--disable-features=PasswordLeakDetection,PasswordManagerOnboarding");
            chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
            chromeOptions.addArguments("--disable-features=HttpsFirstMode,HttpsFirstModeV2");

            FirefoxOptions firefoxOptions = new FirefoxOptions();

            try {
                switch (browserType) {

                    // === LOCAL BROWSERS ===
                    case "chrome" -> {
                        WebDriverManager.chromedriver().setup();
                        driverPool.set(new ChromeDriver(chromeOptions));
                    }

                    case "firefox" -> {
                        WebDriverManager.firefoxdriver().setup();
                        driverPool.set(new FirefoxDriver());
                    }


                    // === HEADLESS MODES ===
                    case "chrome-headless" -> {
                        chromeOptions.addArguments("--headless");
                        WebDriverManager.chromedriver().setup();
                        driverPool.set(new ChromeDriver(chromeOptions));
                    }

                    case "firefox-headless" -> {
                        firefoxOptions.addArguments("--headless");
                        WebDriverManager.firefoxdriver().setup();
                        driverPool.set(new FirefoxDriver(firefoxOptions));
                    }

                    // === LINUX / CI MODES ===
                    case "chrome-linux" -> {
                        WebDriverManager.chromedriver().setup();
                        chromeOptions.addArguments("--headless");
                        chromeOptions.addArguments("--no-sandbox");
                        chromeOptions.addArguments("--disable-dev-shm-usage");
                        driverPool.set(new ChromeDriver(chromeOptions));
                    }

                    case "firefox-linux" -> {
                        WebDriverManager.firefoxdriver().setup();
                        firefoxOptions.addArguments("--headless");
                        firefoxOptions.addArguments("--disable-gpu");
                        firefoxOptions.addArguments("--no-sandbox");
                        driverPool.set(new FirefoxDriver(firefoxOptions));
                    }

                    // === REMOTE EXECUTION (GRID / EC2 / DOCKER) ===
                    case "remote-chrome", "remote-chrome-linux" -> {
                        String gridAddress = "18.234.251.252"; // fixed as requested
                        URL url = new URL("http://" + gridAddress + ":4444/wd/hub");
                        chromeOptions.addArguments("--headless");
                        chromeOptions.addArguments("--no-sandbox");
                        chromeOptions.addArguments("--disable-dev-shm-usage");
                        DesiredCapabilities caps = new DesiredCapabilities();
                        caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                        driverPool.set(new RemoteWebDriver(url, caps));
                    }

                    case "remote-firefox", "remote-firefox-linux" -> {
                        String gridAddress = "18.234.251.252"; // fixed as requested
                        URL url = new URL("http://" + gridAddress + ":4444/wd/hub");
                        firefoxOptions.addArguments("--headless");
                        firefoxOptions.addArguments("--disable-gpu");
                        firefoxOptions.addArguments("--no-sandbox");
                        DesiredCapabilities caps = new DesiredCapabilities();
                        caps.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
                        driverPool.set(new RemoteWebDriver(url, caps));
                    }

                    default -> { // fallback to chrome
                        WebDriverManager.chromedriver().setup();
                        driverPool.set(new ChromeDriver(chromeOptions));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to initialize WebDriver for: " + browserType, e);
            }

            // Common setup
            driverPool.get().manage().window().maximize();
            driverPool.get().manage().timeouts().implicitlyWait(
                    Duration.ofSeconds(Long.parseLong(ConfigurationReader.getProperties("timeouts")))
            );
        }

        return driverPool.get();
    }

    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}