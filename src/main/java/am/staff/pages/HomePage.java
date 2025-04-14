package am.staff.pages;

import am.staff.components.homePageComponents.CareerSearchBlock;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends BasePage {
    private String locatorOfCareerSearchBlock = "//*[contains(text(), 'CAREER SEARCH')]/..";
    private By xpathToCareerSearchBlock = By.xpath(locatorOfCareerSearchBlock);

    protected String getPageUrl() {
        return baseUrl + "/";
    }

    @Override
    public void openPage() {
        getDriver().get(getPageUrl());
        waitPageToBeLoaded();
    }

    private CareerSearchBlock getSearchBlock() {
        return new CareerSearchBlock(find(xpathToCareerSearchBlock));
    }

    public void chooseCareerOpportunity(String opportunity) {
        getSearchBlock().chooseCareerOpportunity(opportunity);
    }

    public void selectDropdownOption(String dropdownButtonPlaceholder, String dropdownOption) {
        getSearchBlock().selectDropdownOption(dropdownButtonPlaceholder, dropdownOption, new Actions(getDriver()));
    }

    public ResultPage clickSearchButton() {
        getSearchBlock().clickSearchButton();
        return new ResultPage();
    }

    @Override
    protected boolean isPageLoaded() {
        return getFooter().isLoaded();
    }
}
