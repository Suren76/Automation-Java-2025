package homework3AutomationTestWithJunit5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.learn.automation.homework2SimpleAutomation.com.sixPm.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.learn.automation.homework2SimpleAutomation.com.sixPm.Page.*;
import static org.learn.automation.homework2SimpleAutomation.com.sixPm.Page.clickElement;

public class TestAddRemoveFilter {
    WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void clean() {
        driver.quit();
    }

    @Test
    public void testAddRemoveFilter() {
        Page.openHomePage(driver);

        WebElement bugsNavElement = getNavBarTabElement(driver, "Clothing");

        hoverElement(driver, bugsNavElement);

        WebElement luggageUnderAllBugs = getSubTabElement(bugsNavElement, "Men's", "T-Shirt");
        clickElement(luggageUnderAllBugs);

        expandFilter(driver, "Color");
        checkSubFilter(driver, "Color", "Brown");

        // թերատ եմ գրել
    }

}
