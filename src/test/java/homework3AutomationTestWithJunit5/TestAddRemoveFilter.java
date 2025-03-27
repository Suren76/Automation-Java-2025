package homework3AutomationTestWithJunit5;

import BaseTestComponents.BaseTestWithDriverInitClose;
import org.junit.jupiter.api.*;
import org.learn.automation.homework2SimpleAutomation.com.sixPm.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.learn.automation.homework2SimpleAutomation.com.sixPm.Page.*;
import static org.learn.automation.homework2SimpleAutomation.com.sixPm.Page.clickElement;

@Tag("filter")
public class TestAddRemoveFilter  extends BaseTestWithDriverInitClose {
    @Test
    @Tag("filter-TestAddRemoveFilter")
    public void testAddRemoveFilter() {
        Page.openHomePage(driver);

        WebElement bugsNavElement = getNavBarTabElement(driver, "Clothing");

        hoverElement(driver, bugsNavElement);

        WebElement luggageUnderAllBugs = getSubTabElement(bugsNavElement, "Men's", "T-Shirts");
        clickElement(luggageUnderAllBugs);

        expandFilter(driver, "Color");
        checkSubFilter(driver, "Color", "Brown");

        System.out.println();

        int itemsCountFromCheckbox = getItemsCountFromFilterCheckbox(driver, "Color", "Brown");
        int itemsCountFromPageSearchResult = getItemsCountFromResult(driver);

        Assertions.assertEquals(itemsCountFromCheckbox, itemsCountFromPageSearchResult,
                "Items count on checkbox didn't equal to filtered page result!");

        removeFilter(driver, "Brown");
        List<String> appliedFiltersList = getAppliedFiltersList(driver);
        Assertions.assertFalse(
                appliedFiltersList.contains("Brown"),
                "Filters list contains a removed filter!"
        );
        Assertions.assertFalse(
                isCheckboxChecked(driver, "Color", "Brown"),
                "The checkbox should not be checked, but it is!"
        );


    }

}
