package am.staff.utils;

import am.staff.helper.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class WaitUtility {
    protected static int SHORT_WAIT_TIME_SECONDS = 3;
    protected static int MIDDLE_WAIT_TIME_SECONDS = 5;
    protected static int LONG_WAIT_TIME_SECONDS = 15;

    protected WebDriverWait wait;

    private WaitUtility(int timeout) {
        this.wait = createWaitWith(timeout);
    }

    private WebDriver getDriver() {
        return WebDriverHelper.getDriver();
    }

    private WebDriverWait getWait() {
        return wait;
    }

    public static WaitUtility getShortWait() {
        return new WaitUtility(SHORT_WAIT_TIME_SECONDS);
    }

    public static WaitUtility getMiddleWait() {
        return new WaitUtility(MIDDLE_WAIT_TIME_SECONDS);
    }

    public static WaitUtility getLongWait() {
        return new WaitUtility(LONG_WAIT_TIME_SECONDS);
    }

    private WebDriverWait createWaitWith(int timeout) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
    }

    public WebElement waitElementToBeClickable(By selector) {
        return getWait().until(ExpectedConditions.elementToBeClickable(selector));
    }

    public WebElement waitElementToBeClickable(WebElement element) {
        return getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitElementToBeVisible(By selector) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public WebElement waitElementToBeVisible(WebElement element) {
        return getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitElementPresence(By selector) {
        return getWait().until(ExpectedConditions.presenceOfElementLocated(selector));
    }

    public boolean waitElementInvisibility(By selector) {
        return getWait().until(ExpectedConditions.invisibilityOfElementLocated(selector));
    }
}
