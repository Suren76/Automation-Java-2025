package org.learn.automation.homework2SimpleAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class Main {
    private static String urlToOpen = "https://www.6pm.com";

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get(urlToOpen);

            By xpathToNavbarBags = By.xpath("//header//a[contains(@href, '/c/bags')]");
            WebElement bugsNavElement = driver.findElement(xpathToNavbarBags);

            // move to element and hover
            new Actions(driver).moveToElement(bugsNavElement).perform();
            By xpathToLuggageUnderAllBugs = By.xpath("//*[text()='All Bags']/following-sibling::ul//a[text()='Luggage']");
            clickElement(driver, xpathToLuggageUnderAllBugs);

            sleep(500);
            By xpathToAllProducts = By.xpath("//article");
            List<ProductCard> allProducts = getAllProducts(driver, xpathToAllProducts);

            ProductCard randomProductToClick = allProducts.getFirst();
            clickOnProduct(driver, randomProductToClick);

            ProductDetailsOnPage productDetailsOnPage = getProductDetailsBlock(driver);
            System.out.println(productDetailsOnPage.productName);
            System.out.println(productDetailsOnPage.price);

            By xpathToAddToProductCardButton = By.xpath("//button[@id='add-to-cart-button']");
            By xpathToRemoveProductButton = By.xpath("//button[text()='Remove']");

            clickElement(driver, xpathToAddToProductCardButton);
            clickElement(driver, xpathToRemoveProductButton);

            By xpathToLoginButton = By.xpath("//a[contains(text(), 'Log In')]");
            String urlToLoginForm = driver.findElement(xpathToLoginButton).getDomAttribute("href");
            System.out.println(getUrlByPath(urlToLoginForm));
        }
        finally {
            driver.quit();
        }
    }

    static List<ProductCard> getAllProducts(WebDriver driver, By xpathToProducts) {
        return driver.findElements(xpathToProducts).
                stream()
                .map(webElement -> new ProductCard(webElement))
                .toList();
    }

    static void clickOnProduct(WebDriver driver, ProductCard productCard) {
        driver.get(getUrlByPath(productCard.productLink));
    }

    static ProductDetailsOnPage getProductDetailsBlock(WebDriver driver) {
        By xpathToDetailsBlock = By.xpath("//div[@id='stage']/following-sibling::div");
        return new ProductDetailsOnPage(driver.findElement(xpathToDetailsBlock));
    }

    static void clickElement(WebDriver driver, By xpathToElementToClick) {
        driver.findElement(xpathToElementToClick).click();
        sleep(700);
    }

    private static String getUrlByPath(String pathFromDomainRoot) {
        return urlToOpen + pathFromDomainRoot;
    }

    static void sleep(int sleepMs) {
        try {
            Thread.sleep(sleepMs);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
