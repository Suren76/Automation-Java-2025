package am.staff.components.homePageComponents;

import am.staff.components.base.BaseComponent;
import am.staff.components.base.DropdownComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CareerSearchBlock extends BaseComponent {
    public CareerSearchBlock(WebElement element) {
        super(element);
    }

    public void chooseCareerOpportunity(String opportunity) {
        By xpathToRadioButtonBlock = By.xpath(".//*[text()='%s']/..".formatted(opportunity));
        find(xpathToRadioButtonBlock).click();
    }

    public void selectDropdownOption(String dropdownButtonPlaceholder, String dropdownOption) {
        new DropdownComponent(dropdownButtonPlaceholder).selectDropdownOption(dropdownOption);
    }

    public void clickSearchButton() {
        By xpathToSearchButton = By.xpath("//*[contains(@alt, 'search-icon')]/..");
        clickElement(xpathToSearchButton);
    }
}
