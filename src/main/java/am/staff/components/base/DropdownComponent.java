package am.staff.components.base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static am.staff.helper.WebDriverHelper.getDriver;

public class DropdownComponent extends BaseComponent {
    private static String tepmlateXpathToDropdownElement = "//*[contains(@class, 'select-selector')][.//*[contains(text(), '%s')]]";

    private static String xpathToScrollBarThumb = "./ancestor::div[parent::body]//*[contains(@class, 'scrollbar-thumb')]";

    protected static Actions actions = new Actions(getDriver());

    protected DropdownComponent(WebElement element) {
        super(element);
    }

    public DropdownComponent(String dropdownPlaceholderName) {
        this(By.xpath(tepmlateXpathToDropdownElement.formatted(dropdownPlaceholderName)));
    }

    public DropdownComponent(By selectorToElement) {
        this(getDriver().findElement(selectorToElement));
    }

    public void selectDropdownOption(String dropdownOption) {
        By xpathToDropdownOption = By.xpath("//*[contains(@class, 'select-item-option')][contains(text(), '%s')]/..".formatted(dropdownOption));

        clickOnDropdownButton(actions, getElement());
        scrollDropdownToOption(actions, xpathToDropdownOption);

        clickElement(xpathToDropdownOption);
    }

    private void scrollDropdownToOption(Actions actions, By xpathToDropdownOption) {
        while (getElement().findElements(xpathToDropdownOption).isEmpty()) {
            actions.sendKeys(Keys.DOWN).perform();

            // to avoid infinity loop
            if (getScrollBarThumbTopValue(xpathToDropdownOption) == 0.0) return;
        }

        // scroll +2 to make root visible
        actions.sendKeys(Keys.DOWN).perform();
        actions.sendKeys(Keys.DOWN).perform();
    }

    private void clickOnDropdownButton(Actions actions, By xpathToDropdown) {
        actions.click(find(xpathToDropdown)).perform();
    }

    private void clickOnDropdownButton(Actions actions, WebElement dropdownElement) {
        actions.click(dropdownElement).perform();
    }

    private Double getScrollBarThumbTopValue(By optionLocator) {
        return Double.parseDouble(
                (String) getJavaScriptExecutor()
                        .executeScript("$0.style.getPropertyValue('top').split('px')[0]")
        );
    }
}

