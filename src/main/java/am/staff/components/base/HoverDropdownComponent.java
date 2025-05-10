package am.staff.components.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static am.staff.helper.WebDriverHelper.getDriver;
import static am.staff.utils.Log.info;

public class HoverDropdownComponent extends BaseComponent {
    private static String templateXpathToNavbarDropdownItem = "//nav//*[./*[text()='%s']]/following-sibling::div[.//a]/..";

    private String dropdownFieldName;

    protected HoverDropdownComponent(WebElement element) {
        super(element);
    }

    protected HoverDropdownComponent(By selectorToNavbarDropdownField) {
        this(getDriver().findElement((selectorToNavbarDropdownField)));
    }

    public HoverDropdownComponent(String  navbarDropdownFieldName) {
        this(By.xpath(templateXpathToNavbarDropdownItem.formatted(navbarDropdownFieldName)));
        dropdownFieldName = navbarDropdownFieldName;
    }


    public void clickMenuHoverDropdownOption(String dropdownOption) {
        info("select '%s' from '%s' hover dropdown field".formatted(dropdownOption, dropdownFieldName));
        By xpathToHoverDropdownOption = By.xpath(".//a[.//text()='%s']".formatted(dropdownOption));

        actions
                .moveToElement(getElement())
                .moveToElement(getElement().findElement(xpathToHoverDropdownOption))
                .perform();
        ;
    }

}
