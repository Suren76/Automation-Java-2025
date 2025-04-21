package BaseTestComponents;

import am.staff.helper.WebDriverHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTestWithDriverInitClose {
    public WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverHelper.initDriver(getBrowserFromEnvVariable());
    }

    @AfterEach
    public void tearDown() {
        WebDriverHelper.closeDriver();
    }

    private WebDriverHelper.BROWSER getBrowserFromEnvVariable() {
        String browserName = System.getenv("BROWSER_NAME");
        if (browserName == null) return WebDriverHelper.BROWSER.CHROME;
        return WebDriverHelper.BROWSER.valueOf(browserName);
    }
}
