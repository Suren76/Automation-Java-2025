package org.learn.automation.homework2SimpleAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductDetailsOnPage {
    private WebElement detailsBlockOnPage;

    private By xpathToProductName = By.xpath("//span[@itemprop='brand']/following-sibling::span");
    private By xpathToPrice = By.xpath("//*[@id='productRecap']//*[@itemprop='price']");

    public String price;
    public String productName;

    public ProductDetailsOnPage(WebElement detailsBlockOnPage) {
        this.detailsBlockOnPage = detailsBlockOnPage;

        price = getPrice();
        productName = getProductName();
    }

    private String getPrice() {
        return detailsBlockOnPage.findElement(xpathToPrice).getText();
    }

    private String getProductName() {
        return detailsBlockOnPage.findElement(xpathToProductName).getText();
    }
}
