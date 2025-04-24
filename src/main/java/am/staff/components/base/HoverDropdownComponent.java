package am.staff.components.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static am.staff.helper.WebDriverHelper.getDriver;

public class HoverDropdownComponent extends BaseComponent {
    private static String templateXpathToNavbarDropdownItem = "//nav//*[./*[text()='%s']]/following-sibling::div[.//a]/..";

    public HoverDropdownComponent(WebElement element) {
        super(element);
    }

    public HoverDropdownComponent(By selectorToNavbarDropdownField) {
        this(getDriver().findElement((selectorToNavbarDropdownField)));
    }
    public HoverDropdownComponent(String  navbarDropdownFieldName) {
        this(By.xpath(templateXpathToNavbarDropdownItem.formatted(navbarDropdownFieldName)));
    }


    public void clickMenuHoverDropdownOption(String dropdownOption) {
        By xpathToHoverDropdownOption = By.xpath(".//a[.//text()='%s']".formatted(dropdownOption));

        actions
                .moveToElement(getElement())
                .moveToElement(getElement().findElement(xpathToHoverDropdownOption))
                .perform();
        ;
    }

}
