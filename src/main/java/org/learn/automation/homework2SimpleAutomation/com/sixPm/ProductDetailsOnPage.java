package org.learn.automation.homework2SimpleAutomation.com.sixPm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductDetailsOnPage extends BaseWebElementToDataClass{
    public String price;
    public String productName;

    public ProductDetailsOnPage(WebElement detailsBlockOnPage) {
        super(detailsBlockOnPage);
    }

    @Override
    public void setupData() {
        price = getPrice();
        productName = getProductName();
    }

    private String getPrice() {
        By xpathToPrice = By.xpath("//*[@id='productRecap']//*[@itemprop='price']");
        return element.findElement(xpathToPrice).getText()
                .replaceAll("\n", "")
                .replaceAll(" ", "");
    }

    private String getProductName() {
        By xpathToProductName = By.xpath("//span[@itemprop='brand']/following-sibling::span");
        return element.findElement(xpathToProductName).getText()
                .replaceAll("\n", "")
                .replaceAll(" ", "");
    }
}
