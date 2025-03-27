package org.learn.automation.homework2SimpleAutomation.com.sixPm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductCard extends BaseWebElementToDataClass{
    public String price;
    public String productName;
    public String productLink;

    public ProductCard(WebElement element) {
        super(element);
    }

    public void setupData() {
        price = getPrice();
        productName = getProductName();
        productLink = getProductLink();
    }

    private String getPrice() {
        By xpathToPrice = By.xpath(".//dd[4]");
        return element.findElement(xpathToPrice).getText();
    }

    private String getProductName() {
        By xpathToProductName = By.xpath(".//dd[1]");
        return element.findElement(xpathToProductName).getText();
    }

    private String getProductLink() {
        By xpathToProductLink = By.xpath("./a");
        return element.findElement(xpathToProductLink).getDomAttribute("href");
    }
}
