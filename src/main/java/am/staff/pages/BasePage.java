package am.staff.pages;

import am.staff.BaseInteractor;
import am.staff.components.base.Footer;
import am.staff.components.base.Header;
import am.staff.helper.WebDriverHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static am.staff.utils.Logger.debug;
import static am.staff.utils.Logger.info;
import static am.staff.utils.WaitUtility.getMiddleWait;

public abstract class BasePage extends BaseInteractor {
    private WebDriver driver = WebDriverHelper.getDriver();

    protected static String domain = "staff.am";
    protected static String baseUrl = "https://" + domain;

    protected WebDriver getDriver() {
        return driver;
    }

    protected static String getUrlWithDomain(String urlPath) {
        return baseUrl + urlPath;
    }

    protected Header getHeader() {
        return new Header(find(By.xpath("//html")));
    }

    protected Footer getFooter() {
        return new Footer(find(By.xpath("//html")));
    }

    @Step("open `CompanyPage` page")
    public CompanyPage clickCompaniesField() {
        info("click `Companies` field in navbar");
        getHeader().clickCompaniesField();
        return new CompanyPage();
    }

    @Step("open `CompanyPage` page")
    public CompanyPage clickFooterMenuViewAllCompaniesItem() {
        info("click `View all companies` field in footer menu");
        getFooter().clickCompaniesMenuItem("View all companies");
        return new CompanyPage();
    }

    @Step("open `CandidateRegisterPage` page")
    public CandidateRegisterPage clickCandidateRegisterPage() {
        info("click `Candidate` dropdown `Register` option");
        getHeader().clickCandidateDropdownOption("Register");
        return new CandidateRegisterPage();
    }

    protected BasePage clickElement(WebElement element) {
        debug("click element[%s]".formatted(element));
        scrollTo(element);
        getMiddleWait()
                .waitElementToBeClickable(element)
                .click();
        return this;
    }

    protected BasePage clickElement(By xpathToElement) {
        clickElement(find(xpathToElement));
        return this;
    }

    @Override
    protected WebElement find(By xpathToElement) {
        debug("find element[%s]".formatted(xpathToElement));
        return getDriver().findElement(xpathToElement);
    }

    protected void waitPageToBeLoaded() {
        debug("wait page to be loaded: %s".formatted(getClass().getSimpleName()));
        new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                .until( (ExpectedCondition<Boolean>) driver  ->
                        isPageLoaded()
                );
    }

    private BasePage openPageByUrl(String url) {
        getDriver().get(url);
        return this;
    }

    protected BasePage openPageByPath(String urlPath) {
        return openPageByUrl(getUrlWithDomain(urlPath));
    }

    // todo: check if no effect on code remove
    protected abstract boolean isPageLoaded();
    protected abstract void openPage();
}
