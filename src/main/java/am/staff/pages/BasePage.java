package am.staff.pages;

import am.staff.BaseInteractor;
import am.staff.components.base.Footer;
import am.staff.components.base.Header;
import am.staff.helper.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static am.staff.utils.WaitUtility.getMiddleWait;

public abstract class BasePage extends BaseInteractor {
    private WebDriver driver = WebDriverHelper.getDriver();

    protected static String domain = "staff.am";
    protected static String baseUrl = "https://" + domain;

    public BasePage() {
        openPage();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getUrlWithDomain(String urlPath) {
        return baseUrl + urlPath;
    }

    protected Header getHeader() {
        return new Header(find(By.xpath("//html")));
    }

    protected Footer getFooter() {
        return new Footer(find(By.xpath("//html")));
    }

    public ResultPage clickCompaniesField() {
        getHeader().clickCompaniesField();
        return new ResultPage();
    }

    public ResultPage clickFooterMenuViewAllCompaniesItem() {
        getFooter().clickCompaniesMenuItem("View all companies");
        return new ResultPage();
    }

    public BasePage clickElement(WebElement element) {
        scrollTo(element);
        getMiddleWait()
                .waitElementToBeClickable(element)
                .click();
        return this;
    }

    public BasePage clickElement(By xpathToElement) {
        clickElement(find(xpathToElement));
        return this;
    }

    public WebElement find(By xpathToElement) {
        return getDriver().findElement(xpathToElement);
    }

    protected void waitPageToBeLoaded() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                .until( (ExpectedCondition<Boolean>) driver  ->
                        isPageLoaded()
                );
    }

    protected abstract boolean isPageLoaded();
    protected abstract void openPage();
}
