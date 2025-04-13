package am.staff.components.resultPageComponents;

import am.staff.components.base.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static am.staff.utils.WaitUtility.getMiddleWait;

public class FiltersBlock extends BaseComponent {
    private static String templateXpathToFilterBlock =
            "//*[.//*[text()='Filter']]/following-sibling::*[.//*[contains(text(), 'by') or contains(text(), 'By')]" +
                    "[text()='%s']]";
    private static String templateXpathToFilterBlockCheckedItem = templateXpathToFilterBlock + "/*[.//*[text()='%s']]//img";

    // find inside filter block root as a child
    private static String templateXpathToFilterBlockCheckboxItem = ".//*[text()='%s']";

    private static By xpathToFilterBlockExpandButton = By.xpath(".//*[text()='View more']");
    private static By xpathToFilterBlockClearFiltersButton = By.xpath("//*[text()='Clear filters']");


    public FiltersBlock(WebElement element) {
        super(element);
    }

    public void addFilter(String filterName, String filterOption) {
        By xpathToFilterBlock = By.xpath(templateXpathToFilterBlock.formatted(filterName));
        By xpathToFilterBlockItem = By.xpath(templateXpathToFilterBlockCheckboxItem.formatted(filterOption));

        clickViewMoreButtonIfExists(xpathToFilterBlock);
        // todo: check if not needed remove
//        if (isFilterChecked(filterName, filterOption)) return;
        clickElement(xpathToFilterBlockItem);
        waitClearFilterToBeLoaded();
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
        getMiddleWait().waitElementToBeClickable(xpathToFilterBlockClearFiltersButton);
    }
}
