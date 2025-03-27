package homework3AutomationTestWithJunit5;

import BaseTestComponents.BaseTestWithDriverInitClose;
import org.junit.jupiter.api.*;
import org.learn.automation.homework2SimpleAutomation.com.sixPm.Page;
import org.learn.automation.homework2SimpleAutomation.com.sixPm.ProductCard;
import org.learn.automation.homework2SimpleAutomation.com.sixPm.ProductDetailsOnPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.learn.automation.homework2SimpleAutomation.com.sixPm.Page.*;

@Tag("cart")
public class TestAddRandomItemToCartAndRemove extends BaseTestWithDriverInitClose {
    @Test
    public void testAddRandomItemToCartAndRemove() {
        Page.openHomePage(driver);

        WebElement bugsNavElement = getNavBarTabElement(driver, "Bags");

        hoverElement(driver, bugsNavElement);

        WebElement luggageUnderAllBugs = getSubTabElement(bugsNavElement, "All Bags", "Luggage");
        clickElement(luggageUnderAllBugs);

        By xpathToAllProducts = By.xpath("//article");
        List<ProductCard> allProducts = getAllProducts(driver, xpathToAllProducts);

        ProductCard firstProductToClick = allProducts.getFirst();
        clickOnProduct(driver, firstProductToClick);

        ProductDetailsOnPage productDetailsOnPage = getProductDetailsBlock(driver);

        Assertions.assertEquals(firstProductToClick.price, productDetailsOnPage.price, "failed price equality check");
        Assertions.assertEquals(firstProductToClick.productName, productDetailsOnPage.productName, "failed product name equality check");

        By xpathToAddToProductCardButton = By.xpath("//button[@id='add-to-cart-button']");
        By xpathToRemoveProductButton = By.xpath("//button[text()='Remove']");

        clickElement(driver, xpathToAddToProductCardButton);
        clickElement(driver, xpathToRemoveProductButton);

        Assertions.assertTrue(isCartEmpty(driver), "failed check of empty cart");

        By xpathToLoginButton = By.xpath("//a[contains(text(), 'Log In')]");
        WebElement loginFormLinkTag = driver.findElement(xpathToLoginButton);

        // login link
        System.out.println(getUrlFromATag(loginFormLinkTag));
    }
}
