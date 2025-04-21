package am.staff.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;


public class WebDriverHelper {
    private static ThreadLocal<WebDriver> driverThreadSafe;

    private WebDriverHelper() {}

    synchronized public static void initDriver(BROWSER browser) {
        driverThreadSafe = new ThreadLocal<>();

        switch (browser) {
            case CHROME -> driverThreadSafe.set(new ChromeDriver());
            case FIREFOX -> driverThreadSafe.set(new FirefoxDriver());
            case SAFARI -> driverThreadSafe.set(new SafariDriver());
        }

        // maximize driver
        getDriver().manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driverThreadSafe.get();
    }

    public static void closeDriver() {
        driverThreadSafe.get().quit();
        driverThreadSafe.remove();
    }

    public enum BROWSER {
        CHROME("chrome"),
        FIREFOX("firefox"),
        SAFARI("safari");

        String value;

        BROWSER(String value) {
            this.value = value;
        }
    }
}
