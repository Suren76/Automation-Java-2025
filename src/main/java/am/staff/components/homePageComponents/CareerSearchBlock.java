package am.staff.components.homePageComponents;

import am.staff.components.base.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CareerSearchBlock extends BaseComponent {
    public CareerSearchBlock(WebElement element) {
        super(element);
    }

    public void chooseCareerOpportunity(String opportunity) {
        By xpathToRadioButtonBlock = By.xpath(".//*[text()='%s']/..".formatted(opportunity));
        find(xpathToRadioButtonBlock).click();
    }

    public void selectDropdownOption(String dropdownButtonPlaceholder, String dropdownOption, Actions actions) {
        By xpathToDropdown = By.xpath("//*[contains(@class, 'select-selector')]//*[contains(text(), '%s')]/../..".formatted(dropdownButtonPlaceholder));
        By xpathToDropdownOption = By.xpath("//*[contains(@class, 'select-item-option')][contains(text(), '%s')]/..".formatted(dropdownOption));


        // click dropdown
        // todo: implement dropdown component
        clickOnDropdownButton(actions, xpathToDropdown);
        scrollDropdownToOption(actions, xpathToDropdownOption);

        clickElement(xpathToDropdownOption);
    }

    public void clickSearchButton() {
        By xpathToSearchButton = By.xpath("//*[contains(@alt, 'search-icon')]/..");
        clickElement(xpathToSearchButton);
    }

    private void scrollDropdownToOption(Actions actions, By xpathToDropdownOption) {
        while (getElement().findElements(xpathToDropdownOption).isEmpty()) {
            actions.sendKeys(Keys.DOWN).perform();
        }

        // scroll +2 to make root visible
        actions.sendKeys(Keys.DOWN).perform();
        actions.sendKeys(Keys.DOWN).perform();
    }

    private void clickOnDropdownButton(Actions actions, By xpathToDropdown) {
        actions.click(find(xpathToDropdown)).perform();
    }
}
