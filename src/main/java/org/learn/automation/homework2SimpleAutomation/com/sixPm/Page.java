package org.learn.automation.homework2SimpleAutomation.com.sixPm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class Page {
    public static String mainUrl = "https://www.6pm.com";

    public static void openHomePage(WebDriver driver) {
        driver.get(mainUrl);
    }

    public static WebElement getNavBarTabElement(WebDriver driver, String categoryName) {
        return driver.findElement(
                By.xpath("//li[count(*)=2]/a[text()='%s']".formatted(categoryName))
        );
    }

    public static WebElement getSubTabElement(WebElement tabElement, String subTabName, String subTabElementName) {
        By xpathToLuggageUnderAllBugs = By.xpath(
                "//*[text()='%s']/following-sibling::ul//a[text()='%s']"
                        .formatted(subTabName, subTabElementName)
        );
        return tabElement.findElement(xpathToLuggageUnderAllBugs);
    }

    public static void hoverElement(WebDriver driver, WebElement elementToHover) {
        new Actions(driver).moveToElement(elementToHover).perform();
    }

    public static List<ProductCard> getAllProducts(WebDriver driver, By xpathToProducts) {
        return driver.findElements(xpathToProducts).
                stream()
                .map(webElement -> new ProductCard(webElement))
                .toList();
    }

    public static void clickOnProduct(WebDriver driver, ProductCard productCard) {
        driver.get(getUrlByPath(productCard.productLink));
    }

    public static ProductDetailsOnPage getProductDetailsBlock(WebDriver driver) {
        By xpathToDetailsBlock = By.xpath("//div[@id='stage']/following-sibling::div");
        return new ProductDetailsOnPage(driver.findElement(xpathToDetailsBlock));
    }

    public static void expandFilter(WebDriver driver, String filterName) {
        By xpathToFilterExpandButton = By.xpath("//div[contains(@id, 'filter')]//button[text()='%s']".formatted(filterName));
        clickElement(driver.findElement(xpathToFilterExpandButton));
    }

    public static void checkSubFilter(WebDriver driver, String filterName, String subFilter) {
        By xpathToFilterExpandButton = By.xpath("//div[contains(@id, 'filter')]//button[text()='%s']//li//span[text()='%s']".formatted(filterName, subFilter));
        clickElement(driver.findElement(xpathToFilterExpandButton));
    }

    public static void clickElement(WebDriver driver, By xpathToElementToClick) {
        clickElement(driver.findElement(xpathToElementToClick));
    }

    public static void clickElement(WebElement elementToClick) {
        elementToClick.click();
        sleep(700);
    }

    public static boolean isCartEmpty(WebDriver driver) {
        By xpathToCartIcon = By.xpath("//a[@href='/cart']/span[1]");
        String cartCountStatus = driver.findElement(xpathToCartIcon).getText();
        return cartCountStatus.contains("empty");
    }

    public static String getUrlFromATag(WebElement elementWithATag) {
        return getUrlByPath(elementWithATag.getDomAttribute("href"));
    }

    private static String getUrlByPath(String pathFromDomainRoot) {
        return mainUrl + pathFromDomainRoot;
    }

    private static void sleep(int sleepMs) {
        try {
            Thread.sleep(sleepMs);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
