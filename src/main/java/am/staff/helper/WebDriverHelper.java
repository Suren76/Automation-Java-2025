package am.staff.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class WebDriverHelper {
    private static WebDriver driver;

    public static void initDriver() {
        ChromeOptions chromeOptions = new ChromeOptions().addArguments("--start-maximized");
        driver = new ChromeDriver(chromeOptions);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
    }
}
