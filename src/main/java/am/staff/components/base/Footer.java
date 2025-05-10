package am.staff.components.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static am.staff.utils.Log.info;


public class Footer extends BaseComponent {
    private static String xpathToFooterBlock = "//noscript/following-sibling::div/div[.//*[contains(., 'All Rights Reserved.')]]";
    private static String templateXpathToFooterMenuItem = xpathToFooterBlock + "//*[text()='%s']/following-sibling::*//*[text()='%s']";

    public static By xpathToFooter = By.xpath(xpathToFooterBlock);

    public Footer(WebElement element) {
        super(element);
    }

    public void clickAboutMenuItem(String itemName) {
        clickFooterMenuItem("About", itemName);
    }

    public void clickIndividualsMenuItem(String itemName) {
        clickFooterMenuItem("Individuals", itemName);
    }

    public void clickCompaniesMenuItem(String itemName) {
        clickFooterMenuItem("Companies", itemName);
    }

    private void clickFooterMenuItem(String menuTitle, String menuItemName) {
        info("select '%s' on '%s' menu".formatted(menuItemName, menuTitle));
        By xpathToFooterMenuItem = By.xpath(templateXpathToFooterMenuItem.formatted(menuTitle, menuItemName));
        scrollTo(xpathToFooterMenuItem);
        clickElement(xpathToFooterMenuItem);
    }

    public boolean isLoaded() {
        return isElementExists(xpathToFooter);
    }
}
