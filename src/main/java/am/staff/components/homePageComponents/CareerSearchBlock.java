package am.staff.components.homePageComponents;

import am.staff.components.base.BaseComponent;
import am.staff.components.base.DropdownComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static am.staff.components.base.DropdownComponent.tepmlateXpathToDropdownElement;

public class CareerSearchBlock extends BaseComponent {
    public CareerSearchBlock(WebElement element) {
        super(element);
    }

    private static By xpathToAllCategories = By.xpath(tepmlateXpathToDropdownElement.formatted("All categories"));
    private static By xpathToAllCities = By.xpath(tepmlateXpathToDropdownElement.formatted("All cities"));
    private static By xpathToAllIndustries = By.xpath(tepmlateXpathToDropdownElement.formatted("All industries"));

    public void chooseCareerOpportunity(String opportunity) {
        By xpathToRadioButtonBlock = By.xpath(".//*[text()='%s']/..".formatted(opportunity));
        find(xpathToRadioButtonBlock).click();
    }

    public void selectDropdownOption(String dropdownButtonPlaceholder, String dropdownOption) {
        By xpathToDropdown = By.xpath(tepmlateXpathToDropdownElement.formatted(dropdownButtonPlaceholder));
        new DropdownComponent(xpathToDropdown).selectDropdownOption(dropdownOption);
    }

    public void clickSearchButton() {
        By xpathToSearchButton = By.xpath("//*[contains(@alt, 'search-icon')]/..");
        clickElement(xpathToSearchButton);
    }
}
