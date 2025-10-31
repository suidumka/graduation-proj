package io.loop.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

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