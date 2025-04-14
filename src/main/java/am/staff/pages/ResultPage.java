package am.staff.pages;

import am.staff.components.base.WebElementToDataClass;
import am.staff.components.resultPageComponents.*;
import am.staff.utils.Logger;
import org.openqa.selenium.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static am.staff.utils.WaitUtility.getMiddleWait;


public class ResultPage extends BasePage {
    private static final int MAX_ITEMS_COUNT_ON_PAGE = 50;

    protected static final String URL_PATH_JOBS = "/jobs";
    protected static final String URL_PATH_TRAINING = "/trainings";
    protected static final String URL_PATH_COMPANIES = "/companies";

    private FiltersBlock filtersBlock = new FiltersBlock(getDriver().findElement(By.xpath("//html")));

    private static String templateXpathToTabButton = "//*[.//*[text()='All']]/following-sibling::*[.//*[text()='%s']]";

    private static By xpathToSearchField = By.xpath("//input[@enterkeyhint='search']");
    private static By xpathToResultItems = By.xpath(
            "//*[.//*[text()='Search']]/following-sibling::*" +
                    "//a[.//*[text()='View more' or text()='Learn more'] or .//*[@alt='bookmark-icon']][.//img]");

    private static String selectorToPaginationElements = "//li[@class='next']/preceding-sibling::li/a[text()]";
    private static By xpathToPaginationElements = By.xpath(selectorToPaginationElements);


    private WebElement getSearchField() {
        return getDriver().findElement(xpathToSearchField);
    }

    public void search(String searchText) {
        waitResultItemsToBeLoadedWrapper(() -> {
            cleanSearchField();
            getSearchField().sendKeys(searchText);
            getSearchField().sendKeys(Keys.ENTER);
        });
    }

    private void cleanSearchField() {
        getSearchField().clear();
    }

    public List<? extends ResultItemBlock> getResultItemBlockList() {
        // todo: research
        //  if i scroll intoViewOfElement it needs to wait
        //  if i pass element without scroll and wait it works faster but may throw `StaleElementReferenceException`
        return getDriver().findElements(xpathToResultItems).stream()
                .map(element -> {
                    scrollTo(element);
                    // todo: implement with `getResultItemsType()` or with `Enum`
                    return switch (getResultPageType()) {
                        case "companies" -> new CompanyItemBlock(
                                getMiddleWait().waitElementToBeVisible(element)
                        );
                        case "jobs" -> new JobItemBlock(
                                getMiddleWait().waitElementToBeVisible(element)
                        );
                        // todo: implement create `ResultItemNotImplementedException` exception
                        default -> throw new RuntimeException("items type is not implemented");
                    };
                })
                .collect(Collectors.toList());
    }

    public SingleCompanyPage openCompanyPage(CompanyItemBlock itemToOpen) {
        return new SingleCompanyPage(itemToOpen.getCompanyPageLink());
    }

    public void switchTabTo(String tabName) {
        By xpathToTabButton = By.xpath(templateXpathToTabButton.formatted(tabName));
        waitResultItemsToBeLoadedWrapper(() -> clickElement(xpathToTabButton));
    }

    public ResultPage addFilter(String filterName, String filterOption) {
        waitResultItemsToBeLoadedWrapper(() ->
                filtersBlock.addFilter(filterName, filterOption)
        );
        return this;
    }

    public ResultPage removeFilter(String filterName, String filterOption) {
        waitResultItemsToBeLoadedWrapper(() ->
                filtersBlock.removeFilter(filterName, filterOption)
        );
        return this;
    }

    public int getFilterOptionItemsCount(String filterName, String filterOption) {
        return filtersBlock.getFilterOptionItemsCount(filterName, filterOption);
    }

    @Override
    public ResultPage clickFooterMenuViewAllCompaniesItem() {
        waitResultItemsToBeLoadedWrapper(() ->
                super.clickFooterMenuViewAllCompaniesItem()
        );
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
        // todo: check if there are no effect remove
        getMiddleWait().waitElementToBeClickable(xpathToResultItems);
        return true;
    }

    private boolean waitResultItemsToBeLoadedAfter(List<? extends ResultItemBlock> listOfResultItemsToBeChanged, int timeoutSeconds) {
        // TODO: implement
        //  add wait to one item root

        // TODO: refactoring
        //  do some researches to identify is there need to implement one by one check
        //  implement one by one if needed


        int timeoutForWaitInMilis = timeoutSeconds * 1000;

        while ((timeoutForWaitInMilis -= 500) > 0) {
            List<? extends ResultItemBlock> currentListOfResultItems;

            try {
                currentListOfResultItems = getResultItemBlockList();
            } catch (StaleElementReferenceException e) {
                Logger.error(e.toString());
                sleep(500);
                continue;
            }

            if (
                    listOfResultItemsToBeChanged.size() != currentListOfResultItems.size() ||
                    !new HashSet<>(listOfResultItemsToBeChanged).equals(
                            new HashSet<>(currentListOfResultItems)
                    )
            ) return true;

            sleep(500);
        }
        return false;
    }

    private boolean waitResultItemsToBeLoadedAfter(List<? extends ResultItemBlock> listOfElementsToBeChanged) {
        return waitResultItemsToBeLoadedAfter(listOfElementsToBeChanged, 5);
    }

    private void waitResultItemsToBeLoadedWrapper(Runnable action) {
        List<? extends ResultItemBlock> itemsToCheckAfterFilterAdd = getResultItemBlockList();
        action.run();
        waitResultItemsToBeLoadedAfter(itemsToCheckAfterFilterAdd);
    }

    public int getAllFoundedItemsCount() {
        List<? extends ResultItemBlock> listOfElementsOnPage = getResultItemBlockList();
        if (listOfElementsOnPage.size() < 50) return listOfElementsOnPage.size();

        // find and if exception return items count else calculate them
        int lastPaginationElementNumber = find(xpathToPaginationElements)
                .findElements(xpathToPaginationElements).stream()
                .map(element -> {
                    try {
                        return Integer.parseInt(element.getText());
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                }).max(Integer::compareTo).get();
        // click last element from pagination
        clickElement(By.xpath(selectorToPaginationElements + "[text()='%s']".formatted(lastPaginationElementNumber)));
        // calculate full items count and add count fo items from last page
        return (lastPaginationElementNumber - 1) * 50 + getResultItemBlockList().size();
    }

    public ResultPage openJobs() {
        openPageByPath(URL_PATH_JOBS);
        return new ResultPage();
    }

    public ResultPage openTraining() {
        openPageByPath(URL_PATH_TRAINING);
        return new ResultPage();
    }

    public ResultPage openCompanies() {
        openPageByPath(URL_PATH_COMPANIES);
        return new ResultPage();
    }

    private Class<? extends WebElementToDataClass> getResultItemsType() {
        return Map.of(
                "jobs", JobItemBlock.class,
                "companies", CompanyItemBlock.class,
                "trainings", TrainingItemBlock.class
        ).get(getResultPageType());
    }

    private String getResultPageType() {
        // todo: implement global get current page url
        for(String resultItemType: List.of("trainings", "jobs", "companies")) {
            if (getDriver().getCurrentUrl().contains(resultItemType)) return resultItemType;
        }
        throw  new RuntimeException("there are no implementation for current page type: %s".formatted(getDriver().getCurrentUrl()));
    }
}
