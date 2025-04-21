package am.staff.pages;

import am.staff.components.resultPageComponents.*;
import am.staff.utils.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.*;


import java.io.File;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static am.staff.utils.WaitUtility.getMiddleWait;

public abstract class ResultPage<T extends ResultItemBlock> extends BasePage {

    private static final int MAX_ITEMS_COUNT_ON_PAGE = 50;

    protected static final String URL_PATH_JOBS = "/jobs";
    protected static final String URL_PATH_TRAINING = "/trainings";
    protected static final String URL_PATH_COMPANIES = "/companies";

    private FiltersBlock filtersBlock = getFiltersBlock();

    private static String templateXpathToTabButton = "//*[.//*[text()='All']]/following-sibling::*[.//*[text()='%s']]";

    private static By xpathToSearchField = By.xpath("//input[@enterkeyhint='search']");
    private static By xpathToResultItems = By.xpath(
            "//*[.//*[text()='Search']]/following-sibling::*" +
                    "//a[.//*[text()='View more' or text()='Learn more'] or .//*[@alt='bookmark-icon']][.//img]");

    private static String selectorToPaginationElements = "//li[@class='next']/preceding-sibling::li/a[text()]";
    private static By xpathToPaginationElements = By.xpath(selectorToPaginationElements);

    public ResultPage() {
        super();
    }

    protected FiltersBlock getFiltersBlock() {
        return new FiltersBlock(getDriver().findElement(By.xpath("//html")));
    }

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

    public List<T> getResultItemBlockList() {
//        getMiddleWait().waitElementPresence(xpathToResultItems);
        sleep(2000);
        return getDriver().findElements(xpathToResultItems).stream()
                .map(element -> {
                    return getResultItemBlockBy(element);
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

    public ResultPage<T> addFilter(String filterName, String filterOption) {
        waitResultItemsToBeLoadedWrapper(() ->
                getFiltersBlock().addFilter(filterName, filterOption)
        );
        return this;
    }

    public ResultPage<T> removeFilter(String filterName, String filterOption) {
        waitResultItemsToBeLoadedWrapper(() ->
                getFiltersBlock().removeFilter(filterName, filterOption)
        );
        return this;
    }

    public int getFilterOptionItemsCount(String filterName, String filterOption) {
        return getFiltersBlock().getFilterOptionItemsCount(filterName, filterOption);
    }

    @Override
    public CompanyPage clickFooterMenuViewAllCompaniesItem() {
        waitResultItemsToBeLoadedWrapper(() ->
                super.clickFooterMenuViewAllCompaniesItem()
        );
        return new CompanyPage();
    }

    private boolean isItemsLoaded() {
        // todo: check if there are no effect remove
        getMiddleWait().waitElementToBeClickable(xpathToResultItems);
        return true;
    }

    // todo: refactoring
    //  split to atomic methods
    private boolean waitResultItemsToBeLoadedAfter(List<T> listOfResultItemsToBeChanged, int timeoutSeconds) {
        int timeoutForWaitInMilis = timeoutSeconds * 1000;

        while ((timeoutForWaitInMilis -= 500) > 0) {
            List<? extends ResultItemBlock> currentListOfResultItems;

            try {
                currentListOfResultItems = getResultItemBlockList();
            } catch (StaleElementReferenceException e) {
                sleep(500);
                continue;
            }

            if (!(listOfResultItemsToBeChanged.getFirst().getClass()).equals(
                    currentListOfResultItems.getFirst().getClass())
            ) return true;
            else if (
                    listOfResultItemsToBeChanged.size() != currentListOfResultItems.size() ||
                            !new HashSet<>(listOfResultItemsToBeChanged).equals(
                                    new HashSet<>(currentListOfResultItems)
                            )
            ) return true;

            sleep(500);
        }
        return false;
    }

    private boolean waitResultItemsToBeLoadedAfter(List<T> listOfElementsToBeChanged) {
        return waitResultItemsToBeLoadedAfter(listOfElementsToBeChanged, 5);
    }

    private void waitResultItemsToBeLoadedWrapper(Runnable actionToChangeItemsState) {
        List<T> itemsToCheckAfterFilterAdd = getResultItemBlockList();
        actionToChangeItemsState.run();
        waitResultItemsToBeLoadedAfter(itemsToCheckAfterFilterAdd);
    }

    public int getAllFoundedItemsCount() {
        List<T> listOfElementsOnPage = getResultItemBlockList();
        if (listOfElementsOnPage.size() < 50) return listOfElementsOnPage.size();

        // find and if exception return items count else calculate them
        int lastPaginationElementNumber = getLastPaginationElementNumber();
        By xpathToPaginationLastElement = By.xpath(selectorToPaginationElements + "[text()='%s']".formatted(lastPaginationElementNumber));
        // click last element from pagination
        clickElement(xpathToPaginationLastElement);
        // calculate full items count and add count fo items from last page
        return (lastPaginationElementNumber - 1) * 50 +  getResultItemBlockList().size();
    }

    private int getLastPaginationElementNumber() {
        return find(xpathToPaginationElements)
                .findElements(xpathToPaginationElements).stream()
                .map(element -> {
                    try {
                        return Integer.parseInt(element.getText());
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                })
                .max(Integer::compareTo)
                .get();
    }

    public JobsPage openJobs() {
        return new JobsPage();
    }

    public TrainingPage openTraining() {
        return new TrainingPage();
    }

    public CompanyPage openCompanies() {
        return new CompanyPage();
    }

    abstract protected T getResultItemBlockBy(WebElement elementToSerialize);
}

