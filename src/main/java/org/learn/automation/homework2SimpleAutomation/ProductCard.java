package org.learn.automation.homework2SimpleAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductCard {
    private WebElement element;

    private By xpathToProductName = By.xpath(".//dd[1]");
    private By xpathToPrice = By.xpath(".//dd[4]");
    private By xpathToProductLink = By.xpath("./a");

    public String price;
    public String productName;
    public String productLink;

    public ProductCard(WebElement element) {
        this.element = element;

        price = getPrice();
        productName = getProductName();
        productLink = getProductLink();
    }

    private String getPrice() {
        return element.findElement(xpathToPrice).getText();
    }

    private String getProductName() {
        return element.findElement(xpathToProductName).getText();
    }

    private String getProductLink() {
        return element.findElement(xpathToProductLink).getDomAttribute("href");
    }
}
