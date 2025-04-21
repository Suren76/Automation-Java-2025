package am.staff.components.resultPageComponents;

import am.staff.components.base.SingleLocatorComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static am.staff.utils.WaitUtility.getMiddleWait;

public class FiltersBlock extends SingleLocatorComponent {
    private static String templateXpathToFilterBlock =
            "//*[.//*[text()='Filter']] /following-sibling::*[ .//*[text()]/following-sibling::div[.//span[text()]] ] [.//*[text()=\"%s\"]]";
    private static String templateXpathToFilterBlockCheckedItem = templateXpathToFilterBlock + "/*[.//*[text()=\"%s\"]]//img";

    // find inside filter block root as a child
    private static String templateXpathToFilterBlockCheckboxItem = templateXpathToFilterBlock + "//*[text()=\"%s\"]";
    // find inside filter option as a child
    private static String xpathToItemsCountNearFilterOption = templateXpathToFilterBlockCheckboxItem + "//*[text()=')' and text()='(']";

    private static By xpathToFilterBlockExpandButton = By.xpath(".//*[text()='View more']");
    private final static String xpathToFilterBlockClearFiltersButton = "//*[text()='Clear filters']";

    @FindBy(xpath = xpathToFilterBlockClearFiltersButton)
    private WebElement filterBlockClearFilterButton;

    public FiltersBlock(WebElement element) {
        super(element);
    }

    public void addFilter(String filterName, String filterOption) {
        if (isFilterChecked(filterName, filterOption)) return;
        clickFilterCheckbox(filterName, filterOption);
        waitClearFilterToBeLoaded();
    }

    public void removeFilter(String filterName, String filterOption) {
        var e = find(By.xpath(xpathToFilterBlockClearFiltersButton));
        clickFilterCheckbox(filterName, filterOption);
//        getMiddleWait().waitElementToNotBe(e);
    }

    public int getFilterOptionItemsCount(String filterName, String filterOption) {
        By xpathToFilterOptionItemsCount = By.xpath(xpathToItemsCountNearFilterOption.formatted(filterName, filterOption));
        return Integer.parseInt(
                find(xpathToFilterOptionItemsCount)
                        .getText().replaceAll("[()]", "")
        );
    }

    private void clickFilterCheckbox(String filterName, String filterOption) {
        By xpathToFilterBlock = By.xpath(templateXpathToFilterBlock.formatted(filterName));
        By xpathToFilterBlockItem = By.xpath(templateXpathToFilterBlockCheckboxItem.formatted(filterName, filterOption));

        clickViewMoreButtonIfExists(xpathToFilterBlock);
        clickElement(xpathToFilterBlockItem);
    }

    private void clickViewMoreButtonIfExists(By xpathToFilterBlock) {
        List<WebElement> listOfExpandButtonInFilterBlock = find(xpathToFilterBlock).findElements(xpathToFilterBlockExpandButton);
        if (listOfExpandButtonInFilterBlock.size() == 1) {
            clickElement(listOfExpandButtonInFilterBlock.getFirst());
        }
    }

    private boolean isFilterChecked(String filterName, String filterOption) {
        By xpathToCheckedFilterItem = By.xpath(templateXpathToFilterBlockCheckedItem.formatted(filterName, filterOption));
        return !getElement().findElements(xpathToCheckedFilterItem).isEmpty();
    }

    private void waitClearFilterToBeLoaded() {
        getMiddleWait().waitElementToBeClickable(filterBlockClearFilterButton);
    }
}
