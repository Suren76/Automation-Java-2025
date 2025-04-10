package am.staff.pages;

import am.staff.components.resultPageComponents.FiltersBlock;
import am.staff.components.resultPageComponents.ResultItemBlock;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static am.staff.utils.WaitUtility.getLongWait;
import static am.staff.utils.WaitUtility.getMiddleWait;


public class ResultPage extends BasePage {
    private FiltersBlock filtersBlock = new FiltersBlock(getDriver().findElement(By.xpath("//html")));

    private String templateXpathToTabButton = "//*[.//*[text()='All']]/following-sibling::*[.//*[text()='%s']]";

    private By xpathToSearchField = By.xpath("//input[@enterkeyhint='search']");
    private By xpathToResultItems = By.xpath(
            "//a[contains(@href, '/company/')]" +
            "[.//*[@alt='company-logo']]" +
            "[.//*[text()='View more'] or .//*[@alt='bookmark-icon']]"
    );



    private WebElement getSearchField() {
        return getDriver().findElement(xpathToSearchField);
    }

    public void search(String searchText) {
        cleanSearchField();
        getSearchField().sendKeys(searchText);
        getSearchField().sendKeys(Keys.ENTER);
        isItemsLoaded();
    }

    private void cleanSearchField() {
        getSearchField().clear();
    }

    public List<ResultItemBlock> getResultItemBlockList() {
        // wait page to be loaded
        // todo: remove `sleep`
        //  and implement `isPageLoaded` method or `waitPageLoad` method
        isItemsLoaded();
//        getMiddleWait().waitElementToBeClickable(xpathToResultItems);

        return getDriver().findElements(xpathToResultItems).stream()
                .map(element -> {
                    scrollTo(element);
                    getMiddleWait()
                            .waitElementToBeVisible(element);
                    return new ResultItemBlock(element);
                })
                .collect(Collectors.toList());
    }

    public SingleCompanyPage openCompanyPage(ResultItemBlock itemToOpen) {
        return new SingleCompanyPage(itemToOpen.getCompanyPageLink());
    }

    public void switchTabTo(String tabName) {
        By xpathToTabButton = By.xpath(templateXpathToTabButton.formatted(tabName));
        clickElement(xpathToTabButton);
    }

    public ResultPage addFilter(String filterName, String filterOption) {
        filtersBlock.addFilter(filterName, filterOption);
        return this;
    }

    @Override
    protected boolean isPageLoaded() {
        return isItemsLoaded();
    }

    @Override
    protected void openPage() {
        waitPageToBeLoaded();
    }

    private boolean isItemsLoaded() {
        sleep(3000);
        return true;
    }
}
