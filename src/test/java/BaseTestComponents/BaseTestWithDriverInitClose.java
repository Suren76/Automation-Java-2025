package BaseTestComponents;

import am.staff.helper.WebDriverHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTestWithDriverInitClose {
    public WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverHelper.initDriver();
    }

    @AfterEach
    public void tearDown() {
        WebDriverHelper.closeDriver();
    }
}
